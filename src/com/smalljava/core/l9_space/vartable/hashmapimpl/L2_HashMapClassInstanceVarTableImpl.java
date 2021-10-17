package com.smalljava.core.l9_space.vartable.hashmapimpl;

public class L2_HashMapClassInstanceVarTableImpl extends AbstractHashMapVarTableImpl {
	public L2_HashMapClassInstanceVarTableImpl(String stype,L2_HashMapClassStaticVarTableImpl parent) {
		super(stype,parent);
	}

	public void setParent(L2_HashMapClassStaticVarTableImpl pnode) {
		this.setParentVarTable(pnode);
		this.setRootflag(false);
	}
	
}
