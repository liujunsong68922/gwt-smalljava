package com.smalljava.core.l9_space.vartable;

import com.smalljava.core.common.VarValue;

public interface IVarTable  {

	public boolean setVarValue(String varname, VarValue varvalue) ;
	public VarValue getVarValue(String varname);
	public boolean defineVar(String varname, String vartype);
	public boolean isValid(String varname);
	public String toJSONString();

}
