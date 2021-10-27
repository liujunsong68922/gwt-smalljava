package com.smalljava.core.eval.l5_expression.operelement;

import com.smalljava.core.common.VarValue;

public class IntegerValue extends VarValue {
	
	public IntegerValue() {
		this.vartype = "int";
	}
	
	public IntegerValue(String s1) {
		this.vartype = "int";
		this.varsvalue = ""+Integer.parseInt(s1);
	}
	
	public IntegerValue doAdd(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = Integer.parseInt(this.varsvalue) + oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	public IntegerValue doDeAdd(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = Integer.parseInt(this.varsvalue) - oper2;
		this.varsvalue = ""+iresult;
		return this;		
	}
	

	public IntegerValue doMulti(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = Integer.parseInt(this.varsvalue) * oper2;
		this.varsvalue = ""+iresult;
		return this;		
	}
	
	public IntegerValue doDevide(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = Integer.parseInt(this.varsvalue) / oper2;
		this.varsvalue = ""+iresult;
		return this;		
	}
	
	public boolean doequals(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) == oper2);
	}
	
	public boolean doNotEquals(String s1) {
		//int oper2;
		//oper2 = (int)Double.parseDouble(s1);
		return !(this.doequals(s1));
		
	}

	public boolean doGreater(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) > oper2);		
	}
	
	public boolean doGE(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) > oper2);		
	}
	
	public boolean doLitter(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) < oper2);		
		
	}
	
	public boolean doLE(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) <= oper2);		
		
	}

}
