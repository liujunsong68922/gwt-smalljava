package com.smalljava.core.l5_expression.vo.obj;

import com.smalljava.core.l5_expression.vo.AbstractOperElement;

/**
 * MEMO：对象调用表达式定义
 * @author liujunsong
 *
 */
public class ObjectCallOperElement extends AbstractOperElement {
	//操作符固定为"."
	private String opercode=".";
	//对象的变量名
	private String objname;
	//调用的方法名
	private String methodname;
	//参数的列表字符串形式，逗号分割，分解以后放置在children里面，都分析为表达式
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
