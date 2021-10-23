package com.smalljava.core.l3_expression.vo.var;

import com.smalljava.core.l3_expression.vo.AbstractOperElement;

public class NewOperElement extends AbstractOperElement {

	private String opercode ="new";
	private String classname ;
	public String getOpercode() {
		return opercode;
	}
	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
}
