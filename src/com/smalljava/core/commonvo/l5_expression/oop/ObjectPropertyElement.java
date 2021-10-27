package com.smalljava.core.commonvo.l5_expression.oop;

import com.smalljava.core.commonvo.l5_expression.AbstractOperElement;

public class ObjectPropertyElement extends AbstractOperElement {
	private String opercode=".";
	private String objname;
	private String propertyname;
	
	public String getOpercode() {
		return opercode;
	}
	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	public String getObjname() {
		return objname;
	}
	public void setObjname(String objname) {
		this.objname = objname;
	}
	public String getPropertyname() {
		return propertyname;
	}
	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}
	
	
}
