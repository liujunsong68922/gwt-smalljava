package com.smalljava.core.analyse.l4_block.worker_plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l4_block.BasicBlock;


public class SingleLineMemoPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin{
	private Logger logger = LoggerFactory.getLogger(SingleLineMemoPlugin.class);
	
	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.1 
		if (rootblock.computestring.startsWith("//")) {
			// 
			int ilineendpos = this._getFirstLineEndPos(rootblock.computestring);
			String strmemo = "";
			if (ilineendpos == -1) {
				// ע
				strmemo = rootblock.computestring;
				logger.error(strmemo);
				rootblock.computestring = "";
				return true;
			} else {
				strmemo = rootblock.computestring.substring(0, ilineendpos);
				logger.error(strmemo);
				rootblock.computestring = rootblock.computestring.substring(ilineendpos + 1);
				return true;
			}
			
		}else {
			return true;
		}
	}
}
