package com.smalljava.core.l123_classsupport.l3_method.vo;

import java.util.ArrayList;

/**
 * Java代码里面Method的表示
 * @author liujunsong
 *
 */
public class SmallJavaMethodRootVO {
	/**
	 *  Method.方法名
	 */
	private String methodname;
	/**
	 * argArray.调用参数列表
	 */
	private ArrayList<SmallJavaMethodArgumentVO> argArray = new ArrayList<SmallJavaMethodArgumentVO>();
	/**
	 * methodContent.方法调用的字符串内容{}来包裹
	 */
	private String methodContent;
	public String getMethodname() {
		return methodname;
	}
	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}
	public ArrayList<SmallJavaMethodArgumentVO> getArgArray() {
		return argArray;
	}
	public void setArgArray(ArrayList<SmallJavaMethodArgumentVO> argArray) {
		this.argArray = argArray;
	}
	public String getMethodContent() {
		return methodContent;
	}
	public void setMethodContent(String methodContent) {
		this.methodContent = methodContent;
	}
	
	private String toJSONString() {
		String strret="{";
		strret += "methodname:"+this.methodname+",";
		strret += "methodarg:[";
		
		for(SmallJavaMethodArgumentVO vo : this.argArray) {
			strret += vo.toJSONString();
		}
		strret += "]";
		strret += "methodContent:"+ this.methodContent;
		return strret;
		
	}
	
	public void show() {
		System.out.println(this.methodContent);
	}
}
