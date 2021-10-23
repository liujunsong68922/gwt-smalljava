package com.smalljava.core.l3_expression.analyse.plugin.two;

import com.smalljava.core.l3_expression.analyse.AstOperAndPos;
import com.smalljava.core.l3_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l3_expression.vo.AbstractAST;
import com.smalljava.core.l3_expression.vo.MiddleAST;
import com.smalljava.core.l3_expression.vo.RootAST;
import com.smalljava.core.l3_expression.vo.two.DualOperDataOperElement;

public class LogicComputeOperPlugin extends DefaultIPluginImplement {
	@Override
	public AbstractAST analyse(String strcode) {
		String opers[] = new String[] { "&&", "||", "==" };
		AstOperAndPos oap = getLastOperCode(strcode, opers);

		if (oap == null) {
			return null;
		}
		DualOperDataOperElement root = new DualOperDataOperElement();
		root.setOpercode(oap.getOpercode());
		String leftstring = strcode.substring(0, oap.ipos);
		String rightString = strcode.substring(oap.ipos + oap.opercode.length());
		if (leftstring.equals("")) {
			return null;
		} else {
			char lastchar1 = leftstring.charAt(leftstring.length() - 1);
			if ((lastchar1 >= 'a' && lastchar1 <= 'z') || (lastchar1 >= 'A' && lastchar1 <= 'Z') || (lastchar1 == ')')
					|| (lastchar1 == ' ')) {
			} else {
				return null;
			}
		}
		if (rightString.equals("")) {
			return null;
		} else {
			char firstchar = rightString.charAt(0);
			if ((firstchar >= 'a' && firstchar <= 'z') || (firstchar >= 'A' && firstchar <= 'Z') || (firstchar == '(')
					|| (firstchar == ' ')) {
			} else {
				return null;
			}
		}
		RootAST leftelement = new MiddleAST();
		RootAST rightelement = new MiddleAST();
		leftelement.setStrexpression(leftstring);
		rightelement.setStrexpression(rightString);
		root.getChildren().add(leftelement);
		root.getChildren().add(rightelement);
		return root;
	}
}
