package com.smalljava.core.classloader.l2_class.analyse.plugin;

import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;

public interface IJavaClassAnalysePlugin {
	/**
	 * ��������ַ������з����������жϳ����ĵ�һ��AbstractJavaFileElement
	 * @param strdata
	 * @return
	 */
	public AbstractJavaClassElement findFirstElement(String strdata);
}
