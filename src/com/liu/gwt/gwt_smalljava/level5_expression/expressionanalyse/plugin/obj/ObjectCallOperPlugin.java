package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.obj;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.obj.ObjectCallOperElement;

public class ObjectCallOperPlugin extends DefaultIPluginImplement {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!","="};
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				return null;
			} else {
				int ipos = strcode.indexOf('.');
				if (ipos < 0 ) {
					//找不到点操作符号，返回
					return null;
				} else {
					//找到.操作符号的情况下，分解成对象调用表达式
					ObjectCallOperElement objcall = new ObjectCallOperElement();
					//获取对象名
					String objname = strcode.substring(0,ipos);
					
					//查找左括号
					int ipos2= strcode.indexOf('(');
					String methodname = strcode.substring(ipos+1,ipos2);
					String args = strcode.substring(ipos2);
					objcall.setObjname(objname);
					objcall.setMethodname(methodname);
					objcall.setArgs(args);
					
					return objcall;
				}
			}

		}
		return null;
	}

}
