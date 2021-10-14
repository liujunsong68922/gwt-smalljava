package com.smalljava.core.classloader.l1_javafile.analyse.plugin;

import java.util.ArrayList;

import com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl.JavaFileClassPlugin;
import com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl.JavaFileImportPlugin;
import com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl.JavaFileMultiLineMemoPlugin;
import com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl.JavaFilePackagePlugin;
import com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl.JavaFileSingleLineMemoPlugin;

public class JavaFileAnalysePluginManager {
	private static ArrayList<IJavaFileAnalysePlugin> pluginarray = new ArrayList<IJavaFileAnalysePlugin>();
	
	public JavaFileAnalysePluginManager() {
		initMap();
	}
	
	public void initMap() {
		if(pluginarray.size()==0) {
			pluginarray.add(new JavaFileImportPlugin());
			pluginarray.add(new JavaFileClassPlugin());
			pluginarray.add(new JavaFileMultiLineMemoPlugin());
			pluginarray.add(new JavaFilePackagePlugin());
			pluginarray.add(new JavaFileSingleLineMemoPlugin());
		}
	}

	public static ArrayList<IJavaFileAnalysePlugin> getPluginarray() {
		return pluginarray;
	}

	public static void setPluginarray(ArrayList<IJavaFileAnalysePlugin> pluginarray) {
		JavaFileAnalysePluginManager.pluginarray = pluginarray;
	}
	
}
