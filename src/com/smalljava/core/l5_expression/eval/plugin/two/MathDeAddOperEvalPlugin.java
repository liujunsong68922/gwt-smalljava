package com.smalljava.core.l5_expression.eval.plugin.two;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.eval.operelement.DoubleValue;
import com.smalljava.core.l5_expression.eval.operelement.FloatValue;
import com.smalljava.core.l5_expression.eval.operelement.IntegerValue;
import com.smalljava.core.l5_expression.eval.operelement.LongValue;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.two.DualOperDataOperElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * MEMO 执行减法运算
 * @author liujunsong
 *
 */
public class MathDeAddOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(MathDeAddOperEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof DualOperDataOperElement) {
			DualOperDataOperElement oper = (DualOperDataOperElement) root;
			// 减法计算
			if (oper.getOpercode().equals("-")) {
				RootAST leftelement = oper.getChildren().get(0);
				RootAST rightelement = oper.getChildren().get(1);
				// 生成一个新的评估器
				ExpressionEval eeval = new ExpressionEval();
				VarValue leftvar = eeval.eval(leftelement, vartable, classtable);
				VarValue rightvar = eeval.eval(rightelement, vartable, classtable);
				if (leftvar == null || rightvar == null) {
					logger.error("减法计算失败，参数为null");
					return null;
				}

				if (leftvar.getVartype() == null) {
					logger.error("程序逻辑错误，左操作对象类型为null");
					return null;
				}
				if (leftvar.getVartype().equals("int")) {
					IntegerValue intoper = new IntegerValue(leftvar.getVarsvalue());
					intoper.doDeAdd(rightvar.getVarsvalue());
					return intoper;
				}
				if (leftvar.getVartype().equals("long")) {
					LongValue longoper = new LongValue(leftvar.getVarsvalue());
					// 把第二个节点的字符串传进去
					logger.error("Long右面操作数:" + rightvar.getVarsvalue());
					longoper.doDeAdd(rightvar.getVarsvalue());
					return longoper;
				}
				if (leftvar.getVartype().equals("float")) {
					FloatValue floatoper = new FloatValue(leftvar.getVarsvalue());
					logger.error("Float右面操作数:" + rightvar.getVarsvalue());
					floatoper.doDeAdd(rightvar.getVarsvalue());
					return floatoper;
				}
				if (leftvar.getVartype().equals("double")) {
					DoubleValue doubleoper = new DoubleValue(leftvar.getVarsvalue());
					;
					// 把第二个节点的字符串传进去
					logger.error("Double右面操作数:" + rightvar.getVarsvalue());
					doubleoper.doDeAdd(rightvar.getVarsvalue());
					return doubleoper;
				}
				logger.error("【ERROR】减号操作遇到了不支持的数据类型：" + leftvar.getVartype());
				return null;

			}
		}
		return null;
	}


}
