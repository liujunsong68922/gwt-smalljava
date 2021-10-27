package com.smalljava.core.l6_supportenv.l6_oopsupport;

import java.util.HashMap;

import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.NewInstancePluginManager;
import com.smalljava.core.l6_supportenv.l6_oopsupport.objectcall.ObjectCallPluginManager;

public class SmallJavaOopSupportEnv {
	private NewInstancePluginManager newinstanceManager
	 	= new NewInstancePluginManager();
	
	private ObjectCallPluginManager objectcallManager
		= new ObjectCallPluginManager();

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
	
}
