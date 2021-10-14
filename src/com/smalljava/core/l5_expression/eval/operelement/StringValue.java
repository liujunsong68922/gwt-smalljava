package com.smalljava.core.l5_expression.eval.operelement;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * ����������͵Ĳ�����װ����
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
		logger.error("�﷨����Stringû�м�������");
		return null;		
	}
	
	/**
	 * �˷�
	 * @param s1
	 * @return
	 */
	public StringValue doMulti(String s1) {
		logger.error("�﷨����Stringû�г˷�����");
		return null;		
	}
	
	/**
	 * ����
	 * @param s1
	 * @return
	 */
	public StringValue doDevide(String s1) {
		logger.error("�﷨����Stringû�г�������");
		return null;		
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
