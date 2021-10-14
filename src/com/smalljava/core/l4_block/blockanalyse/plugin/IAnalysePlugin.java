package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.l4_block.blockvo.BasicBlock;

/**
 * JAVA语法分析器的插件，以插件形式提供，被主程序来调用
 * MEMO：所有的插件实现统一的接口，实现统一的功能
 * @author liujunsong
 *
 */
public interface IAnalysePlugin {
	
	public boolean analyse(BasicBlock rootblock);

}
