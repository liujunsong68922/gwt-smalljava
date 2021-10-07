package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;

//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractAST;

/**
 * MEMO��ͳһ�Ĳ�������ӿ�
 * @author liujunsong
 *
 */
public interface IAstPlugin {
	/**
	 * MEMO����Java�����ַ����ֽ�Ϊһ��AST�ڵ㣬ֻ�ֽ�һ��
	 * @param strcode
	 * @return ��������������AST�ڵ㣬����������������null������ֽ�ʧ��
	 */
	public AbstractAST analyse(String strcode);
}
