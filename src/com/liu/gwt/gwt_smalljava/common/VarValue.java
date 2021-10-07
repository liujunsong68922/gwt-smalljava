package com.liu.gwt.gwt_smalljava.common;

public class VarValue {
	//������
	private String varname=null;
	//������������
	public String vartype=null;
	//����ֵ�����ַ�����ʾ�ַ������͵�ֵ��
	//����Ƕ���������һ��uuid�������ڶ����ڴ���е�ӳ��λ��
	public String varsvalue="";
	
	public String getVarname() {
		return varname;
	}
	public void setVarname(String varname) {
		this.varname = varname;
	}
	public String getVartype() {
		return vartype;
	}
	public void setVartype(String vartype) {
		this.vartype = vartype;
	}
	public String getVarsvalue() {
		return varsvalue;
	}
	public void setVarsvalue(String varsvalue) {
		this.varsvalue = varsvalue;
	}

	public String toString() {
		String s1= varname +":"+vartype +":"+ (varsvalue==null?"null":"")+varsvalue;
		return s1;
	}
	
	
}
