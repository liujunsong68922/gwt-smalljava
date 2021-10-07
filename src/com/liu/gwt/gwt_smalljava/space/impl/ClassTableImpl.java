package com.liu.gwt.gwt_smalljava.space.impl;

import java.util.HashMap;

import com.liu.gwt.gwt_smalljava.space.IClassTable;


public class ClassTableImpl implements IClassTable {
	@SuppressWarnings("rawtypes")
	private HashMap<String,Class> classmap = new HashMap<String,Class>();

	@SuppressWarnings("rawtypes")
	public HashMap<String, Class> getClassmap() {
		return classmap;
	}

	@SuppressWarnings("rawtypes")
	public void setClassmap(HashMap<String, Class> classmap) {
		this.classmap = classmap;
	}
	
	public ClassTableImpl() {
		classmap.put("String", String.class);
		classmap.put("HashMap", HashMap.class);
	}
	
	@SuppressWarnings("rawtypes")
	public Class getClass(String name) {
		if(this.classmap.containsKey(name)) {
			return this.classmap.get(name);
		}else {
			return null;
		}
	}
}
