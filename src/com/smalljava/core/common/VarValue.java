package com.smalljava.core.common;

public class VarValue {
	//变量名
	private String varname=null;
	//变量基础类型
	public String vartype=null;
	//变量值，用字符串表示字符串类型的值，
	//如果是对象，这里存放一个uuid，代表在对象内存表中的映射位置
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
	
	public String toJSONString(){
		String sret="{";
		sret += "varname: "+ varname!=null?varname:"null" +",";
		sret += "vartype: "+ vartype!=null?vartype:"null" +",";
		sret += "varsvalue:" + varsvalue!=null?varsvalue:"null";
		sret += "}";
		return sret;
	}
	
	
}
