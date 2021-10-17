package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;


public class MultiLineMemoPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(MultiLineMemoPlugin.class);
	
	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.1
		if (rootblock.computestring.startsWith("/*")) {
			// 
			int ilineendpos = this._findfirstStringForBlock(rootblock.computestring, "*/");
			String strmemo = "";
			if (ilineendpos == -1) {
				// 
				return false;
			} else {
				strmemo = rootblock.computestring.substring(0, ilineendpos + 2);
				logger.info(strmemo);
				//
				BasicBlock block1 = new BasicBlock(SmallJavaBlockConst.MultiLineMemo, strmemo, rootblock);
				rootblock.getChildren().add(block1);
				rootblock.computestring = rootblock.computestring.substring(ilineendpos + 2);
				return true;
			}
		} else {
			return true;
		}
	}
}
