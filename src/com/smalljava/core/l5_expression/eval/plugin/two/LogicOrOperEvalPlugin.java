package com.smalljava.core.l5_expression.eval.plugin.two;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.eval.operelement.BooleanValue;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.two.DualOperDataOperElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * MEMO ִ�мӷ�����
 * @author liujunsong
 *
 */
public class LogicOrOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(LogicOrOperEvalPlugin.class);
	
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// �ӷ�����
			if (oper.getOpercode().equals("||")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				// ����һ���µ�������
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
				VarValue rightvar = eeval.eval(rightelement, vartable, classtable);
				if (leftvar == null || rightvar == null) {
					logger.error("�ӷ�����ʧ�ܣ�����Ϊnull");
					return null;
				}

				if (leftvar.getVartype() == null) {
					logger.error("�����߼������������������Ϊnull");
					return null;
				}
				if (leftvar.getVartype().equals("boolean")) {
					BooleanValue intoper = new BooleanValue(leftvar.getVarsvalue());
					intoper.doOr(rightvar.getVarsvalue());
					return intoper;
				}
				logger.error("��ERROR���߼�OR�Ų��������˲�֧�ֵ��������ͣ�" + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}


}
