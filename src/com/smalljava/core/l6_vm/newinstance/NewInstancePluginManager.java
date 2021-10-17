package com.smalljava.core.l6_vm.newinstance;

import java.util.HashMap;

import com.smalljava.core.l6_vm.newinstance.javaplugin.HashMapNewInstance;
import com.smalljava.core.l6_vm.newinstance.jvmplugin.JvmNewInstancePlugin;

public class NewInstancePluginManager implements INewInstance {
	
	private static HashMap<String,INewInstance> instancemap = new HashMap<String,INewInstance>();
	
	private JvmNewInstancePlugin jvmplugin = new JvmNewInstancePlugin();

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


	@Override
	public Object newjavaInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object newjvmInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object newgwtuiInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object newSmalljavaInstance(String classname) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
