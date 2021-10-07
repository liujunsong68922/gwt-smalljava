package com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractOperElement;

//import com.liu.smalljavav2.level5_expression.expressionvo.AbstractOperElement;

/**
 * MEMO:��������ı��ʽ
 * @author liujunsong
 *
 */
public class VarDefineOperElement extends AbstractOperElement {
	/**
	 * MEMO:�������Ͷ���
	 */
	private String datatype;
	/**
	 * MEMO��������
	 */
	private String varname;
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getVarname() {
		return varname;
	}
	public void setVarname(String varname) {
		this.varname = varname;
	}
	
	
}
