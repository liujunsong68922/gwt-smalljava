package com.smalljava.core.l4_block.blockeval;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockvo.childblock.DOWHILEBlock;
import com.smalljava.core.l5_expression.analyse.ExpressionASTAnalyse;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

/**
 * 评估执行一个WhileBlock节点
 * 
 * @author liujunsong
 *
 */
public class DOWHILEBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(DOWHILEBlockEvaluator.class);
	
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();

	@SuppressWarnings("static-access")
	public boolean execute(DOWHILEBlock block,L4_HashMapBlockVarTableImpl vartable, IClassTable classtable) throws Exception {
		// 先执行一次do语句
		BlockEvaluator blockevalutor = new BlockEvaluator();
		boolean bdone = blockevalutor.execute(block.getDonode(), vartable, classtable);
		if (!bdone) {
			// 执行失败，返回false.
			log("【ERROR】DoWhile节点执行DO节点发生错误");
			return false;
		}

		// IF NODE的执行过程如下，先计算if表达式的值
		logger.error("while 条件表达式:" + block.getWhilenode().getBlockContent());
		RootAST node = expressionASTAnalyse.analyse(block.getWhilenode().getBlockContent());
		// ASTTreeNode node = new ASTTreeNode(block.getWhilenode().getBlockContent(),
		// 0);
		// node.analyseTree();
		if (node == null) {
			return false;
		} else {
			node.show(0);
		}
		// VarTableNode varmap = this.getVarmaps();
		//boolean b1 = node.eval(block, classtable);
		VarValue b1 = expressionEval.eval(node, vartable, classtable);
		if (b1 != null) {
			logger.error("while条件计算结果：" + b1.getVarsvalue());
		} else {
			logger.error("while条件计算失败:");
			return false;
		}
		while (b1.getVarsvalue().equalsIgnoreCase("true")) {
			// 执行while block
			// this.donode.execute();
			boolean bool2 = blockevalutor.execute(block.getDonode(),vartable, classtable);
			if (!bool2) {
				log("【ERROR】DoWhile块执行do节点失败");
				return false;
			}
			node = expressionASTAnalyse.analyse(block.getWhilenode().getBlockContent());
			//node = new ASTTreeNode(block.getWhilenode().getBlockContent(), 0);
			//node.analyseTree();
			node.show(0);
			
			b1 = expressionEval.eval(node, vartable, classtable);
			//boolean btemp = node.eval(block, classtable);
			if (b1 == null) {
				logger.error("执行时出错，返回false");
				return false;
			}
			logger.error("while条件计算结果：" + b1.getVarsvalue());
			if (b1.getVarsvalue().equalsIgnoreCase("true")) {
				logger.error("while 条件为真");
			} else {
				logger.error("while 条件为假，循环退出.");
				break;
			}
		}
		return true;
	}

	private void log(String sinfo) {
		logger.error(sinfo);
	}
}
