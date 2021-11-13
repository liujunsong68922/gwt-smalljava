package com.smalljava.core.l6_supportenv;

import java.util.ArrayList;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;

/**
 * MEMO:在SmallJava内部模拟一个类和对象的方法
 * @author liujunsong
 *
 */
public interface IClassObject {
	/**
	 * MEMO:返回一个全路径的类名称
	 * @return
	 */
	public String getClassFullname();
	
	/**
	 * MEMO：类的实例化接口定义
	 * MEMO：方法调用时，内部环境接口优先，外部环境接口次之
	 * MEMO：内部环境，外部环境均不支持此调用，则调用失败
	 * @param classname 类名称（shortname or fullname）
	 * @param classenv 	支持环境：smalljava内置的类定义环境（内部环境）
	 * @param oopenv	支持环境：smalljava的扩展类定义环境（外部环境接口）
	 * @return varvalue,发生错误，返回null.(代表newInstance调用失败)
	 */
	public VarValue newInstance(String classname,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv);
	
	/**
	 * MEMO：对象方法调用，用来实现
	 * @param classname	类名称（shortname or fullname）
	 * @param target	对象实例
	 * @param methodname	方法名
	 * @param args		调用参数
	 * @param classenv	smalljava 内部Env
	 * @param oopenv	smalljava 外部Env
	 * @return
	 */
	public VarValue objcall(String classname,
			Object target,
			String methodname,
			ArrayList<VarValue> args,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv);

	/**
	 * MEMO：对象方法调用，用来实现
	 * @param classname	类名称（shortname or fullname）
	 * @param target	对象实例
	 * @param methodname	方法名
	 * @param args		调用参数
	 * @param classenv	smalljava 内部Env
	 * @param oopenv	smalljava 外部Env
	 * @return
	 */
	public VarValue classcall(String classname,
			IClassObject target,
			String methodname,
			ArrayList<VarValue> args,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv);
	
	/**
	 * MEMO：对象方法调用，用来实现
	 * @param classname	类名称（shortname or fullname）
	 * @param target	对象实例
	 * @param propertyname 	属性名称
	 * @param classenv	smalljava 内部Env
	 * @param oopenv	smalljava 外部Env
	 * @return
	 */
	public VarValue objPropertyGet(String classname,
			Object target,
			String propertyname,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv);
	
	/**
	 * MEMO：对象方法调用，用来实现
	 * @param classname	类名称（shortname or fullname）
	 * @param target	对象实例
	 * @param propertyname 	属性名称
	 * @param classenv	smalljava 内部Env
	 * @param oopenv	smalljava 外部Env
	 * @return
	 */
	public VarValue classPropertyGet(String classname,
			IClassObject target,
			String propertyname,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv);	
	
	//public boolean importClassDefine(String fullname);
}
