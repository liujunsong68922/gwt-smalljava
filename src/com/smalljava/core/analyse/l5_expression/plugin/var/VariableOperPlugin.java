package com.smalljava.core.analyse.l5_expression.plugin.var;

import com.smalljava.core.analyse.l5_expression.AstOperAndPos;
import com.smalljava.core.analyse.l5_expression.plugin.DefaultIPluginImplement;
import com.smalljava.core.commonvo.l5_expression.AbstractAST;
import com.smalljava.core.commonvo.l5_expression.var.VarDataElement;

public class VariableOperPlugin extends DefaultIPluginImplement {
	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!", "(", ")",
					"new", "=" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				return null;
			}
			if (strcode.indexOf('.') > 0) {
				return null;
			}
			if (strcode.indexOf(' ') > 0) {
				return null;
			}
			VarDataElement var = new VarDataElement();
			var.setVarname(strcode);
			return var;
		}
		return null;
	}
}
