package com.smalljava.core.l9_space.classtable;

import java.util.HashMap;

public interface IClassTable {
	/**
	 * 根据class的名称来检索获取Class对象
	 * 这个接口是为Expression AST计算时提供的
	 * @param name
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Class getClass(String name) ;
	
	/**
	 * 获取代表ClassMap的HashMap对象
	 * 这个接口为Block AST计算提供，由它来负责加载特定的Class Map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public HashMap<String, Class> getClassmap();
	
	/**
	 * 直接设置一个HashMap进去作为ClassMap.
	 * @param classmap
	 */
	@SuppressWarnings("rawtypes")
	public void setClassmap(HashMap<String, Class> classmap);
}
