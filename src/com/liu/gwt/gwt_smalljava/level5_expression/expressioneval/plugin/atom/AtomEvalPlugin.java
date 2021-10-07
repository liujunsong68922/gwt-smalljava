package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.atom;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.ExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.atom.AtomElement;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

//import com.liu.smalljavav2.common.VarValue;
//import com.liu.smalljavav2.level5_expression.expressioneval.ExpressionEval;
//import com.liu.smalljavav2.level5_expression.expressioneval.IExpressionEval;
//import com.liu.smalljavav2.level5_expression.expressionvo.RootAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.atom.AtomElement;
//import com.liu.smalljavav2.space.IClassTable;
//import com.liu.smalljavav2.space.IVarTable;

/**
 * MEMO:����ִ�У������ʽ
 * @author liujunsong
 *
 */
public class AtomEvalPlugin implements IExpressionEval {

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if(root ==null || vartable == null || classtable ==null) {
			return null;
		}
		if(root instanceof AtomElement) {
			AtomElement atom = (AtomElement) root;
			if(atom.getChildren().size()==0) {
				System.out.println("��ERROR������atomִ������ȴû��child");
			}
			//ֻ�ܵ�һ��child,�����Ĳ���
			RootAST child = root.getChildren().get(0);
			//�ݹ���������м���
			ExpressionEval eval = new ExpressionEval();
			return eval.eval(child, vartable, classtable);
		}
		return null;
	}

}
