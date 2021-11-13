package com.smalljava.core.l9_space.vartable.hashmapimpl;


public class L3_HashMapMethodInstanceVarTableImpl extends AbstractHashMapVarTableImpl {

	public L3_HashMapMethodInstanceVarTableImpl(String stype, L2_HashMapClassInstanceVarTableImpl parentnode) {
		super(stype, parentnode);
	}
	
	public L3_HashMapMethodInstanceVarTableImpl(String stype, L2_HashMapClassStaticVarTableImpl parentnode) {
		super(stype, parentnode);
	}	
}
