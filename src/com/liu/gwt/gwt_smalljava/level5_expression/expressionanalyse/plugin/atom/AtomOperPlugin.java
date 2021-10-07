package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.atom;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.MiddleAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.atom.AtomElement;

//import com.liu.smalljavav2.level5_expression.expressionanalyse.AstOperAndPos;
//import com.liu.smalljavav2.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.MiddleAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.RootAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.atom.AtomElement;
//import com.liu.smalljavav2.time.expressionanalyse.AstOperAndPos;

/**
 * MEMO�������ӱ��ʽ�������
 * 
 * @author liujunsong
 *
 */
public class AtomOperPlugin extends DefaultIPluginImplement{

	/**
	 * MEMO:�����Ű������ӱ��ʽ
	 */
	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if(!(strcode.startsWith("(") && strcode.endsWith(""))) {
			return null;
		}
		
		//��ȥ����һ��),�ж���û��������)
		String opers[] = new String[] {")"};
		AstOperAndPos oap = getFirstOperCode(strcode,opers);
		
		if(oap==null) {
			return null;
		}else if(oap.getIpos()<strcode.length()-1) {
			//���ڶ��),����ʧ�ܣ�����
			return null;
		}
		//���ű��ʽ�ڽ���ʱ����Ҫ���������ĸ������ȼ�
		
		AtomElement root = new AtomElement();
		RootAST child1 = new MiddleAST();
		String s1 = strcode.substring(1,strcode.length()-1);
		child1.setStrexpression(s1);
		root.getChildren().add(child1);
		//��������������֮
		return root;
	}

}