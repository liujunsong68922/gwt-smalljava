package com.smalljava.core.l6_vm.objectcall;

import java.util.ArrayList;

public interface IObjectCall {
	public Object objcall(String classname,Object target,String methodname,Object args);
}
