package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.constvalue;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.constvalue.ConstNumberElement;

//import com.liu.smalljavav2.level5_expression.expressionanalyse.AstOperAndPos;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.constvalue.ConstNumberElement;
//import com.liu.smalljavav2.time.expressionanalyse.AstOperAndPos;

/**
 * MEMO������Ƿ����ַ�������
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
			//�ж��������������������
			String opers[] = new String[] {"+","-","*","/",">=","==","<=",">","<","&&","||","!","(",")"};
			AstOperAndPos oap = getFirstOperCode(strcode,opers);
			if(oap!=null) {
				//���κ�һ���������������
				return null;
			}else {
				ConstNumberElement constelement = new ConstNumberElement();
				//TODO:��Ҫ���������ͽ��н�һ�����ж�
				//TODO:�ȶ�����double������
				constelement.setDatatype("double");
				constelement.setDatavalue(strcode);
				return constelement;
			}
			
		}
		return null;
	}

}
