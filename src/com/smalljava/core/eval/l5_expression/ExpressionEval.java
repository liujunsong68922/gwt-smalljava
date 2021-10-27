package com.smalljava.core.eval.l5_expression;

import java.util.HashMap;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.MiddleAST;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.atom.AtomElement;
import com.smalljava.core.commonvo.l5_expression.constvalue.AbstractConstDataElement;
import com.smalljava.core.commonvo.l5_expression.one.LogicNotOperElement;
import com.smalljava.core.commonvo.l5_expression.oop.ImportExpressionElement;
import com.smalljava.core.commonvo.l5_expression.oop.NewOperElement;
import com.smalljava.core.commonvo.l5_expression.oop.ObjectCallOperElement;
import com.smalljava.core.commonvo.l5_expression.two.DualOperDataOperElement;
import com.smalljava.core.commonvo.l5_expression.var.VarDataElement;
import com.smalljava.core.commonvo.l5_expression.var.VarDefineOperElement;
import com.smalljava.core.commonvo.l5_expression.var.VarSetOperElement;
import com.smalljava.core.eval.l5_expression.plugin.atom.AtomEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.constvalue.ConstEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.one.LogicNotPlugin;
import com.smalljava.core.eval.l5_expression.plugin.oop.ImportExpressionPlugin;
import com.smalljava.core.eval.l5_expression.plugin.oop.NewOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.oop.ObjectCallEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.LogicAndOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.LogicEqualsOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.LogicGreaterEqualOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.LogicGreaterOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.LogicLitterEqualOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.LogicLitterOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.LogicNotEqualOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.LogicOrOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.MathAddOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.MathDeAddOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.MathDevideOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.two.MathMultiOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.var.DefineOperEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.var.VarSetEvalPlugin;
import com.smalljava.core.eval.l5_expression.plugin.var.VariableEvalPlugin;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class ExpressionEval implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(ExpressionEval.class);

	private static HashMap<String, IExpressionEval> evalmap = new HashMap<String, IExpressionEval>();

	public VarValue eval(RootAST root, 
			IVarTable vartable,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if (vartable == null) {
			logger.error("[Argument error],vartable is null.begin show");
			root.show(0);
			return null;
		}

		if (root instanceof MiddleAST) {
			MiddleAST middle = (MiddleAST) root;
			RootAST child = middle.getChildren().get(0);
			return eval(child, vartable, classenv,oopenv);
		}

		if (root instanceof AbstractConstDataElement) {
			ConstEvalPlugin consteval = new ConstEvalPlugin();
			return consteval.eval(root, vartable, classenv,oopenv);
		}

		if (root instanceof VarDataElement) {
			VariableEvalPlugin vareval = new VariableEvalPlugin();
			return vareval.eval(root, vartable, classenv,oopenv);
		}

		if (root instanceof NewOperElement) {
			NewOperEvalPlugin neweval = new NewOperEvalPlugin();
			return neweval.eval(root, vartable, classenv,oopenv);
		}

		if (root instanceof VarDefineOperElement) {
			DefineOperEvalPlugin defineeval = new DefineOperEvalPlugin();
			return defineeval.eval(root, vartable, classenv,oopenv);
		}

		if (root instanceof AtomElement) {
			AtomEvalPlugin atomeval = new AtomEvalPlugin();
			return atomeval.eval(root, vartable, classenv,oopenv);
		}

		if (root instanceof LogicNotOperElement) {
			LogicNotPlugin logicnoteval = new LogicNotPlugin();
			return logicnoteval.eval(root, vartable, classenv,oopenv);
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			logger.info("opercode:" + oper.getOpercode());
			IExpressionEval eeval = this.getEvalPluginByOpercode(oper.getOpercode());
			if (eeval == null) {
				logger.error("Cannot find oper expressioneval:" + oper.getOpercode());
				return null;
			}
			return eeval.eval(root, vartable, classenv,oopenv);
		}

		if (root instanceof VarSetOperElement) {
			VarSetEvalPlugin varseteval = new VarSetEvalPlugin();
			return varseteval.eval(root, vartable, classenv,oopenv);
		}

		if (root instanceof ObjectCallOperElement) {
			ObjectCallOperElement objectcall = (ObjectCallOperElement) root;
			ObjectCallEvalPlugin objcalleval = new ObjectCallEvalPlugin();
			return objcalleval.eval(root, vartable, classenv,oopenv);
		}

		if (root instanceof ImportExpressionElement) {
			ImportExpressionPlugin plugin = new ImportExpressionPlugin();
			return plugin.eval(root, vartable, classenv, oopenv);
		}
		
		logger.error("[ERROR]---->Not Found Suitable Eval plugin{" + root.getShowString(0) + "}");
		logger.error("[ERROR]---->未找到合适的执行插件{" + root.getShowString(0) + "}");
		
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
