package com.smalljava.core.eval.l5_expression;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public interface IExpressionEval {
	public VarValue eval(RootAST root,IVarTable vartable,
				SmallJavaClassSupportEnv classenv,
				SmallJavaOopSupportEnv oopenv);
}
