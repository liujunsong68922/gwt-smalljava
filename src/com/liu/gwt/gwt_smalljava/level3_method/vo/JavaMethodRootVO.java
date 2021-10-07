package com.liu.gwt.gwt_smalljava.level3_method.vo;

import java.util.ArrayList;

//import com.alibaba.fastjson.JSON;

/**
 * Java��������Method�ı�ʾ
 * @author liujunsong
 *
 */
public class JavaMethodRootVO {
	/**
	 * Method.������
	 */
	private String methodname;
	/**
	 * argArray.���ò����б�
	 */
	private ArrayList<JavaMethodArgumentVO> argArray = new ArrayList<JavaMethodArgumentVO>();
	/**
	 * methodContent.�������õ��ַ�������{}������
	 */
	private String methodContent;
	public String getMethodname() {
		return methodname;
	}
	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}
	public ArrayList<JavaMethodArgumentVO> getArgArray() {
		return argArray;
	}
	public void setArgArray(ArrayList<JavaMethodArgumentVO> argArray) {
		this.argArray = argArray;
	}
	public String getMethodContent() {
		return methodContent;
	}
	public void setMethodContent(String methodContent) {
		this.methodContent = methodContent;
	}
	
	public String toJSONString() {
		//return JSON.toJSONString(this);
		return "";
	}
	
	public void show() {
		System.out.println(this.toJSONString());
	}
}
