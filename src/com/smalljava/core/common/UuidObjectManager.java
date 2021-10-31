package com.smalljava.core.common;

import java.util.HashMap;


public class UuidObjectManager {
	private static HashMap<String,Object> objMap ;
	
	public UuidObjectManager() {
		//step1:init map
		initMap();
	}
	
	private static void initMap() {
		if(objMap == null) {
			objMap = new HashMap<String,Object>();
		}		
	}
	
	public void setObject(String uuid,Object obj) {
		initMap();
		objMap.put(uuid, obj);
	}
	
	public Object getObject(String uuid) {
		initMap();
		if(objMap.containsKey(uuid)) {
			return objMap.get(uuid);
		}else {
			return null;
		}
	}
	
	public static void removeObject(String uuid) {
		initMap();
		if(objMap.containsKey(uuid)) {
			objMap.remove(uuid);
		}
	}
}
