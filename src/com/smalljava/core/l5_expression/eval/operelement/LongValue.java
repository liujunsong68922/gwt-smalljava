package com.smalljava.core.l5_expression.eval.operelement;

import com.smalljava.core.common.VarValue;

public class LongValue extends VarValue {

	public LongValue() {
		this.vartype = "long";
	}

	public LongValue(String s1) {
		this.vartype = "long";
		s1=s1.replaceAll("L", "");
		s1=s1.replaceAll("l", "");
		this.varsvalue = ""+Long.parseLong(s1);
	}

	public LongValue doAdd(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = Long.parseLong(this.varsvalue) + oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public LongValue doDeAdd(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = Long.parseLong(this.varsvalue) - oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public LongValue doMulti(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = Long.parseLong(this.varsvalue) * oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public LongValue doDevide(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = Long.parseLong(this.varsvalue) / oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public boolean doequals(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) == oper2);
	}

	public boolean doNotEquals(String s1) {
		long oper2 = Long.parseLong(s1);
		return !(Long.parseLong(this.varsvalue) == oper2);

	}

	public boolean doGreater(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) > oper2);
	}

	public boolean doGE(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) >= oper2);
	}

	public boolean doLitter(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) < oper2);

	}

	public boolean doLE(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) <= oper2);
	}

}
