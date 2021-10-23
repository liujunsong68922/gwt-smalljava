package com.smalljava.core.l3_expression.eval.plugin.atom;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l3_expression.eval.ExpressionEval;
import com.smalljava.core.l3_expression.eval.IExpressionEval;
import com.smalljava.core.l3_expression.vo.RootAST;
import com.smalljava.core.l3_expression.vo.atom.AtomElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;


public class AtomEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(AtomEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if(root ==null || vartable == null || classtable ==null) {
			return null;
		}
		if(root instanceof AtomElement) {
			AtomElement atom = (AtomElement) root;
			if(atom.getChildren().size()==0) {
				logger.error("[error] atom has no children.");
				return null;
			}
			RootAST child = root.getChildren().get(0);
			ExpressionEval eval = new ExpressionEval();
			return eval.eval(child, vartable, classtable);
		}
		return null;
	}
}
