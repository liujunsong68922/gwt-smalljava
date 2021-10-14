package com.smalljava.core.l5_expression.eval.plugin.var;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.var.VarSetOperElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

//import ch.qos.logback.classic.Logger;

public class VarSetEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(VarSetEvalPlugin.class);
	/**
	 * MEMO:������ֵ�ļ�������
	 */
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if(root == null ) {
			logger.error("[ArgumentError] root is null" );
			return null;
		}

		if( vartable == null ) {
			logger.error("[ArgumentError] vartable is null");
			return null;
		}
		
		if( classtable == null) {
			logger.error("[ArgumentError] classtable is null");
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
				logger.error("Set Oper failed.leftvar is null");
				return null;
			}
			if(rightvar == null) {
				logger.error("Set Oper failed.rightvar is null");
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
