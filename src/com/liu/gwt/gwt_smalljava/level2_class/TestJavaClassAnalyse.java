package com.liu.gwt.gwt_smalljava.level2_class;

import com.liu.gwt.gwt_smalljava.level1_javafile.analyse.ClassTableAnalyse;
import com.liu.gwt.gwt_smalljava.level1_javafile.analyse.JavaFileAnalyse;
import com.liu.gwt.gwt_smalljava.level1_javafile.vo.JavaFileRootVO;
import com.liu.gwt.gwt_smalljava.level2_class.analyse.JavaClassAnalyse;
import com.liu.gwt.gwt_smalljava.level2_class.vo.JavaClassRootVO;
import com.liu.gwt.gwt_smalljava.space.IClassTable;

//import com.liu.smalljavav2.level1_javafile.analyse.ClassTableAnalyse;
//import com.liu.smalljavav2.level1_javafile.analyse.JavaFileAnalyse;
//import com.liu.smalljavav2.level1_javafile.vo.JavaFileRootVO;
//import com.liu.smalljavav2.level1_javafile.analyse.JavaFileAnalyse;
//import com.liu.smalljavav2.level1_javafile.vo.JavaFileRootVO;
//import com.liu.smalljavav2.level2_class.analyse.JavaClassAnalyse;
//import com.liu.smalljavav2.level2_class.vo.JavaClassRootVO;
//import com.liu.smalljavav2.space.IClassTable;

public class TestJavaClassAnalyse {
	public static void main(String args[]) {

		JavaFileAnalyse jfa = new JavaFileAnalyse();
		String javastring ="package com.liu;\n";
		javastring +="import java.util.HashMap;\n";
		javastring += "public class Test{ \n";
		javastring += "int i=0;\n";
		javastring += "int j=0;\n";
		javastring += "int k=0;\n";
		javastring += "int l=0;\n";
		javastring += "public test(){ hello world;} \n";
		javastring += "}";
		
		JavaFileRootVO rootvo = jfa.analyse(javastring);
		if(rootvo != null) {
			System.out.println("----->JAVA FILE ����ִ�гɹ���");
			rootvo.show();
		}
		
		//����ClassTable��������������ClassTable
		ClassTableAnalyse classTableAnalyse= new ClassTableAnalyse();
		IClassTable classtable = classTableAnalyse.analyse(rootvo);
		if(classtable !=null) {
			System.out.println("ClassTable �����ɹ�");
		}else {
			System.out.println("ClassTable ����ʧ��");
			return;
		}		
		
		JavaClassAnalyse jca = new JavaClassAnalyse();
		
		JavaClassRootVO classrootvo = jca.analyse(rootvo.getClassElement("Test"));
		if(classrootvo != null) {
			System.out.println("----->JAVA Class ����ִ�гɹ���");
			classrootvo.show();
		}else {
			System.out.println("----->JAVA Class ����ִ��ʧ�ܣ�");
			return;
		}
	}
}
