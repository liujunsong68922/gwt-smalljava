package com.smalljava.core.l5_expression.analyse.plugin.var;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.var.NewOperElement;

/**
 * MEMO:new �ؼ���֧��ʶ��Ĳ��
 * 
 * @author liujunsong
 *
 */
public class NewObjectOperPlugin extends DefaultIPluginImplement {
	Logger logger = LoggerFactory.getLogger(NewObjectOperPlugin.class);
	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		
		//char char1 = strcode.charAt(0);
		if (strcode.startsWith("new ")) {
			// �ж��������������������
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				// ���κ�һ���������������
				logger.info("find higer oper,return null");
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
				
				NewOperElement define = new NewOperElement();
				String classname = strcode.substring(ipos + 1);
				classname = this.trimReturnAndSpace(classname);
				define.setClassname(classname);
				return define;
			}

		}
		return null;
	}

}
