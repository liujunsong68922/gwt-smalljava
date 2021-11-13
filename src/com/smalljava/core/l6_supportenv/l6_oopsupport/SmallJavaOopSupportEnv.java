package com.smalljava.core.l6_supportenv.l6_oopsupport;

import java.util.HashMap;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l6_supportenv.IClassObject;
import com.smalljava.core.l6_supportenv.ISupportEnv;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.NewInstancePluginManager;
import com.smalljava.core.l6_supportenv.l6_oopsupport.objectcall.ObjectCallPluginManager;

public class SmallJavaOopSupportEnv implements ISupportEnv {
	Logger logger = LoggerFactory.getLogger(SmallJavaOopSupportEnv.class);
	/**
	 * MEMO：由于GWT不支持类的反射机制，所以所有的外部类插件必须实现IClass的接口方可注册
	 */
	private HashMap<String,IClassObject> oopmap = new HashMap<String,IClassObject>();
	
	
	/**
	 * MEMO：类实例化支持插件
	 */
	private NewInstancePluginManager newinstanceManager
	 	= new NewInstancePluginManager();
	
	/**
	 * MEMO：对象方法调用支持插件
	 */
	private ObjectCallPluginManager objectcallManager
		= new ObjectCallPluginManager();

	/**
	 * MEMO：对象属性获取方法插件
	 * MEMO：按照JAVA的语法规则，目前只支持对象属性的GET，暂不支持Set方法
	 */
//	private ObjectPropertyGetPluginManager objectpropertyManager
//		= new ObjectPropertyGetPluginManager();
	
	public NewInstancePluginManager getNewinstanceManager() {
		return newinstanceManager;
	}

	public void setNewinstanceManager(NewInstancePluginManager newinstanceManager) {
		this.newinstanceManager = newinstanceManager;
	}

	public ObjectCallPluginManager getObjectcallManager() {
		return objectcallManager;
	}

	public void setObjectcallManager(ObjectCallPluginManager objectcallManager) {
		this.objectcallManager = objectcallManager;
	}

//	public ObjectPropertyGetPluginManager getObjectpropertyManager() {
//		return objectpropertyManager;
//	}
//
//	public void setObjectpropertyManager(ObjectPropertyGetPluginManager objectpropertyManager) {
//		this.objectpropertyManager = objectpropertyManager;
//	}

	@Override
	public boolean loadClassDefine(String fullname, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		// This function is not supported by smalljava oopsupport.
		logger.error("[ERROR] loadClassDefine is not supported by oop env.");
		return false;
	}

	/**
	 * oop 支持类的注册接口
	 */
	@Override
	public boolean loadClassDefine(IClassObject classimpl, 
			SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		String classname = classimpl.getClassFullname();
		this.oopmap.put(classname, classimpl);
		return true;
	}

	/**
	 * MEMO：按照类的名称来获取一个IClass，代表一个oop的支持类
	 */
	@Override
	public IClassObject getIClassByName(String fullname) {
		IClassObject classimpl = this.oopmap.get(fullname);
		return classimpl;
	}
}
