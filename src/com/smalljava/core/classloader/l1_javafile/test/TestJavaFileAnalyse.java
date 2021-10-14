package com.smalljava.core.classloader.l1_javafile.test;

import com.smalljava.core.classloader.l1_javafile.analyse.ClassTableAnalyse;
import com.smalljava.core.classloader.l1_javafile.analyse.JavaFileAnalyse;
import com.smalljava.core.classloader.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l9_space.classtable.IClassTable;

public class TestJavaFileAnalyse {
	static Logger log = LoggerFactory.getLogger(TestJavaFileAnalyse.class);
	public static void main(String args[]) {
		log.debug(" test start ");
		JavaFileAnalyse jfa = new JavaFileAnalyse();
		String javastring ="package com.liu;\n";
		javastring +="import java.util.HashMap;\n";
		javastring += "public class aaa{ \n";
		javastring += "int i;\n";
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
		}
	}
}
