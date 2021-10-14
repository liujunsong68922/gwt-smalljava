package com.smalljava.core.l5_expression.vo;

/**
 * AbstractOperElement代表一个带有操作符的表达式
 * @author liujunsong
 *
 */
public abstract class AbstractOperElement extends RootAST {
	/**
	 * MEMO 操作符定义,操作数定义在children里面
	 */
	private String opercode;

	public String getOpercode() {
		return opercode;
	}

	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	
}
