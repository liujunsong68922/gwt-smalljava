package com.smalljava.core.l5_expression.eval.operelement;

import com.smalljava.core.common.VarValue;

/**
 * 针对Float类型的操作封装符号
 * 
 * @author liujunsong
 *
 */
public class FloatValue extends VarValue {

	public FloatValue() {
		this.vartype = "float";
	}

	public FloatValue(String s1) {
		this.vartype = "float";
		this.varsvalue = ""+Float.parseFloat(s1);
	}

	/**
	 * 加法
	 * 
	 * @param s1
	 * @return
	 */
	public FloatValue doAdd(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = Float.parseFloat(this.varsvalue) + oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	/**
	 * 减法
	 * 
	 * @param s1
	 * @return
	 */
	public FloatValue doDeAdd(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = Float.parseFloat(this.varsvalue) - oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	/**
	 * 乘法
	 * 
	 * @param s1
	 * @return
	 */
	public FloatValue doMulti(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = Float.parseFloat(this.varsvalue) * oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	/**
	 * 除法
	 * 
	 * @param s1
	 * @return
	 */
	public FloatValue doDevide(String s1) {
		float oper2 = Float.parseFloat(s1);
		float iresult = Float.parseFloat(this.varsvalue) / oper2;
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
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) == oper2);
	}

	/**
	 * 不相等判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		//float oper2 = Float.parseFloat(s1);
		return ! (this.varsvalue.equals(s1));

	}

	/**
	 * 大于判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(s1) > oper2);
	}

	/**
	 * 大于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) > oper2);
	}

	/**
	 * 小于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) < oper2);

	}

	/**
	 * 小于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) <= oper2);

	}

}
