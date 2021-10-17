package com.smalljava.core.common;

public class VarValue {
	private String varname=null;
	public String vartype=null;
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
