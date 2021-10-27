package com.smalljava.core.commonvo.l1_javafile;

/**
 * ���������JavaFile��Ԫ�ز㼶
 * @author liujunsong
 *
 */
public abstract class AbstractJavaFileElement {
	/**
	 * ���õĲ���
	 */
	private String stringcontent;
	
	/**
	 * ���������ʣ��Ĳ���
	 */
	private String computeleftstring;
	
	
	public String getStringcontent() {
		return stringcontent;
	}

	public void setStringcontent(String stringcontent) {
		this.stringcontent = stringcontent;
	}

	public String getComputeleftstring() {
		return computeleftstring;
	}

	public void setComputeleftstring(String computeleftstring) {
		this.computeleftstring = computeleftstring;
	}
	
}
