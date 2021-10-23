package com.smalljava.core.l3_expression.eval.plugin.two;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l3_expression.eval.ExpressionEval;
import com.smalljava.core.l3_expression.eval.IExpressionEval;
import com.smalljava.core.l3_expression.eval.operelement.BooleanValue;
import com.smalljava.core.l3_expression.vo.RootAST;
import com.smalljava.core.l3_expression.vo.two.DualOperDataOperElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;
public class LogicAndOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(LogicAndOperEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			if (oper.getOpercode().equals("&&")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
				VarValue rightvar = eeval.eval(rightelement, vartable, classtable);
				if (leftvar == null || rightvar == null) {
					logger.error(" leftvar or rightvar is null");
					return null;
				}

				if (leftvar.getVartype() == null) {
					logger.error(" leftvar vartype is Ϊnull");
					return null;
				}
				if (leftvar.getVartype().equals("boolean")) {
					BooleanValue intoper = new BooleanValue(leftvar.getVarsvalue());
					intoper.doAnd(rightvar.getVarsvalue());
					return intoper;
				}
				logger.error("[error]unsupported vartype:" + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}
}
