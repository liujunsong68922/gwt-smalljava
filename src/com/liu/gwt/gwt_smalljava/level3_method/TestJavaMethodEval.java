package com.liu.gwt.gwt_smalljava.level3_method;

import com.liu.gwt.gwt_smalljava.level1_javafile.analyse.ClassTableAnalyse;
import com.liu.gwt.gwt_smalljava.level1_javafile.analyse.JavaFileAnalyse;
import com.liu.gwt.gwt_smalljava.level1_javafile.vo.JavaFileRootVO;
import com.liu.gwt.gwt_smalljava.level2_class.analyse.JavaClassAnalyse;
import com.liu.gwt.gwt_smalljava.level2_class.vo.JavaClassRootVO;
import com.liu.gwt.gwt_smalljava.level2_class.vo.element.JavaClassMethodElement;
import com.liu.gwt.gwt_smalljava.level3_method.analyse.JavaMethodAnalyse;
import com.liu.gwt.gwt_smalljava.level3_method.eval.JavaMethodEval;
import com.liu.gwt.gwt_smalljava.level3_method.vo.JavaMethodRootVO;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

public class TestJavaMethodEval {
	//private static Logger logger = LoggerFactory.getLogger(TestJavaMethodEval.class);
	/**
	 * MEMO ����������
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
		javastring += "     i=100; j=200; k=300; l=i+j+k; \n";
		javastring += "}\n";
		javastring += "}";
		
		JavaFileRootVO rootvo = jfa.analyse(javastring);
		if(rootvo != null) {
			//logger.debug("----->JAVA FILE ����ִ�гɹ���");
			rootvo.show();
		}
		
		//����ClassTable��������������ClassTable
		ClassTableAnalyse classTableAnalyse= new ClassTableAnalyse();
		IClassTable classtable = classTableAnalyse.analyse(rootvo);
		if(classtable !=null) {
			//logger.debug("ClassTable �����ɹ�");
		}else {
			//logger.error("ClassTable ����ʧ��");
			return;
		}		
		
		JavaClassAnalyse jca = new JavaClassAnalyse();
		JavaClassRootVO classrootvo = jca.analyse(rootvo.getClassElement("Test"));
		if(classrootvo != null) {
			//logger.debug("----->JAVA Class ����ִ�гɹ���");
			classrootvo.show();
		}else {
			//logger.error("----->JAVA Class ����ִ��ʧ�ܣ�");
			return;
		}
	
		String methodname="test";
		JavaClassMethodElement methodelement = classrootvo.getMethod(methodname);
		
		if(methodelement==null) {
			//logger.error("����method����ʧ��");
			return ;
		}else {
			//logger.debug("����method�����ɹ�");
		}
		
		JavaMethodAnalyse jma = new JavaMethodAnalyse();
		JavaMethodRootVO methodrootvo = jma.analyseMethod(methodelement);
		
		if(methodrootvo == null) {
			//logger.error("����ʧ��:"+methodelement.getStringcontent());
			return;
		}else {
			//logger.debug("�����ɹ�!");
			methodrootvo.show();
		}
		
		//ִ�з���
		JavaMethodEval jmethodeval = new JavaMethodEval();
		IVarTable classvartable = classrootvo;
		jmethodeval.eval(methodrootvo, classvartable,classtable);
		
	}
		
}
