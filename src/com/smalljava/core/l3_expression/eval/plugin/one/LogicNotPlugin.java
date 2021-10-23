package com.smalljava.core.l3_expression.eval.plugin.one;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l3_expression.eval.IExpressionEval;
import com.smalljava.core.l3_expression.eval.plugin.constvalue.ConstEvalPlugin;
import com.smalljava.core.l3_expression.vo.RootAST;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class LogicNotPlugin implements IExpressionEval {
	Logger logger = LoggerFactory.getLogger(LogicNotPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		// TODO Auto-generated method stub
		logger.error("This method is not supported now.");
		return null;
	}

}
