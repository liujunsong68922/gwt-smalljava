package com.smalljava.core.l6_supportenv.l6_classsupport.helper;

import java.util.ArrayList;

import com.smalljava.core.analyse.l1_analyse.SmallJavaFileAnalyse;
import com.smalljava.core.analyse.l4_block.BlockAnalyse;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l1_javafile.AbstractJavaFileElement;
import com.smalljava.core.commonvo.l1_javafile.JavaFileRootVO;
import com.smalljava.core.commonvo.l1_javafile.element.JavaFileClassElement;
import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;
import com.smalljava.core.commonvo.l2_javaclass.element.SmallJavaClassVarDefineElement;
import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.eval.l4_block.SmallJavaBlockEvaluator;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.impl.ClassTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassStaticVarTableImpl;

/**
 * This is SmallJava local ClassLoader it read a SmallJavaClassTemplateVO,
 * register it on SmallJavaSupportEnv
 * 
 * @author liujunsong
 *
 */
public class SmallJavaClassSupportEnv_ClassLoader {
	private Logger logger = LoggerFactory.getLogger(SmallJavaClassSupportEnv_ClassLoader.class);

	/**
	 * MEMO: load a SmallJavaClassTemplateVO to smalljava ClassManager
	 * 
	 * @return
	 */
	public boolean loadClassTemplateVO(SmallJavaClassManager classmanager,
				SmallJavaClassTemplateVO classtemplatevo,
				SmallJavaClassSupportEnv classenv,
				SmallJavaOopSupportEnv oopenv) {
		// step1:check argment.
		if (classmanager == null) {
			logger.error("[error] classmanager is null.");
			//MEMO：加载失败
			return false;
		}

		if (classtemplatevo == null) {
			logger.error("[error] classtemplatevo is null.");
			//MEMO:加载失败
			return false;
		}

		// step2.register classtemplatevo to classmanger.
		String classname = classtemplatevo.getClassname();
		classmanager.getClassdefinemap().put(classname, classtemplatevo);
		
		//step3. After load class, define class static vartable.
		// but this function only work once.
		// MEMO： 每个Class都定义一个自己的静态变量的变量表，并在变量表里面定义静态变量
		L2_HashMapClassStaticVarTableImpl vartable =
				new L2_HashMapClassStaticVarTableImpl("classstatic");
		if(classmanager.getClassStaticVartableMap().containsKey(classname)) {
			logger.info("[info]This class has been loaded."+classname);
		}else {
			logger.info("[info]This class has not been loaded."+classname);
			
			//MEMO:针对Class级别，也就是static级别的静态变量定义
			classmanager.getClassStaticVartableMap().put(classname, vartable);
			
			//MEMO:仅仅定义一个变量表是不够的，现在需要在变量表上定义static变量定义
			//MEMO:这里需要对原来的代码进行整理，再定义static变量
			ArrayList<SmallJavaClassVarDefineElement> vardefinelist = classtemplatevo.getPropertiesArray();
			for(SmallJavaClassVarDefineElement vardefine: vardefinelist) {
				String strcontent = vardefine.getStringcontent();
				//logger output for debug.
				logger.debug("[loadClassTemplateVO]vardefine:"+strcontent);
				
				//MEMO: execute this {strcontent} on {varTable} that creat upside.
				//process only static var define.
				if(strcontent.indexOf("static ") >= 0) {
					this.executeStatementOnVarTable(strcontent, vartable,classenv,oopenv);
				}else {
					//this is not static variable, 
					//igore it.
				}
			}
		}
		return true;
	}
	/**
	 * This function only execute var define statement.
	 * This function will call block evaluate function.
	 * @param strcontent
	 * @param vartable
	 * @return
	 */
	private boolean executeStatementOnVarTable(String strcontent,
			L2_HashMapClassStaticVarTableImpl vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		BasicBlock closedblock = new BasicBlock("",strcontent,null);
		BlockAnalyse ba = new BlockAnalyse();
		boolean isok = ba.analyse(closedblock);
		if(! isok) {
			logger.error("[ERROR]error at analyse block:"+strcontent);
			return false;
		}
		
		//on success analyse the string input
		SmallJavaBlockEvaluator node = new SmallJavaBlockEvaluator();
		//ClassTableImpl classtable = new ClassTableImpl();
		try {
			boolean b2 = node.execute(closedblock,vartable,classenv,oopenv);
			return b2;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[ERROR]error on execute. "+strcontent);
			return false;
		}
	}
	
	public boolean loadClassDefineString(SmallJavaClassManager classmanager,
			String strClassdefine,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		//step1: convert classDefineString to ClassTemplateVO
		SmallJavaFileAnalyse javaclassanalyse = new SmallJavaFileAnalyse();
		 JavaFileRootVO vo = javaclassanalyse.analyse(strClassdefine);
		if(vo == null) {
			logger.info("[ERROR] javafileanalyse failed.");
			return false;
		}else {
			ArrayList<AbstractJavaFileElement> children = vo.getChildren();
			for(AbstractJavaFileElement child:children) {
				if (child instanceof JavaFileClassElement) {
					//类型转换
					JavaFileClassElement classelement = (JavaFileClassElement) child;
					if(classelement.getClasstemplatevo() == null) {
						logger.error("[ERROR] Classtemplatevo is null.");
						return false;
					}else {
						boolean b1 = this.loadClassTemplateVO(classmanager, classelement.getClasstemplatevo(),classenv,oopenv);
						if(! b1) {
							logger.error("[ERROR] error at loadClassTemplateVO.");
							return false;
						}else {
							//继续循环
							continue;
						}
					}
				}
				
			}
			return true;
		}
	}
	
}
	

