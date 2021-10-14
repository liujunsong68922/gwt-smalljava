package com.smalljava.core.classloader.l3_method.test;

import com.smalljava.core.classloader.l1_javafile.analyse.ClassTableAnalyse;
import com.smalljava.core.classloader.l1_javafile.analyse.JavaFileAnalyse;
import com.smalljava.core.classloader.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.core.classloader.l2_class.analyse.JavaClassAnalyse;
import com.smalljava.core.classloader.l2_class.eval.JavaClassEval;
import com.smalljava.core.classloader.l2_class.vo.JavaClassInstanceVO;
import com.smalljava.core.classloader.l2_class.vo.JavaClassTemplateVO;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassMethodElement;
import com.smalljava.core.classloader.l3_method.analyse.JavaMethodAnalyse;
import com.smalljava.core.classloader.l3_method.eval.JavaMethodEval;
import com.smalljava.core.classloader.l3_method.vo.JavaMethodRootVO;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassStaticVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L3_HashMapMethodInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class TestJavaMethodEval {
	private static Logger logger = LoggerFactory.getLogger(TestJavaMethodEval.class);
	/**
	 * MEMO 测试主方法
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
		javastring += " int i =100*100; \n";
		javastring += "}\n";
		javastring += "}";
		
		JavaFileRootVO rootvo = jfa.analyse(javastring);
		if(rootvo != null) {
			logger.debug("----->JAVA FILE 分析执行成功！");
			rootvo.show();
		}
		
		//利用ClassTable分析工具来处理ClassTable
		ClassTableAnalyse classTableAnalyse= new ClassTableAnalyse();
		IClassTable classtable = classTableAnalyse.analyse(rootvo);
		if(classtable !=null) {
			logger.debug("ClassTable 分析成功");
		}else {
			logger.error("ClassTable 分析失败");
			return;
		}		
		
		JavaClassAnalyse jca = new JavaClassAnalyse();
		JavaClassTemplateVO classrootvo = jca.analyse(rootvo.getClassElement("Test").getStringcontent());
		if(classrootvo != null) {
			logger.debug("----->JAVA Class 分析执行成功！");
			classrootvo.show();
		}else {
			logger.error("----->JAVA Class 分析执行失败！");
			return;
		}
	
		//对classvo进行执行，以初始化其变量表
		JavaClassEval jclasseval = new JavaClassEval();
		boolean classevalflag = jclasseval.loadClass(classrootvo, classtable);
		
		if(classevalflag){
			logger.info("类初始化成功");
		}else {
			logger.error("类初始化失败");
			return;
		}
		
		//生成新实例
		JavaClassInstanceVO instancevo =jclasseval.newInstance(classrootvo, classtable);
		
		if(instancevo == null) {
			logger.error("instancevo is null");
			return;
		}
		
		String methodname="test";
		JavaClassMethodElement methodelement = instancevo.getMethod(methodname);
		
		if(methodelement==null) {
			logger.error("查找method方法失败");
			return ;
		}else {
			logger.debug("查找method方法成功");
		}
		
		JavaMethodAnalyse jma = new JavaMethodAnalyse();
		JavaMethodRootVO methodrootvo = jma.analyse(methodelement);
		
		if(methodrootvo == null) {
			logger.error("解析失败:"+methodelement.getStringcontent());
			return;
		}else {
			logger.debug("解析成功!");
			methodrootvo.show();
		}
		
		//执行方法
		JavaMethodEval jmethodeval = new JavaMethodEval();
		//IVarTable classvartable = (IVarTable)instancevo;
		//在方法执行之前，需要先执行类定义，以初始化类的变量表
		//否则调用时会找不到类的static变量表
		L2_HashMapClassStaticVarTableImpl vartable1 = new L2_HashMapClassStaticVarTableImpl("");
		//b1.setVartable(vartable1);
		L2_HashMapClassInstanceVarTableImpl vartable2 = new L2_HashMapClassInstanceVarTableImpl("",vartable1);
		
		L3_HashMapMethodInstanceVarTableImpl vartable3 = new L3_HashMapMethodInstanceVarTableImpl("",vartable2);
		L4_HashMapBlockVarTableImpl vartable4 = new L4_HashMapBlockVarTableImpl("",vartable3);
		
	
		jmethodeval.eval(methodrootvo, vartable4,classtable);
		
	}
		
}
