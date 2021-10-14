package com.smalljava.core.l5_expression.eval.plugin.var;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.var.VarDefineOperElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class DefineOperEvalPlugin implements IExpressionEval {

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		//null÷µºÏ≤È
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
