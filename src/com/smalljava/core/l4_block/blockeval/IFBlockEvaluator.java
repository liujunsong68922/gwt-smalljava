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
		//IF NODE的执行过程如下，先计算if表达式的值
		log("IF 条件表达式:"+block.getIfConditionblock().getBlockContent());
		//ASTTreeNode node = new ASTTreeNode(block.getIfConditionblock().getBlockContent(),0);
		RootAST node = expressionASTAnalyse.analyse(block.getIfConditionblock().getBlockContent());
		//node.analyseTree();
		if(node == null) {
			return false;
		}else {
			node.show(0);
		}
		//VarTableNode varmap = this.getVarmaps();
		//boolean b1 = node.eval(block,classtable);
		VarValue b1 = expressionEval.eval(node, vartable, classtable);
		if(! b1.getVarsvalue().equalsIgnoreCase("true")) {
			log("【ERROR】IF节点计算判断条件出错:"+block.getIfConditionblock().getBlockContent());
			return false;
		}else {
			logger.error("计算结果："+b1.getVarsvalue());
		}
		if(b1.getVarsvalue().equalsIgnoreCase("true")) {
			//执行IF block
			BlockEvaluator be = new BlockEvaluator();
			boolean b2 = be.execute(block.getIftrueblock(),vartable,classtable);
			if(! b2) {
				log("【ERROR】IF节点执行 iftrueblock出错."+block.getIftrueblock().getBlockContent());
				return false;
			}
		}else {
			if (block.getIfelseblock() !=null) {
				//执行else块部分
				BlockEvaluator be2 = new BlockEvaluator();
				boolean b3 = be2.execute(block.getIfelseblock(),vartable,classtable);
				if(! b3) {
					log("【ERROR】IF节点执行 ifelseblock出错."+block.getIftrueblock().getBlockContent());
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
