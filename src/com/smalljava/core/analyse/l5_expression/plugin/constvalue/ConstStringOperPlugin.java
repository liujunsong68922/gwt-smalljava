package com.smalljava.core.analyse.l5_expression.plugin.constvalue;

import com.smalljava.core.analyse.l5_expression.AstOperAndPos;
import com.smalljava.core.analyse.l5_expression.plugin.DefaultIPluginImplement;
import com.smalljava.core.commonvo.l5_expression.AbstractAST;
import com.smalljava.core.commonvo.l5_expression.constvalue.ConstStringElement;

public class ConstStringOperPlugin extends DefaultIPluginImplement  {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if(strcode.startsWith("\"") && strcode.endsWith("\"")) {
			String opers[] = new String[] {"+","-","*","/",">=","==","<=",">","<","&&","||","!","(",")"};
			AstOperAndPos oap = getFirstOperCode(strcode,opers);
			if(oap!=null) {
				return null;
			}else {
				ConstStringElement constelement = new ConstStringElement();
				constelement.setDatatype("String");
				constelement.setDatavalue(strcode);
				return constelement;
			}
		}
		return null;
	}
}
