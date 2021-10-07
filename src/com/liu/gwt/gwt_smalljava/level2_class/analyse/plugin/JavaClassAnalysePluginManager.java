package com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin;

import java.util.ArrayList;

import com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin.impl.JavaClassMethodPlugin;
import com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin.impl.JavaClassMultiLineMemoPlugin;
import com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin.impl.JavaClassSingleLineMemoPlugin;
import com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin.impl.JavaClassVarDefinePlugin;

//import com.liu.smalljavav2.level2_class.analyse.plugin.impl.JavaClassMethodPlugin;
//import com.liu.smalljavav2.level2_class.analyse.plugin.impl.JavaClassMultiLineMemoPlugin;
//import com.liu.smalljavav2.level2_class.analyse.plugin.impl.JavaClassSingleLineMemoPlugin;
//import com.liu.smalljavav2.level2_class.analyse.plugin.impl.JavaClassVarDefinePlugin;

public class JavaClassAnalysePluginManager {
	private static ArrayList<IJavaClassAnalysePlugin> pluginarray = new ArrayList<IJavaClassAnalysePlugin>();
	
	public JavaClassAnalysePluginManager() {
		initMap();
	}
	
	public void initMap() {
		if(pluginarray.size()==0) {
			pluginarray.add(new JavaClassSingleLineMemoPlugin());
			pluginarray.add(new JavaClassMultiLineMemoPlugin());
			pluginarray.add(new JavaClassVarDefinePlugin());
			pluginarray.add(new JavaClassMethodPlugin());
		}
	}

	public static ArrayList<IJavaClassAnalysePlugin> getPluginarray() {
		return pluginarray;
	}

	public static void setPluginarray(ArrayList<IJavaClassAnalysePlugin> pluginarray) {
		JavaClassAnalysePluginManager.pluginarray = pluginarray;
	}
	
}
