package com.smalljava.core.l9_space.vartable;

import com.smalljava.core.common.VarValue;

public interface IVarTable  {
	/**
	 * MEMO����������ֵ���������ҵ����������Ȼ���ٸ�ֵ
	 * MEMO�����������һ�����ص����⣬��һ����Ҫ���Ǳ�������������
	 * 
	 * @param varname
	 * @param varvalue
	 * @return
	 */
	public boolean setVarValue(String varname, VarValue varvalue) ;
	
	/**
	 * ����varname��VarMapNode�л�ȡ��ֵ������Ҳ������򷵻�null,
	 * 
	 * @param varname
	 * @return
	 */
	public VarValue getVarValue(String varname);
	
	/**
	 * ���������á��㷨���������ҵ���һ�����õı�����������������ж������ֵ
	 * @param varname
	 * @param vartype
	 * @return
	 */
	public boolean defineVar(String varname, String vartype);
	
	/**
	 * MEMO����������Ч�ԡ����жϱ����Ƿ���Ч��ִ��ʱ�����Ч�����մ�����
	 * @param varname
	 * @return
	 */
	public boolean isValid(String varname);
	
	/**
	 * ��JSON��ʽ�������������
	 * @return
	 */
	public String toJSONString();

}
