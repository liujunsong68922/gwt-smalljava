package com.liu.gwt.gwt_smalljava.level4_block.blockeval;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.DOWHILEBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.FORBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.IFBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.MethodBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.WHILEBlock;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.ExpressionASTAnalyse;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.ExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.space.IClassTable;

public class BlockEvaluator {

	public BlockEvaluator() {

	}

	/**
	 * ��rootblock���м�������
	 * 
	 * @param child2
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public boolean execute(BasicBlock child2,IClassTable classtable) throws Exception {
		// 首先判断这个node有没有child
		if (child2.getChildren() == null || child2.getChildren().size() == 0) {
			// 空节点不用返回
			if (child2.getBlockContent() != null && child2.getBlockContent().equals("")) {
				//logger.info("空节点不用计算");
				return true;
			}

			// 备注节点不用处理，直接返回
			if (child2.getBlocktype() != null 
					&& child2.getBlocktype().equals(SmallJavaBlockConst.SingleLineMemo)) {
				//logger.info("备注节点不用计算");
				return true;
			}

			// 备注节点不用处理，直接返回
			if (child2.getBlocktype() != null 
					&& child2.getBlocktype().equals(SmallJavaBlockConst.MultiLineMemo)) {
				//logger.info("备注节点不用计算");
				return true;
			}

			// 这是一个没有下级节点的节点，已经是最底层的节点了
			// 把这个节点转换成一个ASTTree
			ExpressionASTAnalyse expanalyse = new ExpressionASTAnalyse();
			
			RootAST node = expanalyse.analyse(child2.getBlockContent());
			if(node==null) {
				//logger.info("表达式解析失败."+child2.getBlockContent());
				consoleLog("表达式解析失败."+child2.getBlockContent());
				return false;
			}else {
				//logger.info("表达式解析成功:"+child2.getBlockContent());
				consoleLog("表达式解析成功:"+child2.getBlockContent());
			}
			
			//在控制台打印表达式解释的结果
			node.show(0);
			//Window.alert(node.getStrexpression());
			
			ExpressionEval expressioneval = new ExpressionEval();
			VarValue b3 = expressioneval.eval(node, child2, classtable);

			if (b3 != null) {
				System.out.println("计算结果：" + b3.toString());
				consoleLog("计算结果：" + b3.toString());
				return true;
			} else {
				System.out.println("计算失败！" + node.getStrexpression());
				consoleLog("计算失败！" + node.getStrexpression());
				return false;
			}

		}

		// �����BaseBlockNode�Ľڵ����ͣ���ô��˳��ִ�и��ӽڵ�
		// ������ͬ���͵Ľڵ��ִ�з������ڶ�Ӧ���͵Ľڵ��н��ж���
		for (BasicBlock child : child2.getChildren()) {
			// child.setParent(this);
			// child.execute();
			// ���child�� MethodBlock,���˳�ִ��
			if(child instanceof MethodBlock) {
				//MethodBlock��������Ϊһ��children��ִ��
				//��Ҫ��Ϊһ�����ڵ�������
				//MethodBlock��Ϊһ��Child��ʱ�򲻿�ִ��
				continue;
			}
			
			if (child instanceof DOWHILEBlock) {
				DOWHILEBlockEvaluator doblockeval = new DOWHILEBlockEvaluator();
				boolean b1 = doblockeval.execute((DOWHILEBlock) child,classtable);
				//logger.info("doblock ��������" + b1);
				if (b1) {
					// �ӽڵ�ִ�гɹ�������
					continue;
				} else {
					return false;
				}

			}
			if (child instanceof WHILEBlock) {
				WHILEBlockEvaluator whileblockeval = new WHILEBlockEvaluator();
				boolean b2 = whileblockeval.execute((WHILEBlock) child,classtable);
				//logger.info("while block ������:" + b2);
				if (b2) {
					continue;
				} else {
					return false;
				}
			}
			if (child instanceof IFBlock) {
				IFBlockEvaluator ifeval = new IFBlockEvaluator();
				boolean b3 = ifeval.execute((IFBlock) child,classtable);
				//logger.info("if block ������" + b3);
				if (b3) {
					continue;
				} else {
					return false;
				}
			}
			if (child instanceof FORBlock) {
				FORBlockEvaluator foreval = new FORBlockEvaluator();
				boolean b4 = foreval.execute((FORBlock) child,classtable);
				//logger.info("for block ������" + b4);
				if (b4) {
					continue;
				} else {
					return false;
				}
			}
			if (execute(child,classtable)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[BlockEvaluator]:" + message );
	}-*/;
	
}
