package com.liu.gwt.gwt_smalljava.level6_vm.objectcall;

import com.liu.gwt.gwt_smalljava.level6_vm.newinstance.IJava_NewInstance;

public interface IJava_ObjectCall {
	/**
	 * 针对Object来进行分支调用，第一步先实现不带参数的调用接口
	 * @param object
	 * @param methodname
	 * @return
	 */
	public Object objectCall(Object object,String methodname);
}
