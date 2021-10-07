package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.operelement;

import com.liu.gwt.gwt_smalljava.common.VarValue;

//import com.liu.smalljavav2.common.VarValue;

/**
 * ���Double���͵Ĳ�����װ����
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
	 * �ӷ�
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
	 * ����
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
	 * �˷�
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
	 * ����
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
	 * ����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) == oper2);
	}

	/**
	 * ������ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		//double oper2 = Double.parseDouble(s1);
		return ! (this.doequals(s1));

	}

	/**
	 * �����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) > oper2);
	}

	/**
	 * ���ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) >= oper2);
	}

	/**
	 * С��
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) < oper2);

	}

	/**
	 * С�ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		double oper2 = Double.parseDouble(s1);
		return (Double.parseDouble(this.varsvalue) <= oper2);

	}

}
