package com.liu.gwt.gwt_smalljava.level2_class.vo;

import java.util.ArrayList;

import com.liu.gwt.gwt_smalljava.level2_class.vo.element.JavaClassMethodElement;
import com.liu.gwt.gwt_smalljava.level2_class.vo.element.JavaClassVarDefineElement;
import com.liu.gwt.gwt_smalljava.space.impl.RootVarTableImpl;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import com.liu.smalljavav2.level1_javafile.vo.AbstractJavaFileElement;
//import com.liu.smalljavav2.level2_class.vo.element.JavaClassMethodElement;
//import com.liu.smalljavav2.level2_class.vo.element.JavaClassVarDefineElement;
//import com.liu.smalljavav2.space.impl.RootVarTableImpl;

/**
 * JavaClassRootVO �Ƕ�Java�ļ��ڲ���class�ؼ����岿�ֵĳ����ʾ
 * 
 * @author liujunsong
 *
 */
public class JavaClassRootVO extends RootVarTableImpl {
	//private Logger logger = LoggerFactory.getLogger(JavaClassRootVO.class);
	/**
	 * Java��������ƣ��ݲ�ʵ�֣�Ϊδ������Ԥ����
	 */
	private String packagename;

	/**
	 * Java�������
	 */
	private String classname;

	/**
	 * ��Ԫ�ض���
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
	 * ����Java�������еķ�������Ԫ��
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
	 * ����Java�������еķ�������Ԫ��
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
			//logger.info(child.getClass().getSimpleName() + ":" + child.getStringcontent());
		}
	}

	public JavaClassMethodElement getMethod(String methodname) {
		for (AbstractJavaClassElement child : children) {
			if (child instanceof JavaClassMethodElement) {
				JavaClassMethodElement method = (JavaClassMethodElement) child;
				// �ж�method�����������Ƿ������������
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
