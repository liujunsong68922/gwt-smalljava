package com.smalljava.core.analyse.l5_expression.plugin.oop;

import com.smalljava.core.analyse.l5_expression.AstOperAndPos;
import com.smalljava.core.analyse.l5_expression.plugin.DefaultIPluginImplement;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.AbstractAST;
import com.smalljava.core.commonvo.l5_expression.MiddleAST;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.oop.ObjectCallOperElement;
import com.smalljava.core.commonvo.l5_expression.oop.ObjectPropertyElement;

public class ObjectPropertyPlugin extends DefaultIPluginImplement {
	private Logger logger = LoggerFactory.getLogger(ObjectPropertyPlugin.class);

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!","(" ,")" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				logger.debug("[debug] find higer operator.return null.");
				return null;
			} else {
				int ipos = strcode.indexOf('.');
				if (ipos < 0) {
					logger.debug("[debug] not find . operator");
					return null;
				} else {
					ObjectPropertyElement objectproperty = new ObjectPropertyElement();
					objectproperty.setStrexpression(strcode);
					String objname = strcode.substring(0, ipos);
					logger.info("objname:" + objname);
					objectproperty.setObjname(objname);

					String strleft = strcode.substring(ipos + 1);
					logger.info("strleft:" + strleft);
					objectproperty.setPropertyname(strleft);
					
					return objectproperty;
				}
			}
		}
		return null;
	}
}
