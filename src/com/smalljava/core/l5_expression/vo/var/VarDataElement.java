package com.smalljava.core.l5_expression.vo.var;

import com.smalljava.core.l5_expression.vo.AbstractLeafElement;

/**
 * MEMO:变量元素定义
 * @author liujunsong
 *
 */
public class VarDataElement extends AbstractLeafElement {
	/**
	 * MEMO：变量元素只有一个字段有效，代表变量R名
	 */
	private String varname;

	public String getVarname() {
		return varname;
	}

	public void setVarname(String varname) {
		this.varname = varname;
	}
	
}
