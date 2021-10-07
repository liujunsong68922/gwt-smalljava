package com.liu.gwt.gwt_smalljava.level4_block.blockeval;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.WHILEBlock;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.ExpressionASTAnalyse;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.ExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.space.IClassTable;


/**
 * ����ִ��һ��WhileBlock�ڵ�
 * @author liujunsong
 *
 */
public class WHILEBlockEvaluator {
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();
	
	@SuppressWarnings("static-access")
	public boolean execute(WHILEBlock block,IClassTable classtable) throws Exception {
		// IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
		System.out.println("while �������ʽ:" + block.getWhilecondition().getBlockContent());
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
		VarValue b1 = expressionEval.eval(node, block, classtable);
		if(b1.getVarsvalue().equalsIgnoreCase("true")) {
			System.out.println("while������������" + b1.getVarsvalue());
		}else {
			log("��ERROR��WhileBlock . while��������ʧ��."+block.getWhilecondition().getBlockContent());
			return false;
		}
		
		while (b1.getVarsvalue().equalsIgnoreCase("true")) {
			// ִ��while block
			// ������һ��ִ����
			BlockEvaluator blockevalutor = new BlockEvaluator();
			boolean b2 = blockevalutor.execute(block.getWhileloopnode(),classtable);
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
			VarValue b3 = expressionEval.eval(node, block, classtable);
			if(b3 == null) {
				log("��ERROR��While block ִ�������ڵ�ʧ��");
				return false;				
			}
			System.out.println("while������������" + b3.getVarsvalue());
			if(b3.getVarsvalue().equalsIgnoreCase("true")) {
				System.out.println("---->while ����Ϊ��");
			}else {
				System.out.println("---->while ����Ϊ�٣�ѭ���˳�.");
				break;
			}
		}
		//������ѭ������������true
		return true;
	}
	
	private void log(String sinfo) {
		System.out.println(sinfo);
	}
	
//	@SuppressWarnings("unused")
//	private ElementWrapper getLoopnode(Element root) {
//		String xpath="Loopnode";
//		Element donodeelement = (Element) root.selectSingleNode(xpath);
//		if(donodeelement !=null) {
//			//��װһ�·���
//			ElementWrapper ew = new ElementWrapper();
//			ew.setElement(donodeelement);
//			return ew;
//		}else {
//			log("[WARNING]�Ҳ���Loopnode�ڵ�");
//			return null;
//		}
//	}
//	@SuppressWarnings("unused")
//	private ElementWrapper getWhileCondition(Element root) {
//		String xpath=SmallJavaBlockConst.WhileCondition;
//		Element whilenodeelement = (Element) root.selectSingleNode(xpath);
//		if(whilenodeelement !=null) {
//			//��װһ�·���
//			ElementWrapper ew = new ElementWrapper();
//			ew.setElement(whilenodeelement);
//			return ew;
//		}else {
//			log("[WARNING]�Ҳ���Whilenode�ڵ�");
//			return null;
//		}
//	}
	
	public boolean execute(ElementWrapper block,IClassTable classtable) throws Exception {
//		//check
//		if(this.getLoopnode(block.getElement()) == null
//				|| this.getWhileCondition(block.getElement()) == null) {
//			return false;
//		}
//		
//		// IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
//		System.out.println("while �������ʽ:" + getWhileCondition(block.getElement()).getBlockContent());
//		ASTTreeNode node = new ASTTreeNode(getWhileCondition(block.getElement()).getBlockContent(), 0);
//		node.analyseTree();
//		node.show();
//		//VarTableNode varmap = block.get.getVarmaps();
//		boolean b1 = node.eval(block,classtable);
//		if(b1) {
//			System.out.println("while������������" + node.getOperdataresult().isElementBooleanValue());
//		}else {
//			log("��ERROR��WhileBlock . while��������ʧ��."+getWhileCondition(block.getElement()).getBlockContent());
//			return false;
//		}
//		
//		while (node.getOperdataresult().isElementBooleanValue()) {
//			// ִ��while block
//			// ������һ��ִ����
//			BlockEvaluator blockevalutor = new BlockEvaluator();
//			boolean b2 = blockevalutor.execute(getLoopnode(block.getElement()),classtable);
//			if(! b2) {
//				log("��ERROR��While block ִ��loop�ڵ�ʧ��");
//				return false;
//			}
//			
//			
//			node = new ASTTreeNode(getWhileCondition(block.getElement()).getBlockContent(),0);
//			node.analyseTree();
//			node.show();
//			boolean b3  = node.eval(block,classtable);
//			if(! b3) {
//				log("��ERROR��While block ִ�������ڵ�ʧ��");
//				return false;				
//			}
//			System.out.println("while������������" + node.getOperdataresult().isElementBooleanValue());
//			if(node.getOperdataresult().isElementBooleanValue()) {
//				System.out.println("---->while ����Ϊ��");
//			}else {
//				System.out.println("---->while ����Ϊ�٣�ѭ���˳�.");
//				break;
//			}
//		}
//		//������ѭ������������true
//		return true;
		return false;
	}
}
