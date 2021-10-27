package com.smalljava.core.analyse.l5_expression.plugin.constvalue;

import com.smalljava.core.analyse.l5_expression.AstOperAndPos;
import com.smalljava.core.analyse.l5_expression.plugin.DefaultIPluginImplement;
import com.smalljava.core.commonvo.l5_expression.AbstractAST;
import com.smalljava.core.commonvo.l5_expression.constvalue.ConstNumberElement;

public class ConstNumberOperPlugin extends DefaultIPluginImplement  {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if(strcode.length()==0) {
			return null;
		}
		char char1 = strcode.charAt(0);
		if(char1 >='0' && char1 <='9') {
			String opers[] = new String[] {"+","-","*","/",">=","==","<=",">","<","&&","||","!","(",")"};
			AstOperAndPos oap = getFirstOperCode(strcode,opers);
			if(oap!=null) {
				return null;
			}else {
				ConstNumberElement constelement = new ConstNumberElement();
				constelement.setDatatype("double");
				constelement.setDatavalue(strcode);
				return constelement;
			}
		}
		return null;
	}
}
