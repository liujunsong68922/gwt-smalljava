package com.smalljava.core.l5_expression.analyse.plugin.constvalue;

import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.constvalue.ConstNumberElement;

/**
 * MEMO：检查是否是字符串常量
 * @author liujunsong
 *
 */
public class ConstNumberOperPlugin extends DefaultIPluginImplement  {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if(strcode.length()==0) {
			return null;
		}
		char char1 = strcode.charAt(0);
		if(char1 >='0' && char1 <='9') {
			//判断其中有无其他的运算符
			String opers[] = new String[] {"+","-","*","/",">=","==","<=",">","<","&&","||","!","(",")"};
			AstOperAndPos oap = getFirstOperCode(strcode,opers);
			if(oap!=null) {
				//有任何一个运算符，不处理
				return null;
			}else {
				ConstNumberElement constelement = new ConstNumberElement();
				//TODO:需要对数据类型进行进一步的判断
				//TODO:先都按照double来处理
				constelement.setDatatype("double");
				constelement.setDatavalue(strcode);
				return constelement;
			}
			
		}
		return null;
	}

}
