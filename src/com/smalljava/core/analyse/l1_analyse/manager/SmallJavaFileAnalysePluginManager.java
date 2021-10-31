package com.smalljava.core.analyse.l1_analyse.manager;

import java.util.ArrayList;

import com.smalljava.core.analyse.l1_analyse.worker_plugin.SmallJavaFileClassPlugin;
import com.smalljava.core.analyse.l1_analyse.worker_plugin.SmallJavaFileImportPlugin;
import com.smalljava.core.analyse.l1_analyse.worker_plugin.SmallJavaFileMultiLineMemoPlugin;
import com.smalljava.core.analyse.l1_analyse.worker_plugin.SmallJavaFilePackagePlugin;
import com.smalljava.core.analyse.l1_analyse.worker_plugin.SmallJavaFileSingleLineMemoPlugin;

//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl.JavaFileClassPlugin;
//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl.JavaFileImportPlugin;
//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl.JavaFileMultiLineMemoPlugin;
//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl.JavaFilePackagePlugin;
//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl.JavaFileSingleLineMemoPlugin;

public class SmallJavaFileAnalysePluginManager {
	private static ArrayList<ISmallJavaFileAnalysePlugin> pluginarray = new ArrayList<ISmallJavaFileAnalysePlugin>();
	
	public SmallJavaFileAnalysePluginManager() {
		initMap();
	}
	
	public void initMap() {
		if(pluginarray.size()==0) {
			pluginarray.add(new SmallJavaFileImportPlugin());
			pluginarray.add(new SmallJavaFileClassPlugin());
			pluginarray.add(new SmallJavaFileMultiLineMemoPlugin());
			pluginarray.add(new SmallJavaFilePackagePlugin());
			pluginarray.add(new SmallJavaFileSingleLineMemoPlugin());
		}
	}

	public static ArrayList<ISmallJavaFileAnalysePlugin> getPluginarray() {
		return pluginarray;
	}

	public static void setPluginarray(ArrayList<ISmallJavaFileAnalysePlugin> pluginarray) {
		SmallJavaFileAnalysePluginManager.pluginarray = pluginarray;
	}
	
}
