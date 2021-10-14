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
 * ����ִ��һ��WhileBlock�ڵ�
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
		// ��ִ��һ��do���
		BlockEvaluator blockevalutor = new BlockEvaluator();
		boolean bdone = blockevalutor.execute(block.getDonode(), vartable, classtable);
		if (!bdone) {
			// ִ��ʧ�ܣ�����false.
			log("��ERROR��DoWhile�ڵ�ִ��DO�ڵ㷢������");
			return false;
		}

		// IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
		logger.error("while �������ʽ:" + block.getWhilenode().getBlockContent());
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
			logger.error("while������������" + b1.getVarsvalue());
		} else {
			logger.error("while��������ʧ��:");
			return false;
		}
		while (b1.getVarsvalue().equalsIgnoreCase("true")) {
			// ִ��while block
			// this.donode.execute();
			boolean bool2 = blockevalutor.execute(block.getDonode(),vartable, classtable);
			if (!bool2) {
				log("��ERROR��DoWhile��ִ��do�ڵ�ʧ��");
				return false;
			}
			node = expressionASTAnalyse.analyse(block.getWhilenode().getBlockContent());
			//node = new ASTTreeNode(block.getWhilenode().getBlockContent(), 0);
			//node.analyseTree();
			node.show(0);
			
			b1 = expressionEval.eval(node, vartable, classtable);
			//boolean btemp = node.eval(block, classtable);
			if (b1 == null) {
				logger.error("ִ��ʱ��������false");
				return false;
			}
			logger.error("while������������" + b1.getVarsvalue());
			if (b1.getVarsvalue().equalsIgnoreCase("true")) {
				logger.error("while ����Ϊ��");
			} else {
				logger.error("while ����Ϊ�٣�ѭ���˳�.");
				break;
			}
		}
		return true;
	}

	private void log(String sinfo) {
		logger.error(sinfo);
	}
}
