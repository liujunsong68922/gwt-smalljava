package com.smalljava.core.analyse.l5_expression.manager;

import com.smalljava.core.commonvo.l5_expression.AbstractAST;

public interface IAstPlugin {
	public AbstractAST analyse(String strcode);
}
