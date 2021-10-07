package com.liu.gwt.gwt_smalljava.level5_expression.test;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.BlockAnalyse;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.ExpressionASTAnalyse;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.ExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.impl.ClassTableImpl;

public class TestExpressionEval {

	@SuppressWarnings("static-access")
	public static void main(String args[]) {
		String s1="{int i=0}";
		BasicBlock closedblock = new BasicBlock("",s1,null);
		BlockAnalyse ba = new BlockAnalyse();
		boolean isok = ba.analyse(closedblock);		
		System.out.println("result:"+isok);
		ExpressionASTAnalyse eanlyse = new ExpressionASTAnalyse();
		
		String s2="int ii=1+2";
		RootAST root = eanlyse.analyse(s2);
		if(root == null) {
			System.out.println("ast ʧ��");
			return;
		}else {
			System.out.println("ast �ɹ�");
			root.show(0);
		}
		
		IClassTable classtable = new ClassTableImpl();
		boolean b1 = closedblock.defineVar("i","int");
		ExpressionEval eval = new ExpressionEval();
		VarValue vv = eval.eval(root, closedblock, classtable);
		if(vv == null) {
			System.out.println("���ʽ����ʧ��:vv is null");
		}else {
			System.out.println(vv.toString());
		}
		
	}
}
