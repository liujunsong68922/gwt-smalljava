package com.smalljava.core.l5_expression.eval.operelement;

import com.smalljava.core.common.VarValue;

/**
 * 针对boolean类型的操作封装符号
 * @author liujunsong
 *
 */
public class BooleanValue extends VarValue {
	

	public BooleanValue(String s1) {
		this.vartype = "boolean";
		s1 = s1.trim();
		this.varsvalue = s1.equalsIgnoreCase("true")?"true":"false";
	}
	
	public BooleanValue(boolean b1) {
		this.vartype = "boolean";
		this.varsvalue = b1?"true":"false";
	}
	
	/**
	 * 逻辑与
	 * @param s1
	 * @return
	 */
	public BooleanValue doAnd(String s1) {
		s1 = s1.trim();
		boolean b1 = s1.equalsIgnoreCase("true");
		this.varsvalue = (Boolean.parseBoolean(this.varsvalue) && b1) ?"true":"false";
		return this;
	}
	
	/**
	 * 逻辑或
	 * @param s1
	 * @return
	 */
	public BooleanValue doOr(String s1) {
		s1 = s1.trim();
		boolean b1 = s1.equalsIgnoreCase("true");
		this.varsvalue = Boolean.parseBoolean(this.varsvalue) || b1?"true":"false";
		return this;
	}
	
	/**
	 * 逻辑非
	 * @return
	 */
	public BooleanValue doNot() {
		if(Boolean.parseBoolean(this.varsvalue)) {
			this.varsvalue = "false";
		}else {
			this.varsvalue = "true";
		}
		return this;
	}
	
	/**
	 * 相等判断
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		if (this.varsvalue.equalsIgnoreCase(s1) ) {
			return true;
		}else {
			return false;
		}
	}

}
