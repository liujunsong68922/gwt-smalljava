package com.smalljava.core.l5_expression.analyse.plugin.atom;

import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.atom.AtomElement;

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