package com.smalljava.core.l6_supportenv.l6_classsupport.helper;

import java.util.ArrayList;

import com.smalljava.core.analyse.l4_block.BlockAnalyse;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.instancevo.JavaClassInstanceVO;
import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;
import com.smalljava.core.commonvo.l2_javaclass.element.SmallJavaClassVarDefineElement;
import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.eval.l4_block.SmallJavaBlockEvaluator;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.impl.ClassTableImpl;
import com.smalljava.core.l9_space.vartable.IVarTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassStaticVarTableImpl;

public class SmallJavaClassSupportEnv_NewInstance {
	private Logger logger = LoggerFactory.getLogger(SmallJavaClassSupportEnv_NewInstance.class);
	/**
	 * MEMO：执行类的实例化，输入参数为SmallJava类名
	 * MEMO：（暂不考虑重名问题，重名问题下一步解决）
	 * MEMO：返回指定的VO对象
	 * @param classenv
	 * @param classname
	 * @return
	 */
	public JavaClassInstanceVO newInstance(SmallJavaClassSupportEnv classenv, 
			String classname , SmallJavaOopSupportEnv oopenv) {
		//step1: arguement check.
		if (classenv == null) {
			logger.error("[ERROR] env is null.");
			return null;
		}
		
		if (classname == null) {
			logger.error("[ERROR] classname is null.");
			return null;
		}
		//step1: from env,retrieve class definevo
		SmallJavaClassTemplateVO classvo = classenv.getClassmanager().getClassdefinemap().get(classname);
		if(classvo == null) {
			logger.error("[ERROR] classvo is null.");
			return null;
		}
		IVarTable staticvartable = classenv.getClassmanager().getClassStaticVartableMap().get(classname);
		if(staticvartable == null) {
			logger.error("[ERROR] class static vartable is null.");
			return null;
		}
		L2_HashMapClassStaticVarTableImpl parentVartable=null;
		if(staticvartable instanceof L2_HashMapClassStaticVarTableImpl) {
			parentVartable = (L2_HashMapClassStaticVarTableImpl) staticvartable;
		}else {
			logger.error("[ERROR] [Type mismatch]staticvartable is not L2_HashMapClassStaticVarTableImpl.");
		}
		
		//step2.define return objectinstance vo.
		JavaClassInstanceVO instanceVO = new JavaClassInstanceVO();
		//copy packagename
		instanceVO.setPackagename(classvo.getPackagename());
		//copy classname
		instanceVO.setClassname(classvo.getClassname());
		
		//copy child elements.
		//This elements is generate at class analyse time
		//and this element will never changed at runtime
		//so that we can directily copy it from class to object.
		instanceVO.setChildren(classvo.getChildren());
		
		//create a new Vartable for object instance.
		L2_HashMapClassInstanceVarTableImpl  objectvartable =
				new L2_HashMapClassInstanceVarTableImpl("objectvartable",parentVartable);
		//class instancevo 本身挂载一个 vartable
		instanceVO.setVartable(objectvartable);
		
		//MEMO: 在class instance vo 创建的时候，需要初始化他对应的变量表
		//MEMO：这一过程类似于在类定义读入时初始化类对应的静态变量表
		ArrayList<SmallJavaClassVarDefineElement> vardefinelist = instanceVO.getPropertiesArray();
		for(SmallJavaClassVarDefineElement vardefine: vardefinelist) {
			String strcontent = vardefine.getStringcontent();
			//logger output for debug.
			logger.debug("[loadClassTemplateVO]vardefine:"+strcontent);
			
			//MEMO: execute this {strcontent} on {varTable} that creat upside.
			//process only not static var define.
			if(strcontent.indexOf("static ") < 0) {
				this.executeStatementOnVarTable(strcontent, objectvartable,classenv,oopenv);
			}else {
				//this is static variable, 
				//igore it. static variable will defined on class loader.
			}		
		}
		
		//TODO:在类的实例化时，需要在构建完成的instance上调用构造函数的实例化代码
		//MEMO:这部分功能将在后续完成
		//MEMO:目前暂时只考虑无参数的构造函数支持，带有参数的构造函数暂时不支持。
		return instanceVO;
	}
	
	/**
	 * This function only execute var define statement.
	 * This function will call block evaluate function.
	 * @param strcontent
	 * @param vartable
	 * @return
	 */
	private boolean executeStatementOnVarTable(String strcontent,
			L2_HashMapClassInstanceVarTableImpl vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		BasicBlock closedblock = new BasicBlock("",strcontent,null);
		BlockAnalyse ba = new BlockAnalyse();
		boolean isok = ba.analyse(closedblock);
		if(! isok) {
			logger.error("[ERROR]error at analyse block:"+strcontent);
			return false;
		}
		
		//on success analyse the string input
		SmallJavaBlockEvaluator node = new SmallJavaBlockEvaluator();
		//ClassTableImpl classtable = new ClassTableImpl();
		try {
			boolean b2 = node.execute(closedblock,vartable,classenv,oopenv);
			return b2;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[ERROR]error on execute. "+strcontent);
			return false;
		}
	}
	
}
