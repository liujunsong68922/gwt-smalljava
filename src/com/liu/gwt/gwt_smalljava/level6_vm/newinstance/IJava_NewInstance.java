package com.liu.gwt.gwt_smalljava.level6_vm.newinstance;

public interface IJava_NewInstance {
	/**
	 * 不带参数的new 方法调用
	 * 模拟实现原本属于JVM的 Class.newInstance()方法
	 * 因为这一方法在GWT环境下不被支持
	 * @param classname
	 * @return
	 */
	public Object newInstance(String classname);
}
