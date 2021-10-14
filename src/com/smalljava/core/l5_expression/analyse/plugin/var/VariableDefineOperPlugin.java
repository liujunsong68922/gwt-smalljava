package com.smalljava.core.l5_expression.analyse.plugin.var;

import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.var.VarDefineOperElement;

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
