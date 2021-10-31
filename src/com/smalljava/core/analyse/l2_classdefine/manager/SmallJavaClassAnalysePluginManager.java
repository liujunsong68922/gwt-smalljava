package com.smalljava.core.analyse.l2_classdefine.manager;

import java.util.ArrayList;

import com.smalljava.core.analyse.l2_classdefine.worker_plugin.SmallJavaClassMethodPlugin;
import com.smalljava.core.analyse.l2_classdefine.worker_plugin.SmallJavaClassMultiLineMemoPlugin;
import com.smalljava.core.analyse.l2_classdefine.worker_plugin.SmallJavaClassSingleLineMemoPlugin;
import com.smalljava.core.analyse.l2_classdefine.worker_plugin.SmallJavaClassVarDefinePlugin;

public class SmallJavaClassAnalysePluginManager {
	private static ArrayList<ISmallJavaClassAnalysePlugin> pluginarray = new ArrayList<ISmallJavaClassAnalysePlugin>();
	
	public SmallJavaClassAnalysePluginManager() {
		initMap();
	}
	
	public void initMap() {
		if(pluginarray.size()==0) {
			pluginarray.add(new SmallJavaClassSingleLineMemoPlugin());
			pluginarray.add(new SmallJavaClassMultiLineMemoPlugin());
			pluginarray.add(new SmallJavaClassVarDefinePlugin());
			pluginarray.add(new SmallJavaClassMethodPlugin());
		}
	}

	public static ArrayList<ISmallJavaClassAnalysePlugin> getPluginarray() {
		return pluginarray;
	}

	public static void setPluginarray(ArrayList<ISmallJavaClassAnalysePlugin> pluginarray) {
		SmallJavaClassAnalysePluginManager.pluginarray = pluginarray;
	}
	
}
