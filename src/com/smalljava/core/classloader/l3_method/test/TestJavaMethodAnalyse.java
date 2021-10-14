package com.smalljava.core.classloader.l3_method.test;

import com.smalljava.core.classloader.l1_javafile.analyse.ClassTableAnalyse;
import com.smalljava.core.classloader.l1_javafile.analyse.JavaFileAnalyse;
import com.smalljava.core.classloader.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.core.classloader.l2_class.analyse.JavaClassAnalyse;
import com.smalljava.core.classloader.l2_class.vo.JavaClassTemplateVO;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassMethodElement;
import com.smalljava.core.classloader.l3_method.analyse.JavaMethodAnalyse;
import com.smalljava.core.classloader.l3_method.vo.JavaMethodRootVO;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l9_space.classtable.IClassTable;

public class TestJavaMethodAnalyse {
	private static Logger logger = LoggerFactory.getLogger(TestJavaMethodAnalyse.class);
	
	/**
	 * MEMO������������
	 * @param args
	 */
	public static void main(String args[]) {
		JavaFileAnalyse jfa = new JavaFileAnalyse();
		String javastring ="package com.liu;\n";
		javastring +="import java.util.HashMap;\n";
		javastring += "public class Test{ \n";
		javastring += "int i=0;\n";
		javastring += "int j=0;\n";
		javastring += "int k=0;\n";
		javastring += "int l=0;\n";
		javastring += "public void test(){ \n";
		javastring += "    hello workd; \n";
		javastring += "}\n";
		javastring += "}";
		
		JavaFileRootVO rootvo = jfa.analyse(javastring);
		if(rootvo != null) {
			logger.info("----->JAVA FILE ����ִ�гɹ���");
			rootvo.show();
		}
		
		//����ClassTable��������������ClassTable
		ClassTableAnalyse classTableAnalyse= new ClassTableAnalyse();
		IClassTable classtable = classTableAnalyse.analyse(rootvo);
		if(classtable !=null) {
			logger.info("ClassTable �����ɹ�");
		}else {
			logger.info("ClassTable ����ʧ��");
			return;
		}		
		
		JavaClassAnalyse jca = new JavaClassAnalyse();
		JavaClassTemplateVO classrootvo = jca.analyse(rootvo.getClassElement("Test").getStringcontent());
		if(classrootvo != null) {
			logger.info("----->JAVA Class ����ִ�гɹ���");
			classrootvo.show();
		}else {
			logger.info("----->JAVA Class ����ִ��ʧ�ܣ�");
			return;
		}
	
		String methodname="test";
		JavaClassMethodElement methodelement = classrootvo.getMethod(methodname);
		
		if(methodelement==null) {
			logger.info("����method����ʧ��");
			return ;
		}else {
			logger.info("����method�����ɹ�");
		}
		
		JavaMethodAnalyse jma = new JavaMethodAnalyse();
		JavaMethodRootVO root = jma.analyse(methodelement);
		
		if(root == null) {
			logger.info("����ʧ��:"+methodelement.getStringcontent());
		}else {
			logger.info("�����ɹ�!");
			root.show();
		}
	}
}
