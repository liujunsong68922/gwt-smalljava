package com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance;

import java.util.ArrayList;

import com.smalljava.core.common.VarValue;

public interface INewInstance {
	//newInstance,without params
	//newInstance 调用，不带参数
	public Object newInstance(String classname);
	
	//newInstance, with params;
	//newInstance调用，带参数，所有参数封装为ArrayList<VarValue>
	public Object newInstance(String classname, ArrayList<VarValue> args);
}
