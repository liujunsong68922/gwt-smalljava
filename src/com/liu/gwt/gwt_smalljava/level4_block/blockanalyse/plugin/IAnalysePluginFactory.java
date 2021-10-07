package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

/**
 * �����࣬���ù����෵�ص����߽��е���
 * ����������չ���ƣ����Ƕ��岻ͬ��Plugin��Ȼ����������е���
 * @author liujunsong
 *
 */
public class IAnalysePluginFactory {
	
	public static IAnalysePlugin getIAnalysePlugin(String name) {
		if(name==null) {
			System.out.println("��ERROR��You call a unknown plugin name.name:null");
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
		System.out.println("��ERROR��You call a unknown plugin name.name:"+name);
		return null;
	}
}
