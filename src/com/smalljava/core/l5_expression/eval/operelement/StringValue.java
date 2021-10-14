package com.smalljava.core.l5_expression.eval.operelement;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * 针对整数类型的操作封装符号
 * @author liujunsong
 *
 */
public class StringValue extends VarValue {
	private Logger logger = LoggerFactory.getLogger(StringValue.class);
	
	public StringValue() {
		this.vartype = "String";
	}
	
	public StringValue(String s1) {
		this.vartype = "String";
		this.varsvalue = s1;
	}
	
	/**
	 * 加法
	 * @param s1
	 * @return
	 */
	public StringValue doAdd(String s1) {
		String iresult = this.varsvalue + s1;
		this.varsvalue = iresult;
		return this;
	}
	/**
	 * 减法
	 * @param s1
	 * @return
	 */
	public StringValue doDeAdd(String s1) {
		logger.error("语法错误，String没有减法运算");
		return null;		
	}
	
	/**
	 * 乘法
	 * @param s1
	 * @return
	 */
	public StringValue doMulti(String s1) {
		logger.error("语法错误，String没有乘法运算");
		return null;		
	}
	
	/**
	 * 除法
	 * @param s1
	 * @return
	 */
	public StringValue doDevide(String s1) {
		logger.error("语法错误，String没有除法运算");
		return null;		
	}
	
	/**
	 * 相等判断
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		return (this.varsvalue.equals(s1));
	}
	
	/**
	 * 不相等判断
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		 
		return (! this.varsvalue.equals(s1));
		
	}

	/**
	 * 大于判断
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		 
		return (this.varsvalue.compareTo(s1)>0);		
	}
	
	/**
	 * 大于等于
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		 
		return (this.varsvalue.compareTo(s1)>=0);		
	}
	
	/**
	 * 小于
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		 
		return (this.varsvalue.compareTo(s1)<0);		
		
	}
	
	/**
	 * 小于等于
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		 
		return (this.varsvalue.compareTo(s1)<=0);		
		
	}

}
