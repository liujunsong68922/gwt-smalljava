package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.operelement;

import com.liu.gwt.gwt_smalljava.common.VarValue;

//import com.liu.smalljavav2.common.VarValue;

/**
 * ���Float���͵Ĳ�����װ����
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
	 * �ӷ�
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
	 * ����
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
	 * �˷�
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
	 * ����
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
	 * ����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) == oper2);
	}

	/**
	 * ������ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		//float oper2 = Float.parseFloat(s1);
		return ! (this.varsvalue.equals(s1));

	}

	/**
	 * �����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(s1) > oper2);
	}

	/**
	 * ���ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) > oper2);
	}

	/**
	 * С��
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) < oper2);

	}

	/**
	 * С�ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		float oper2 = Float.parseFloat(s1);
		return (Float.parseFloat(this.varsvalue) <= oper2);

	}

}
