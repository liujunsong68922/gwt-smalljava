package com.smalljava.core.classloader.l1_javafile.analyse.plugin;

import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;

public interface IJavaFileAnalysePlugin {
	/**
	 * 对输入的字符串进行分析，返回判断出来的第一个AbstractJavaFileElement
	 * @param strdata
	 * @return
	 */
	public AbstractJavaFileElement findFirstElement(String strdata);
}
