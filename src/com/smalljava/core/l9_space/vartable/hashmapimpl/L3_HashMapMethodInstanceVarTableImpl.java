package com.smalljava.core.l9_space.vartable.hashmapimpl;

/**
 *MEMO： 这个类是一个Method级别的VarTable定义
 * @author liujunsong
 *
 */
public class L3_HashMapMethodInstanceVarTableImpl extends AbstractHashMapVarTableImpl {
	/**
	 * MEMO:基于L2,定义L3级别变量表
	 * @param stype
	 * @param parentnode
	 */
	public L3_HashMapMethodInstanceVarTableImpl(String stype, L2_HashMapClassInstanceVarTableImpl parentnode) {
		super(stype, parentnode);
	}
	
}
