package com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.constvalue;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractLeafElement;

public abstract class AbstractConstDataElement extends AbstractLeafElement {
	private String datatype;
	private String datavalue;
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getDatavalue() {
		return datavalue;
	}
	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
	}
	
}
