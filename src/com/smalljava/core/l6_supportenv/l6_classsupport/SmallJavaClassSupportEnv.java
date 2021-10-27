package com.smalljava.core.l6_supportenv.l6_classsupport;

import java.util.HashMap;

import com.smalljava.core.commonvo.instancevo.JavaClassInstanceVO;
import com.smalljava.core.l6_supportenv.l6_classsupport.helper.SmallJavaClassManager;
import com.smalljava.core.l6_supportenv.l6_classsupport.helper.SmallJavaClassSupportEnv_ClassLoader;
import com.smalljava.core.l6_supportenv.l6_classsupport.helper.SmallJavaClassSupportEnv_NewInstance;
import com.smalljava.core.l6_supportenv.l6_classsupport.helper.SmallJavaObjectManager;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * SmallJava 内部Class关键词支持的支持环境
 * MEMO： 这个执行环境集成了SmallJavaClassManager 和 SmallJavaObjectManager
 * MEMO： 这两个具体的执行管理类，这个类是一个封装类
 * @author liujunsong
 *
 */
public class SmallJavaClassSupportEnv {
	private SmallJavaClassManager classmanager = new SmallJavaClassManager();
	private SmallJavaObjectManager objcetmanager = new SmallJavaObjectManager();
	

	
	//get method
	public SmallJavaClassManager getClassmanager() {
		return classmanager;
	}
	//set method
	public void setClassmanager(SmallJavaClassManager classmanager) {
		this.classmanager = classmanager;
	}
	//get method
	public SmallJavaObjectManager getObjcetmanager() {
		return objcetmanager;
	}
	//set method
	public void setObjcetmanager(SmallJavaObjectManager objcetmanager) {
		this.objcetmanager = objcetmanager;
	}
	
	/**
	 * SmallJava 类支持环境，加载一个字符串格式的Class定义
	 * MEMO:这个Class是在SmallJava环境内运行的Class.没有二进制格式。
	 * MEMO:这一功能类似于JVM中的ClassLoader(),是一个类加载器。
	 * @param strClassDefine
	 * @return
	 */
	public boolean loadClassDefineString(String strClassDefine) {
		SmallJavaClassSupportEnv_ClassLoader classloader = 
				new SmallJavaClassSupportEnv_ClassLoader();
		return classloader.loadClassDefineString(this.getClassmanager(), strClassDefine,this,new SmallJavaOopSupportEnv());
	}
	
	/**
	 * newInstance support function.
	 * create a new SmallJava ClassInstaneVO by classname.
	 * MEMO: 注意SmallJava的Class部分支持，只负责生成类的实例，并不提供支持运行功能
	 * MEMO: SmallJava的类和对象实例仅仅是提供源代码层次的存储分解，不直接提供运行功能
	 * @param classname
	 * @return
	 */
	public JavaClassInstanceVO newInstance(String classname) {
		SmallJavaClassSupportEnv_NewInstance newinstance =
				new SmallJavaClassSupportEnv_NewInstance();
		JavaClassInstanceVO instancevo = newinstance.newInstance(this, classname,new SmallJavaOopSupportEnv());
		if(instancevo !=null) {
			this.getObjcetmanager().getVolist().add(instancevo);
		}
		return instancevo;
	}
}
