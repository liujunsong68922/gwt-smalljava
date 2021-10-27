package com.smalljava.core.eval.l5_expression.operelement;

import com.smalljava.core.common.VarValue;

public class BooleanValue extends VarValue {
	

	public BooleanValue(String s1) {
		this.vartype = "boolean";
		s1 = s1.trim();
		this.varsvalue = s1.equalsIgnoreCase("true")?"true":"false";
	}
	
	public BooleanValue(boolean b1) {
		this.vartype = "boolean";
		this.varsvalue = b1?"true":"false";
	}
	
	public BooleanValue doAnd(String s1) {
		s1 = s1.trim();
		boolean b1 = s1.equalsIgnoreCase("true");
		this.varsvalue = (Boolean.parseBoolean(this.varsvalue) && b1) ?"true":"false";
		return this;
	}
	
	public BooleanValue doOr(String s1) {
		s1 = s1.trim();
		boolean b1 = s1.equalsIgnoreCase("true");
		this.varsvalue = Boolean.parseBoolean(this.varsvalue) || b1?"true":"false";
		return this;
	}
	
	public BooleanValue doNot() {
		if(Boolean.parseBoolean(this.varsvalue)) {
			this.varsvalue = "false";
		}else {
			this.varsvalue = "true";
		}
		return this;
	}
	
	public boolean doequals(String s1) {
		if (this.varsvalue.equalsIgnoreCase(s1) ) {
			return true;
		}else {
			return false;
		}
	}

}
