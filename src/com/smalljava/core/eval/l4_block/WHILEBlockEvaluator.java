package com.smalljava.core.eval.l4_block;

import com.smalljava.core.analyse.l5_expression.ExpressionASTAnalyse;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l4_block.childblock.WHILEBlock;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.eval.l5_expression.ExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;
public class WHILEBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(WHILEBlockEvaluator.class);
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();
	
	@SuppressWarnings("static-access")
	public boolean execute(WHILEBlock block,L4_HashMapBlockVarTableImpl vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) throws Exception {
		//ֵ
		logger.error("while condition:" + block.getWhilecondition().getBlockContent());
		RootAST node = expressionASTAnalyse.analyse(block.getWhilecondition().getBlockContent());
		if(node==null) {
			return false;
		}else {
			node.show(0);
		}
		VarValue b1 = expressionEval.eval(node, vartable, classenv,oopenv);
		if(b1.getVarsvalue().equalsIgnoreCase("true")) {
			logger.error("while execute ok." + b1.getVarsvalue());
		}else {
			log("[error] while execute error."+block.getWhilecondition().getBlockContent());
			return false;
		}
		
		while (b1.getVarsvalue().equalsIgnoreCase("true")) {
			BlockEvaluator blockevalutor = new BlockEvaluator();
			boolean b2 = blockevalutor.execute(block.getWhileloopnode(),vartable,classenv,oopenv);
			if(! b2) {
				log("[error] while execute error.");
				return false;
			}
			
			node = expressionASTAnalyse.analyse(block.getWhilecondition().getBlockContent());
			if(node==null) {
				return false;
			}else {
				node.show(0);
			}
			VarValue b3 = expressionEval.eval(node, vartable, classenv,oopenv);
			if(b3 == null) {
				log("[error] eval error.");
				return false;				
			}
			logger.error("while execute result." + b3.getVarsvalue());
			if(b3.getVarsvalue().equalsIgnoreCase("true")) {
				logger.error("---->while execute ok");
			}else {
				logger.error("---->while execute false.");
				break;
			}
		}
		return true;
	}
	
	private void log(String sinfo) {
		logger.error(sinfo);
	}


}
