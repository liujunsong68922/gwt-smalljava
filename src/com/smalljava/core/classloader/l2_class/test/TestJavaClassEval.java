package com.smalljava.core.classloader.l2_class.test;

import com.smalljava.core.classloader.l1_javafile.analyse.ClassTableAnalyse;
import com.smalljava.core.classloader.l1_javafile.analyse.JavaFileAnalyse;
import com.smalljava.core.classloader.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.core.classloader.l2_class.analyse.JavaClassAnalyse;
import com.smalljava.core.classloader.l2_class.eval.JavaClassEval;
import com.smalljava.core.classloader.l2_class.vo.JavaClassTemplateVO;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l9_space.classtable.IClassTable;

public class TestJavaClassEval {
	private static Logger logger = LoggerFactory.getLogger(TestJavaClassEval.class);
	public static void main(String args[]) {
		JavaFileAnalyse jfa = new JavaFileAnalyse();
		String javastring ="package com.liu;\n";
		javastring +="import java.util.HashMap;\n";
		javastring += "public class Test{ \n";
		javastring += "int i=100; \n";
		javastring += "int j=200; \n";
		javastring += "int k=300; \n";
		javastring += "int l=400; \n";
		javastring += "public static void main(String args[]){ \n";
		javastring += " This is main function definition.; \n";
		javastring += "} \n";
		javastring += "}";
		
		JavaFileRootVO rootvo = jfa.analyse(javastring);
		if(rootvo != null) {
			System.out.println("----->JAVA FILE 分析执行成功！");
			rootvo.show();
		}
		
		//利用ClassTable分析工具来处理ClassTable
		ClassTableAnalyse classTableAnalyse= new ClassTableAnalyse();
		IClassTable classtable = classTableAnalyse.analyse(rootvo);
		if(classtable !=null) {
			System.out.println("ClassTable 分析成功");
		}else {
			System.out.println("ClassTable 分析失败");
			return;
		}		
		
		JavaClassAnalyse jca = new JavaClassAnalyse();
		
		String strclassname = "Test";
		JavaClassTemplateVO classrootvo = jca.analyse(rootvo.getClassElement(strclassname).getStringcontent());
		if(classrootvo != null) {
			System.out.println("----->JAVA Class 分析执行成功！");
			classrootvo.show();
		}else {
			System.out.println("----->JAVA Class 分析执行失败！");
			return;
		}

		JavaClassEval eval = new JavaClassEval();
		
		boolean b1 = eval.loadClass(classrootvo,classtable);
		if(b1) {
			logger.info("Class 初始化eval成功. ok");
		}else {
			logger.error("Class 初始化eval failed.");
			System.out.println("Class执行失败.failed.");
		}
	}
}
