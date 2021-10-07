package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.var;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.NewOperElement;

public class NewObjectOperPlugin extends DefaultIPluginImplement {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}

		// char char1 = strcode.charAt(0);
		consoleLog("strcode:" + strcode);

		if (strcode.startsWith("new ")) {
			// �ж��������������������
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				//有其他优先级的运算符号
				return null;
			}

			if (strcode.indexOf('.') > 0) {
				return null;
			}

			if (strcode.indexOf(' ') > 0) {
				String[] str1 = strcode.split(" ");
				if (str1.length != 2) {
					return null;
				}

				int ipos = strcode.indexOf(' ');
				NewOperElement define = new NewOperElement();
				String classname = strcode.substring(ipos + 1);
				//去掉classname中的()方法
				consoleLog("---->s1:"+classname);
				classname = classname.replaceAll("\\(\\)", "");
				consoleLog("---->s2:"+classname);
				
				classname = this.trimReturnAndSpace(classname);
				define.setClassname(classname);
				return define;
			}

		}
		return null;
	}

	public native void consoleLog(String message) /*-{
													//alert(message);
													console.log( "[NewObjectOperPlugin]" + message );
													}-*/;

}
