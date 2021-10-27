package com.smalljava.core.analyse.l1_analyse.plugin;

import com.smalljava.core.commonvo.l1_javafile.AbstractJavaFileElement;

//import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;

public interface IJavaFileAnalysePlugin {

	public AbstractJavaFileElement findFirstElement(String strdata);
}
