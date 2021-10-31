package com.smalljava.core.eval.l4_block.worker_plugin;

import com.smalljava.core.analyse.l5_expression.ExpressionASTAnalyse;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l4_block.childblock.IFBlock;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.eval.l4_block.SmallJavaBlockEvaluator;
import com.smalljava.core.eval.l5_expression.SmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class SmallJavaIFBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(SmallJavaIFBlockEvaluator.class);
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	SmallJavaExpressionEval expressionEval = new SmallJavaExpressionEval();

	@SuppressWarnings("static-access")
	public boolean execute(IFBlock block,L4_HashMapBlockVarTableImpl vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) throws Exception {
		log("IF condition:"+block.getIfConditionblock().getBlockContent());
		RootAST node = expressionASTAnalyse.analyse(block.getIfConditionblock().getBlockContent());
		if(node == null) {
			return false;
		}else {
			node.show(0);
		}
		VarValue b1 = expressionEval.eval(node, vartable, classenv,oopenv);
		if(! b1.getVarsvalue().equalsIgnoreCase("true")) {
			log("[error] condition false:"+block.getIfConditionblock().getBlockContent());
			return false;
		}else {
			logger.error("condition result"+b1.getVarsvalue());
		}
		if(b1.getVarsvalue().equalsIgnoreCase("true")) {
			//
			SmallJavaBlockEvaluator be = new SmallJavaBlockEvaluator();
			boolean b2 = be.execute(block.getIftrueblock(),vartable,classenv,oopenv);
			if(! b2) {
				log("if condition false."+block.getIftrueblock().getBlockContent());
				return false;
			}
		}else {
			if (block.getIfelseblock() !=null) {
				SmallJavaBlockEvaluator be2 = new SmallJavaBlockEvaluator();
				boolean b3 = be2.execute(block.getIfelseblock(),vartable,classenv,oopenv);
				if(! b3) {
					log("[error]"+block.getIftrueblock().getBlockContent());
					return false;
				}
			}
		}
		return true;
	}
	

	private void log(String sinfo) {
		logger.error(sinfo);
	}	
}
