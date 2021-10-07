package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.var;

import com.google.gwt.user.client.Window;
import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.ExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarSetOperElement;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

//import ch.qos.logback.classic.Logger;

public class VarSetEvalPlugin implements IExpressionEval {
	//private Logger logger = LoggerFactory.getLogger(VarSetEvalPlugin.class);
	/**
	 * MEMO:������ֵ�ļ�������
	 */
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if(root == null ) {
			Window.alert("[ArgumentError] root is null" );
			return null;
		}

		if( vartable == null ) {
			Window.alert("[ArgumentError] vartable is null");
			return null;
		}
		
		if( classtable == null) {
			Window.alert("[ArgumentError] classtable is null");
			return null;
		}
		
		if(root instanceof VarSetOperElement) {
			VarSetOperElement element = (VarSetOperElement) root;
			RootAST leftelement = element.getChildren().get(0);
			RootAST rightelement = element.getChildren().get(1);
			//����һ���µ�������
			ExpressionEval eeval = new ExpressionEval();
			VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
			VarValue rightvar = eeval.eval(rightelement, vartable,classtable);
			
			//TODO:�����쳣��Ϣ���ж�
			if(leftvar == null) {
				System.out.println("Set Oper failed.leftvar is null");
				return null;
			}
			if(rightvar == null) {
				System.out.println("Set Oper failed.rightvar is null");
				return null;
				
			}
			//������д���������
			//TODO:���б�Ҫ����������ת��������
			//��һ������һ����ɡ�
			leftvar.setVarsvalue(rightvar.getVarsvalue());
			return leftvar;
		}
		return null;
	}

}
