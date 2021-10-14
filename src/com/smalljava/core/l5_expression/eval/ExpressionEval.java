package com.smalljava.core.l5_expression.eval;

import java.util.HashMap;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.eval.plugin.atom.AtomEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.constvalue.ConstEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.obj.ObjectCallEvalPlugin;
import com.smalljava.core.l5_expression.eval.plugin.one.LogicNotPlugin;
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
import com.smalljava.core.l5_expression.eval.plugin.var.NewOperEvalPlugin;
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

	/**
	 * 所有RootAST节点的计算结果均以String格式返回
	 */
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		// 判断变量表是否为null
		if(vartable == null) {
			logger.error("[Argument error],vartable is null.begin show");
			root.show(0);
			return null;
		}

		
		// Part1:无子节点的叶子部分节点的评估计算规则
		// step0.判斷是不是中间节点
		if (root instanceof MiddleAST) {
			MiddleAST middle = (MiddleAST) root;
			RootAST child = middle.getChildren().get(0);
			// 跳过MiddleAST节点，递归调用
			return eval(child, vartable, classtable);
		}
		

		// step1.如果root是常量节点，则返回常量
		if (root instanceof AbstractConstDataElement) {
			ConstEvalPlugin consteval = new ConstEvalPlugin();
			return consteval.eval(root, vartable, classtable);
		}

		// step2.如果是root是一个变量节点，则返回变量结算结果
		// 如果是由赋值运算符推动，则左面的变量作为参数使用，而不作为变量使用
		if (root instanceof VarDataElement) {
			VariableEvalPlugin vareval = new VariableEvalPlugin();
			return vareval.eval(root, vartable, classtable);
		}

		// step3.如果root是一个new操作符，返回新创建的对象变量
		if (root instanceof NewOperElement) {
			NewOperEvalPlugin neweval = new NewOperEvalPlugin();
			return neweval.eval(root, vartable, classtable);
		}

		// step4.如果root是一个变量定义操作符，则执行变量定义
		if (root instanceof VarDefineOperElement) {
			DefineOperEvalPlugin defineeval = new DefineOperEvalPlugin();
			return defineeval.eval(root, vartable, classtable);
		}

		// step5.如果root是一个Atom,则调用Atom执行器执行
		if (root instanceof AtomElement) {
			AtomEvalPlugin atomeval = new AtomEvalPlugin();
			return atomeval.eval(root, vartable, classtable);
		}

		// Part2. 一元运算符的支持部分,暂时只支持逻辑取否的运算符
		// step6.逻辑取反的操作支持
		if (root instanceof LogicNotOperElement) {
			LogicNotPlugin logicnoteval = new LogicNotPlugin();
			return logicnoteval.eval(root, vartable, classtable);
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// 不同的运算符，调用不同的处理器
			logger.info("opercode:"+oper.getOpercode());
			IExpressionEval eeval = this.getEvalPluginByOpercode(oper.getOpercode());
			if(eeval == null) {
				logger.error("Cannot find oper expressioneval:"+oper.getOpercode());
				return null;
			}
			return eeval.eval(root, vartable, classtable);
		}

		// Part3.二元运算符的支持部分，这一部分也全部依据表达式重写
		// step7 赋值运算符的支持
		if (root instanceof VarSetOperElement) {
			VarSetEvalPlugin varseteval = new VarSetEvalPlugin();
			return varseteval.eval(root, vartable, classtable);
		}

		// Part4 对象调用的部分
		if (root instanceof ObjectCallOperElement) {
			ObjectCallOperElement objectcall = (ObjectCallOperElement) root;
			//调用对象调用执行器
			ObjectCallEvalPlugin objcalleval = new ObjectCallEvalPlugin();
			return objcalleval.eval(root, vartable, classtable);
		}
		
		// Part5 如果执行都这里，说明上面的逻辑都没有命中
		// 这种情况下是属于执行错误
		logger.error("---->执行出错了。{"+root.getShowString(0)+"}");
		root.show(0);
		return null;
	}

	/**
	 * MEMO：根据运算符来获取对应的计算处理插件
	 * @param opercode
	 * @return
	 */
	private IExpressionEval getEvalPluginByOpercode(String opercode) {
		initEvalMap();
		return evalmap.get(opercode);
	}

	private static void initEvalMap() {
		if (evalmap.size() == 0) {
			//先加入算术运算符
			evalmap.put("+", new MathAddOperEvalPlugin());
			evalmap.put("-", new MathDeAddOperEvalPlugin());
			evalmap.put("*", new MathMultiOperEvalPlugin());
			evalmap.put("/", new MathDevideOperEvalPlugin());

			//再加入逻辑运算符
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
