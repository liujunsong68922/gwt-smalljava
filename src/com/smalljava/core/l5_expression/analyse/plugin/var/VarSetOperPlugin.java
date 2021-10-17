package com.smalljava.core.l5_expression.analyse.plugin.var;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.var.VarSetOperElement;

public class VarSetOperPlugin extends DefaultIPluginImplement {
	Logger logger = LoggerFactory.getLogger(VarSetOperPlugin.class);

	@Override
	public AbstractAST analyse(String strcode) {
		String opers[] = new String[] { "=" };
		logger.info("strcode:" + strcode);
		AstOperAndPos oap = getFirstOperCode(strcode, opers);
		if (oap == null) {
			logger.info("oap is null,return.");
			return null;
		}
		String highopers[] = new String[] { "<=", "==", ">=" };
		AstOperAndPos highoap = getFirstOperCode(strcode, highopers);
		if (highoap != null) {
			logger.info("find high oper,return.");
			return null;
		}
		VarSetOperElement root = new VarSetOperElement();
		root.setOpercode(oap.getOpercode());
		String leftstring = strcode.substring(0, oap.ipos);
		String rightString = strcode.substring(oap.ipos + oap.opercode.length());
		if (leftstring.equals("")) {
			return null;
		} else {
			char lastchar1 = leftstring.charAt(leftstring.length() - 1);
			if ((lastchar1 >= 'a' && lastchar1 <= 'z') || (lastchar1 >= 'A' && lastchar1 <= 'Z')
					|| (lastchar1 >= '0' && lastchar1 <= '9') || (lastchar1 == ' ')) {
			} else {
				return null;
			}
		}
		if (rightString.equals("")) {
			return null;
		} else {
			char firstchar = rightString.charAt(0);
			if ((firstchar >= 'a' && firstchar <= 'z') || (firstchar >= 'A' && firstchar <= 'Z')
					|| (firstchar >= '0' && firstchar <= '9') || (firstchar == '(') || (firstchar == '\"')
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
