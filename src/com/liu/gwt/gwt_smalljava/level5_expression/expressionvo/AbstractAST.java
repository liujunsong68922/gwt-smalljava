package com.liu.gwt.gwt_smalljava.level5_expression.expressionvo;

import java.util.ArrayList;

/**
 * MEMO:�߶ȳ����AST�ڵ㣬�ṩ��״�ṹ֧��
 * @author liujunsong
 *
 */
public abstract class AbstractAST {
	
	//�ӽڵ�
	private ArrayList<RootAST> children = new ArrayList<RootAST>();
	
	//���ڵ�
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
	 * ���룺nodeString ��������ҵ��ĵ�һ�������������ղ����������ȼ��������ң�
	 * 
	 * @return
	 */
//	private AstOperAndPos getFirstOperCode(String nodeString,String opers[]) {
//		// �������Ҫ���ҵ������Ѿ���һ�������������˳�����
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
//		// �Ҳ���������������һ����ֵ
//		return null;
//	}
	

}
