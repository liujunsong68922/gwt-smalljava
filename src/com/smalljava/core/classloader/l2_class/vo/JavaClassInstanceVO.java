package com.smalljava.core.classloader.l2_class.vo;

import java.util.ArrayList;
//import java.util.HashMap;

import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassMethodElement;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassVarDefineElement;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * MEMO： JavaClassInstanceVO 是使用new关键词生成的类实例， MEMO:
 * 从变量表的角度来看，这个类不是一个RootVarTable， MEMO: 因此在这个类里面，需要重载查找变量表的方法，继续查找更上一层的变量表
 * 
 * @author liujunsong
 *
 */
public class JavaClassInstanceVO {
	private Logger logger = LoggerFactory.getLogger(JavaClassInstanceVO.class);
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

	/**
	 * 在class instance里面注入一个vartable的接口
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

	/**
	 * 根据varname从VarMapNode中获取数值，如果找不到，则返回null,
	 * 
	 * @param varname
	 * @return
	 */
//	public VarValue getVarValue(String varname) {
//		HashMap<String, VarValue> varmap = this.myvarmap;
//		if (varmap == null) {
//			logger.error("【ERROR】查找变量表失败.");
//			return null;
//		}
//
//		if (varmap.containsKey(varname)) {
//			// 如果varname被定义为一个本地变量，则从本地HashMap中获取其值
//			VarValue varvalue = varmap.get(varname);
//			return varvalue;
//		} else {
//			// 在类实例的本地变量表中查找不到，
//			// 需要到类的静态实例对象里面去查找。
//			JavaClassStaticInstanceVO staticvo = JavaClassStaticInstanceMap.getJavaClassStaticInstanceVO(classname);
//			if(staticvo == null) {
//				logger.error("【ERROR】 Program Logic Error, staticInstancevo is null!");
//				return null;
//			}else {
//				//从静态类实例的对象中访问变量表
//				//并返回对象供操作。
//				return staticvo.getVarValue(varname);
//			}
//		}
//	}

	public IVarTable getVartable() {
		return vartable;
	}

	public void setVartable(IVarTable vartable) {
		this.vartable = vartable;
	}
	

}
