package com.smalljava.core.l5_expression.eval.operelement;

import com.smalljava.core.common.VarValue;

/**
 * 针对Double类型的操作封装符号
 * 
 * @author liujunsong
 *
 */
public class DoubleValue extends VarValue {

	public DoubleValue() {
		this.vartype = "double";
	}

	public DoubleValue(String s1) {
		this.vartype = "double";
		this.varsvalue = ""+Float.parseFloat(s1);
	}

	/**
	 * 加法
	 * 
	 * @param s1
	 * @return
	 */
	public DoubleValue doAdd(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = Double.parseDouble(this.varsvalue) + oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	/**
	 * 减法
	 * 
	 * @param s1
	 * @return
	 */
	public DoubleValue doDeAdd(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = Double.parseDouble(this.varsvalue) - oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	/**
	 * 乘法
	 * 
	 * @param s1
	 * @return
	 */
	public DoubleValue doMulti(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = Double.parseDouble(this.varsvalue) * oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	/**
	 * 除法
	 * 
	 * @param s1
	 * @return
	 */
	public DoubleValue doDevide(String s1) {
		double oper2 = Double.parseDouble(s1);
		double iresult = Double.parseDouble(this.varsvalue) / oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	/**
	 * 相等判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) == oper2);
	}

	/**
	 * 不相等判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		//double oper2 = Double.parseDouble(s1);
		return ! (this.doequals(s1));

	}

	/**
	 * 大于判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) > oper2);
	}

	/**
	 * 大于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) >= oper2);
	}

	/**
	 * 小于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) < oper2);

	}

	/**
	 * 小于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) <= oper2);

	}

}
