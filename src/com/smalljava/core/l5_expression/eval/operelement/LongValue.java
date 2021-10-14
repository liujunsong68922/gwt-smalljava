package com.smalljava.core.l5_expression.eval.operelement;

import com.smalljava.core.common.VarValue;

/**
 * 针对Long类型的操作封装符号
 * 
 * @author liujunsong
 *
 */
public class LongValue extends VarValue {

	public LongValue() {
		this.vartype = "long";
	}

	public LongValue(String s1) {
		this.vartype = "long";
		//需要把字符串里面的L,l去掉
		s1=s1.replaceAll("L", "");
		s1=s1.replaceAll("l", "");
		this.varsvalue = ""+Long.parseLong(s1);
	}

	/**
	 * 加法
	 * 
	 * @param s1
	 * @return
	 */
	public LongValue doAdd(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = Long.parseLong(this.varsvalue) + oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	/**
	 * 减法
	 * 
	 * @param s1
	 * @return
	 */
	public LongValue doDeAdd(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = Long.parseLong(this.varsvalue) - oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	/**
	 * 乘法
	 * 
	 * @param s1
	 * @return
	 */
	public LongValue doMulti(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = Long.parseLong(this.varsvalue) * oper2;
		this.varsvalue = ""+iresult;
		return this;
	}

	/**
	 * 除法
	 * 
	 * @param s1
	 * @return
	 */
	public LongValue doDevide(String s1) {
		long oper2 = Long.parseLong(s1);
		long iresult = Long.parseLong(this.varsvalue) / oper2;
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
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) == oper2);
	}

	/**
	 * 不相等判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		long oper2 = Long.parseLong(s1);
		return !(Long.parseLong(this.varsvalue) == oper2);

	}

	/**
	 * 大于判断
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) > oper2);
	}

	/**
	 * 大于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) >= oper2);
	}

	/**
	 * 小于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) < oper2);

	}

	/**
	 * 小于等于
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) <= oper2);
	}

}
