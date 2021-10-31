package com.smalljava.core.analyse.l4_block.worker_plugin;

import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.commonvo.l4_block.SmallJavaBlockConst;

public class BracePlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.7 
		if (rootblock.computestring.startsWith("{")) {
			return this._analyse_brace(rootblock);
		} else {
			return true;
		}
	}

	private boolean _analyse_brace(BasicBlock rootblock) {
		log("enter _analyse_brace");

		int ipos2 = this._findfirstStringForBlock(rootblock.computestring, "}");
		if (ipos2 == -1) {
			log("[ERROR]" + rootblock.computestring);
			return false;
		} else {
			//
			String sinfo = rootblock.computestring.substring(1, ipos2);
			BasicBlock child1 = new BasicBlock(SmallJavaBlockConst.SubBlock,sinfo,rootblock);
			rootblock.getChildren().add(child1);
			if (ipos2 > 2) {
				rootblock.computestring = rootblock.computestring.substring(ipos2 + 1);
			} else {
				rootblock.computestring = "";
			}
			log("_analyse_brace ok.");
			return true;
		}
	}
	
}
