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
	 * MEMO:����Class������ MEMO������������ȫ�ֵ�JavaClassStaticInstanceMap�������¶���
	 * 
	 * @param javafilestring ����JavaԴ������ַ���
	 * @param javaclass      �������е�java class����
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

		// ����һ�������жϣ����������Ѿ������ع�������Ҫ�ظ������ˡ�
		String classname = rootvo.getClassname();
		if (JavaClassStaticInstanceMap.getJavaClassStaticInstanceVO(classname) != null) {
			logger.error("[INFO] Class has been loaded.");
			return true;
		}

		// ����������ұ�������Ĳ��ִ��룬�����Խ��б������塣
		// rootvo����ʵ���˱�����Ĺ��ܣ���˿���ת�ͳɱ�������ʹ�á�
		// ���캯����ʱ������֧�֡�
		// IVarTable vartable = rootvo;
		String classinitblock = "";
		for (JavaClassVarDefineElement element : rootvo.getPropertiesArray()) {
			logger.info(element.getStringcontent());
			// ���Ƕ����е����Զ��嶼���д���
			// ��Ҫ�ж�������������Ƿ����static�ؼ���
			if (element.getStringcontent().indexOf(" static ") > 0) {
				// ������Ҫȥ��public,private,static ��Щǰ׺����
				// public,private�����ַ��ʿ�����ʱ������ʵ��
				String strcode = element.getStringcontent();
				// TODO:��Ҫ���������֮�ڵ����
				// �˴���ʱʹ�ü����㷨
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
				// ������������Ǹɾ��Ĵ�����
				logger.info(strcode);
				classinitblock += strcode;
			}
		}

		// ����rootvo��Ϊ���룬��������һ��JavaClassStaticInstanceVO����
		JavaClassStaticInstanceVO staticvo = this.getJavaClassStaticInstanceVO(rootvo);
		
		// ����������ı��������
		L2_HashMapClassStaticVarTableImpl staticvartable = new L2_HashMapClassStaticVarTableImpl("");
		
		// ����̬ʵ������;�̬���������������
		// ��������ƺ����Է�װ��staticvo����
		staticvo.setVartable(staticvartable);
		// TODO:����level3�������װ������������������������
		BasicBlock closedblock = new BasicBlock("", classinitblock, null);

		// ���ñ�����,��������class����ı�����
		// TODO:���ñ�������ı�����
		//closedblock.setMyvarmap(staticvo.getMyvarmap());
		//closedblock.setVartable(staticvo.getVartable());
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		closedblock.show(0);
		logger.info("�������������" + isok);

		BlockEvaluator node = new BlockEvaluator();
		L2_HashMapClassStaticVarTableImpl vartable1 = new L2_HashMapClassStaticVarTableImpl("");
		boolean b2;
		try {
			b2 = node.execute(closedblock, vartable1, classtable);
			if (b2) {
				// ִ�гɹ�������£�����̬ʵ��д��ȫ�ִ洢��
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
	 * MEMO:�����ඨ���ģ���������һ���µ�ʵ��������� MEMO������������ȫ�ֵ�JavaClassStaticInstanceMap�������¶���
	 * 
	 * @param javafilestring ����JavaԴ������ַ���
	 * @param javaclass      �������е�java class����
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

		// ����������ұ�������Ĳ��ִ��룬�����Խ��б������塣
		// rootvo����ʵ���˱�����Ĺ��ܣ���˿���ת�ͳɱ�������ʹ�á�
		// ���캯����ʱ������֧�֡�
		// IVarTable vartable = rootvo;
		String classinitblock = "";
		for (JavaClassVarDefineElement element : rootvo.getPropertiesArray()) {
			logger.info(element.getStringcontent());
			// ���Ƕ����е����Զ��嶼���д���
			// ��Ҫ�ж�������������Ƿ����static�ؼ���
			if (element.getStringcontent().indexOf("static ") < 0) {
				// ������Ҫȥ��public,private,static ��Щǰ׺����
				// public,private�����ַ��ʿ�����ʱ������ʵ��
				String strcode = element.getStringcontent();
				// TODO:��Ҫ���������֮�ڵ����
				// �˴���ʱʹ�ü����㷨
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
				// ������������Ǹɾ��Ĵ�����
				logger.info(strcode);
				classinitblock += strcode;
			}
		}

		// ����rootvo��Ϊ���룬��������һ��JavaClassStaticInstanceVO����
		JavaClassInstanceVO instancevo = this.getJavaClassInstanceVO(rootvo);

		// TODO:����level3�������װ������������������������
		BasicBlock closedblock = new BasicBlock("", classinitblock, null);

		// ���ñ�����,��������class����ı�����
		//closedblock.setMyvarmap(instancevo.getMyvarmap());
		//TODO:���ñ�����
		//closedblock.setVartable(instancevo.getVarTable()));
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		closedblock.show(0);
		logger.info("�������������" + isok);

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
	 * MEMO:����ģ���������ྲ̬ʵ������
	 * @param rootvo
	 * @return
	 */
	private JavaClassStaticInstanceVO getJavaClassStaticInstanceVO(JavaClassTemplateVO rootvo) {
		JavaClassStaticInstanceVO staticInstanceVO = new JavaClassStaticInstanceVO();
		staticInstanceVO.setClassname(rootvo.getClassname());
		staticInstanceVO.setPackagename(rootvo.getPackagename());
		// staticInstanceVO�ı���������һ���趨
		// staticInstanceVO��ʱ������֧��static Method���壨��΢���ӣ�
		return staticInstanceVO;
	}

	/**
	 * MEMO������ģ�����������ʵ������
	 * @param rootvo
	 * @return
	 */
	private JavaClassInstanceVO getJavaClassInstanceVO(JavaClassTemplateVO rootvo) {
		JavaClassInstanceVO instanceVO = new JavaClassInstanceVO();
		instanceVO.setClassname(rootvo.getClassname());
		instanceVO.setPackagename(rootvo.getPackagename());
		// staticInstanceVO�ı���������һ���趨
		// staticInstanceVO��ʱ������֧��static Method���壨��΢���ӣ�
		for (JavaClassMethodElement method : rootvo.getMethodArray()) {
			JavaClassMethodElement method2 = new JavaClassMethodElement();
			method2.setStringcontent(method.getStringcontent());
			instanceVO.getChildren().add(method2);
		}
		return instanceVO;
	}
}
