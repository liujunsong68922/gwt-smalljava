package com.smalljava.core.common;

import java.util.HashMap;

/**
 * MEMO:这个Manager是一个映射表，给每个应用程序创建的对象，
 * MEMO:【1】 使用new 关键词创建的应用程序对象
 * MEMO:【2】使用应用程序方法调用获取到的应用程序对象
 * MEMO:【3】使用static方法引用返回的对象（暂时不考虑支持，未来可能支持）
 * @author liujunsong
 *
 */
public class UuidObjectManager {
	/**
	 * 采用静态方法定义，保证只有这样一个对象
	 */
	private static HashMap<String,Object> objMap ;
	
	public UuidObjectManager() {
		//step1:init map
		initMap();
	}
	
	/**
	 * MEMO：初始化对象存储的Map
	 */
	private static void initMap() {
		if(objMap == null) {
			objMap = new HashMap<String,Object>();
		}		
	}
	
	/**
	 * MEMO：保存一个UUID和Object的映射关系
	 * @param uuid
	 * @param obj
	 */
	public static void setObject(String uuid,Object obj) {
		initMap();
		objMap.put(uuid, obj);
	}
	
	/**
	 * MEMO:利用uuid来检索对象的实际存储
	 * @param uuid
	 * @return
	 */
	public static Object getObject(String uuid) {
		initMap();
		if(objMap.containsKey(uuid)) {
			//利用uuid来获取指定对象
			return objMap.get(uuid);
		}else {
			//uuid不存在
			return null;
		}
	}
	
	/**
	 * MEMO:从对象存储中删除一个对象，这样才能释放对于这个对象的具体引用
	 * @param uuid
	 */
	public static void removeObject(String uuid) {
		initMap();
		if(objMap.containsKey(uuid)) {
			objMap.remove(uuid);
		}
	}
}
