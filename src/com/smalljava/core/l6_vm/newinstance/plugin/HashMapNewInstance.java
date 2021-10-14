package com.smalljava.core.l6_vm.newinstance.plugin;

import java.util.HashMap;

import com.smalljava.core.l6_vm.newinstance.INewInstance;

public class HashMapNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new HashMap();
	}

}
