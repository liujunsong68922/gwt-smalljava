package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval;

import java.util.HashMap;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.atom.AtomEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.constvalue.ConstEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.obj.ObjectCallEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.one.LogicNotPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.LogicAndOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.LogicEqualsOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.LogicGreaterEqualOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.LogicGreaterOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.LogicLitterEqualOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.LogicLitterOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.LogicNotEqualOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.LogicOrOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.MathAddOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.MathDeAddOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.MathDevideOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.two.MathMultiOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.var.DefineOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.var.NewOperEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.var.VarSetEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.var.VariableEvalPlugin;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.MiddleAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.atom.AtomElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.constvalue.AbstractConstDataElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.obj.ObjectCallOperElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.one.LogicNotOperElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.two.DualOperDataOperElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.NewOperElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarDataElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarDefineOperElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarSetOperElement;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

public class ExpressionEval implements IExpressionEval {
	private static HashMap<String, IExpressionEval> evalmap = new HashMap<String, IExpressionEval>();

	/**
	 * 所有RootAST节点的计算结果均以String格式返回
	 */
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		// Part1:无子节点的叶子部分节点的评估计算规则
		// step0.判斷是不是中间节点
		// Part1:no child leaf point compute rule
		// step0: check whether middle AST point
		if (root instanceof MiddleAST) {
			consoleLog("This is MiddleAST");
			MiddleAST middle = (MiddleAST) root;
			RootAST child = middle.getChildren().get(0);
			// 跳过MiddleAST节点，递归调用
			// Bypass Middle point, compute its first children
			// MiddleAST has only one child.
			return eval(child, vartable, classtable);
		}

		// step1.如果root是常量节点，则返回常量
		// step1.if root is constdata,return const data.
		if (root instanceof AbstractConstDataElement) {
			consoleLog("This is AbstractConstDataElement");
			ConstEvalPlugin consteval = new ConstEvalPlugin();
			return consteval.eval(root, vartable, classtable);
		}

		// step2.如果是root是一个变量节点，则返回变量结算结果
		// 如果是由赋值运算符推动，则左面的变量作为参数使用，而不作为变量使用
		// if root is a vardata point, return var compute result.
		if (root instanceof VarDataElement) {
			consoleLog("This is VarDataElement");
			VariableEvalPlugin vareval = new VariableEvalPlugin();
			return vareval.eval(root, vartable, classtable);
		}

		// step3.如果root是一个new操作符，返回新创建的对象变量
		if (root instanceof NewOperElement) {
			consoleLog("This is NewOperElement");
			NewOperEvalPlugin neweval = new NewOperEvalPlugin();
			return neweval.eval(root, vartable, classtable);
		}

		// step4.如果root是一个变量定义操作符，则执行变量定义
		if (root instanceof VarDefineOperElement) {
			consoleLog("This is VarDefineOperElement");
			DefineOperEvalPlugin defineeval = new DefineOperEvalPlugin();
			return defineeval.eval(root, vartable, classtable);
		}

		// step5.如果root是一个Atom,则调用Atom执行器执行
		if (root instanceof AtomElement) {
			consoleLog("This is AtomElement");
			AtomEvalPlugin atomeval = new AtomEvalPlugin();
			return atomeval.eval(root, vartable, classtable);
		}

		// Part2. 一元运算符的支持部分,暂时只支持逻辑取否的运算符
		// step6.逻辑取反的操作支持
		if (root instanceof LogicNotOperElement) {
			consoleLog("This is LogicNotOperElement");
			LogicNotPlugin logicnoteval = new LogicNotPlugin();
			return logicnoteval.eval(root, vartable, classtable);
		}

		// Part3. 二元运算符号的部分
		if (root instanceof DualOperDataOperElement) {
			consoleLog("This is DualOperDataOperElement");
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// 不同的运算符，调用不同的处理器
			IExpressionEval eeval = this.getEvalPluginByOpercode(oper.getOpercode());
			if(eeval == null) {
				System.out.println("Cannot find oper expressioneval:"+oper.getOpercode());
				consoleLog("Cannot find oper expressioneval:"+oper.getOpercode());
				return null;
			}
			return eeval.eval(root, vartable, classtable);
		}

		// Part3.二元运算符的支持部分，这一部分也全部依据表达式重写
		// step7 赋值运算符的支持
		if (root instanceof VarSetOperElement) {
			consoleLog("This is VarSetOperElement");
			VarSetEvalPlugin varseteval = new VarSetEvalPlugin();
			return varseteval.eval(root, vartable, classtable);
		}

		// Part4 对象调用的部分
		if (root instanceof ObjectCallOperElement) {
			consoleLog("This is ObjectCallOperElement");
			ObjectCallOperElement objcall = (ObjectCallOperElement) root;
			consoleLog("obj/method:"+ objcall.getObjname()+"/"+objcall.getMethodname());
			
			ObjectCallEvalPlugin plugin1 = new ObjectCallEvalPlugin();
			
			return plugin1.eval(root, vartable, classtable);
		}
				
		// Part5 如果执行都这里，说明上面的逻辑都没有命中
		// 这种情况下是属于执行错误
		System.out.println("---->执行出错了。");
		consoleLog("---->执行出错了。");
		root.show(0);
		return null;
	}

	/**
	 * MEMO���������������ȡ��Ӧ�ļ��㴦����
	 * @param opercode
	 * @return
	 */
	private IExpressionEval getEvalPluginByOpercode(String opercode) {
		initEvalMap();
		return evalmap.get(opercode);
	}

	private static void initEvalMap() {
		if (evalmap.size() == 0) {
			//�ȼ������������
			evalmap.put("+", new MathAddOperEvalPlugin());
			evalmap.put("-", new MathDeAddOperEvalPlugin());
			evalmap.put("*", new MathMultiOperEvalPlugin());
			evalmap.put("/", new MathDevideOperEvalPlugin());

			//�ټ����߼������
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

	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[ExpressionEval]:" + message );
	}-*/;

}
