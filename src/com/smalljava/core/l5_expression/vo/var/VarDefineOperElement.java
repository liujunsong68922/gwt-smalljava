package com.smalljava.core.l5_expression.vo.var;

import com.smalljava.core.l5_expression.vo.AbstractOperElement;

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
