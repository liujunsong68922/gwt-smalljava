package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * �����࣬���ù����෵�ص����߽��е���
 * ����������չ���ƣ����Ƕ��岻ͬ��Plugin��Ȼ����������е���
 * @author liujunsong
 *
 */
public class IAnalysePluginFactory {
	private static Logger logger = LoggerFactory.getLogger(IAnalysePluginFactory.class);
	
	public static IAnalysePlugin getIAnalysePlugin(String name) {
		if(name==null) {
			logger.error("��ERROR��You call a unknown plugin name.name:null");
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
		logger.error("��ERROR��You call a unknown plugin name.name:"+name);
		return null;
	}
}
