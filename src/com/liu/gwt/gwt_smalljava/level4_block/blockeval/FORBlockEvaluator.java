package com.liu.gwt.gwt_smalljava.level4_block.blockeval;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.FORBlock;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.ExpressionASTAnalyse;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.ExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.space.IClassTable;

public class FORBlockEvaluator {
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();

	@SuppressWarnings("static-access")
	public boolean execute(FORBlock block,IClassTable classtable) throws Exception {
		log("---------->ִ��for�ڵ�");

		//step1:ִ�п�ʼ�ڵ�
		//ASTTreeNode node = new ASTTreeNode(block.getBeginNode().getBlockContent(),0);
		RootAST node = expressionASTAnalyse.analyse(block.getBeginNode().getBlockContent());
		//node.analyseTree();
		if(node == null) {
			return false;
		}else {
			node.show(0);
		}

		//boolean b1 = node.eval(block,classtable);
		VarValue b1 = expressionEval.eval(node, block, classtable);
		if(b1 == null) {
			log("��ERROR��IF�ڵ�ִ��begin�ڵ����."+block.getBeginNode().getBlockContent());
			return false;
		}
		//this.beginNode.execute();
		//step2:ִ���ж�����
		//System.out.println("while �������ʽ:" + this.forcondition);
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
		VarValue b2 = expressionEval.eval(node2, block, classtable);
		if(b2 == null) {
			log("��ERROR��IF�ڵ�ִ��condition�ڵ����."+block.getForconditionNode().getBlockContent());
			return false;
		}
		System.out.println("for������������" + b2.getVarsvalue());
		
		while (b2.getVarsvalue().equalsIgnoreCase("true")) {
			//step3.ִ�д����
			BlockEvaluator be = new BlockEvaluator();
			boolean b3 = be.execute(block.getForloopBlock(),classtable);
			if(! b3) {
				log("��ERROR��IF�ڵ�ִ��loop�����."+block.getForloopBlock().getBlockContent());
				return false;
				
			}
			
			//ASTTreeNode node3 = new ASTTreeNode(block.getLoopNode().getBlockContent(), 0);
			RootAST node3 = expressionASTAnalyse.analyse(block.getLoopNode().getBlockContent());
			//node3.analyseTree();
			if(node3 == null) {
				return false;
			}else {
				node3.show(0);
			}
			
			//boolean b4 = node3.eval(block,classtable);
			VarValue b4 = expressionEval.eval(node3, block, classtable);
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
			VarValue b5 =expressionEval.eval(node2, block, classtable);
			if(b5 == null) {
				log("��ERROR��IF�ڵ�ִ��condition�ڵ����."+block.getForconditionNode().getBlockContent());
				return false;				
			}
			
			System.out.println("for������������" +b5.getVarsvalue());
			b2 = b5;
		}
		
		return true;
	}
	
	private void log(String sinfo) {
		System.out.println(sinfo);
	}
	
	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[ForBlockEvaluaotr]:" + message );
	}-*/;
}
