package com.smalljava.core.l4_block.blockeval;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockvo.childblock.IFBlock;
import com.smalljava.core.l5_expression.analyse.ExpressionASTAnalyse;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class IFBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(IFBlockEvaluator.class);
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();

	@SuppressWarnings("static-access")
	public boolean execute(IFBlock block,L4_HashMapBlockVarTableImpl vartable,IClassTable classtable) throws Exception {
		log("IF condition:"+block.getIfConditionblock().getBlockContent());
		RootAST node = expressionASTAnalyse.analyse(block.getIfConditionblock().getBlockContent());
		if(node == null) {
			return false;
		}else {
			node.show(0);
		}
		VarValue b1 = expressionEval.eval(node, vartable, classtable);
		if(! b1.getVarsvalue().equalsIgnoreCase("true")) {
			log("[error] condition false:"+block.getIfConditionblock().getBlockContent());
			return false;
		}else {
			logger.error("condition result"+b1.getVarsvalue());
		}
		if(b1.getVarsvalue().equalsIgnoreCase("true")) {
			//
			BlockEvaluator be = new BlockEvaluator();
			boolean b2 = be.execute(block.getIftrueblock(),vartable,classtable);
			if(! b2) {
				log("if condition false."+block.getIftrueblock().getBlockContent());
				return false;
			}
		}else {
			if (block.getIfelseblock() !=null) {
				BlockEvaluator be2 = new BlockEvaluator();
				boolean b3 = be2.execute(block.getIfelseblock(),vartable,classtable);
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
