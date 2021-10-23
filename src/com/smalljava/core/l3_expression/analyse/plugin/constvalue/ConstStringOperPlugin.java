package com.smalljava.core.l3_expression.analyse.plugin.constvalue;

import com.smalljava.core.l3_expression.analyse.AstOperAndPos;
import com.smalljava.core.l3_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l3_expression.vo.AbstractAST;
import com.smalljava.core.l3_expression.vo.constvalue.ConstStringElement;

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
