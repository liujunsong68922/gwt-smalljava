package com.smalljava.core.eval.l5_expression.plugin.two;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.two.DualOperDataOperElement;
import com.smalljava.core.eval.l5_expression.ExpressionEval;
import com.smalljava.core.eval.l5_expression.IExpressionEval;
import com.smalljava.core.eval.l5_expression.operelement.BooleanValue;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;
public class LogicAndOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(LogicAndOperEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if (root == null || vartable == null ) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			if (oper.getOpercode().equals("&&")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classenv,oopenv);
				VarValue rightvar = eeval.eval(rightelement, vartable, classenv,oopenv);
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
