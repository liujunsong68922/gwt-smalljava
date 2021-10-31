package com.smalljava.workflow.core.vo;

import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;

public class NodeFunction {
	public static int readIntAttribute(Node node, String key) {
		NamedNodeMap map1 = node.getAttributes();
		Node dataobj = map1.getNamedItem(key);
		if (dataobj == null) {
			return 0;
		} else {
			try {
				int ivalue = Integer.parseInt(dataobj.getNodeValue());
				return ivalue;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
	}
	public static String readStringAttribute(Node node, String key) {
		NamedNodeMap map1 = node.getAttributes();
		Node dataobj = map1.getNamedItem(key);
		if (dataobj == null) {
			return "";
		} else {
			try {
				String svalue = dataobj.getNodeValue();
				return svalue;
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
	}

}
