package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.operelement;

import com.liu.gwt.gwt_smalljava.common.VarValue;

//import com.liu.smalljavav2.common.VarValue;

/**
 * ���Long���͵Ĳ�����װ����
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
		//��Ҫ���ַ��������L,lȥ��
		s1=s1.replaceAll("L", "");
		s1=s1.replaceAll("l", "");
		this.varsvalue = ""+Long.parseLong(s1);
	}

	/**
	 * �ӷ�
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
	 * ����
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
	 * �˷�
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
	 * ����
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
	 * ����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) == oper2);
	}

	/**
	 * ������ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		long oper2 = Long.parseLong(s1);
		return !(Long.parseLong(this.varsvalue) == oper2);

	}

	/**
	 * �����ж�
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) > oper2);
	}

	/**
	 * ���ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) >= oper2);
	}

	/**
	 * С��
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) < oper2);

	}

	/**
	 * С�ڵ���
	 * 
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		long oper2 = Long.parseLong(s1);
		return (Long.parseLong(this.varsvalue) <= oper2);
	}

}
