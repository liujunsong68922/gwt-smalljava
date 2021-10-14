package com.smalljava.core.l5_expression.analyse;

import com.smalljava.core.l5_expression.vo.AbstractAST;

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
