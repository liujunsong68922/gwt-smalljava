package com.smalljava.core.l4_block.blockeval;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockvo.childblock.WHILEBlock;
import com.smalljava.core.l5_expression.analyse.ExpressionASTAnalyse;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;
public class WHILEBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(WHILEBlockEvaluator.class);
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();
	
	@SuppressWarnings("static-access")
	public boolean execute(WHILEBlock block,L4_HashMapBlockVarTableImpl vartable,IClassTable classtable) throws Exception {
		//ֵ
		logger.error("while condition:" + block.getWhilecondition().getBlockContent());
		RootAST node = expressionASTAnalyse.analyse(block.getWhilecondition().getBlockContent());
		if(node==null) {
			return false;
		}else {
			node.show(0);
		}
		VarValue b1 = expressionEval.eval(node, vartable, classtable);
		if(b1.getVarsvalue().equalsIgnoreCase("true")) {
			logger.error("while execute ok." + b1.getVarsvalue());
		}else {
			log("[error] while execute error."+block.getWhilecondition().getBlockContent());
			return false;
		}
		
		while (b1.getVarsvalue().equalsIgnoreCase("true")) {
			BlockEvaluator blockevalutor = new BlockEvaluator();
			boolean b2 = blockevalutor.execute(block.getWhileloopnode(),vartable,classtable);
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
			VarValue b3 = expressionEval.eval(node, vartable, classtable);
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
