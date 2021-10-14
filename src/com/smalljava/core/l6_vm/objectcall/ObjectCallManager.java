package com.smalljava.core.l6_vm.objectcall;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectCallManager implements IObjectCall {
	public static HashMap<String,IObjectCall> callersmap = new HashMap<String,IObjectCall>();
	
	public ObjectCallManager() {
		if(callersmap.size()==0) {
			//init map itself.
		}
	}

	@Override
	public Object objcall(String classname,Object target, String methodname, Object args) {
		IObjectCall caller = callersmap.get(classname);
		if(caller != null) {
			return caller.objcall(classname, target, methodname, args);
		}
		
		System.out.println("Unsupported class:"+classname);
		return null;
	}
	
	
}
