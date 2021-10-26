package com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin;

import java.util.ArrayList;

import com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin.impl.SmallJavaClassMethodPlugin;
import com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin.impl.SmallJavaClassMultiLineMemoPlugin;
import com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin.impl.SmallJavaClassSingleLineMemoPlugin;
import com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin.impl.SmallJavaClassVarDefinePlugin;

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
