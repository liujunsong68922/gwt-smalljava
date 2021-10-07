package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.var;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarDefineOperElement;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

public class DefineOperEvalPlugin implements IExpressionEval {

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		//nullֵ���
		if(root == null || vartable == null || classtable == null) {
			return null;
		}
		
		if(root instanceof VarDefineOperElement) {
			VarDefineOperElement defineoper = (VarDefineOperElement) root;
			String datatype = defineoper.getDatatype();
			String varname = defineoper.getVarname();
			boolean oper = vartable.defineVar(varname, datatype);
			if(oper) {
				VarValue retvalue = new VarValue();
				retvalue.setVartype(datatype);
				retvalue.setVarsvalue(varname);
				return retvalue;
			}
		}
		return null;
	}

}
