package com.smalljava.core.l9_space.vartable.hashmapimpl;

import com.smalljava.core.l9_space.vartable.IVarTable;

public abstract class AbstractRootHashMapVarTableImpl extends AbstractHashMapVarTableImpl implements IVarTable {
	//private Logger logger = LoggerFactory.getLogger(AbstractRootHashMapVarTableImpl.class);
	
	public AbstractRootHashMapVarTableImpl(String stype) {
		super(stype);
	}
	
	private AbstractRootHashMapVarTableImpl() {
		super("");
	}
}
