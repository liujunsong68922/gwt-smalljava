package com.smalljava.core.l5_expression.eval.operelement;

import com.smalljava.core.common.VarValue;

/**
 * 针对整数类型的操作封装符号
 * @author liujunsong
 *
 */
public class IntegerValue extends VarValue {
	
	public IntegerValue() {
		this.vartype = "int";
	}
	
	public IntegerValue(String s1) {
		this.vartype = "int";
		this.varsvalue = ""+Integer.parseInt(s1);
	}
	
	/**
	 * 加法
	 * @param s1
	 * @return
	 */
	public IntegerValue doAdd(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = Integer.parseInt(this.varsvalue) + oper2;
		this.varsvalue = ""+iresult;
		return this;
	}
	/**
	 * 减法
	 * @param s1
	 * @return
	 */
	public IntegerValue doDeAdd(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = Integer.parseInt(this.varsvalue) - oper2;
		this.varsvalue = ""+iresult;
		return this;		
	}
	
	/**
	 * 乘法
	 * @param s1
	 * @return
	 */
	public IntegerValue doMulti(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = Integer.parseInt(this.varsvalue) * oper2;
		this.varsvalue = ""+iresult;
		return this;		
	}
	
	/**
	 * 除法
	 * @param s1
	 * @return
	 */
	public IntegerValue doDevide(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		int iresult = Integer.parseInt(this.varsvalue) / oper2;
		this.varsvalue = ""+iresult;
		return this;		
	}
	
	/**
	 * 相等判断
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) == oper2);
	}
	
	/**
	 * 不相等判断
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		//int oper2;
		//oper2 = (int)Double.parseDouble(s1);
		return !(this.doequals(s1));
		
	}

	/**
	 * 大于判断
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) > oper2);		
	}
	
	/**
	 * 大于等于
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) > oper2);		
	}
	
	/**
	 * 小于
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) < oper2);		
		
	}
	
	/**
	 * 小于等于
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) <= oper2);		
		
	}

}
