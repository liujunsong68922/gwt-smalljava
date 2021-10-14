package com.smalljava.core.l5_expression.analyse;

import com.smalljava.core.l5_expression.vo.AbstractAST;

/**
 * MEMO：统一的插件分析接口
 * @author liujunsong
 *
 */
public interface IAstPlugin {
	/**
	 * MEMO：把Java代码字符串分解为一个AST节点，只分解一层
	 * @param strcode
	 * @return 符合条件，返回AST节点，不符合条件，返回null，代表分解失败
	 */
	public AbstractAST analyse(String strcode);
}
