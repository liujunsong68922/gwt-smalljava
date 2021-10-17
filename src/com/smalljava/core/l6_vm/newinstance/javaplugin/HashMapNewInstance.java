package com.smalljava.core.l6_vm.newinstance.javaplugin;

import java.util.HashMap;

import com.smalljava.core.l6_vm.newinstance.INewInstance;

public class HashMapNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new HashMap();
	}

	@Override
	public Object newjavaInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object newjvmInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object newgwtuiInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object newSmalljavaInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}

}
