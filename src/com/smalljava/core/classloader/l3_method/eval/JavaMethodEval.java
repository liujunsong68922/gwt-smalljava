package com.smalljava.core.classloader.l3_method.eval;

import com.smalljava.core.classloader.l3_method.vo.JavaMethodRootVO;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockanalyse.BlockAnalyse;
import com.smalljava.core.l4_block.blockeval.BlockEvaluator;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassStaticVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L3_HashMapMethodInstanceVarTableImpl;

public class JavaMethodEval {
	private Logger logger = LoggerFactory.getLogger(JavaMethodEval.class);
	/**
	 * MEMO：针对Method的方法调用，需要传入IVarTable,ClassTable
	 * MEMO:先不考虑调用参数的问题
	 * MEMO：Method块需要把调用参数加入到自己的变量表里面去，并引用对象的变量表
	 * @param methodvo
	 * @param vartable
	 * @return
	 */
	public VarValue eval(JavaMethodRootVO methodvo,IVarTable classvartable,IClassTable classtable) {
		if(methodvo == null) {
			logger.error("methodvo is null.");
			return null;
		}
		
		BasicBlock closedblock = new BasicBlock("",methodvo.getMethodContent(),null);
		//closedblock.setClassVarTable(classvartable);
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		closedblock.show(0);
		logger.info("代码块分析结果："+isok);	
		
		
		BlockEvaluator eval = new BlockEvaluator();
		L2_HashMapClassStaticVarTableImpl vartable1 = new L2_HashMapClassStaticVarTableImpl("");
		L2_HashMapClassInstanceVarTableImpl vartable2 = new L2_HashMapClassInstanceVarTableImpl("",vartable1);
		L3_HashMapMethodInstanceVarTableImpl vartable3 = new L3_HashMapMethodInstanceVarTableImpl("",vartable2);
		boolean b2;
		try {
			b2 = eval.execute(closedblock,vartable3,classtable);
			if(b2) {
				//TODO:此处需要查找并设计返回值
				logger.info("Method call finished ok.");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
		
		
	}
}
