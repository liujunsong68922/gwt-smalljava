package com.smalljava.core.eval.l4_block;

import com.smalljava.core.analyse.l5_expression.ExpressionASTAnalyse;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l4_block.childblock.DOWHILEBlock;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.eval.l5_expression.ExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;


public class DOWHILEBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(DOWHILEBlockEvaluator.class);
	
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();

	@SuppressWarnings("static-access")
	public boolean execute(DOWHILEBlock block,L4_HashMapBlockVarTableImpl vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) throws Exception {
		//
		BlockEvaluator blockevalutor = new BlockEvaluator();
		boolean bdone = blockevalutor.execute(block.getDonode(), vartable, classenv,oopenv);
		if (!bdone) {
			// ִ
			log("[error] do while block error");
			return false;
		}

		// IF NODEֵ
		logger.error("while :" + block.getWhilenode().getBlockContent());
		RootAST node = expressionASTAnalyse.analyse(block.getWhilenode().getBlockContent());
		if (node == null) {
			return false;
		} else {
			node.show(0);
		}

		VarValue b1 = expressionEval.eval(node, vartable, classenv,oopenv);
		if (b1 != null) {
			logger.error("while eval true" + b1.getVarsvalue());
		} else {
			logger.error("while eval false:");
			return false;
		}
		while (b1.getVarsvalue().equalsIgnoreCase("true")) {
			boolean bool2 = blockevalutor.execute(block.getDonode(),vartable, classenv,oopenv);
			if (!bool2) {
				log("��ERROR��DoWhile��ִ��do�ڵ�ʧ��");
				return false;
			}
			node = expressionASTAnalyse.analyse(block.getWhilenode().getBlockContent());
			node.show(0);
			
			b1 = expressionEval.eval(node, vartable, classenv,oopenv);
			if (b1 == null) {
				logger.error("ִdo while eval false.");
				return false;
			}
			logger.error("while result:" + b1.getVarsvalue());
			if (b1.getVarsvalue().equalsIgnoreCase("true")) {
				logger.error("while condition true");
			} else {
				logger.error("while condition false");
				break;
			}
		}
		return true;
	}

	private void log(String sinfo) {
		logger.error(sinfo);
	}
}
