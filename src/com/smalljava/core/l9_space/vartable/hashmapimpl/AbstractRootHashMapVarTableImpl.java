package com.smalljava.core.l9_space.vartable.hashmapimpl;

import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * MEMO：变量表的支持类，这个变量表是没有parent节点的，只有本地变量表
 * 
 * @author liujunsong
 *
 */
public abstract class AbstractRootHashMapVarTableImpl extends AbstractHashMapVarTableImpl implements IVarTable {
	//private Logger logger = LoggerFactory.getLogger(AbstractRootHashMapVarTableImpl.class);
	
	public AbstractRootHashMapVarTableImpl(String stype) {
		super(stype);
	}
	
	private AbstractRootHashMapVarTableImpl() {
		super("");
	}
}
