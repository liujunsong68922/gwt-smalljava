package com.liu.gwt.gwt_smalljava.level5_expression.expressionvo;

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
