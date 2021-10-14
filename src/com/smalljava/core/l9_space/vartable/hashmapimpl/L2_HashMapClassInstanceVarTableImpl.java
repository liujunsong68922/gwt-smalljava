package com.smalljava.core.l9_space.vartable.hashmapimpl;

/**
 * 这个类是一个Class的实例级别的HashMap的变量表定义
 * @author liujunsong
 *
 */
public class L2_HashMapClassInstanceVarTableImpl extends AbstractHashMapVarTableImpl {
	/**
	 * MEMO：这个类必须依赖于另一个类来构造，不能凭空构造
	 * @param stype
	 */
	public L2_HashMapClassInstanceVarTableImpl(String stype,L2_HashMapClassStaticVarTableImpl parent) {
		super(stype,parent);
	}

	/**
	 * 挂载父节点
	 * @param pnode
	 */
	public void setParent(L2_HashMapClassStaticVarTableImpl pnode) {
		//挂载到父级的变量表上去
		this.setParentVarTable(pnode);
		//设置根标志为false,不是根节点
		this.setRootflag(false);
	}
	
}
