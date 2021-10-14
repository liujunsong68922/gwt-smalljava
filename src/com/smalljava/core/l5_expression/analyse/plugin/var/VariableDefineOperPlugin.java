package com.smalljava.core.l5_expression.analyse.plugin.var;

import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.var.VarDefineOperElement;

/**
 * MEMO:变量定义识别的插件
 * 
 * @author liujunsong
 *
 */
public class VariableDefineOperPlugin extends DefaultIPluginImplement {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		//排除new 运算符
		if(strcode.startsWith("new")) {
			return null;
		}
		
		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			// 判断其中有无其他的运算符
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!", "(", ")","=" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				// 有任何一个运算符，不处理
				return null;
			}
			// 判断变量中是否带有.运算符
			if (strcode.indexOf('.') > 0) {
				return null;
			}
			
			if (strcode.indexOf(' ') > 0) {
				// 判断是否仅有两个元素
				String[] str1 = strcode.split(" ");
				// 长度不等于2，则放弃
				// TODO：未来可以考虑支持一次定义多个变量
				// 目前只支持定义一个变量
				
				if(str1.length != 2) {
					return null;
				}
				
				int ipos = strcode.indexOf(' ');
				// 有空格代表是变量定义,抽象为DefineOper
				VarDefineOperElement define = new VarDefineOperElement();
				define.setDatatype(strcode.substring(0, ipos));
				String varname = strcode.substring(ipos + 1);
				varname = this.trimReturnAndSpace(varname);
				define.setVarname(varname);
				return define;
			}

		}
		return null;
	}

}
