package com.smalljava.core.l4_oopsupport.newinstance.javaplugin;

import java.util.ArrayList;
import java.util.HashMap;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l4_oopsupport.newinstance.INewInstance;

public class HashMapNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new HashMap();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		return new HashMap();
	}
}
