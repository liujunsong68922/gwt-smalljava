package com.smalljava.core.l5_expression.eval;

import java.util.HashMap;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.eval.plugin.atom.AtomEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.constvalue.ConstEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.one.LogicNotPlugin;
import com.smalljava.core.l5_expression.eval.plugin.oop.NewOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.oop.ObjectCallEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.LogicAndOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.LogicEqualsOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.LogicGreaterEqualOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.LogicGreaterOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.LogicLitterEqualOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.LogicLitterOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.LogicNotEqualOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.LogicOrOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.MathAddOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.MathDeAddOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.MathDevideOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.two.MathMultiOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.var.DefineOperEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.var.VarSetEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.var.VariableEvalPlugin;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.atom.AtomElement;
import com.smalljava.core.l5_expression.vo.constvalue.AbstractConstDataElement;
import com.smalljava.core.l5_expression.vo.obj.ObjectCallOperElement;
import com.smalljava.core.l5_expression.vo.one.LogicNotOperElement;
import com.smalljava.core.l5_expression.vo.two.DualOperDataOperElement;
import com.smalljava.core.l5_expression.vo.var.NewOperElement;
import com.smalljava.core.l5_expression.vo.var.VarDataElement;
import com.smalljava.core.l5_expression.vo.var.VarDefineOperElement;
import com.smalljava.core.l5_expression.vo.var.VarSetOperElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class ExpressionEval implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(ExpressionEval.class);

	private static HashMap<String, IExpressionEval> evalmap = new HashMap<String, IExpressionEval>();

	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (vartable == null) {
			logger.error("[Argument error],vartable is null.begin show");
			root.show(0);
			return null;
		}

		if (root instanceof MiddleAST) {
			MiddleAST middle = (MiddleAST) root;
			RootAST child = middle.getChildren().get(0);
			return eval(child, vartable, classtable);
		}

		if (root instanceof AbstractConstDataElement) {
			ConstEvalPlugin consteval = new ConstEvalPlugin();
			return consteval.eval(root, vartable, classtable);
		}

		if (root instanceof VarDataElement) {
			VariableEvalPlugin vareval = new VariableEvalPlugin();
			return vareval.eval(root, vartable, classtable);
		}

		if (root instanceof NewOperElement) {
			NewOperEvalPlugin neweval = new NewOperEvalPlugin();
			return neweval.eval(root, vartable, classtable);
		}

		if (root instanceof VarDefineOperElement) {
			DefineOperEvalPlugin defineeval = new DefineOperEvalPlugin();
			return defineeval.eval(root, vartable, classtable);
		}

		if (root instanceof AtomElement) {
			AtomEvalPlugin atomeval = new AtomEvalPlugin();
			return atomeval.eval(root, vartable, classtable);
		}

		if (root instanceof LogicNotOperElement) {
			LogicNotPlugin logicnoteval = new LogicNotPlugin();
			return logicnoteval.eval(root, vartable, classtable);
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			logger.info("opercode:" + oper.getOpercode());
			IExpressionEval eeval = this.getEvalPluginByOpercode(oper.getOpercode());
			if (eeval == null) {
				logger.error("Cannot find oper expressioneval:" + oper.getOpercode());
				return null;
			}
			return eeval.eval(root, vartable, classtable);
		}

		if (root instanceof VarSetOperElement) {
			VarSetEvalPlugin varseteval = new VarSetEvalPlugin();
			return varseteval.eval(root, vartable, classtable);
		}

		if (root instanceof ObjectCallOperElement) {
			ObjectCallOperElement objectcall = (ObjectCallOperElement) root;
			// ���ö������ִ����
			ObjectCallEvalPlugin objcalleval = new ObjectCallEvalPlugin();
			return objcalleval.eval(root, vartable, classtable);
		}

		logger.error("---->ִ�г����ˡ�{" + root.getShowString(0) + "}");
		root.show(0);
		return null;
	}

	private IExpressionEval getEvalPluginByOpercode(String opercode) {
		initEvalMap();
		return evalmap.get(opercode);
	}

	private static void initEvalMap() {
		if (evalmap.size() == 0) {
			evalmap.put("+", new MathAddOperEvalPlugin());
			evalmap.put("-", new MathDeAddOperEvalPlugin());
			evalmap.put("*", new MathMultiOperEvalPlugin());
			evalmap.put("/", new MathDevideOperEvalPlugin());

			evalmap.put("&&", new LogicAndOperEvalPlugin());
			evalmap.put("||", new LogicOrOperEvalPlugin());
			evalmap.put(">", new LogicGreaterOperEvalPlugin());
			evalmap.put(">=", new LogicGreaterEqualOperEvalPlugin());
			evalmap.put("<", new LogicLitterOperEvalPlugin());
			evalmap.put("<=", new LogicLitterEqualOperEvalPlugin());
			evalmap.put("==", new LogicEqualsOperEvalPlugin());
			evalmap.put("!=", new LogicNotEqualOperEvalPlugin());
		}
	}
}
