package com.smalljava.core.eval.l5_expression.worker_plugin.var;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.var.VarDataElement;
import com.smalljava.core.eval.l5_expression.ISmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class VariableEvalPlugin implements ISmallJavaExpressionEval{
	private Logger logger = LoggerFactory.getLogger(VariableEvalPlugin.class);
	
	@Override
	public VarValue eval(RootAST root, IVarTable vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if(root == null) {
			return null;
		}
		
		if(root instanceof VarDataElement) {
			VarDataElement varele = (VarDataElement) root;
			
			String varname = varele.getVarname();
			if(! vartable.isValid(varname)) {
				logger.error("[error] varname is invalid:"+varname);
				return null;
			}
			VarValue varvalue = vartable.getVarValue(varname);
			return varvalue;
		}
		return null;
	}
}
