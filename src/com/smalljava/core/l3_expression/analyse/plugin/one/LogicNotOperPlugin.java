package com.smalljava.core.l3_expression.analyse.plugin.one;

import com.smalljava.core.l3_expression.analyse.AstOperAndPos;
import com.smalljava.core.l3_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l3_expression.vo.AbstractAST;
import com.smalljava.core.l3_expression.vo.MiddleAST;
import com.smalljava.core.l3_expression.vo.RootAST;
import com.smalljava.core.l3_expression.vo.one.LogicNotOperElement;

public class LogicNotOperPlugin extends DefaultIPluginImplement {

	@Override
	public AbstractAST analyse(String strcode) {
		String opers[] = new String[] { "! ", "!(" };
		AstOperAndPos oap = getFirstOperCode(strcode, opers);
		if (oap == null) {
			return null;
		}
		String highopers[] = new String[] { "&&", "||", "=" };
		AstOperAndPos highoap = getFirstOperCode(strcode, highopers);
		if (highoap != null) {
			return null;
		}
		LogicNotOperElement root = new LogicNotOperElement();
		root.setOpercode("!");
		String leftstring = strcode.substring(0, oap.ipos);
		String rightString = strcode.substring(oap.ipos + 1);
		leftstring = this.trimReturnAndSpace(leftstring);
		if (!leftstring.equals("")) {
			return null;
		}
		if (rightString.equals("")) {
			return null;
		} else {
			char firstchar = rightString.charAt(0);
			if ((firstchar >= 'a' && firstchar <= 'z') || (firstchar >= 'A' && firstchar <= 'Z') || (firstchar == '(')
					|| (firstchar == ' ') || (firstchar == '!')) {
			} else {
				return null;
			}
		}
		RootAST rightelement = new MiddleAST();
		rightelement.setStrexpression(rightString);
		root.getChildren().add(rightelement);
		return root;
	}

}
