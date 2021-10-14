package com.smalljava.core.l5_expression.eval.plugin.constvalue;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.constvalue.ConstNumberElement;
import com.smalljava.core.l5_expression.vo.constvalue.ConstStringElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class ConstEvalPlugin implements IExpressionEval {

	/**
	 * 计算常量的表达式,返回null表示未计算
	 * 
	 */
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
 		//返回数值常量
		if(root instanceof ConstNumberElement) {
			//强制转型
			ConstNumberElement const1 = (ConstNumberElement)root;
			//返回一个字符串格式的数据，如果以\"开头，是字符串，否则是数值
			VarValue v1 = new VarValue();
			v1.setVarname("");
			v1.setVartype("double");
			v1.setVarsvalue(const1.getDatavalue());
			return v1;
		}
		//返回字符串常量
		if(root instanceof ConstStringElement) {
			//强制转型
			ConstNumberElement const2 = (ConstNumberElement)root;
			//返回一个字符串格式的数据，如果以\"开头，是字符串，否则是数值
			VarValue v1 = new VarValue();
			v1.setVarname("");
			v1.setVartype("String");
			v1.setVarsvalue(const2.getDatavalue());
			return v1;			
		}
		
		//其他情况不处理
		return null;
	}
	
}
