package com.smalljava.core.classloader.l2_class.vo.element;

/**
 * JAVA Class定义所支持的抽象子元素类
 * @author liujunsong
 *
 */
public abstract class AbstractJavaClassElement {
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


