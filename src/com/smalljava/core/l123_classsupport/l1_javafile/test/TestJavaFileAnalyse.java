package com.smalljava.core.l123_classsupport.l1_javafile.test;

import com.smalljava.core.l123_classsupport.l1_javafile.analyse.JavaFileAnalyse;
import com.smalljava.core.l123_classsupport.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.core.l123_classsupport.l2_classdefine.analyse.SmallJavaClassAnalyse;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.SmallJavaClassTemplateVO;

public class TestJavaFileAnalyse {
	public static void main(String args[]) {
		System.out.println("Start TestClassAnalyse");
		
		JavaFileAnalyse classanalyse = new JavaFileAnalyse();
		String s1 = "class A{"
				+" int i; "
				+ "} ";
		 JavaFileRootVO vo = classanalyse.analyse(s1);
		
		if(vo == null) {
			System.out.println("class analyse failed.");
		}else {
			System.out.println("class analyse ok.");
			System.out.println("children:"+vo.getChildren().size());
			vo.show();
		}
	}
}
