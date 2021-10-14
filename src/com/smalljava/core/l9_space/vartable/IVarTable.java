package com.smalljava.core.l9_space.vartable;

import com.smalljava.core.common.VarValue;

public interface IVarTable  {
	/**
	 * MEMO：【变量赋值】，首先找到这个变量，然后再赋值
	 * MEMO：这里可能有一个隐藏的问题，下一步需要考虑变量重名的问题
	 * 
	 * @param varname
	 * @param varvalue
	 * @return
	 */
	public boolean setVarValue(String varname, VarValue varvalue) ;
	
	/**
	 * 根据varname从VarMapNode中获取数值，如果找不到，则返回null,
	 * 
	 * @param varname
	 * @return
	 */
	public VarValue getVarValue(String varname);
	
	/**
	 * 【变量设置】算法描述：先找到第一个可用的变量表，在这个变量表中定义变量值
	 * @param varname
	 * @param vartype
	 * @return
	 */
	public boolean defineVar(String varname, String vartype);
	
	/**
	 * MEMO：【变量有效性】：判断变量是否有效，执行时如果无效，则按照错误处理
	 * @param varname
	 * @return
	 */
	public boolean isValid(String varname);
	
	/**
	 * 以JSON格式输出变量表内容
	 * @return
	 */
	public String toJSONString();

}
