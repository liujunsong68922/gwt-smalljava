package com.smalljava.core.l6_vm.newinstance.smalljavaplugin;

import com.smalljava.core.l6_vm.newinstance.INewInstance;

public class SmallJavaNewInstancePlugin implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return this.newSmalljavaInstance(classname);
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
