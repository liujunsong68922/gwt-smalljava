package com.smalljava.core.l9_space.vartable.hashmapimpl;

/**
 * �������һ��Class��ʵ�������HashMap�ı�������
 * @author liujunsong
 *
 */
public class L2_HashMapClassInstanceVarTableImpl extends AbstractHashMapVarTableImpl {
	/**
	 * MEMO������������������һ���������죬����ƾ�չ���
	 * @param stype
	 */
	public L2_HashMapClassInstanceVarTableImpl(String stype,L2_HashMapClassStaticVarTableImpl parent) {
		super(stype,parent);
	}

	/**
	 * ���ظ��ڵ�
	 * @param pnode
	 */
	public void setParent(L2_HashMapClassStaticVarTableImpl pnode) {
		//���ص������ı�������ȥ
		this.setParentVarTable(pnode);
		//���ø���־Ϊfalse,���Ǹ��ڵ�
		this.setRootflag(false);
	}
	
}
