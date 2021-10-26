package com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin;

import com.smalljava.core.l123_classsupport.l2_classdefine.vo.AbstractSmallJavaClassElement;

//import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;

public interface ISmallJavaClassAnalysePlugin {
	/**
	 * MEMO:对输入的字符串进行分析，返回判断出来的第一个AbstractJavaFileElement
	 * @param strdata
	 * @return
	 */
	public AbstractSmallJavaClassElement findFirstElement(String strdata);
}
