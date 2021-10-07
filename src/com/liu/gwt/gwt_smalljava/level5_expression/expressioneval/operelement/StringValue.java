package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.operelement;

import com.liu.gwt.gwt_smalljava.common.VarValue;

//import com.liu.smalljavav2.common.VarValue;

/**
 * ����������͵Ĳ�����װ����
 * @author liujunsong
 *
 */
public class StringValue extends VarValue {
	
	public StringValue() {
		this.vartype = "String";
	}
	
	public StringValue(String s1) {
		this.vartype = "String";
		this.varsvalue = s1;
	}
	
	/**
	 * �ӷ�
	 * @param s1
	 * @return
	 */
	public StringValue doAdd(String s1) {
		String iresult = this.varsvalue + s1;
		this.varsvalue = iresult;
		return this;
	}
	/**
	 * ����
	 * @param s1
	 * @return
	 */
	public StringValue doDeAdd(String s1) {
		System.out.println("�﷨����Stringû�м�������");
		return this;		
	}
	
	/**
	 * �˷�
	 * @param s1
	 * @return
	 */
	public StringValue doMulti(String s1) {
		System.out.println("�﷨����Stringû�г˷�����");
		return this;		
	}
	
	/**
	 * ����
	 * @param s1
	 * @return
	 */
	public StringValue doDevide(String s1) {
		System.out.println("�﷨����Stringû�г�������");
		return this;		
	}
	
	/**
	 * ����ж�
	 * @param s1
	 * @return
	 */
	public boolean doequals(String s1) {
		return (this.varsvalue.equals(s1));
	}
	
	/**
	 * ������ж�
	 * @param s1
	 * @return
	 */
	public boolean doNotEquals(String s1) {
		 
		return (! this.varsvalue.equals(s1));
		
	}

	/**
	 * �����ж�
	 * @param s1
	 * @return
	 */
	public boolean doGreater(String s1) {
		 
		return (this.varsvalue.compareTo(s1)>0);		
	}
	
	/**
	 * ���ڵ���
	 * @param s1
	 * @return
	 */
	public boolean doGE(String s1) {
		 
		return (this.varsvalue.compareTo(s1)>=0);		
	}
	
	/**
	 * С��
	 * @param s1
	 * @return
	 */
	public boolean doLitter(String s1) {
		 
		return (this.varsvalue.compareTo(s1)<0);		
		
	}
	
	/**
	 * С�ڵ���
	 * @param s1
	 * @return
	 */
	public boolean doLE(String s1) {
		 
		return (this.varsvalue.compareTo(s1)<=0);		
		
	}

}
