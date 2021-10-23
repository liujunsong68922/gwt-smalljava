package com.smalljava.core.l3_expression.analyse;

import com.smalljava.core.l3_expression.vo.AbstractAST;

public interface IAstPlugin {
	public AbstractAST analyse(String strcode);
}
