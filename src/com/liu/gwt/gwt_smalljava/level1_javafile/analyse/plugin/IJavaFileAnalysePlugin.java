package com.liu.gwt.gwt_smalljava.level1_javafile.analyse.plugin;

import com.liu.gwt.gwt_smalljava.level1_javafile.vo.AbstractJavaFileElement;

//import com.liu.smalljavav2.level1_javafile.vo.AbstractJavaFileElement;

public interface IJavaFileAnalysePlugin {
	/**
	 * ��������ַ������з����������жϳ����ĵ�һ��AbstractJavaFileElement
	 * @param strdata
	 * @return
	 */
	public AbstractJavaFileElement findFirstElement(String strdata);
}
