package com.liu.gwt.gwt_smalljava.level6_vm;

import com.liu.gwt.gwt_smalljava.level6_vm.newinstance.IJava_NewInstance;
import com.liu.gwt.gwt_smalljava.level6_vm.newinstance.Java_NewInstanceImpl;
import com.liu.gwt.gwt_smalljava.level6_vm.objectcall.IJava_ObjectCall;
import com.liu.gwt.gwt_smalljava.level6_vm.objectcall.Java_ObjectCallImpl;

public class JavaVMImpl 
	implements IJavaVM, IJava_NewInstance, IJava_ObjectCall{

	
	@Override
	public Object newInstance(String classname) {
		Java_NewInstanceImpl impl1 = new Java_NewInstanceImpl();
		return impl1.newInstance(classname);

	}

	@Override
	public Object objectCall(Object object, String methodname) {
		Java_ObjectCallImpl impl2 = new Java_ObjectCallImpl();
		return impl2.objectCall(object, methodname);
	}

	
}


