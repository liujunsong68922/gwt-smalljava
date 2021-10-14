package com.smalljava.core.classloader.l2_class.eval;

import com.smalljava.core.classloader.JavaClassStaticInstanceMap;
import com.smalljava.core.classloader.l2_class.vo.JavaClassInstanceVO;
import com.smalljava.core.classloader.l2_class.vo.JavaClassStaticInstanceVO;
import com.smalljava.core.classloader.l2_class.vo.JavaClassTemplateVO;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassMethodElement;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassVarDefineElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockanalyse.BlockAnalyse;
import com.smalljava.core.l4_block.blockeval.BlockEvaluator;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassStaticVarTableImpl;

public class JavaClassEval {
	private static Logger logger = LoggerFactory.getLogger(JavaClassEval.class);

	/**
	 * MEMO:加载Class对象定义 MEMO：输出结果：在全局的JavaClassStaticInstanceMap中增加新对象
	 * 
	 * @param javafilestring 代表Java源代码的字符串
	 * @param javaclass      代表其中的java class名称
	 */
	public boolean loadClass(JavaClassTemplateVO rootvo, IClassTable classtable) {
		if (rootvo == null) {
			logger.error("[Arugment Error]rootvo is null.");
			return false;
		} else {
			logger.info("[Argument OK]rootvo is not null.");
		}

		if (classtable == null) {
			logger.error("[Arugment Error]classtable is null.");
			return false;
		}

		// 增加一个额外判断，如果这个类已经被加载过，则不需要重复加载了。
		String classname = rootvo.getClassname();
		if (JavaClassStaticInstanceMap.getJavaClassStaticInstanceVO(classname) != null) {
			logger.error("[INFO] Class has been loaded.");
			return true;
		}

		// 在类里面查找变量定义的部分代码，并尝试进行变量定义。
		// rootvo本身实现了变量表的功能，因此可以转型成变量表来使用。
		// 构造函数暂时不考虑支持。
		// IVarTable vartable = rootvo;
		String classinitblock = "";
		for (JavaClassVarDefineElement element : rootvo.getPropertiesArray()) {
			logger.info(element.getStringcontent());
			// 不是对所有的属性定义都进行处理
			// 需要判断这个变量定义是否包含static关键词
			if (element.getStringcontent().indexOf(" static ") > 0) {
				// 这里需要去掉public,private,static 这些前缀定义
				// public,private这两种访问控制暂时不考虑实现
				String strcode = element.getStringcontent();
				// TODO:需要规避在引号之内的情况
				// 此处暂时使用简易算法
				StringFindUtil util = new StringFindUtil();
				strcode = util.trimReturnAndSpace(strcode);
				if (strcode.startsWith("public")) {
					strcode = strcode.substring("public".length());
					strcode = util.trimReturnAndSpace(strcode);
				}
				if (strcode.startsWith("private")) {
					strcode = strcode.substring("private".length());
					strcode = util.trimReturnAndSpace(strcode);
				}
				if (strcode.startsWith("static")) {
					strcode = strcode.substring("static".length());
					strcode = util.trimReturnAndSpace(strcode);
				}
				// 现在这个代码是干净的代码了
				logger.info(strcode);
				classinitblock += strcode;
			}
		}

		// 利用rootvo作为输入，构建另外一个JavaClassStaticInstanceVO对象
		JavaClassStaticInstanceVO staticvo = this.getJavaClassStaticInstanceVO(rootvo);
		
		// 构建根级别的变量表对象
		L2_HashMapClassStaticVarTableImpl staticvartable = new L2_HashMapClassStaticVarTableImpl("");
		
		// 将静态实例对象和静态变量表关联起来。
		// 这个方法似乎可以封装在staticvo里面
		staticvo.setVartable(staticvartable);
		// TODO:调用level3包里面封装的评估方法来进行评估计算
		BasicBlock closedblock = new BasicBlock("", classinitblock, null);

		// 设置变量表,这个块就是class本身的变量表
		// TODO:设置变量计算的变量表
		//closedblock.setMyvarmap(staticvo.getMyvarmap());
		//closedblock.setVartable(staticvo.getVartable());
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		closedblock.show(0);
		logger.info("代码块分析结果：" + isok);

		BlockEvaluator node = new BlockEvaluator();
		L2_HashMapClassStaticVarTableImpl vartable1 = new L2_HashMapClassStaticVarTableImpl("");
		boolean b2;
		try {
			b2 = node.execute(closedblock, vartable1, classtable);
			if (b2) {
				// 执行成功的情况下，将静态实例写入全局存储中
				JavaClassStaticInstanceMap.add(staticvo);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * MEMO:根据类定义的模板对象，生成一个新的实例对象出来 MEMO：输出结果：在全局的JavaClassStaticInstanceMap中增加新对象
	 * 
	 * @param javafilestring 代表Java源代码的字符串
	 * @param javaclass      代表其中的java class名称
	 */
	public JavaClassInstanceVO newInstance(JavaClassTemplateVO rootvo, IClassTable classtable) {
		if (rootvo == null) {
			logger.error("[Arugment Error]rootvo is null.");
			return null;
		} else {
			logger.info("[Argument OK]rootvo is not null.");
		}

		if (classtable == null) {
			logger.error("[Arugment Error]classtable is null.");
			return null;
		}

		// 在类里面查找变量定义的部分代码，并尝试进行变量定义。
		// rootvo本身实现了变量表的功能，因此可以转型成变量表来使用。
		// 构造函数暂时不考虑支持。
		// IVarTable vartable = rootvo;
		String classinitblock = "";
		for (JavaClassVarDefineElement element : rootvo.getPropertiesArray()) {
			logger.info(element.getStringcontent());
			// 不是对所有的属性定义都进行处理
			// 需要判断这个变量定义是否包含static关键词
			if (element.getStringcontent().indexOf("static ") < 0) {
				// 这里需要去掉public,private,static 这些前缀定义
				// public,private这两种访问控制暂时不考虑实现
				String strcode = element.getStringcontent();
				// TODO:需要规避在引号之内的情况
				// 此处暂时使用简易算法
				StringFindUtil util = new StringFindUtil();
				strcode = util.trimReturnAndSpace(strcode);
				if (strcode.startsWith("public")) {
					strcode = strcode.substring("public".length());
					strcode = util.trimReturnAndSpace(strcode);
				}
				if (strcode.startsWith("private")) {
					strcode = strcode.substring("private".length());
					strcode = util.trimReturnAndSpace(strcode);
				}
				if (strcode.startsWith("static")) {
					strcode = strcode.substring("static".length());
					strcode = util.trimReturnAndSpace(strcode);
				}
				// 现在这个代码是干净的代码了
				logger.info(strcode);
				classinitblock += strcode;
			}
		}

		// 利用rootvo作为输入，构建另外一个JavaClassStaticInstanceVO对象
		JavaClassInstanceVO instancevo = this.getJavaClassInstanceVO(rootvo);

		// TODO:调用level3包里面封装的评估方法来进行评估计算
		BasicBlock closedblock = new BasicBlock("", classinitblock, null);

		// 设置变量表,这个块就是class本身的变量表
		//closedblock.setMyvarmap(instancevo.getMyvarmap());
		//TODO:设置变量表
		//closedblock.setVartable(instancevo.getVarTable()));
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		closedblock.show(0);
		logger.info("代码块分析结果：" + isok);

		BlockEvaluator node = new BlockEvaluator();
		L2_HashMapClassStaticVarTableImpl vartable1 = new L2_HashMapClassStaticVarTableImpl("");
		L2_HashMapClassInstanceVarTableImpl vartable2 = new L2_HashMapClassInstanceVarTableImpl("",vartable1);
		boolean b2;
		try {
			b2 = node.execute(closedblock,vartable2, classtable);
			if (b2) {
				return instancevo;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * MEMO:根据模板来生成类静态实例对象
	 * @param rootvo
	 * @return
	 */
	private JavaClassStaticInstanceVO getJavaClassStaticInstanceVO(JavaClassTemplateVO rootvo) {
		JavaClassStaticInstanceVO staticInstanceVO = new JavaClassStaticInstanceVO();
		staticInstanceVO.setClassname(rootvo.getClassname());
		staticInstanceVO.setPackagename(rootvo.getPackagename());
		// staticInstanceVO的变量表将在下一步设定
		// staticInstanceVO暂时不考虑支持static Method定义（略微复杂）
		return staticInstanceVO;
	}

	/**
	 * MEMO：根据模板来生成类的实例对象
	 * @param rootvo
	 * @return
	 */
	private JavaClassInstanceVO getJavaClassInstanceVO(JavaClassTemplateVO rootvo) {
		JavaClassInstanceVO instanceVO = new JavaClassInstanceVO();
		instanceVO.setClassname(rootvo.getClassname());
		instanceVO.setPackagename(rootvo.getPackagename());
		// staticInstanceVO的变量表将在下一步设定
		// staticInstanceVO暂时不考虑支持static Method定义（略微复杂）
		for (JavaClassMethodElement method : rootvo.getMethodArray()) {
			JavaClassMethodElement method2 = new JavaClassMethodElement();
			method2.setStringcontent(method.getStringcontent());
			instanceVO.getChildren().add(method2);
		}
		return instanceVO;
	}
}
