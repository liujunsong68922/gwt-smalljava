package com.liu.gwt.gwt_smalljava.level6_vm.newinstance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 这是一个接口类，通过这个接口实现类来实现对Java VM的模拟实现
 * @author liujunsong
 *
 */
public class Java_NewInstanceImpl implements IJava_NewInstance {

	@Override
	public Object newInstance(String classname) {
		consoleLog("classname:"+classname);
		// 根据classname来生成新的Java对象实例，并返回
		// 这里封装常用的java类，可以根据需要进行自定义扩展和增加新的构造类
		// 暂时不考虑带有参数的构造函数的支持
		if (classname.equals("HashMap") || classname.equals("java.util.HashMap")) {
			return new HashMap();
		}

		if (classname.equals("HashSet") || classname.equals("java.util.HashSet")) {
			return new HashSet();
		}
		
		if (classname.equals("ArrayList") || classname.equals("java.util.ArrayList")){
			return new ArrayList();
		}
		
		if (classname.equals("Date") || classname.equalsIgnoreCase("java.util.Date")) {
			return new Date();
		}
		
		if (classname.equals("String") || classname.equals("java.lang.String")) {
			return new String("");
		}
		
		if(classname.equals("StringBuffer") || classname.equals("java.lang.StringBuffer")) {
			return new StringBuffer("");
		}
		
		if(classname.equals("Boolean") || classname.equals("java.lang.Boolean")) {
			return new Boolean(false);
		}
		
		if(classname.equals("Integer") || classname.equals("java.lang.Integer")) {
			return new Integer(0);
		}
		
		if(classname.equals("Long") || classname.equals("java.lang.Long")) {
			return new Long(0);
		}

		if(classname.equals("Float") || classname.equals("java.lang.Float")) {
			return new Float(0);
		}

		if(classname.equals("Double") || classname.equals("java.lang.Double")) {
			return new Double(0);
		}

		if(classname.equals("BigDecimal") || classname.equals("java.math.BigDecimal")) {
			return new BigDecimal(0);
		}

		return null;
	}

	
	public native void consoleLog(String message) /*-{
													//alert(message);
													console.log( "[JVM][Java_NewInstanceImpl]:" + message );
													}-*/;

}


