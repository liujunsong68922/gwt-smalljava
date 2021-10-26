package com.smalljava.core.l123_classsupport.l2_classdefine.vo.element;

import com.smalljava.core.l123_classsupport.l2_classdefine.vo.AbstractSmallJavaClassElement;
import com.smalljava.core.l123_classsupport.l3_method.vo.SmallJavaMethodRootVO;

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
