package com.smalljava.core.eval.l5_expression.worker_plugin.constvalue;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.constvalue.ConstNumberElement;
import com.smalljava.core.commonvo.l5_expression.constvalue.ConstStringElement;
import com.smalljava.core.eval.l5_expression.ISmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;
public class ConstEvalPlugin implements ISmallJavaExpressionEval {
	Logger logger = LoggerFactory.getLogger(ConstEvalPlugin.class);
	@Override
	public VarValue eval(RootAST root,
			IVarTable vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if(root instanceof ConstNumberElement) {
			ConstNumberElement const1 = (ConstNumberElement)root;
			VarValue v1 = new VarValue();
			v1.setVarname("");
			v1.setVartype("double");
			v1.setVarsvalue(const1.getDatavalue());
			return v1;
		}
		if(root instanceof ConstStringElement) {
			ConstNumberElement const2 = (ConstNumberElement)root;
			VarValue v1 = new VarValue();
			v1.setVarname("");
			v1.setVartype("String");
			v1.setVarsvalue(const2.getDatavalue());
			return v1;			
		}
		logger.error("[error]unknown element type.");
		return null;
	}
	
}
