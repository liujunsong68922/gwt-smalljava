package com.smalljava.core.l6_supportenv;

import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;

/**
 * OopSuportEnv是下属两个env的封装
 * 
 * @author liujunsong
 *
 */
public class SupportEnvImpl implements ISupportEnv {
	/**
	 * smalljava 内部classenv环境
	 */
	private SmallJavaClassSupportEnv classenv = new SmallJavaClassSupportEnv();
	/**
	 * MEMO:利用插件技术支持的oopenv环境
	 */
	private SmallJavaOopSupportEnv oopenv = new SmallJavaOopSupportEnv();

	@Override
	public boolean loadClassDefine(String fullname, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		// 调用smalljavaclasssupportenv来实现
		boolean b1 = classenv.loadClassDefine(fullname, classenv, oopenv);
		return b1;
	}

	@Override
	public boolean loadClassDefine(IClassObject classimpl, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		// 调用oop env 来读取 oop support 的 类加载
		boolean b2 = oopenv.loadClassDefine(classimpl, classenv, oopenv);
		return b2;
	}

	@Override
	public IClassObject getIClassByName(String fullname) {
		// first ,read it from class env
		IClassObject class1 = this.classenv.getIClassByName(fullname);
		if (class1 != null) {
			return class1;
		}
		// second ,read it from oop env,
		IClassObject class2 = this.oopenv.getIClassByName(fullname);
		return class2;

	}

}
