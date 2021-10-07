package com.liu.gwt.gwt_smalljava.space;

import com.liu.gwt.gwt_smalljava.common.VarValue;

//import com.liu.smalljavav2.common.VarValue;

public interface IVarTable  {
	/**
	 * set var value 
	 * @param varname
	 * @param varvalue
	 * @return
	 */
	public boolean setVarValue(String varname, VarValue varvalue) ;
	
	/**
	* get varvalue ,it maybe return null
	 * 
	 * @param varname
	 * @return
	 */
	public VarValue getVarValue(String varname);
	
	/**
	 * define a var value
	 * @param varname
	 * @param vartype
	 * @return
	 */
	public boolean defineVar(String varname, String vartype);
	
	/**
	 * check whether var is valid.
	 * @param varname
	 * @return
	 */
	public boolean isValid(String varname);

}
