package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.constvalue;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.constvalue.ConstStringElement;

//import com.liu.smalljavav2.level5_expression.expressionanalyse.AstOperAndPos;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.constvalue.ConstStringElement;
//import com.liu.smalljavav2.time.expressionanalyse.AstOperAndPos;

/**
 * MEMO������Ƿ����ַ�������
 * @author liujunsong
 *
 */
public class ConstStringOperPlugin extends DefaultIPluginImplement  {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if(strcode.startsWith("\"") && strcode.endsWith("\"")) {
			//�ж��������������������
			String opers[] = new String[] {"+","-","*","/",">=","==","<=",">","<","&&","||","!","(",")"};
			AstOperAndPos oap = getFirstOperCode(strcode,opers);
			if(oap!=null) {
				//���κ�һ���������������
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
