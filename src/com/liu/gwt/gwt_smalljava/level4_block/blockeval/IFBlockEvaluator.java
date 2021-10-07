package com.liu.gwt.gwt_smalljava.level4_block.blockeval;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.IFBlock;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.ExpressionASTAnalyse;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.ExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.space.IClassTable;

public class IFBlockEvaluator {
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();

	@SuppressWarnings("static-access")
	public boolean execute(IFBlock block,IClassTable classtable) throws Exception {
		//IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
		log("IF �������ʽ:"+block.getIfConditionblock().getBlockContent());
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
		VarValue b1 = expressionEval.eval(node, block, classtable);
		if(! b1.getVarsvalue().equalsIgnoreCase("true")) {
			log("��ERROR��IF�ڵ�����ж���������:"+block.getIfConditionblock().getBlockContent());
			return false;
		}else {
			System.out.println("��������"+b1.getVarsvalue());
		}
		if(b1.getVarsvalue().equalsIgnoreCase("true")) {
			//ִ��IF block
			BlockEvaluator be = new BlockEvaluator();
			boolean b2 = be.execute(block.getIftrueblock(),classtable);
			if(! b2) {
				log("��ERROR��IF�ڵ�ִ�� iftrueblock����."+block.getIftrueblock().getBlockContent());
				return false;
			}
		}else {
			if (block.getIfelseblock() !=null) {
				//ִ��else�鲿��
				BlockEvaluator be2 = new BlockEvaluator();
				boolean b3 = be2.execute(block.getIfelseblock(),classtable);
				if(! b3) {
					log("��ERROR��IF�ڵ�ִ�� ifelseblock����."+block.getIftrueblock().getBlockContent());
					return false;
				}
			}
		}
		return true;
	}
	

	private void log(String sinfo) {
		System.out.println(sinfo);
	}	
	
	public boolean execute(ElementWrapper block,IClassTable classtable) throws Exception {
//		//check it
//		if(this.getIfConditionblock(block.getElement()) == null) {
//			return false;
//		}
//		if(this.getIftrueblock(block.getElement())==null) {
//			return false;
//		}
//		
//		//IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
//		log("IF �������ʽ:"+getIfConditionblock(block.getElement()).getBlockContent());
//		ASTTreeNode node = new ASTTreeNode(getIfConditionblock(block.getElement()).getBlockContent(),0);
//		node.analyseTree();
//		node.show();
//		//VarTableNode varmap = this.getVarmaps();
//		boolean b1 = node.eval(block,classtable);
//		if(! b1) {
//			log("��ERROR��IF�ڵ�����ж���������:"+getIfConditionblock(block.getElement()).getBlockContent());
//			return false;
//		}else {
//			System.out.println("��������"+node.getOperdataresult().isElementBooleanValue());
//		}
//		if(node.getOperdataresult().isElementBooleanValue()) {
//			//ִ��IF block
//			BlockEvaluator be = new BlockEvaluator();
//			boolean b2 = be.execute(getIftrueblock(block.getElement()),classtable);
//			if(! b2) {
//				log("��ERROR��IF�ڵ�ִ�� iftrueblock����."+getIftrueblock(block.getElement()).getBlockContent());
//				return false;
//			}
//		}else {
//			if (getIfelseblock(block.getElement()) !=null) {
//				//ִ��else�鲿��
//				BlockEvaluator be2 = new BlockEvaluator();
//				boolean b3 = be2.execute(getIfelseblock(block.getElement()),classtable);
//				if(! b3) {
//					log("��ERROR��IF�ڵ�ִ�� ifelseblock����."+getIfelseblock(block.getElement()).getBlockContent());
//					return false;
//				}
//			}
//		}
//		return true;
		return false;
	}	
	
//	@SuppressWarnings("unused")
//	private ElementWrapper getIfConditionblock(Element root) {
//		String xpath = "IfConditonNode";
//		Element ele = (Element) root.selectSingleNode(xpath);
//		if(ele!=null) {
//			ElementWrapper ew = new ElementWrapper();
//			ew.setElement(ele);
//			return ew;
//		}else {
//			return null;
//		}
//	}
	
//	@SuppressWarnings("unused")
//	private ElementWrapper getIftrueblock(Element root) {
//		String xpath = SmallJavaBlockConst.IfTrueBlock;
//		Element ele = (Element) root.selectSingleNode(xpath);
//		if(ele!=null) {
//			ElementWrapper ew = new ElementWrapper();
//			ew.setElement(ele);
//			return ew;
//		}else {
//			return null;
//		}
//	}
	
//	@SuppressWarnings("unused")
//	private ElementWrapper getIfelseblock(Element root) {
//		String xpath = SmallJavaBlockConst.IfElseBlock;
//		Element ele = (Element) root.selectSingleNode(xpath);
//		if(ele!=null) {
//			ElementWrapper ew = new ElementWrapper();
//			ew.setElement(ele);
//			return ew;
//		}else {
//			return null;
//		}
//	}
	
	
	
}
