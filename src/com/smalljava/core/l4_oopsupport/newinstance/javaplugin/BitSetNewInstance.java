package com.smalljava.core.l4_oopsupport.newinstance.javaplugin;

import java.util.ArrayList;
import java.util.BitSet;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l4_oopsupport.newinstance.INewInstance;

public class BitSetNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new BitSet();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		return new BitSet();
	}

}
