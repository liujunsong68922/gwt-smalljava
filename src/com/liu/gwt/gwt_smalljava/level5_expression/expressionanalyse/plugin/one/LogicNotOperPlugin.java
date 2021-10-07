package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.one;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.MiddleAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.one.LogicNotOperElement;

//import com.liu.smalljavav2.level5_expression.expressionanalyse.AstOperAndPos;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.MiddleAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.RootAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.one.LogicNotOperElement;
//import com.liu.smalljavav2.expression.expressionvo.one.SingleOperDataOperElement;
//import com.liu.smalljavav2.time.expressionanalyse.AstOperAndPos;

/**
 * �߼��Ƚ�������ŵ�ʵ��
 * @author liujunsong
 *
 */
public class LogicNotOperPlugin extends DefaultIPluginImplement{

	@Override
	public AbstractAST analyse(String strcode) {
		//��Ч���߼�ȡ����������ʽ������һ���ǿո�һ�������ţ�����ĺ���
		String opers[] = new String[] {"! ","!("};
		AstOperAndPos oap = getFirstOperCode(strcode,opers);
		
		if(oap==null) {
			//�����߼��ȽϷ���ʧ��
			return null;
		}
		
		String highopers[] = new String[] {"&&","||","="};
		AstOperAndPos highoap = getFirstOperCode(strcode,highopers);
		
		if(highoap!=null) {
			//���ڼ������ȼ����͵������������
			return null;
		}
		
		
		//�����¶���׼������
		//ʹ��һ��1Ԫ�����Ԫ�������з�װ
		LogicNotOperElement root = new LogicNotOperElement();
		//��¼������
		root.setOpercode("!");
		
		//�Ӳ�������ʼ�и��ַ���Ϊ������
		String leftstring = strcode.substring(0, oap.ipos);
		String rightString = strcode.substring(oap.ipos + 1);
		leftstring = this.trimReturnAndSpace(leftstring);
		if(! leftstring.equals("")) {
			//�����������Ϊ��,���򲻴���
			return null;
		}
		
		//����Ҳ������Ƿ�Ϸ�
		if(rightString.equals("")) {
			//�Ҳ���������Ϊ��
			return null;
		}else {
			char firstchar = rightString.charAt(0);
			if((firstchar>='a' && firstchar<='z') 
					|| (firstchar>='A' && firstchar<='Z')
					|| (firstchar=='(')
					|| (firstchar==' ')
					|| (firstchar=='!')){
					//��Щ�ǺϷ��ַ�����ͨ��
				}else {
					//�Ҳ������������Ϸ���������
					return null;
				}
		}
		
		
		RootAST rightelement = new MiddleAST();
		rightelement.setStrexpression(rightString);
		
		root.getChildren().add(rightelement);
		
		//��������������֮
		return root;
	}

}
