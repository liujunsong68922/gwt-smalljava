package com.smalljava.core.l9_space.vartable.hashmapimpl;

//import com.smalljava.l0_space.IVarTable;

/**
 * �������һ��Root�����HashMap�ı�������
 * @author liujunsong
 *
 */
public class L4_HashMapBlockVarTableImpl extends AbstractHashMapVarTableImpl {

	/**
	 * MEMO:���ص�һ��L3�Ľڵ���
	 * @param stype
	 * @param parentnode
	 */
	public L4_HashMapBlockVarTableImpl(String stype, AbstractHashMapVarTableImpl parentnode) {
		super(stype, parentnode);
	}

	/**
	 * MEMO�����ص���һ��L4�Ľڵ���
	 * @param stype
	 * @param parentnode
	 */
	public L4_HashMapBlockVarTableImpl(String stype, L4_HashMapBlockVarTableImpl parentnode) {
		super(stype, parentnode);
	}
	

	
}
