package com.smalljava.core.l6_supportenv.l6_oopsupport.objectcall;

import java.util.ArrayList;
import java.util.HashMap;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

public class ObjectCallPluginManager implements IObjectCall {
	Logger logger = LoggerFactory.getLogger(ObjectCallPluginManager.class);
	public static HashMap<String,IObjectCall> callersmap = new HashMap<String,IObjectCall>();
	
	public ObjectCallPluginManager() {
		if(callersmap.size()==0) {
			//init map itself.
		}
	}

	@Override
	public Object objcall(String classname,Object target, String methodname, ArrayList<VarValue> args) {
		logger.info("classname:"+classname);
		IObjectCall caller = callersmap.get(classname);
		if(caller != null) {
			return caller.objcall(classname, target, methodname, args);
		}
		
		System.out.println("Unsupported class:"+classname);
		logger.info("Unsupported class:"+classname);
		return null;
	}
	
	
}
