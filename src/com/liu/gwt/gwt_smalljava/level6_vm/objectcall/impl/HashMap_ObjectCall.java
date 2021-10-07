package com.liu.gwt.gwt_smalljava.level6_vm.objectcall.impl;

import java.util.HashMap;

import com.liu.gwt.gwt_smalljava.level6_vm.objectcall.IJava_ObjectCall;

public class HashMap_ObjectCall implements IJava_ObjectCall {


	@Override
	public Object objectCall(Object object, String methodname) {
		if(methodname.equals("size")) {
			return this.size(object);
		}
		
		
		consoleLog("Unknown methodname:"+methodname);
		return null;
	}
	
	public Object size(Object object) {
		HashMap map = (HashMap) object;
		return map.size();
	}

	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[JVM][HashMap_ObjectCall]" + message );
	}-*/;
		
}
