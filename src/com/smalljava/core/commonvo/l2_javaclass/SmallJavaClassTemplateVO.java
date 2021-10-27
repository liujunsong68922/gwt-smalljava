package com.smalljava.core.commonvo.l2_javaclass;

import java.util.ArrayList;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l2_javaclass.element.SmallJavaClassMethodElement;
import com.smalljava.core.commonvo.l2_javaclass.element.SmallJavaClassVarDefineElement;

/**
  * JavaClassTemplateRootVO 是对Java文件内部对class关键定义部分的抽象表示
 * @author liujunsong
 *
 */
public class SmallJavaClassTemplateVO {
	private Logger logger = LoggerFactory.getLogger(SmallJavaClassTemplateVO.class);
	/**
	 *  Java类包的名称（暂不实现，为未来进行预留）
	 */
	private String packagename;

	/**
	 * Java类的名称
	 */
	private String classname;

	/**
	 * MEMO:子元素定义，统一抽象为AbstarctJavaClassElement
	 */
	private ArrayList<AbstractSmallJavaClassElement> children = new ArrayList<AbstractSmallJavaClassElement>();

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

	/**
	 * MEMO: 检索Java类中所有的方法定义元素
	 * 
	 * @return
	 */
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

	/**
	 * MEMO:检索Java类中所有的方法定义元素
	 * 
	 * @return
	 */
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
	
	/**
	 * MEMO：按照方法名来检索JAVA类定义方法
	 * MEMO：这个方法暂时没有考虑同名方法的问题，这一问题需要在后期解决
	 * @param methodname
	 * @return
	 */
	public SmallJavaClassMethodElement getMethod(String methodname) {
		for (AbstractSmallJavaClassElement child : children) {
			if (child instanceof SmallJavaClassMethodElement) {
				SmallJavaClassMethodElement method = (SmallJavaClassMethodElement) child;
				// MEMO:判断method定义内容中是否是这个方法名
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
