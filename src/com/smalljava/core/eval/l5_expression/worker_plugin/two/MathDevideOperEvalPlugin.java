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

public class MathDevideOperEvalPlugin implements ISmallJavaExpressionEval {
	private Logger logger = LoggerFactory.getLogger(MathDevideOperEvalPlugin.class);
	
	@Override
	public VarValue eval(RootAST root, IVarTable vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if (root == null || vartable == null ) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			
			if (oper.getOpercode().equals("/")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				
				SmallJavaExpressionEval eeval = new SmallJavaExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classenv,oopenv);
				VarValue rightvar = eeval.eval(rightelement, vartable, classenv,oopenv);
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
