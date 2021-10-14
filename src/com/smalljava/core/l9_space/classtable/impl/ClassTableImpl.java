package com.smalljava.core.l9_space.classtable.impl;

import java.util.HashMap;

import com.smalljava.core.l9_space.classtable.IClassTable;

/**
 * �����Map������������ʱ��Class����
 * ��ʵ��ʹ���У�����import ���Ľ�����ʵ�ʸ�������б�
 * @author liujunsong
 *
 */
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
