package com.smalljava.core.l123_classsupport.l2_classdefine.test;

import com.smalljava.core.l123_classsupport.l2_classdefine.analyse.SmallJavaClassAnalyse;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.SmallJavaClassTemplateVO;

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
