package com.smalljava.core.l6_supportenv;

import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;

/**
 * Object Orient Program Support Interface Define.
 * MEMO:面向对象支持的接口定义，所有支持面向对象的实现类都必须实现这个接口
 * MEMO:这个接口替代了原来的一堆接口类
 * @author liujunsong
 *
 */
public interface ISupportEnv {
	
	
	/**
	 * MEMO: 解析字符串格式的class file定义
	 * @param fullname	类的全路径，类似于com.liu.A
	 * @param classenv	SmallJava 内部类支持环境
	 * @param oopenv	SmallJava 外部OOP支持环境
	 * @return
	 */
	public boolean loadClassDefine(String fullname,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv);
	
	/**
	 * MEMO: 在执行环境加载使用插件支持的IClass
	 * @param fullname	类的全路径，类似于com.liu.A
	 * @param classenv	SmallJava 内部类支持环境
	 * @param oopenv	SmallJava 外部OOP支持环境
	 * @return
	 */
	public boolean loadClassDefine(IClassObject classimpl,
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv);
	
	/**
	 * MEMO:按照类定义的fullname来获取一个IClass对象
	 * @param fullname
	 * @return
	 */
	public IClassObject getIClassByName(String fullname);
	
	
}
