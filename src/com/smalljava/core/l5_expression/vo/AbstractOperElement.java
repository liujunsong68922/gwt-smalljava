package com.smalljava.core.l5_expression.vo;

/**
 * AbstractOperElement����һ�����в������ı��ʽ
 * @author liujunsong
 *
 */
public abstract class AbstractOperElement extends RootAST {
	/**
	 * MEMO ����������,������������children����
	 */
	private String opercode;

	public String getOpercode() {
		return opercode;
	}

	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	
}
