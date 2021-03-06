package com.smalljava.core.eval.l5_expression.worker_plugin.two;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.two.DualOperDataOperElement;
import com.smalljava.core.eval.l5_expression.SmallJavaExpressionEval;
import com.smalljava.core.eval.l5_expression.ISmallJavaExpressionEval;
import com.smalljava.core.eval.l5_expression.worker_plugin.operelement.DoubleValue;
import com.smalljava.core.eval.l5_expression.worker_plugin.operelement.FloatValue;
import com.smalljava.core.eval.l5_expression.worker_plugin.operelement.IntegerValue;
import com.smalljava.core.eval.l5_expression.worker_plugin.operelement.LongValue;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class LogicLitterEqualOperEvalPlugin implements ISmallJavaExpressionEval {
	private Logger logger = LoggerFactory.getLogger(LogicLitterEqualOperEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if (root == null || vartable == null ) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			if (oper.getOpercode().equals("<=")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				SmallJavaExpressionEval eeval = new SmallJavaExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classenv,oopenv);
				VarValue rightvar = eeval.eval(rightelement, vartable, classenv,oopenv);
				if (leftvar == null || rightvar == null) {
					logger.error(" leftvar or rightvar is null");
					return null;
				}

				if (leftvar.getVartype() == null) {
					logger.error(" leftvar vartype is null");
					return null;
				}
				if (leftvar.getVartype().equals("int")) {
					IntegerValue intoper = new IntegerValue(leftvar.getVarsvalue());
					boolean b1 = intoper.doLE(rightvar.getVarsvalue());
					VarValue varvalue1 = new VarValue();
					varvalue1.setVarname("");
					varvalue1.setVartype("boolean");
					varvalue1.setVarsvalue("" + b1);
					return varvalue1;

				}
				if (leftvar.getVartype().equals("long")) {
					LongValue longoper = new LongValue(leftvar.getVarsvalue());
					// ???????????????????????????????????????????????????????
					logger.error("Long value :" + rightvar.getVarsvalue());
					boolean b2 = longoper.doLE(rightvar.getVarsvalue());
					VarValue varvalue2 = new VarValue();
					varvalue2.setVarname("");
					varvalue2.setVartype("boolean");
					varvalue2.setVarsvalue("" + b2);

					return varvalue2;
				}
				if (leftvar.getVartype().equals("float")) {
					FloatValue floatoper = new FloatValue(leftvar.getVarsvalue());
					logger.error("Float value :" + rightvar.getVarsvalue());
					boolean b3 = floatoper.doLE(rightvar.getVarsvalue());
					VarValue varvalue3 = new VarValue();
					varvalue3.setVarname("");
					varvalue3.setVartype("boolean");
					varvalue3.setVarsvalue("" + b3);
					return varvalue3;

				}
				if (leftvar.getVartype().equals("double")) {
					DoubleValue doubleoper = new DoubleValue(leftvar.getVarsvalue());
					logger.error("Double value :" + rightvar.getVarsvalue());
					boolean b4 = doubleoper.doLE(rightvar.getVarsvalue());
					VarValue varvalue4 = new VarValue();
					varvalue4.setVarname("");
					varvalue4.setVartype("boolean");
					varvalue4.setVarsvalue("" + b4);
					return varvalue4;
				}
				logger.error("[error]unsupported vartype: " + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}

}
