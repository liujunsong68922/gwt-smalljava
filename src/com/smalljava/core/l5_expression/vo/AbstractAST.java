package com.smalljava.core.l5_expression.vo;

import java.util.ArrayList;

public abstract class AbstractAST {
	private ArrayList<RootAST> children = new ArrayList<RootAST>();
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
}
