package com.smalljava.core.classloader.l2_class.vo.element;

/**
 * JAVA Class������֧�ֵĳ�����Ԫ����
 * @author liujunsong
 *
 */
public abstract class AbstractJavaClassElement {
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


