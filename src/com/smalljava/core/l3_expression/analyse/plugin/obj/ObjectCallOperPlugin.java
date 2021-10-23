package com.smalljava.core.l3_expression.analyse.plugin.obj;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l3_expression.analyse.AstOperAndPos;
import com.smalljava.core.l3_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l3_expression.vo.AbstractAST;
import com.smalljava.core.l3_expression.vo.MiddleAST;
import com.smalljava.core.l3_expression.vo.RootAST;
import com.smalljava.core.l3_expression.vo.obj.ObjectCallOperElement;

public class ObjectCallOperPlugin extends DefaultIPluginImplement {
	private Logger logger = LoggerFactory.getLogger(ObjectCallOperPlugin.class);

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				return null;
			} else {
				int ipos = strcode.indexOf('.');
				if (ipos < 0) {
					return null;
				} else {
					ObjectCallOperElement objcall = new ObjectCallOperElement();
					String objname = strcode.substring(0, ipos);
					logger.info("objname:" + objname);
					objcall.setObjname(objname);

					String strleft = strcode.substring(ipos + 1);
					logger.info("strleft:" + strleft);
					int ipos1 = strleft.indexOf("(");
					int ipos2 = strleft.indexOf(")");

					if (ipos1 > 0 && ipos2 > ipos1) {
						String methodname = strleft.substring(0, ipos1);
						logger.info("methodname:" + methodname);
						objcall.setMethodname(methodname);

						String args = strleft.substring(ipos1 + 1, ipos2);
						logger.info("args:{" + args + "}");
						objcall.setArgs(args);
						if (args.length() > 0) {
							String argarray[] = args.split(",");
							for (String arg : argarray) {
								logger.info("---->arg:" + arg);
								RootAST argelement = new MiddleAST();
								argelement.setStrexpression(arg);
								objcall.getChildren().add(argelement);
							}
						}
						logger.info("analyse ok.");
						return objcall;
					} else {
						logger.info("[ERROR]Cannot find ( and )");
						return null;
					}
				}
			}

		}
		return null;
	}
}
