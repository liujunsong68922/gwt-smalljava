package com.smalljava.core.l5_expression.vo.var;

import com.smalljava.core.l5_expression.vo.AbstractOperElement;

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
