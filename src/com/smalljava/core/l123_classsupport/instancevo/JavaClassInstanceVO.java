package com.smalljava.core.l123_classsupport.instancevo;

import java.util.ArrayList;
//import java.util.HashMap;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.AbstractSmallJavaClassElement;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.element.SmallJavaClassMethodElement;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.element.SmallJavaClassVarDefineElement;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * 
 * @author liujunsong
 *
 */
public class JavaClassInstanceVO {
	private Logger logger = LoggerFactory.getLogger(JavaClassInstanceVO.class);
	/**
	 * Java packagename
	 */
	private String packagename;

	/**
	 * Java classname
	 */
	private String classname;

	/**
	 * children
	 */
	private ArrayList<AbstractSmallJavaClassElement> children = new ArrayList<AbstractSmallJavaClassElement>();

	/**
	 * each object instance has its own vartable,
	 * while will connect to static class 's vartable as parent
	 * so that the static variable defined in class file could be supported.
	 */
	private IVarTable vartable;
	
	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public ArrayList<AbstractSmallJavaClassElement> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<AbstractSmallJavaClassElement> children) {
		this.children = children;
	}

	public ArrayList<SmallJavaClassMethodElement> getMethodArray() {
		ArrayList<SmallJavaClassMethodElement> retlist = new ArrayList<SmallJavaClassMethodElement>();
		for (AbstractSmallJavaClassElement child : children) {
			if (child instanceof SmallJavaClassMethodElement) {
				SmallJavaClassMethodElement method = (SmallJavaClassMethodElement) child;
				retlist.add(method);
			}
		}
		return retlist;
	}

	public ArrayList<SmallJavaClassVarDefineElement> getPropertiesArray() {
		ArrayList<SmallJavaClassVarDefineElement> retlist = new ArrayList<SmallJavaClassVarDefineElement>();
		for (AbstractSmallJavaClassElement child : children) {
			if (child instanceof SmallJavaClassVarDefineElement) {
				SmallJavaClassVarDefineElement vardefine = (SmallJavaClassVarDefineElement) child;
				retlist.add(vardefine);
			}
		}
		return retlist;
	}

	public void show() {
		for (AbstractSmallJavaClassElement child : children) {
			logger.info(child.getClass().getSimpleName() + ":" + child.getStringcontent());
		}
	}

	public SmallJavaClassMethodElement getMethod(String methodname) {
		for (AbstractSmallJavaClassElement child : children) {
			if (child instanceof SmallJavaClassMethodElement) {
				SmallJavaClassMethodElement method = (SmallJavaClassMethodElement) child;
				int ipos = method.getStringcontent().indexOf('(');
				if (ipos < 0) {
					continue;
				} else {
					String strleft = method.getStringcontent().substring(0, ipos + 1);
					if (strleft.indexOf(" " + methodname + " ") > 0) {
						return method;
					}
					if (strleft.indexOf(" " + methodname + "(") > 0) {
						return method;
					}
				}

			}
		}

		return null;
	}

	public IVarTable getVartable() {
		return vartable;
	}

	public void setVartable(IVarTable vartable) {
		this.vartable = vartable;
	}

}
