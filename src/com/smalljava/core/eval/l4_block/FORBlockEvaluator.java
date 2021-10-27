package com.smalljava.core.eval.l4_block;

import com.smalljava.core.analyse.l5_expression.ExpressionASTAnalyse;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l4_block.childblock.FORBlock;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.eval.l5_expression.ExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class FORBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(FORBlockEvaluator.class);
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();

	@SuppressWarnings("static-access")
	public boolean execute(FORBlock block,L4_HashMapBlockVarTableImpl vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) throws Exception {
		log("---------->enter forblock eval");

		//step1:
		//ASTTreeNode node = new ASTTreeNode(block.getBeginNode().getBlockContent(),0);
		RootAST node = expressionASTAnalyse.analyse(block.getBeginNode().getBlockContent());
		//node.analyseTree();
		if(node == null) {
			return false;
		}else {
			node.show(0);
		}

		//boolean b1 = node.eval(block,classtable);
		VarValue b1 = expressionEval.eval(node, vartable, classenv,oopenv);
		if(b1 == null) {
			log("[ERROR]."+block.getBeginNode().getBlockContent());
			return false;
		}
		//this.beginNode.execute();
		//step2:ִ���ж�����
		//logger.error("while �������ʽ:" + this.forcondition);
		//ASTTreeNode node2 = new ASTTreeNode(block.getForconditionNode().getBlockContent(), 0);
		RootAST node2 =expressionASTAnalyse.analyse(block.getForconditionNode().getBlockContent());
		
		//node2.analyseTree();
		if(node2 == null) {
			return false;
		}else {
			node2.show(0);
		}
		//VarTableNode varmap = this.getVarmaps();
		//node.eval(varmap);
		//boolean b2 = node2.eval(block,classtable);
		VarValue b2 = expressionEval.eval(node2, vartable, classenv,oopenv);
		if(b2 == null) {
			log("[ERROR] if condition."+block.getForconditionNode().getBlockContent());
			return false;
		}
		logger.error("for condition value:" + b2.getVarsvalue());
		
		while (b2.getVarsvalue().equalsIgnoreCase("true")) {
			//step3
			BlockEvaluator be = new BlockEvaluator();
			boolean b3 = be.execute(block.getForloopBlock(),vartable,classenv,oopenv);
			if(! b3) {
				log("[ERROR]"+block.getForloopBlock().getBlockContent());
				return false;
				
			}
			
			//ASTTreeNode node3 = new ASTTreeNode(block.getLoopNode().getBlockContent(), 0);
			System.out.println(block.getLoopNode().getBlockContent());
			RootAST node3 = expressionASTAnalyse.analyse(block.getLoopNode().getBlockContent());
			//node3.analyseTree();
			if(node3 == null) {
				return false;
			}else {
				node3.show(0);
			}
			
			//boolean b4 = node3.eval(block,classtable);
			VarValue b4 = expressionEval.eval(node3, vartable, classenv,oopenv);
			if(b4 == null) {
				log("��ERROR��ִ��loop�ڵ����"+block.getLoopNode().getBlockContent());
				return false;
			}
					
			//���¼����ж�����
			//node2 = new ASTTreeNode(block.getForconditionNode().getBlockContent(), 0);
			node2 =expressionASTAnalyse.analyse(block.getForconditionNode().getBlockContent());
			//node2.analyseTree();
			if(node2 == null) {
				return false;
			}else {
				node2.show(0);
			}
			//boolean b5= node2.eval(block,classtable);
			
			VarValue b5 =expressionEval.eval(node2, vartable, classenv,oopenv);
			if(b5 == null) {
				log("��ERROR��IF�ڵ�ִ��condition�ڵ����."+block.getForconditionNode().getBlockContent());
				return false;				
			}
			
			logger.error("for condition value" +b5.getVarsvalue());
			//copy b5 to b2 for check
			
			b2 = b5;
		}
		
		return true;
	}
	
	private void log(String sinfo) {
		logger.error(sinfo);
	}



}
