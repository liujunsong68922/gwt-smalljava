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
	 * ����RootAST�ڵ�ļ���������String��ʽ����
	 */
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		// �жϱ������Ƿ�Ϊnull
		if(vartable == null) {
			logger.error("[Argument error],vartable is null.begin show");
			root.show(0);
			return null;
		}

		
		// Part1:���ӽڵ��Ҷ�Ӳ��ֽڵ�������������
		// step0.�Д��ǲ����м�ڵ�
		if (root instanceof MiddleAST) {
			MiddleAST middle = (MiddleAST) root;
			RootAST child = middle.getChildren().get(0);
			// ����MiddleAST�ڵ㣬�ݹ����
			return eval(child, vartable, classtable);
		}
		

		// step1.���root�ǳ����ڵ㣬�򷵻س���
		if (root instanceof AbstractConstDataElement) {
			ConstEvalPlugin consteval = new ConstEvalPlugin();
			return consteval.eval(root, vartable, classtable);
		}

		// step2.�����root��һ�������ڵ㣬�򷵻ر���������
		// ������ɸ�ֵ������ƶ���������ı�����Ϊ����ʹ�ã�������Ϊ����ʹ��
		if (root instanceof VarDataElement) {
			VariableEvalPlugin vareval = new VariableEvalPlugin();
			return vareval.eval(root, vartable, classtable);
		}

		// step3.���root��һ��new�������������´����Ķ������
		if (root instanceof NewOperElement) {
			NewOperEvalPlugin neweval = new NewOperEvalPlugin();
			return neweval.eval(root, vartable, classtable);
		}

		// step4.���root��һ�������������������ִ�б�������
		if (root instanceof VarDefineOperElement) {
			DefineOperEvalPlugin defineeval = new DefineOperEvalPlugin();
			return defineeval.eval(root, vartable, classtable);
		}

		// step5.���root��һ��Atom,�����Atomִ����ִ��
		if (root instanceof AtomElement) {
			AtomEvalPlugin atomeval = new AtomEvalPlugin();
			return atomeval.eval(root, vartable, classtable);
		}

		// Part2. һԪ�������֧�ֲ���,��ʱֻ֧���߼�ȡ��������
		// step6.�߼�ȡ���Ĳ���֧��
		if (root instanceof LogicNotOperElement) {
			LogicNotPlugin logicnoteval = new LogicNotPlugin();
			return logicnoteval.eval(root, vartable, classtable);
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// ��ͬ������������ò�ͬ�Ĵ�����
			logger.info("opercode:"+oper.getOpercode());
			IExpressionEval eeval = this.getEvalPluginByOpercode(oper.getOpercode());
			if(eeval == null) {
				logger.error("Cannot find oper expressioneval:"+oper.getOpercode());
				return null;
			}
			return eeval.eval(root, vartable, classtable);
		}

		// Part3.��Ԫ�������֧�ֲ��֣���һ����Ҳȫ�����ݱ��ʽ��д
		// step7 ��ֵ�������֧��
		if (root instanceof VarSetOperElement) {
			VarSetEvalPlugin varseteval = new VarSetEvalPlugin();
			return varseteval.eval(root, vartable, classtable);
		}

		// Part4 ������õĲ���
		if (root instanceof ObjectCallOperElement) {
			ObjectCallOperElement objectcall = (ObjectCallOperElement) root;
			//���ö������ִ����
			ObjectCallEvalPlugin objcalleval = new ObjectCallEvalPlugin();
			return objcalleval.eval(root, vartable, classtable);
		}
		
		// Part5 ���ִ�ж����˵��������߼���û������
		// ���������������ִ�д���
		logger.error("---->ִ�г����ˡ�{"+root.getShowString(0)+"}");
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

}
