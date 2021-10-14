package com.smalljava.core.common;

import java.util.HashMap;
import java.util.Iterator;

public class JSONFunction {
	/**
	 * convert hashmap to json format string, using for output only.
	 * 
	 * @param map
	 * @return
	 */
	public static String hashmapToJsonString(HashMap<String, VarValue> map) {
		String sret = "";
		Iterator iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			VarValue vvalue = map.get(key);
			if (vvalue == null) {
				sret += "{key:" + key + ", value:null" + "}\r\n";
			} else {
				sret += "{key:" + key + ", value:" + vvalue.toJSONString() + "}\r\n";
			}
		}
		return sret;
	}

	/**
	 * convert hashmap to json format string, using for output only.
	 * 
	 * @param map
	 * @return
	 */
	public static String stringhashmapToJsonString(HashMap<String, String> map) {
		String sret = "";
		Iterator iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			String vvalue = map.get(key);
			sret += "{key:" + key + ", value:" + vvalue != null ? vvalue : "null" + "}\r\n";
		}
		return sret;
	}
}
