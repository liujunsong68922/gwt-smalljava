package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.ExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.operelement.DoubleValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.operelement.FloatValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.operelement.IntegerValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.operelement.LongValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.two.DualOperDataOperElement;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

/**
 * MEMO ִ�м�������
 * @author liujunsong
 *
 */
public class MathDeAddOperEvalPlugin implements IExpressionEval {


	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// ��������
			if (oper.getOpercode().equals("-")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				// ����һ���µ�������
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
				VarValue rightvar = eeval.eval(rightelement, vartable, classtable);
				if (leftvar == null || rightvar == null) {
					System.out.println("��������ʧ�ܣ�����Ϊnull");
					return null;
				}

				if (leftvar.getVartype() == null) {
					System.out.println("�����߼������������������Ϊnull");
					return null;
				}
				if (leftvar.getVartype().equals("int")) {
					IntegerValue intoper = new IntegerValue(leftvar.getVarsvalue());
					intoper.doDeAdd(rightvar.getVarsvalue());
					return intoper;
				}
				if (leftvar.getVartype().equals("long")) {
					LongValue longoper = new LongValue(leftvar.getVarsvalue());
					// �ѵڶ����ڵ���ַ�������ȥ
					System.out.println("Long���������:" + rightvar.getVarsvalue());
					longoper.doDeAdd(rightvar.getVarsvalue());
					return longoper;
				}
				if (leftvar.getVartype().equals("float")) {
					FloatValue floatoper = new FloatValue(leftvar.getVarsvalue());
					System.out.println("Float���������:" + rightvar.getVarsvalue());
					floatoper.doDeAdd(rightvar.getVarsvalue());
					return floatoper;
				}
				if (leftvar.getVartype().equals("double")) {
					DoubleValue doubleoper = new DoubleValue(leftvar.getVarsvalue());
					;
					// �ѵڶ����ڵ���ַ�������ȥ
					System.out.println("Double���������:" + rightvar.getVarsvalue());
					doubleoper.doDeAdd(rightvar.getVarsvalue());
					return doubleoper;
				}
				System.out.println("��ERROR�����Ų��������˲�֧�ֵ��������ͣ�" + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}


}
