package com.smalljava.core.l2_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

public class IAnalysePluginFactory {
	private static Logger logger = LoggerFactory.getLogger(IAnalysePluginFactory.class);
	
	public static IAnalysePlugin getIAnalysePlugin(String name) {
		if(name==null) {
			logger.error("[ERROR]You call a unknown plugin name.name:null");
			return null;			
		}
		
		if(name.equals("if")) {
			
		}
		
		if(name.equals("else")) {
			
		}
		
		if(name.equals("do")) {
			
		}
		
		if(name.equals("while")){
			
		}
		logger.error("[error]You call a unknown plugin name.name:"+name);
		return null;
	}
}
