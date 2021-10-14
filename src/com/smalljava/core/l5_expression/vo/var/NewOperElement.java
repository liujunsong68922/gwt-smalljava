package com.smalljava.core.l5_expression.vo.var;

import com.smalljava.core.l5_expression.vo.AbstractOperElement;

/**
 * new 运算符的表达式
 * @author liujunsong
 *
 */
public class NewOperElement extends AbstractOperElement {
	/**
	 * MEMO:运算符固定为 new 
	 */
	private String opercode ="new";
	private String classname ;
	public String getOpercode() {
		return opercode;
	}
	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
}
