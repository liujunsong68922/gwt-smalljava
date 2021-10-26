package com.smalljava.core.l5_expression.analyse;

import com.smalljava.core.l5_expression.vo.AbstractAST;

public interface IAstPlugin {
	public AbstractAST analyse(String strcode);
}
