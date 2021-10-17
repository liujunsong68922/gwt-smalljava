package com.smalljava.core.l5_expression.vo.obj;

import com.smalljava.core.l5_expression.vo.AbstractOperElement;

public class ObjectCallOperElement extends AbstractOperElement {
	private String opercode=".";
	private String objname;
	private String methodname;
	private String args;
	
	public String getObjname() {
		return objname;
	}
	public void setObjname(String objname) {
		this.objname = objname;
	}
	public String getMethodname() {
		return methodname;
	}
	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public String getOpercode() {
		return opercode;
	}
	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}


}
