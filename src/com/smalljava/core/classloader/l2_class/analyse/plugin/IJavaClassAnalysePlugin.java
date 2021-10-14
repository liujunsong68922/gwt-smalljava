package com.smalljava.core.classloader.l2_class.analyse.plugin;

import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;

public interface IJavaClassAnalysePlugin {
	/**
	 * 对输入的字符串进行分析，返回判断出来的第一个AbstractJavaFileElement
	 * @param strdata
	 * @return
	 */
	public AbstractJavaClassElement findFirstElement(String strdata);
}
