package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.var;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarDataElement;

//import com.liu.smalljavav2.level5_expression.expressionanalyse.AstOperAndPos;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.var.VarDataElement;
//import com.liu.smalljavav2.time.expressionanalyse.AstOperAndPos;

/**
 * MEMO:����Ԫ�ص�ʶ����
 * 
 * @author liujunsong
 *
 */
public class VariableOperPlugin extends DefaultIPluginImplement {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			// �ж��������������������
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!", "(", ")",
					"new","=" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				// ���κ�һ���������������
				return null;
			}
			// �жϱ������Ƿ����.�����,
			if (strcode.indexOf('.') > 0) {
				return null;
			}
			
			// �������в��ɴ��ո��򷵻�
			if(strcode.indexOf(' ')>0) {
				return null;
			}
			
			//��������½�����һ������
			VarDataElement var = new VarDataElement();
			var.setVarname(strcode);
			return var;

		}
		return null;
	}

}
