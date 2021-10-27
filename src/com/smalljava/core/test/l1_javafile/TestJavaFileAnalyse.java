package com.smalljava.core.test.l1_javafile;

import com.smalljava.core.analyse.l1_analyse.JavaFileAnalyse;
import com.smalljava.core.analyse.l2_classdefine.SmallJavaClassAnalyse;
import com.smalljava.core.commonvo.l1_javafile.JavaFileRootVO;
import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;

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
