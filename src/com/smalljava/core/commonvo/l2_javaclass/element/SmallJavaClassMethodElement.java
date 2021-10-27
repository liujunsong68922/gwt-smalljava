package com.smalljava.core.commonvo.l2_javaclass.element;

import com.smalljava.core.commonvo.l2_javaclass.AbstractSmallJavaClassElement;
import com.smalljava.core.commonvo.l3_javamethod.SmallJavaMethodRootVO;

/**
 * SmallJavaClassMethodElement
 * @author liujunsong
 *
 */
public class SmallJavaClassMethodElement extends AbstractSmallJavaClassElement {
	private SmallJavaMethodRootVO methodvo = null;

	public SmallJavaMethodRootVO getMethodvo() {
		return methodvo;
	}

	public void setMethodvo(SmallJavaMethodRootVO methodvo) {
		this.methodvo = methodvo;
	}
	
}
