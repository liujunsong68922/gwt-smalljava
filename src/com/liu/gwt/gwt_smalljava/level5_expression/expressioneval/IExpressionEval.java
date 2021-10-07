package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

//import com.liu.smalljavav2.common.VarValue;
//import com.liu.smalljavav2.level5_expression.expressionvo.RootAST;
//import com.liu.smalljavav2.space.IClassTable;
//import com.liu.smalljavav2.space.IVarTable;

public interface IExpressionEval {
	public VarValue eval(RootAST root,IVarTable vartable,IClassTable classtable);
}
