package com.smalljava.core.l5_expression.analyse.plugin.one;

import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.one.LogicNotOperElement;

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
