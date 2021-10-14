package com.smalljava.core.l5_expression.eval.plugin.atom;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.atom.AtomElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * MEMO:评估执行（）表达式
 * @author liujunsong
 *
 */
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
				logger.error("【ERROR】调用atom执行器，却没有child");
			}
			//只管第一个child,其他的不管
			RootAST child = root.getChildren().get(0);
			//递归调用来进行计算
			ExpressionEval eval = new ExpressionEval();
			return eval.eval(child, vartable, classtable);
		}
		return null;
	}

}
