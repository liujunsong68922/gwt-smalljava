package com.smalljava.core.l3_expression.eval.plugin.constvalue;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l3_expression.eval.IExpressionEval;
import com.smalljava.core.l3_expression.vo.RootAST;
import com.smalljava.core.l3_expression.vo.constvalue.ConstNumberElement;
import com.smalljava.core.l3_expression.vo.constvalue.ConstStringElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;
public class ConstEvalPlugin implements IExpressionEval {
	Logger logger = LoggerFactory.getLogger(ConstEvalPlugin.class);
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
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
