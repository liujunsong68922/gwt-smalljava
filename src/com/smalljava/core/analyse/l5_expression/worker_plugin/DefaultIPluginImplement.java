package com.smalljava.core.analyse.l5_expression.worker_plugin;

import com.smalljava.core.analyse.l5_expression.manager.IAstPlugin;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

public abstract class DefaultIPluginImplement implements IAstPlugin {
	private Logger logger = LoggerFactory.getLogger(DefaultIPluginImplement.class);

	public AstOperAndPos getFirstOperCode(String nodeString, String opers[]) {
		AstOperAndPos retaop = null;
		for (String oper1 : opers) {
			AstOperAndPos aop1 = this.getFirstOperCode(nodeString, oper1);
			if (aop1 == null) {
				continue;
			} else {
				if (retaop == null) {
					retaop = aop1;
				} else {
					if (retaop.getIpos() > aop1.getIpos()) {
						retaop = aop1;
					}
				}
			}

		}

		return retaop;
	}

	public AstOperAndPos getLastOperCode(String nodeString, String opers[]) {
		AstOperAndPos retaop = null;
		for (String oper1 : opers) {
			AstOperAndPos aop1 = this.getLastOperCode(nodeString, oper1);
			if (aop1 == null) {
				continue;
			} else {
				if (retaop == null) {
					retaop = aop1;
				} else {
					if (retaop.getIpos() < aop1.getIpos()) {
						retaop = aop1;
					}
				}
			}

		}

		return retaop;
	}

	public AstOperAndPos getFirstOperCode(String nodeString, String opers) {
		if (nodeString.equals("&&") || nodeString.equals("||") || nodeString.equals("==") || nodeString.equals(">=")
				|| nodeString.equals("<=")) {
			return null;
		}

		StringFindUtil util = new StringFindUtil();
		int ipos = util.findfirstStringForAST(nodeString, opers);
		if (ipos >= 0) {

			AstOperAndPos oap = new AstOperAndPos();
			oap.setIpos(ipos);
			oap.setOpercode(opers);
			return oap;
		}

		return null;
	}

	public AstOperAndPos getLastOperCode(String nodeString, String opers) {
		if (nodeString.equals("&&") || nodeString.equals("||") || nodeString.equals("==") || nodeString.equals(">=")
				|| nodeString.equals("<=")) {
			return null;
		}

		StringFindUtil util = new StringFindUtil();
		int ipos = util.findLastStringForAST(nodeString, opers);
		if (ipos >= 0) {

			AstOperAndPos oap = new AstOperAndPos();
			oap.setIpos(ipos);
			oap.setOpercode(opers);
			return oap;
		}

		return null;
	}

	public String trimReturnAndSpace(String strinput) {
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			return "";
		}

		int ipos2 = -1;
		for (int i = strinput.length() - 1; i >= 0; i--) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				continue;
			} else {
				ipos2 = i;
				break;
			}
		}

		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			logger.error("[error]wrong postion.ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}
}
