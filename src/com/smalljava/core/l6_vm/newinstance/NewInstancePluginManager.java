package com.smalljava.core.l6_vm.newinstance;

import java.util.HashMap;

import com.smalljava.core.l6_vm.newinstance.plugin.HashMapNewInstance;

public class NewInstancePluginManager implements INewInstance {
	private static HashMap<String,INewInstance> instancemap = new HashMap<String,INewInstance>();
	
	public NewInstancePluginManager() {
		if(instancemap.size()==0) {
			//add plugin into maps
			instancemap.put("HashMap", new HashMapNewInstance());
		}
	}
	
	
	@Override
	public Object newInstance(String classname) {
		
		INewInstance inst = instancemap.get(classname);
		if(inst != null) {
			return inst.newInstance(classname);
		}else {
			System.out.println("Unsuppored classname:"+classname);
			return null;
		}
	}
	
}
