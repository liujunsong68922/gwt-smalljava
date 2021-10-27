package com.smalljava.core.analyse.l5_expression.plugin.two;

import com.smalljava.core.analyse.l5_expression.AstOperAndPos;
import com.smalljava.core.analyse.l5_expression.plugin.DefaultIPluginImplement;
import com.smalljava.core.analyse.l5_expression.plugin.var.VarSetOperPlugin;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.AbstractAST;
import com.smalljava.core.commonvo.l5_expression.MiddleAST;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.two.DualOperDataOperElement;

public class LogicCompareOperPlugin extends DefaultIPluginImplement {
	Logger logger = LoggerFactory.getLogger(LogicCompareOperPlugin.class);

	@Override
	public AbstractAST analyse(String strcode) {
		logger.info("enter LogicCompareOperPlugin.analyse.");

		String opers[] = new String[] { ">=", "<=", ">", "<", "==", "!=" };
		AstOperAndPos oap = getFirstOperCode(strcode, opers);

		if (oap == null) {
			logger.info("Cannot find opers");
			return null;
		}

		String highopers[] = new String[] { "&&", "||", "!" };
		AstOperAndPos highoap = getFirstOperCode(strcode, highopers);

		if (highoap != null) {
			logger.info("find high oper,return.");
			return null;
		}

		DualOperDataOperElement root = new DualOperDataOperElement();
		root.setOpercode(oap.getOpercode());

		String leftstring = strcode.substring(0, oap.ipos);
		String rightString = strcode.substring(oap.ipos + oap.opercode.length());

		if (leftstring.equals("")) {
			logger.error("left var is null, return.");
			return null;
		} else {
			char lastchar1 = leftstring.charAt(leftstring.length() - 1);
			if ((lastchar1 >= 'a' && lastchar1 <= 'z') || (lastchar1 >= 'A' && lastchar1 <= 'Z') || (lastchar1 == ')')
					|| (lastchar1 == ' ')) {
			} else {
				logger.error("left var is not valid. return null." + leftstring);
				return null;
			}
		}
		if (rightString.equals("")) {
			logger.error("rightstring is empty,return null");
			return null;
		} else {
			char firstchar = rightString.charAt(0);
			if ((firstchar >= 'a' && firstchar <= 'z') || (firstchar >= 'A' && firstchar <= 'Z') || (firstchar == '(')
					|| (firstchar == ' ')) {
			} else {
				logger.error("rightstring is invalid,return null." + rightString);
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
