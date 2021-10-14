package com.smalljava.core.l5_expression.analyse.plugin.constvalue;

import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.constvalue.ConstStringElement;

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
