package com.smalljava.core.classloader.l3_method.vo;

/**
 * Java Method����Ĳ�������
 * @author liujunsong
 *
 */
public class JavaMethodArgumentVO {
	/**
	 * ������������
	 */
	private String argtype;
	
	/**
	 * ����������
	 */
	private String argname;

	public String getArgname() {
		return argname;
	}
	public void setArgname(String argname) {
		this.argname = argname;
	}
	public String getArgtype() {
		return argtype;
	}
	public void setArgtype(String argtype) {
		this.argtype = argtype;
	}
	
	public String toJSONString() {
		String s1="{";
		s1 += "argname:"+argname+",";
		s1 += "argtype:"+argtype;
		s1 +="}";
	
		return s1;
	}
}
