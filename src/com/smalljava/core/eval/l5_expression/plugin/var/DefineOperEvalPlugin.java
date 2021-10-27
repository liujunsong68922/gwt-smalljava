package com.smalljava.core.eval.l5_expression.plugin.var;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.var.VarDefineOperElement;
import com.smalljava.core.eval.l5_expression.IExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class DefineOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(DefineOperEvalPlugin.class);
	@Override
	public VarValue eval(RootAST root, IVarTable vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if(root == null || vartable == null ) {
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
			}else {
				logger.error("[error] vartable define false.");
				return null;
			}
		}
		return null;
	}

}
