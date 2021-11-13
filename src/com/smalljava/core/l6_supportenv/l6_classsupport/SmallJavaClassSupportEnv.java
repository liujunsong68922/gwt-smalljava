package com.smalljava.core.l6_supportenv.l6_classsupport;

import java.util.HashMap;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;
import com.smalljava.core.l6_supportenv.IClassObject;
import com.smalljava.core.l6_supportenv.ISupportEnv;
import com.smalljava.core.l6_supportenv.l6_classsupport.helper.SmallJavaClassSupportEnv_ClassLoader;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;

/**
 * SmallJava 内部Class关键词支持的支持环境
 * MEMO： 这个执行环境集成了SmallJavaClassManager 和 SmallJavaObjectManager
 * MEMO： 这两个具体的执行管理类，这个类是一个封装类
 * @author liujunsong
 *
 */
public class SmallJavaClassSupportEnv implements ISupportEnv {
	private Logger logger = LoggerFactory.getLogger(SmallJavaClassSupportEnv.class);
	
	/**
	 * MEMO：这是所有使用字符串格式加载的SmallJava的内部类定义
	 */
	private HashMap<String,SmallJavaClassTemplateVO> classdefinemap = 
			new HashMap<String,SmallJavaClassTemplateVO>();
	/**
	 * MEMO：这是所有使用字符串加载以后的ClassImpl转换类的定义
	 * MEMO:SmallJavaClassImpl从SmallJavaClassTemplateVO中转换得到
	 */
	private HashMap<String,SmallJavaIClassImpl> classmap = new HashMap<String,SmallJavaIClassImpl>();
	
//	private HashMap<String,IVarTable> classStaticVartableMap 
//	= new HashMap<String,IVarTable>();
	
	public HashMap<String, SmallJavaClassTemplateVO> getClassdefinemap() {
		return classdefinemap;
	}

	public void setClassdefinemap(HashMap<String, SmallJavaClassTemplateVO> classdefinemap) {
		this.classdefinemap = classdefinemap;
	}

	//get method
//	public HashMap<String, IVarTable> getClassStaticVartableMap() {
//		return classStaticVartableMap;
//	}

	//set method
//	public void setClassStaticVartableMap(HashMap<String, IVarTable> classStaticVartableMap) {
//		this.classStaticVartableMap = classStaticVartableMap;
//	}	
	
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
		return classloader.loadClassDefineString( strClassDefine,this,new SmallJavaOopSupportEnv());
	}
	
	@Override
	public boolean loadClassDefine(String fullname, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean loadClassDefine(IClassObject classimpl, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		//[ERROR] This function is not support by ClassSupportEnv
		logger.error("[ERROR] unsupported loadClassDefine.");
		return false;
	}
	
	@Override
	public IClassObject getIClassByName(String fullname) {
		IClassObject classimpl = this.classmap.get(fullname);
		return classimpl;
	}
}
