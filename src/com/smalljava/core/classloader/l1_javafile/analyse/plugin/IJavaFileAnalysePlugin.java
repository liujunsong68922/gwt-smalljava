package com.smalljava.core.classloader.l1_javafile.analyse.plugin;

import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;

public interface IJavaFileAnalysePlugin {
	/**
	 * ��������ַ������з����������жϳ����ĵ�һ��AbstractJavaFileElement
	 * @param strdata
	 * @return
	 */
	public AbstractJavaFileElement findFirstElement(String strdata);
}
