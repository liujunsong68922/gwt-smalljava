package com.smalljava.core.l6_vm.newinstance.jvmplugin;

import java.util.HashMap;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l6_vm.newinstance.INewInstance;

public class JvmNewInstancePlugin implements INewInstance {
	Logger logger = LoggerFactory.getLogger(JvmNewInstancePlugin.class);
	private static HashMap<String,String> classnamemap = new HashMap<String,String>();
	
	
	public static HashMap<String, String> getClassnamemap() {
		return classnamemap;
	}

	public static void setClassnamemap(HashMap<String, String> classnamemap) {
		JvmNewInstancePlugin.classnamemap = classnamemap;
	}

	public JvmNewInstancePlugin() {
		initClassnamemap();
	}
	
	/**
	 * init classname map
	 */
	private void initClassnamemap() {
		//if you have any special class stored in JVM
		//then you can put the classname in it.
		
	}
	
	@Override
	public Object newInstance(String classname) {
		return this.newjvmInstance(classname);
	}

	@Override
	public Object newjavaInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object newjvmInstance(String classname) {
		String fullclassname = this.classnamemap.get(classname);
		if(fullclassname == null) {
			logger.error("[ERROR] classname not support."+classname);
			return null;
		}

		
		return null;
	}

	@Override
	public Object newgwtuiInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Object createJvmByFullName(String fullname) {
		try {
			//[Condition1] This is Java Condition
			//newInstance() is not support in gwt env
			//so you need to close it in gwt env.
			//Object obj = Class.forName(fullname).newInstance();
			
			//[Condition2] This is GWT work Condition
			Object obj = null;
			return obj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}

	@Override
	public Object newSmalljavaInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}

}
