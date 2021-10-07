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
 * MEMO ִ�мӷ�����
 * @author liujunsong
 *
 */
public class LogicEqualsOperEvalPlugin implements IExpressionEval {

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// �ӷ�����
			if (oper.getOpercode().equals("==")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				// ����һ���µ�������
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
				VarValue rightvar = eeval.eval(rightelement, vartable, classtable);
				if (leftvar == null || rightvar == null) {
					System.out.println("�ӷ�����ʧ�ܣ�����Ϊnull");
					return null;
				}

				if (leftvar.getVartype() == null) {
					System.out.println("�����߼������������������Ϊnull");
					return null;
				}
				if (leftvar.getVartype().equals("int")) {
					IntegerValue intoper = new IntegerValue(leftvar.getVarsvalue());
					boolean b1 = intoper.doequals(rightvar.getVarsvalue());
					VarValue v1 = new VarValue();
					v1.setVarname("");
					v1.setVartype("boolean");
					v1.setVarsvalue(""+b1);
					return v1;

				}
				if (leftvar.getVartype().equals("long")) {
					LongValue longoper = new LongValue(leftvar.getVarsvalue());
					// �ѵڶ����ڵ���ַ�������ȥ
					System.out.println("Long���������:" + rightvar.getVarsvalue());
					boolean b2 =longoper.doequals(rightvar.getVarsvalue());
					VarValue v2 = new VarValue();
					v2.setVarname("");
					v2.setVartype("boolean");
					v2.setVarsvalue(""+b2);
					return v2;
				}
				if (leftvar.getVartype().equals("float")) {
					FloatValue floatoper = new FloatValue(leftvar.getVarsvalue());
					System.out.println("Float���������:" + rightvar.getVarsvalue());
					boolean b3 =floatoper.doequals(rightvar.getVarsvalue());
					VarValue v3 = new VarValue();
					v3.setVarname("");
					v3.setVartype("boolean");
					v3.setVarsvalue(""+b3);
					return v3;
				}
				if (leftvar.getVartype().equals("double")) {
					DoubleValue doubleoper = new DoubleValue(leftvar.getVarsvalue());
					;
					// �ѵڶ����ڵ���ַ�������ȥ
					System.out.println("Double���������:" + rightvar.getVarsvalue());
					boolean b4 = doubleoper.doequals(rightvar.getVarsvalue());
					VarValue v4 = new VarValue();
					v4.setVarname("");
					v4.setVartype("boolean");
					v4.setVarsvalue(""+b4);
					return v4;
				}
				System.out.println("��ERROR��GE���������˲�֧�ֵ��������ͣ�" + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}


}
