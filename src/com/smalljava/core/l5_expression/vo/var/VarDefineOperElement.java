package com.smalljava.core.l5_expression.vo.var;

import com.smalljava.core.l5_expression.vo.AbstractOperElement;

/**
 * MEMO:变量定义的表达式
 * @author liujunsong
 *
 */
public class VarDefineOperElement extends AbstractOperElement {
	/**
	 * MEMO:数据类型定义
	 */
	private String datatype;
	/**
	 * MEMO：变量名
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
