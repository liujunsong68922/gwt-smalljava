package com.liu.gwt.gwt_smalljava.level6_vm.objectcall.impl;

import java.util.HashMap;

import com.liu.gwt.gwt_smalljava.level6_vm.objectcall.IJava_ObjectCall;

public class HashMap_ObjectCall implements IJava_ObjectCall {


	@Override
	public Object objectCall(Object object, String methodname) {
		if(methodname.equals("size")) {
			//return this.eval(object, methodname);
			return this.size(object);
		}

		consoleLog("Unknown methodname:"+methodname);
		return null;
	}
	
	public Object size(Object object) {
		HashMap map = (HashMap) object;
		//map.put("aa", "aa");
		//return this.eval(map, "size");
		return map.size();
	}

	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[JVM][HashMap_ObjectCall]" + message );
	}-*/;
		
	public native Object eval(Object obj,String methodname) /*-{
	//alert(message);
	alert('enter eval');
	alert(obj);
	
	$wnd.obj2 = eval(obj);
	alert($wnd.obj2);
	
	var obj1 = obj.methodname;
	return obj1;
	}-*/;
}
