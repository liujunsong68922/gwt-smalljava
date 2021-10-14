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


/**
 * 评估执行一个WhileBlock节点
 * @author liujunsong
 *
 */
public class WHILEBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(WHILEBlockEvaluator.class);
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();
	
	@SuppressWarnings("static-access")
	public boolean execute(WHILEBlock block,L4_HashMapBlockVarTableImpl vartable,IClassTable classtable) throws Exception {
		// IF NODE的执行过程如下，先计算if表达式的值
		logger.error("while 条件表达式:" + block.getWhilecondition().getBlockContent());
		//ASTTreeNode node = new ASTTreeNode(block.getWhilecondition().getBlockContent(), 0);
		RootAST node = expressionASTAnalyse.analyse(block.getWhilecondition().getBlockContent());
		//node.analyseTree();
		if(node==null) {
			return false;
		}else {
			node.show(0);
		}
		//VarTableNode varmap = block.get.getVarmaps();
		//boolean b1 = node.eval(block,classtable);
		VarValue b1 = expressionEval.eval(node, vartable, classtable);
		if(b1.getVarsvalue().equalsIgnoreCase("true")) {
			logger.error("while条件计算结果：" + b1.getVarsvalue());
		}else {
			log("【ERROR】WhileBlock . while条件计算失败."+block.getWhilecondition().getBlockContent());
			return false;
		}
		
		while (b1.getVarsvalue().equalsIgnoreCase("true")) {
			// 执行while block
			// 定义另一个执行器
			BlockEvaluator blockevalutor = new BlockEvaluator();
			boolean b2 = blockevalutor.execute(block.getWhileloopnode(),vartable,classtable);
			if(! b2) {
				log("【ERROR】While block 执行loop节点失败");
				return false;
			}
			
			
			//node = new ASTTreeNode(block.getWhilecondition().getBlockContent(),0);
			node = expressionASTAnalyse.analyse(block.getWhilecondition().getBlockContent());
			//node.analyseTree();
			if(node==null) {
				return false;
			}else {
				node.show(0);
			}
			//boolean b3  = node.eval(block,classtable);
			VarValue b3 = expressionEval.eval(node, vartable, classtable);
			if(b3 == null) {
				log("【ERROR】While block 执行条件节点失败");
				return false;				
			}
			logger.error("while条件计算结果：" + b3.getVarsvalue());
			if(b3.getVarsvalue().equalsIgnoreCase("true")) {
				logger.error("---->while 条件为真");
			}else {
				logger.error("---->while 条件为假，循环退出.");
				break;
			}
		}
		//不满足循环条件，返回true
		return true;
	}
	
	private void log(String sinfo) {
		logger.error(sinfo);
	}


}
