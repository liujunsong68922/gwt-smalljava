package com.smalljava.core.commonvo.l5_expression.var;

import com.smalljava.core.commonvo.l5_expression.AbstractOperElement;

public class VarDefineOperElement extends AbstractOperElement {
	private String datatype;
	private String varname;
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getVarname() {
		return varname;
	}
	public void setVarname(String varname) {
		this.varname = varname;
	}
	
	
}
