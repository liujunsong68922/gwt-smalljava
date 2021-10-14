package com.smalljava.core.classloader.l1_javafile.vo;

import java.util.ArrayList;

import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFileClassElement;
import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFileImportElement;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * JavaFileVO代表一个Java的源文件或者字符串，它没有基于自身的层次关系
 * 
 * @author liujunsong
 *
 */
public class JavaFileRootVO {
	private Logger logger = LoggerFactory.getLogger(JavaFileRootVO.class);
	/**
	 * 所有的children
	 */
	private ArrayList<AbstractJavaFileElement> children = new ArrayList<AbstractJavaFileElement>();

	public ArrayList<AbstractJavaFileElement> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<AbstractJavaFileElement> children) {
		this.children = children;
	}

	/**
	 * 从所有的children里面检索其中的import语句列表出来
	 * 
	 * @return
	 */
	public ArrayList<JavaFileImportElement> getImportlist() {
		ArrayList<JavaFileImportElement> retList = new ArrayList<JavaFileImportElement>();
		for (AbstractJavaFileElement child : children) {
			if (child instanceof JavaFileImportElement) {
				JavaFileImportElement importelement = (JavaFileImportElement) child;
				retList.add(importelement);
			}
		}
		return retList;
	}

	/**
	 * 检索文件中的所有class定义部分
	 * 
	 * @return
	 */
	public ArrayList<JavaFileClassElement> getClasslist() {
		ArrayList<JavaFileClassElement> retList = new ArrayList<JavaFileClassElement>();
		for (AbstractJavaFileElement child : children) {
			if (child instanceof JavaFileClassElement) {
				JavaFileClassElement importelement = (JavaFileClassElement) child;
				retList.add(importelement);
			}
		}
		return retList;

	}

	public void show() {
		for (AbstractJavaFileElement child : children) {
			String classname;
			if (child instanceof JavaFileClassElement) {
				JavaFileClassElement classelement = (JavaFileClassElement) child;
				classname = classelement.getClassname();
				logger.debug("classname:" + classname);
				logger.debug(child.getStringcontent());
			} else {
				logger.debug(child.getStringcontent());
			}
		}
	}

	public JavaFileClassElement getClassElement(String classname) {
		JavaFileClassElement classelement = null;
		for (AbstractJavaFileElement child : children) {
			// String classname;
			if (child instanceof JavaFileClassElement) {
				classelement = (JavaFileClassElement) child;
				logger.debug("classname:" + classelement.getClassname());
				logger.debug(child.getStringcontent());
				if (classelement.getClassname().equals(classname)) {
					break;
				}
			} else {
				logger.debug(child.getStringcontent());
			}
		}

		return classelement;
	}

}
