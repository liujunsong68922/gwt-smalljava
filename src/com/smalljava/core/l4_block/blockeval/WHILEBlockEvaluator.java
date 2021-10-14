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
 * ����ִ��һ��WhileBlock�ڵ�
 * @author liujunsong
 *
 */
public class WHILEBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(WHILEBlockEvaluator.class);
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();
	
	@SuppressWarnings("static-access")
	public boolean execute(WHILEBlock block,L4_HashMapBlockVarTableImpl vartable,IClassTable classtable) throws Exception {
		// IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
		logger.error("while �������ʽ:" + block.getWhilecondition().getBlockContent());
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
			logger.error("while������������" + b1.getVarsvalue());
		}else {
			log("��ERROR��WhileBlock . while��������ʧ��."+block.getWhilecondition().getBlockContent());
			return false;
		}
		
		while (b1.getVarsvalue().equalsIgnoreCase("true")) {
			// ִ��while block
			// ������һ��ִ����
			BlockEvaluator blockevalutor = new BlockEvaluator();
			boolean b2 = blockevalutor.execute(block.getWhileloopnode(),vartable,classtable);
			if(! b2) {
				log("��ERROR��While block ִ��loop�ڵ�ʧ��");
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
				log("��ERROR��While block ִ�������ڵ�ʧ��");
				return false;				
			}
			logger.error("while������������" + b3.getVarsvalue());
			if(b3.getVarsvalue().equalsIgnoreCase("true")) {
				logger.error("---->while ����Ϊ��");
			}else {
				logger.error("---->while ����Ϊ�٣�ѭ���˳�.");
				break;
			}
		}
		//������ѭ������������true
		return true;
	}
	
	private void log(String sinfo) {
		logger.error(sinfo);
	}


}
