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
public class LogicLitterOperEvalPlugin implements IExpressionEval {

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		consoleLog("enter LogicLitterOperEvalPlugin.");
		if (root == null || vartable == null || classtable == null) {
			consoleLog("argument error");
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			consoleLog("is DualOperDataOperElement");
			if (oper.getOpercode().equals("<")) {
				consoleLog("is <");
				
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				// ����һ���µ�������
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
				VarValue rightvar = eeval.eval(rightelement, vartable, classtable);
				consoleLog("leftvar:"+leftvar.toString());
				consoleLog("rightvar:"+rightvar.toString());
				
				if (leftvar == null || rightvar == null) {
					System.out.println("�ӷ�����ʧ�ܣ�����Ϊnull");
					consoleLog("[ERROR] left or right is null");
					return null;
				}

				if (leftvar.getVartype() == null) {
					System.out.println("�����߼������������������Ϊnull");
					consoleLog("[ERROR]leftvar type is null ");
					return null;
				}
				if (leftvar.getVartype().equals("int")) {
					IntegerValue intoper = new IntegerValue(leftvar.getVarsvalue());
					boolean b1 = intoper.doLitter(rightvar.getVarsvalue());
					VarValue v1 = new VarValue();
					v1.setVarname("");
					v1.setVartype("boolean");
					v1.setVarsvalue(""+b1);
					return v1;
					
				}
				if (leftvar.getVartype().equals("long")) {
					LongValue longoper = new LongValue(leftvar.getVarsvalue());
					System.out.println("Long���������:" + rightvar.getVarsvalue());
					boolean b2 = longoper.doLitter(rightvar.getVarsvalue());
					VarValue v2 = new VarValue();
					v2.setVarname("");
					v2.setVartype("boolean");
					v2.setVarsvalue(""+b2);
					return v2;
				
				}
				if (leftvar.getVartype().equals("float")) {
					FloatValue floatoper = new FloatValue(leftvar.getVarsvalue());
					System.out.println("Float���������:" + rightvar.getVarsvalue());
					boolean b3 = floatoper.doLitter(rightvar.getVarsvalue());
					VarValue v3 = new VarValue();
					v3.setVarname("");
					v3.setVartype("boolean");
					v3.setVarsvalue(""+b3);
					return v3;
				}
				if (leftvar.getVartype().equals("double")) {
					DoubleValue doubleoper = new DoubleValue(leftvar.getVarsvalue());
					;
					System.out.println("Double���������:" + rightvar.getVarsvalue());
					boolean b4 = doubleoper.doLitter(rightvar.getVarsvalue());
					VarValue v4 = new VarValue();
					v4.setVarname("");
					v4.setVartype("boolean");
					v4.setVarsvalue(""+b4);
					return v4;

				}
				System.out.println("��ERROR��< ���������˲�֧�ֵ��������ͣ�" + leftvar.getVartype());
				consoleLog("[ERROR]:"+leftvar.getVartype());
				return null;
			}else {
				consoleLog("opercode is not <");
				return null;
			}
		}else {
			consoleLog("this is not  DualOperDataOperElement");
			return null;
		}
	}

	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[LogicLitterEvalPlugin]:" + message );
	}-*/;	
}
