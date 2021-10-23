package com.smalljava.core.l3_expression.vo;

public abstract class AbstractOperElement extends RootAST {
	private String opercode;

	public String getOpercode() {
		return opercode;
	}

	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	
}
