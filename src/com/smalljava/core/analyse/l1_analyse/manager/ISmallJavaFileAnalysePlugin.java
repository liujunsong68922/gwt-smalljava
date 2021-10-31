package com.smalljava.core.analyse.l1_analyse.manager;

import com.smalljava.core.commonvo.l1_javafile.AbstractJavaFileElement;

//import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;

public interface ISmallJavaFileAnalysePlugin {

	public AbstractJavaFileElement findFirstElement(String strdata);
}
