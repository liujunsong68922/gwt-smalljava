package com.smalljava.core.classloader.l2_class.vo;

import java.util.ArrayList;

import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassMethodElement;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassVarDefineElement;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * JavaClassTemplateRootVO JAVA的类模板VO
 * MEMO:这个类代表使用String转换过来的纯粹的VO对象，不可用于实际试计算
 * @author liujunsong
 *
 */
public class JavaClassTemplateVO {
	private Logger logger = LoggerFactory.getLogger(JavaClassTemplateVO.class);
	/**
	 * Java类包的名称（暂不实现，为未来进行预留）
	 */
	private String packagename;

	/**
	 * Java类的名称
	 */
	private String classname;

	/**
	 * 子元素定义
	 */
	private ArrayList<AbstractJavaClassElement> children = new ArrayList<AbstractJavaClassElement>();

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

	public ArrayList<AbstractJavaClassElement> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<AbstractJavaClassElement> children) {
		this.children = children;
	}

	/**
	 * 检索Java类中所有的方法定义元素
	 * 
	 * @return
	 */
	public ArrayList<JavaClassMethodElement> getMethodArray() {
		ArrayList<JavaClassMethodElement> retlist = new ArrayList<JavaClassMethodElement>();
		for (AbstractJavaClassElement child : children) {
			if (child instanceof JavaClassMethodElement) {
				JavaClassMethodElement method = (JavaClassMethodElement) child;
				retlist.add(method);
			}
		}
		return retlist;
	}

	/**
	 * 检索Java类中所有的方法定义元素
	 * 
	 * @return
	 */
	public ArrayList<JavaClassVarDefineElement> getPropertiesArray() {
		ArrayList<JavaClassVarDefineElement> retlist = new ArrayList<JavaClassVarDefineElement>();
		for (AbstractJavaClassElement child : children) {
			if (child instanceof JavaClassVarDefineElement) {
				JavaClassVarDefineElement vardefine = (JavaClassVarDefineElement) child;
				retlist.add(vardefine);
			}
		}
		return retlist;
	}

	public void show() {
		for (AbstractJavaClassElement child : children) {
			logger.info(child.getClass().getSimpleName() + ":" + child.getStringcontent());
		}
	}

	public JavaClassMethodElement getMethod(String methodname) {
		for (AbstractJavaClassElement child : children) {
			if (child instanceof JavaClassMethodElement) {
				JavaClassMethodElement method = (JavaClassMethodElement) child;
				// 判断method定义内容中是否是这个方法名
				int ipos = method.getStringcontent().indexOf('(');
				if (ipos < 0) {
					continue;
				} else {
					String strleft = method.getStringcontent().substring(0, ipos+1);
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
}
