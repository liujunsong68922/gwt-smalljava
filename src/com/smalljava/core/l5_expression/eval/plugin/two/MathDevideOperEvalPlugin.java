package com.smalljava.core.l5_expression.eval.plugin.two;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.eval.operelement.DoubleValue;
import com.smalljava.core.l5_expression.eval.operelement.FloatValue;
import com.smalljava.core.l5_expression.eval.operelement.IntegerValue;
import com.smalljava.core.l5_expression.eval.operelement.LongValue;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.two.DualOperDataOperElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class MathDevideOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(MathDevideOperEvalPlugin.class);
	
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			
			if (oper.getOpercode().equals("/")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
				VarValue rightvar = eeval.eval(rightelement, vartable, classtable);
				if (leftvar == null || rightvar == null) {
					logger.error("leftvar or rightvar is null");
					return null;
				}

				if (leftvar.getVartype() == null) {
					logger.error("leftvar vartype is null");
					return null;
				}
				if (leftvar.getVartype().equals("int")) {
					IntegerValue intoper = new IntegerValue(leftvar.getVarsvalue());
					intoper.doDevide(rightvar.getVarsvalue());
					return intoper;
				}
				if (leftvar.getVartype().equals("long")) {
					LongValue longoper = new LongValue(leftvar.getVarsvalue());
					
					logger.error("Long value :" + rightvar.getVarsvalue());
					longoper.doDevide(rightvar.getVarsvalue());
					return longoper;
				}
				if (leftvar.getVartype().equals("float")) {
					FloatValue floatoper = new FloatValue(leftvar.getVarsvalue());
					logger.error("Float value :" + rightvar.getVarsvalue());
					floatoper.doDevide(rightvar.getVarsvalue());
					return floatoper;
				}
				if (leftvar.getVartype().equals("double")) {
					DoubleValue doubleoper = new DoubleValue(leftvar.getVarsvalue());
					;
					// �ѵڶ����ڵ���ַ�������ȥ
					logger.error("Double value :" + rightvar.getVarsvalue());
					doubleoper.doDevide(rightvar.getVarsvalue());
					return doubleoper;
				}
				logger.error("[error]unsupported vartype:" + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}
}
