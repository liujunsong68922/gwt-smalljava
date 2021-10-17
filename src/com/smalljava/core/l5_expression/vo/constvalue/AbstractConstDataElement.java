package com.smalljava.core.l5_expression.vo.constvalue;

import com.smalljava.core.l5_expression.vo.AbstractLeafElement;


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
