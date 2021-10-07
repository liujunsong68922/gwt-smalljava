package com.liu.gwt.gwt_smalljava.common;

import java.util.HashMap;

/**
 * MEMO:���Manager��һ��ӳ�����ÿ��Ӧ�ó��򴴽��Ķ���
 * MEMO:��1�� ʹ��new �ؼ��ʴ�����Ӧ�ó������
 * MEMO:��2��ʹ��Ӧ�ó��򷽷����û�ȡ����Ӧ�ó������
 * MEMO:��3��ʹ��static�������÷��صĶ�����ʱ������֧�֣�δ������֧�֣�
 * @author liujunsong
 *
 */
public class UuidObjectManager {
	/**
	 * ���þ�̬�������壬��ֻ֤������һ������
	 */
	private static HashMap<String,Object> objMap ;
	
	public UuidObjectManager() {
		//step1:init map
		initMap();
	}
	
	/**
	 * MEMO����ʼ������洢��Map
	 */
	private static void initMap() {
		if(objMap == null) {
			objMap = new HashMap<String,Object>();
		}		
	}
	
	/**
	 * MEMO������һ��UUID��Object��ӳ���ϵ
	 * @param uuid
	 * @param obj
	 */
	public static void setObject(String uuid,Object obj) {
		initMap();
		objMap.put(uuid, obj);
	}
	
	/**
	 * MEMO:����uuid�����������ʵ�ʴ洢
	 * @param uuid
	 * @return
	 */
	public static Object getObject(String uuid) {
		initMap();
		if(objMap.containsKey(uuid)) {
			//����uuid����ȡָ������
			return objMap.get(uuid);
		}else {
			//uuid������
			return null;
		}
	}
	
	/**
	 * MEMO:�Ӷ���洢��ɾ��һ���������������ͷŶ����������ľ�������
	 * @param uuid
	 */
	public static void removeObject(String uuid) {
		initMap();
		if(objMap.containsKey(uuid)) {
			objMap.remove(uuid);
		}
	}
}
