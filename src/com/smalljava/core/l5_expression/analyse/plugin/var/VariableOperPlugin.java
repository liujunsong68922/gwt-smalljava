package com.smalljava.core.l5_expression.analyse.plugin.var;

import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.var.VarDataElement;

/**
 * MEMO:变量元素的识别定义
 * 
 * @author liujunsong
 *
 */
public class VariableOperPlugin extends DefaultIPluginImplement {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			// 判断其中有无其他的运算符
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!", "(", ")",
					"new","=" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				// 有任何一个运算符，不处理
				return null;
			}
			// 判断变量中是否带有.运算符,
			if (strcode.indexOf('.') > 0) {
				return null;
			}
			
			// 变量名中不可带空格，则返回
			if(strcode.indexOf(' ')>0) {
				return null;
			}
			
			//这种情况下解析成一个变量
			VarDataElement var = new VarDataElement();
			var.setVarname(strcode);
			return var;

		}
		return null;
	}

}
