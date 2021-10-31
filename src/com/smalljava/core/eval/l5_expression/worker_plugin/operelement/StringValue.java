package com.smalljava.core.eval.l5_expression.worker_plugin.operelement;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

public class StringValue extends VarValue {
	private Logger logger = LoggerFactory.getLogger(StringValue.class);

	public StringValue() {
		this.vartype = "String";
	}

	public StringValue(String s1) {
		this.vartype = "String";
		this.varsvalue = s1;
	}

	public StringValue doAdd(String s1) {
		String iresult = this.varsvalue + s1;
		this.varsvalue = iresult;
		return this;
	}

	public StringValue doDeAdd(String s1) {
		logger.error("string donot support deadd");
		return null;
	}

	public StringValue doMulti(String s1) {
		logger.error("string donot support multi");
		return null;
	}

	public StringValue doDevide(String s1) {
		logger.error("string donnot support devide");
		return null;
	}

	public boolean doequals(String s1) {
		return (this.varsvalue.equals(s1));
	}

	public boolean doNotEquals(String s1) {
		return (!this.varsvalue.equals(s1));
	}

	public boolean doGreater(String s1) {
		return (this.varsvalue.compareTo(s1) > 0);
	}

	public boolean doGE(String s1) {
		return (this.varsvalue.compareTo(s1) >= 0);
	}

	public boolean doLitter(String s1) {
		return (this.varsvalue.compareTo(s1) < 0);
	}

	public boolean doLE(String s1) {
		return (this.varsvalue.compareTo(s1) <= 0);
	}
}
