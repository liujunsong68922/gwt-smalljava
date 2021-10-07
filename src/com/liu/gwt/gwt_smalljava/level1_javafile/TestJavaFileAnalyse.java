package com.liu.gwt.gwt_smalljava.level1_javafile;

import com.liu.gwt.gwt_smalljava.level1_javafile.analyse.ClassTableAnalyse;
import com.liu.gwt.gwt_smalljava.level1_javafile.analyse.JavaFileAnalyse;
import com.liu.gwt.gwt_smalljava.level1_javafile.vo.JavaFileRootVO;
import com.liu.gwt.gwt_smalljava.space.IClassTable;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.liu.smalljavav2.level1_javafile.analyse.ClassTableAnalyse;
//import com.liu.smalljavav2.level1_javafile.analyse.JavaFileAnalyse;
//import com.liu.smalljavav2.level1_javafile.vo.JavaFileRootVO;
//import com.liu.smalljavav2.space.IClassTable;

public class TestJavaFileAnalyse {
	//static Logger log = LoggerFactory.getLogger(TestJavaFileAnalyse.class);
	public static void main(String args[]) {
		//log.debug(" test start ");
		JavaFileAnalyse jfa = new JavaFileAnalyse();
		String javastring ="package com.liu;\n";
		javastring +="import java.util.HashMap;\n";
		javastring += "public class aaa{ \n";
		javastring += "int i;\n";
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
		}
	}
}
