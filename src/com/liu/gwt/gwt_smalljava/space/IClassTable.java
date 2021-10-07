package com.liu.gwt.gwt_smalljava.space;

import java.util.HashMap;

public interface IClassTable {

	@SuppressWarnings("rawtypes")
	public Class getClass(String name) ;
	

	@SuppressWarnings("rawtypes")
	public HashMap<String, Class> getClassmap();
	

	@SuppressWarnings("rawtypes")
	public void setClassmap(HashMap<String, Class> classmap);
}
