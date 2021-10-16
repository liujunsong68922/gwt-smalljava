package com.smalljava.core.l6_vm.objectcall;

import java.util.ArrayList;

import com.smalljava.core.common.VarValue;

public interface IObjectCall {
	public Object objcall(String classname,Object target,String methodname,ArrayList<VarValue> args);
}
