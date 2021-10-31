package com.smalljava.core.eval.l5_expression.worker_plugin.atom;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.atom.AtomElement;
import com.smalljava.core.eval.l5_expression.SmallJavaExpressionEval;
import com.smalljava.core.eval.l5_expression.ISmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;


public class AtomEvalPlugin implements ISmallJavaExpressionEval {
	private Logger logger = LoggerFactory.getLogger(AtomEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if(root ==null || vartable == null || classenv ==null) {
			return null;
		}
		if(root instanceof AtomElement) {
			AtomElement atom = (AtomElement) root;
			if(atom.getChildren().size()==0) {
				logger.error("[error] atom has no children.");
				return null;
			}
			RootAST child = root.getChildren().get(0);
			SmallJavaExpressionEval eval = new SmallJavaExpressionEval();
			return eval.eval(child, vartable, classenv,oopenv);
		}
		return null;
	}
}
