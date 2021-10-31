package com.smalljava.core.analyse.l5_expression.worker_plugin.oop;

import com.smalljava.core.analyse.l5_expression.worker_plugin.AstOperAndPos;
import com.smalljava.core.analyse.l5_expression.worker_plugin.DefaultIPluginImplement;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.AbstractAST;
import com.smalljava.core.commonvo.l5_expression.oop.NewOperElement;

public class NewObjectOperPlugin extends DefaultIPluginImplement {
	Logger logger = LoggerFactory.getLogger(NewObjectOperPlugin.class);
	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		if (strcode.startsWith("new ")) {
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				logger.info("find higer oper,return null");
				return null;
			}
			if (strcode.indexOf('.') > 0) {
				return null;
			}
			
			if (strcode.indexOf(' ') > 0) {
				String[] str1 = strcode.split(" ");
				if(str1.length != 2) {
					return null;
				}
				int ipos = strcode.indexOf(' ');
				NewOperElement define = new NewOperElement();
				String classname = strcode.substring(ipos + 1);
				classname = this.trimReturnAndSpace(classname);
				define.setClassname(classname);
				return define;
			}
		}
		return null;
	}
}
