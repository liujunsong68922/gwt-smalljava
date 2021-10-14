package com.smalljava.core.l5_expression.analyse;

public class AstOperAndPos {
	// 操作符号，暂时指2元操作符
	public String opercode;
	public int ipos;

	public String getOpercode() {
		return opercode;
	}

	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}

	public int getIpos() {
		return ipos;
	}

	public void setIpos(int ipos) {
		this.ipos = ipos;
	}
}