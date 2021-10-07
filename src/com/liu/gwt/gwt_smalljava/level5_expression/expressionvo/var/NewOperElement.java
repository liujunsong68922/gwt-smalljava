package com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractOperElement;

//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractOperElement;

/**
 * new ������ı��ʽ
 * @author liujunsong
 *
 */
public class NewOperElement extends AbstractOperElement {
	/**
	 * MEMO:������̶�Ϊ new 
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
