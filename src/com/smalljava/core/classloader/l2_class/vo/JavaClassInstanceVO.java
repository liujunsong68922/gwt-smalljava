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
 * MEMO�� JavaClassInstanceVO ��ʹ��new�ؼ������ɵ���ʵ���� MEMO:
 * �ӱ�����ĽǶ�����������಻��һ��RootVarTable�� MEMO: �������������棬��Ҫ���ز��ұ�����ķ������������Ҹ���һ��ı�����
 * 
 * @author liujunsong
 *
 */
public class JavaClassInstanceVO {
	private Logger logger = LoggerFactory.getLogger(JavaClassInstanceVO.class);
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

	/**
	 * ��class instance����ע��һ��vartable�Ľӿ�
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
			logger.info(child.getClass().getSimpleName() + ":" + child.getStringcontent());
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
	 * ����varname��VarMapNode�л�ȡ��ֵ������Ҳ������򷵻�null,
	 * 
	 * @param varname
	 * @return
	 */
//	public VarValue getVarValue(String varname) {
//		HashMap<String, VarValue> varmap = this.myvarmap;
//		if (varmap == null) {
//			logger.error("��ERROR�����ұ�����ʧ��.");
//			return null;
//		}
//
//		if (varmap.containsKey(varname)) {
//			// ���varname������Ϊһ�����ر�������ӱ���HashMap�л�ȡ��ֵ
//			VarValue varvalue = varmap.get(varname);
//			return varvalue;
//		} else {
//			// ����ʵ���ı��ر������в��Ҳ�����
//			// ��Ҫ����ľ�̬ʵ����������ȥ���ҡ�
//			JavaClassStaticInstanceVO staticvo = JavaClassStaticInstanceMap.getJavaClassStaticInstanceVO(classname);
//			if(staticvo == null) {
//				logger.error("��ERROR�� Program Logic Error, staticInstancevo is null!");
//				return null;
//			}else {
//				//�Ӿ�̬��ʵ���Ķ����з��ʱ�����
//				//�����ض��󹩲�����
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
