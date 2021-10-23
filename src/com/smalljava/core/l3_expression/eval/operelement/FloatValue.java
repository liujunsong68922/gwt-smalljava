package com.smalljava.core.l3_expression.eval.operelement;

import com.smalljava.core.common.VarValue;

public class FloatValue extends VarValue {

	public FloatValue() {
		this.vartype = "float";
	}

	public FloatValue(String s1) {
		this.vartype = "float";
		this.varsvalue = ""+Float.parseFloat(s1);
	}

	public FloatValue doAdd(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = Float.parseFloat(this.varsvalue) + oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public FloatValue doDeAdd(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = Float.parseFloat(this.varsvalue) - oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public FloatValue doMulti(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = Float.parseFloat(this.varsvalue) * oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public FloatValue doDevide(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = Float.parseFloat(this.varsvalue) / oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public boolean doequals(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) == oper2);
	}

	public boolean doNotEquals(String s1) {
		//float oper2 = Float.parseFloat(s1);
		return ! (this.varsvalue.equals(s1));

	}

	public boolean doGreater(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(s1) > oper2);
	}

	public boolean doGE(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) > oper2);
	}

	public boolean doLitter(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) < oper2);
	}
	public boolean doLE(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) <= oper2);

	}

}
