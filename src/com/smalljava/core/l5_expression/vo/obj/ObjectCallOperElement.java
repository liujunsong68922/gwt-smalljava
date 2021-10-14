package com.smalljava.core.l5_expression.vo.obj;

import com.smalljava.core.l5_expression.vo.AbstractOperElement;

/**
 * MEMO��������ñ��ʽ����
 * @author liujunsong
 *
 */
public class ObjectCallOperElement extends AbstractOperElement {
	//�������̶�Ϊ"."
	private String opercode=".";
	//����ı�����
	private String objname;
	//���õķ�����
	private String methodname;
	//�������б��ַ�����ʽ�����ŷָ�ֽ��Ժ������children���棬������Ϊ���ʽ
	private String args;
	
	public String getObjname() {
		return objname;
	}
	public void setObjname(String objname) {
		this.objname = objname;
	}
	public String getMethodname() {
		return methodname;
	}
	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public String getOpercode() {
		return opercode;
	}
	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}


}
