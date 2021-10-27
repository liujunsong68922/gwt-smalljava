package com.smalljava.core.test.l2_javaclass;

import com.smalljava.core.analyse.l2_classdefine.SmallJavaClassAnalyse;
import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;

public class TestClassAnalyse {
	public static void main(String args[]) {
		System.out.println("Start TestClassAnalyse");
		
		SmallJavaClassAnalyse classanalyse = new SmallJavaClassAnalyse();
		String s1 = ""
				+" int i; "
				+ " ";
		SmallJavaClassTemplateVO vo = classanalyse.analyse(s1);
		
		if(vo == null) {
			System.out.println("class analyse failed.");
		}else {
			System.out.println("class analyse ok.");
		}
	}
}
