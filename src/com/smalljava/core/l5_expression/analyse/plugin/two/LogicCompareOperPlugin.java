package com.smalljava.core.l5_expression.analyse.plugin.two;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.analyse.plugin.var.VarSetOperPlugin;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.two.DualOperDataOperElement;

/**
 * �߼��Ƚ�������ŵ�ʵ��
 * @author liujunsong
 *
 */
public class LogicCompareOperPlugin extends DefaultIPluginImplement{
	Logger logger = LoggerFactory.getLogger(LogicCompareOperPlugin.class);
	
	@Override
	public AbstractAST analyse(String strcode) {
		logger.info("enter LogicCompareOperPlugin.analyse.");
		
		String opers[] = new String[] {">=","<=",">","<","==","!="};
		AstOperAndPos oap = getFirstOperCode(strcode,opers);
		
		if(oap==null) {
			//�����߼��ȽϷ���ʧ��
			logger.info("Cannot find opers");
			return null;
		}
		
		String highopers[] = new String[] {"&&","||","!"};
		AstOperAndPos highoap = getFirstOperCode(strcode,highopers);
		
		if(highoap!=null) {
			//���ڼ������ȼ����͵������������
			logger.info("find high oper,return.");
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
			logger.error("left var is null, return.");
			return null;
		}else {
			//�жϽ����ַ����ĺϷ���
			char lastchar1 = leftstring.charAt(leftstring.length()-1);
			if((lastchar1>='a' && lastchar1<='z') 
				|| (lastchar1>='A' && lastchar1<='Z')
				|| (lastchar1==')')
				|| (lastchar1==' ')){
				//��Щ�ǺϷ��ַ�����ͨ��
			}else {
				//��������������Ϸ���������
				logger.error("left var is not valid. return null."+ leftstring);
				return null;
			}
		}
		//����Ҳ������Ƿ�Ϸ�
		if(rightString.equals("")) {
			//�����������Ϊ��
			logger.error("rightstring is empty,return null");
			return null;
		}else {
			char firstchar = rightString.charAt(0);
			if((firstchar>='a' && firstchar<='z') 
					|| (firstchar>='A' && firstchar<='Z')
					|| (firstchar=='(')
					|| (firstchar==' ')){
					//��Щ�ǺϷ��ַ�����ͨ��
				}else {
					//��������������Ϸ���������
					logger.error("rightstring is invalid,return null."+rightString);
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
