package com.smalljava.core.l5_expression.analyse.plugin.constvalue;

import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.constvalue.ConstStringElement;

/**
 * MEMO：检查是否是字符串常量
 * @author liujunsong
 *
 */
public class ConstStringOperPlugin extends DefaultIPluginImplement  {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if(strcode.startsWith("\"") && strcode.endsWith("\"")) {
			//判断其中有无其他的运算符
			String opers[] = new String[] {"+","-","*","/",">=","==","<=",">","<","&&","||","!","(",")"};
			AstOperAndPos oap = getFirstOperCode(strcode,opers);
			if(oap!=null) {
				//有任何一个运算符，不处理
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
