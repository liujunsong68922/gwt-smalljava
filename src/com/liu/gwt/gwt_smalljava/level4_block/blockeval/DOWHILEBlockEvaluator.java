package com.liu.gwt.gwt_smalljava.level4_block.blockeval;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.DOWHILEBlock;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.ExpressionASTAnalyse;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.ExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.space.IClassTable;

/**
 * ����ִ��һ��WhileBlock�ڵ�
 * 
 * @author liujunsong
 *
 */
public class DOWHILEBlockEvaluator {
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	ExpressionEval expressionEval = new ExpressionEval();

	@SuppressWarnings("static-access")
	public boolean execute(DOWHILEBlock block, IClassTable classtable) throws Exception {
		// ��ִ��һ��do���
		BlockEvaluator blockevalutor = new BlockEvaluator();
		boolean bdone = blockevalutor.execute(block.getDonode(), classtable);
		if (!bdone) {
			// ִ��ʧ�ܣ�����false.
			log("��ERROR��DoWhile�ڵ�ִ��DO�ڵ㷢������");
			return false;
		}

		// IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
		System.out.println("while �������ʽ:" + block.getWhilenode().getBlockContent());
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
		VarValue b1 = expressionEval.eval(node, block, classtable);
		if (b1 != null) {
			System.out.println("while������������" + b1.getVarsvalue());
		} else {
			System.out.println("while��������ʧ��:");
			return false;
		}
		while (b1.getVarsvalue().equalsIgnoreCase("true")) {
			// ִ��while block
			// this.donode.execute();
			boolean bool2 = blockevalutor.execute(block.getDonode(), classtable);
			if (!bool2) {
				log("��ERROR��DoWhile��ִ��do�ڵ�ʧ��");
				return false;
			}
			node = expressionASTAnalyse.analyse(block.getWhilenode().getBlockContent());
			//node = new ASTTreeNode(block.getWhilenode().getBlockContent(), 0);
			//node.analyseTree();
			node.show(0);
			
			b1 = expressionEval.eval(node, block, classtable);
			//boolean btemp = node.eval(block, classtable);
			if (b1 == null) {
				System.out.println("ִ��ʱ��������false");
				return false;
			}
			System.out.println("while������������" + b1.getVarsvalue());
			if (b1.getVarsvalue().equalsIgnoreCase("true")) {
				System.out.println("while ����Ϊ��");
			} else {
				System.out.println("while ����Ϊ�٣�ѭ���˳�.");
				break;
			}
		}
		return true;
	}

	private void log(String sinfo) {
		System.out.println(sinfo);
	}

	public boolean execute(ElementWrapper block, IClassTable classtable) throws Exception {
//		// ��ִ��һ��do���
//		BlockEvaluator blockevalutor = new BlockEvaluator();
//		ElementWrapper donnodeew = this.getDonode(block.getElement());
//		if (donnodeew == null) {
//			// ���Ҳ���donnode,return
//			return false;
//		}
//		ElementWrapper whilenodeew = this.getWhilenode(block.getElement());
//		if (whilenodeew == null) {
//			// ���Ҳ���donnode,return
//			return false;
//		}
//
//		boolean bdone = blockevalutor.execute(donnodeew, classtable);
//		if (!bdone) {
//			// ִ��ʧ�ܣ�����false.
//			log("��ERROR��DoWhile�ڵ�ִ��DO�ڵ㷢������");
//			return false;
//		}
//
//		// IF NODE��ִ�й������£��ȼ���if���ʽ��ֵ
//		System.out.println("while �������ʽ:" + whilenodeew.getBlockContent());
//		ASTTreeNode node = new ASTTreeNode(whilenodeew.getBlockContent(), 0);
//		node.analyseTree();
//		node.show();
//		// VarTableNode varmap = this.getVarmaps();
//		boolean b1 = node.eval(block, classtable);
//		if (b1) {
//			System.out.println("while������������" + node.getOperdataresult().isElementBooleanValue());
//		} else {
//			System.out.println("while��������ʧ��:");
//			return false;
//		}
//		while (node.getOperdataresult().isElementBooleanValue()) {
//			// ִ��while block
//			// this.donode.execute();
//			boolean bool2 = blockevalutor.execute(getDonode(block.getElement()), classtable);
//			if (!bool2) {
//				log("��ERROR��DoWhile��ִ��do�ڵ�ʧ��");
//				return false;
//			}
//
//			node = new ASTTreeNode(getWhilenode(block.getElement()).getBlockContent(), 0);
//			node.analyseTree();
//			node.show();
//			boolean btemp = node.eval(block, classtable);
//			if (!btemp) {
//				System.out.println("ִ��ʱ��������false");
//				return false;
//			}
//			System.out.println("while������������" + node.getOperdataresult().isElementBooleanValue());
//			if (node.getOperdataresult().isElementBooleanValue()) {
//				System.out.println("while ����Ϊ��");
//			} else {
//				System.out.println("while ����Ϊ�٣�ѭ���˳�.");
//			}
//		}
//		return true;
		return false;
	}

//	@SuppressWarnings("unused")
//	private ElementWrapper getDonode(Element root) {
//		String xpath = SmallJavaBlockConst.DoNode;
//		Element donodeelement = (Element) root.selectSingleNode(xpath);
//		if (donodeelement != null) {
//			// ��װһ�·���
//			ElementWrapper ew = new ElementWrapper();
//			ew.setElement(donodeelement);
//			return ew;
//		} else {
//			log("[WARNING]�Ҳ���Donode�ڵ�");
//			return null;
//		}
//	}

//	@SuppressWarnings("unused")
//	private ElementWrapper getWhilenode(Element root) {
//		String xpath = SmallJavaBlockConst.WhileNode;
//		Element whilenodeelement = (Element) root.selectSingleNode(xpath);
//		if (whilenodeelement != null) {
//			// ��װһ�·���
//			ElementWrapper ew = new ElementWrapper();
//			ew.setElement(whilenodeelement);
//			return ew;
//		} else {
//			log("[WARNING]�Ҳ���Whilenode�ڵ�");
//			return null;
//		}
//	}
}
