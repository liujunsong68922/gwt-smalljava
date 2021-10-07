package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.operelement;

import com.liu.gwt.gwt_smalljava.common.VarValue;

//import com.liu.smalljavav2.common.VarValue;

/**
 * ����������͵Ĳ�����װ����
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
	 * �ӷ�
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
	 * ����
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
	 * �˷�
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
	 * ����
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
	 * ����ж�
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) == oper2);
	}
	
	/**
	 * ������ж�
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		//int oper2;
		//oper2 = (int)Double.parseDouble(s1);
		return !(this.doequals(s1));
		
	}

	/**
	 * �����ж�
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) > oper2);		
	}
	
	/**
	 * ���ڵ���
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) > oper2);		
	}
	
	/**
	 * С��
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		
		return (Integer.parseInt(this.varsvalue) < oper2);		
		
	}
	
	/**
	 * С�ڵ���
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		int oper2;
		oper2 = (int)Double.parseDouble(s1);
		return (Integer.parseInt(this.varsvalue) <= oper2);		
		
	}

}
