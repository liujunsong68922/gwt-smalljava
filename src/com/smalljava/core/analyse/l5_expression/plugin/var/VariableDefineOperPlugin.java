package com.smalljava.core.analyse.l5_expression.plugin.var;

import com.smalljava.core.analyse.l5_expression.AstOperAndPos;
import com.smalljava.core.analyse.l5_expression.plugin.DefaultIPluginImplement;
import com.smalljava.core.commonvo.l5_expression.AbstractAST;
import com.smalljava.core.commonvo.l5_expression.var.VarDefineOperElement;

public class VariableDefineOperPlugin extends DefaultIPluginImplement {
	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		if (strcode.startsWith("new")) {
			return null;
		}

		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!", "(", ")",
					"=" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
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
