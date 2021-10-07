package com.liu.gwt.gwt_smalljava.level6_vm.objectcall;

import java.util.HashMap;

import com.liu.gwt.gwt_smalljava.level6_vm.objectcall.impl.HashMap_ObjectCall;

public class Java_ObjectCallImpl  
	implements IJava_ObjectCall {

	private static HashMap<String,IJava_ObjectCall> implMap = new HashMap<String, IJava_ObjectCall>();
	public Java_ObjectCallImpl() {
		if(implMap.size()==0) {
			implMap.put("HashMap", new HashMap_ObjectCall());
		}
	}
	
	@Override
	public Object objectCall(Object object, String methodname) {
		String stype="";
		if(object instanceof HashMap) {
			stype = "HashMap";
		}
		
		IJava_ObjectCall implcall = implMap.get(stype);
		if(implcall != null) {
			return implcall.objectCall(object, methodname);
		}else {
			consoleLog("Cannot find used objectcall. stype:"+stype);
			return null;
		}
	}

	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[JVM][Java_ObjectCallImpl]" + message );
	}-*/;
	
}
