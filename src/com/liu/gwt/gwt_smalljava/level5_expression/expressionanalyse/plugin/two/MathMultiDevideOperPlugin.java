package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.two;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.MiddleAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.two.DualOperDataOperElement;

//import com.liu.smalljavav2.level5_expression.expressionanalyse.AstOperAndPos;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.MiddleAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.RootAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.two.DualOperDataOperElement;
//import com.liu.smalljavav2.time.expressionanalyse.AstOperAndPos;

/**
 * MEMO���˺ţ����ŷ������
 * 
 * @author liujunsong
 *
 */
public class MathMultiDevideOperPlugin extends DefaultIPluginImplement{

	/**
	 * MEMO�ӷ�����������
	 */
	@Override
	public AbstractAST analyse(String strcode) {
		String opers[] = new String[] {"*","/"};
		AstOperAndPos oap = getFirstOperCode(strcode,opers);
		
		if(oap==null) {
			//���ҼӺţ�����ʧ��
			return null;
		}
		
		String highopers[] = new String[] {"&&","||","!","==",">=","<=",">","<","="};
		AstOperAndPos highoap = getFirstOperCode(strcode,highopers);
		if(highoap!=null) {
			//���ڼ������ȼ����͵������������
			return null;
		}
		
		
		//�����¶���׼������
		//ʹ��һ����Ԫ�����Ԫ�������з�װ
		DualOperDataOperElement root = new DualOperDataOperElement();
		//��¼������
		root.setOpercode(oap.getOpercode());
		
		//�Ӳ�������ʼ�и��ַ���Ϊ������
		String leftstring = strcode.substring(0, oap.ipos);
		String rightString = strcode.substring(oap.ipos + oap.opercode.length());

		//leftstring = trimReturnAndSpace(leftstring);
		if(leftstring.equals("")) {
			//�����������Ϊ��
			return null;
		}else {
			//�жϽ����ַ����ĺϷ���
			char lastchar1 = leftstring.charAt(leftstring.length()-1);
			if((lastchar1>='a' && lastchar1<='z') 
				|| (lastchar1>='A' && lastchar1<='Z')
				|| (lastchar1>='0' && lastchar1<='9')
				|| (lastchar1==')')
				|| (lastchar1==' ')){
				//��Щ�ǺϷ��ַ�����ͨ��
			}else {
				//��������������Ϸ���������
				return null;
			}
		}
		//����Ҳ������Ƿ�Ϸ�
		if(rightString.equals("")) {
			//�����������Ϊ��
			return null;
		}else {
			char firstchar = rightString.charAt(0);
			if((firstchar>='a' && firstchar<='z') 
					|| (firstchar>='A' && firstchar<='Z')
					|| (firstchar>='0' && firstchar<='9')
					|| (firstchar=='(')
					|| (firstchar==' ')){
					//��Щ�ǺϷ��ַ�����ͨ��
				}else {
					//��������������Ϸ���������
					return null;
				}
		}
		
		
		RootAST leftelement = new MiddleAST();
		RootAST rightelement = new MiddleAST();
		leftelement.setStrexpression(leftstring);
		rightelement.setStrexpression(rightString);
		
		
		root.getChildren().add(leftelement);
		root.getChildren().add(rightelement);
		
		//��������������֮
		return root;
	}

}