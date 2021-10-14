package com.smalljava.core.l5_expression.vo;

import java.util.ArrayList;

/**
 * MEMO:高度抽象的AST节点，提供树状结构支持
 * @author liujunsong
 *
 */
public abstract class AbstractAST {
	
	//子节点
	private ArrayList<RootAST> children = new ArrayList<RootAST>();
	
	//父节点
	private AbstractAST parent;
	
	public ArrayList<RootAST> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<RootAST> children) {
		this.children = children;
	}

	public AbstractAST getParent() {
		return parent;
	}

	public void setParent(AbstractAST parent) {
		this.parent = parent;
	}

	/**
	 * 输入：nodeString 输出：查找到的第一个操作符（按照操作符的优先级别来查找）
	 * 
	 * @return
	 */
//	private AstOperAndPos getFirstOperCode(String nodeString,String opers[]) {
//		// 如果本身要查找的内容已经是一个操作符，则退出查找
//		if ( nodeString.equals("&&") 
//				||  nodeString.equals("||") 
//				||  nodeString.equals("==") 
//				||  nodeString.equals(">=")
//				||  nodeString.equals("<=")) {
//			return null;
//		}
//
//		//String opers[] = new String[] { "&&", "||", "==", ">=", "<=", ">", "<", "=", "+", "-", "*", "/" };
//
//		int i = 0;
//		StringFindUtil util = new StringFindUtil();
//		for (i = 0; i < opers.length; i++) {
//			int ipos = util.findfirstStringForAST( nodeString, opers[i]);
//			if (ipos >= 0) {
//				AstOperAndPos oap = new AstOperAndPos();
//				oap.setIpos(ipos);
//				oap.setOpercode(opers[i]);
//				return oap;
//			}
//		}
//
//		// 找不到操作符，返回一个空值
//		return null;
//	}
	

}
