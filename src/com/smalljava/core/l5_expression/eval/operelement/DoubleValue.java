package com.smalljava.core.l5_expression.eval.operelement;

import com.smalljava.core.common.VarValue;

public class DoubleValue extends VarValue {

	public DoubleValue() {
		this.vartype = "double";
	}

	public DoubleValue(String s1) {
		this.vartype = "double";
		this.varsvalue = ""+Float.parseFloat(s1);
	}

	public DoubleValue doAdd(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = Double.parseDouble(this.varsvalue) + oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public DoubleValue doDeAdd(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = Double.parseDouble(this.varsvalue) - oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public DoubleValue doMulti(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = Double.parseDouble(this.varsvalue) * oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public DoubleValue doDevide(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = Double.parseDouble(this.varsvalue) / oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public boolean doequals(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) == oper2);
	}

	public boolean doNotEquals(String s1) {
		//double oper2 = Double.parseDouble(s1);
		return ! (this.doequals(s1));

	}

	public boolean doGreater(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) > oper2);
	}

	public boolean doGE(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) >= oper2);
	}

	public boolean doLitter(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) < oper2);

	}

	public boolean doLE(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) <= oper2);

	}

}
