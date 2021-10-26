package com.smalljava.core.l123_classsupport.l2_classdefine.vo;

/**
 * MEMO: JAVA Class定义所支持的抽象子元素类
 * 
 * @author liujunsong
 *
 */
public abstract class AbstractSmallJavaClassElement {
	/**
	 * MEMO: 有用的部分
	 */
	private String stringcontent;

	/**
	 * MEMO: 计算结束后剩余的部分
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
