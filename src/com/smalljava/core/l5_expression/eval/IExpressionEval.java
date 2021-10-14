package com.smalljava.core.l5_expression.eval;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public interface IExpressionEval {
	public VarValue eval(RootAST root,IVarTable vartable,IClassTable classtable);
}
