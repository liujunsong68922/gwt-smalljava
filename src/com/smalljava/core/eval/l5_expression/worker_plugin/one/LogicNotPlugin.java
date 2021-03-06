package com.smalljava.core.eval.l5_expression.worker_plugin.one;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.eval.l5_expression.ISmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class LogicNotPlugin implements ISmallJavaExpressionEval {
	Logger logger = LoggerFactory.getLogger(LogicNotPlugin.class);

	@Override
	public VarValue eval(RootAST root, 
			IVarTable vartable, 
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		// TODO Auto-generated method stub
		logger.error("This method is not supported now.");
		return null;
	}

}
