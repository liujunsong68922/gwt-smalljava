package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.var;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarDefineOperElement;

//import com.liu.smalljavav2.level5_expression.expressionanalyse.AstOperAndPos;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.var.VarDefineOperElement;
////import com.liu.smalljavav2.time.expressionanalyse.AstOperAndPos;

/**
 * MEMO:��������ʶ��Ĳ��
 * 
 * @author liujunsong
 *
 */
public class VariableDefineOperPlugin extends DefaultIPluginImplement {

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		//�ų�new �����
		if(strcode.startsWith("new")) {
			return null;
		}
		
		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			// �ж��������������������
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!", "(", ")","=" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				// ���κ�һ���������������
				return null;
			}
			// �жϱ������Ƿ����.�����
			if (strcode.indexOf('.') > 0) {
				return null;
			}
			
			if (strcode.indexOf(' ') > 0) {
				// �ж��Ƿ��������Ԫ��
				String[] str1 = strcode.split(" ");
				// ���Ȳ�����2�������
				// TODO��δ�����Կ���֧��һ�ζ���������
				// Ŀǰֻ֧�ֶ���һ������
				
				if(str1.length != 2) {
					return null;
				}
				
				int ipos = strcode.indexOf(' ');
				// �пո�����Ǳ�������,����ΪDefineOper
				VarDefineOperElement define = new VarDefineOperElement();
				define.setDatatype(strcode.substring(0, ipos));
				String varname = strcode.substring(ipos + 1);
				varname = this.trimReturnAndSpace(varname);
				define.setVarname(varname);
				return define;
			}

		}
		return null;
	}

}
