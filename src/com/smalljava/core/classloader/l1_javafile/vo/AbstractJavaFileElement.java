package com.smalljava.core.classloader.l1_javafile.vo;

/**
 * 这个代表构成JavaFile的元素层级
 * @author liujunsong
 *
 */
public abstract class AbstractJavaFileElement {
	/**
	 * 有用的部分
	 */
	private String stringcontent;
	
	/**
	 * 计算结束后剩余的部分
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
