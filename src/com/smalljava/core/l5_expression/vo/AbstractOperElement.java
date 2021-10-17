package com.smalljava.core.l5_expression.vo;

public abstract class AbstractOperElement extends RootAST {
	private String opercode;

	public String getOpercode() {
		return opercode;
	}

	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	
}
