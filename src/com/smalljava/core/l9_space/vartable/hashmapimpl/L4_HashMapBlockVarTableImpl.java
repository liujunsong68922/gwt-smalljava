package com.smalljava.core.l9_space.vartable.hashmapimpl;

//import com.smalljava.l0_space.IVarTable;

/**
 * 这个类是一个Root级别的HashMap的变量表定义
 * @author liujunsong
 *
 */
public class L4_HashMapBlockVarTableImpl extends AbstractHashMapVarTableImpl {

	/**
	 * MEMO:挂载到一个L3的节点上
	 * @param stype
	 * @param parentnode
	 */
	public L4_HashMapBlockVarTableImpl(String stype, AbstractHashMapVarTableImpl parentnode) {
		super(stype, parentnode);
	}

	/**
	 * MEMO：挂载到另一个L4的节点上
	 * @param stype
	 * @param parentnode
	 */
	public L4_HashMapBlockVarTableImpl(String stype, L4_HashMapBlockVarTableImpl parentnode) {
		super(stype, parentnode);
	}
	

	
}
