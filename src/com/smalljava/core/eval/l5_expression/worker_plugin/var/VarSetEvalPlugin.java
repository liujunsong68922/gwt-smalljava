package com.smalljava.core.eval.l5_expression.worker_plugin.var;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.var.VarSetOperElement;
import com.smalljava.core.eval.l5_expression.SmallJavaExpressionEval;
import com.smalljava.core.eval.l5_expression.ISmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class VarSetEvalPlugin implements ISmallJavaExpressionEval {
	private Logger logger = LoggerFactory.getLogger(VarSetEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if (root == null) {
			logger.error("[ArgumentError] root is null");
			return null;
		}
		if (vartable == null) {
			logger.error("[ArgumentError] vartable is null");
			return null;
		}
//		if (classtable == null) {
//			logger.error("[ArgumentError] classtable is null");
//			return null;
//		}
		if (root instanceof VarSetOperElement) {
			VarSetOperElement element = (VarSetOperElement) root;
			RootAST leftelement = element.getChildren().get(0);
			RootAST rightelement = element.getChildren().get(1);
			SmallJavaExpressionEval eeval = new SmallJavaExpressionEval();
			VarValue leftvar = eeval.eval(leftelement, vartable, classenv,oopenv);
			VarValue rightvar = eeval.eval(rightelement, vartable, classenv,oopenv);
			if (leftvar == null) {
				logger.error("Set Oper failed.leftvar is null");
				return null;
			}
			if (rightvar == null) {
				logger.error("Set Oper failed.rightvar is null");
				return null;
			}
			leftvar.setVarsvalue(rightvar.getVarsvalue());
			return leftvar;
		}
		return null;
	}
}
