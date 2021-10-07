package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.operelement;

import com.liu.gwt.gwt_smalljava.common.VarValue;

//import com.liu.smalljavav2.common.VarValue;

/**
 * ���boolean���͵Ĳ�����װ����
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
	 * �߼���
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
	 * �߼���
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
	 * �߼���
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
	 * ����ж�
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
