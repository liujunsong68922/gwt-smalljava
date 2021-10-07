package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin;

import com.liu.gwt.gwt_smalljava.common.StringFindUtil;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.IAstPlugin;

//import com.liu.smalljavav2.common.StringFindUtil;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.AstOperAndPos;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.IAstPlugin;
//import com.liu.smalljavav2.time.expressionanalyse.AstOperAndPos;

/**
 * MEMO���������������Ŀ���ǰ�װ���в���Ĺ��÷����������ظ�����
 * MEMO�������û�о����ʵ��
 * MEMO���������֮��Ӧ������ȫ�����ģ������໥Ӱ��
 * MEMO:�����������Ҫ���Ǳ��˵Ĵ���
 * @author liujunsong
 *
 */
public abstract class DefaultIPluginImplement implements IAstPlugin {
	
	/**
	 * ���룺nodeString ������Ƚ϶��ͬ�����ȼ��Ĳ����������ص�һ��λ�õ������
	 * 
	 * @return
	 */
	public AstOperAndPos getFirstOperCode(String nodeString,String opers[]) {
		AstOperAndPos retaop = null;
		for(String oper1:opers) {
			//����ĳһ����������λ��
			AstOperAndPos aop1 = this.getFirstOperCode(nodeString, oper1);
			if(aop1 == null) {
				//δ�ҵ�����ѭ��
				continue;
			}else {
				if(retaop == null) {
					retaop = aop1;
				}else {
					//�Ƚ�����aop��λ��
					if(retaop.getIpos()>aop1.getIpos()) {
						retaop = aop1;
					}
				}
			}
			
		}
		
		return retaop;
	}

	/**
	 * ���룺nodeString ������Ƚ϶��ͬ�����ȼ��Ĳ����������ص�һ��λ�õ������
	 * 
	 * @return
	 */
	public AstOperAndPos getLastOperCode(String nodeString,String opers[]) {
		AstOperAndPos retaop = null;
		for(String oper1:opers) {
			//����ĳһ����������λ��
			AstOperAndPos aop1 = this.getLastOperCode(nodeString, oper1);
			if(aop1 == null) {
				//δ�ҵ�����ѭ��
				continue;
			}else {
				if(retaop == null) {
					retaop = aop1;
				}else {
					//�Ƚ�����aop��λ��
					if(retaop.getIpos()<aop1.getIpos()) {
						retaop = aop1;
					}
				}
			}
			
		}
		
		return retaop;
	}	
	/**
	 * ���룺nodeString ��������ҵ��ĵ�һ�������������ղ����������ȼ��������ң�
	 * 
	 * @return
	 */
	public AstOperAndPos getFirstOperCode(String nodeString,String opers) {
		// �������Ҫ���ҵ������Ѿ���һ�������������˳�����
		// ��Ҫ��һ��������������
		if ( nodeString.equals("&&") 
				||  nodeString.equals("||") 
				||  nodeString.equals("==") 
				||  nodeString.equals(">=")
				||  nodeString.equals("<=")) {
			return null;
		}

//		int i = 0;
		StringFindUtil util = new StringFindUtil();
		int ipos = util.findfirstStringForAST( nodeString, opers);
		if (ipos >= 0) {
			
			AstOperAndPos oap = new AstOperAndPos();
			oap.setIpos(ipos);
			oap.setOpercode(opers);
			return oap;
		}
		

		// �Ҳ���������������һ����ֵ
		return null;
	}	

	/**
	 * ���룺nodeString ��������ҵ��ĵ�һ�������������ղ����������ȼ��������ң�
	 * 
	 * @return
	 */
	public AstOperAndPos getLastOperCode(String nodeString,String opers) {
		// �������Ҫ���ҵ������Ѿ���һ�������������˳�����
		// ��Ҫ��һ��������������
		if ( nodeString.equals("&&") 
				||  nodeString.equals("||") 
				||  nodeString.equals("==") 
				||  nodeString.equals(">=")
				||  nodeString.equals("<=")) {
			return null;
		}

//		int i = 0;
		StringFindUtil util = new StringFindUtil();
		int ipos = util.findLastStringForAST( nodeString, opers);
		if (ipos >= 0) {
			
			AstOperAndPos oap = new AstOperAndPos();
			oap.setIpos(ipos);
			oap.setOpercode(opers);
			return oap;
		}
		

		// �Ҳ���������������һ����ֵ
		return null;
	}		
	/**
	 * ���ַ�����ʼ�ͽ���λ�õ�\r\n ,\r,�ո񶼹��˵�
	 * 
	 * @param strinput
	 * @return
	 */
	public String trimReturnAndSpace(String strinput) {
		//String sout = "";
		// �Ȳ��ҵ�һ������\r\n \r �ո��λ��
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// ����ѭ��
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			// û���ҵ���Ч�ַ�
			return "";
		}

		// ��ʼ�Ӻ���ǰ���ҵ�һ����Ч�ַ�
		int ipos2 = -1;
		for (int i = strinput.length() - 1; i >= 0; i--) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// ����ѭ��
				continue;
			} else {
				ipos2 = i;
				break;
			}
		}

		// ����ipos��Ч������ipos2һ��Ҳ����Ч��
		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			System.out.println("����ִ�г��ִ�����Ҫ������������.ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}
}
