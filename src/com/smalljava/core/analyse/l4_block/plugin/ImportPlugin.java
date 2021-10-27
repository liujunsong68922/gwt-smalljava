package com.smalljava.core.analyse.l4_block.plugin;

import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.commonvo.l4_block.SmallJavaBlockConst;

public class ImportPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.7
		if (rootblock.computestring.startsWith("import ")) {
			return this._analyse_import(rootblock);
		} else {
			return true;
		}
	}
	

	private boolean _analyse_import(BasicBlock rootblock) {
		log("enter _analyse_import");

		int ipos2 = this._findfirstStringForBlock(rootblock.computestring, ";");
		if (ipos2 == -1) {
			log("cannot find ; :" + rootblock.computestring);
			return false;
		} else {
			String sinfo = rootblock.computestring.substring(0, ipos2);
			BasicBlock child1 = new BasicBlock(SmallJavaBlockConst.ImportBlock,sinfo,rootblock);
			rootblock.getChildren().add(child1);
			rootblock.computestring = rootblock.computestring.substring(ipos2 + 1);
			log("_analyse_import ok.");
			return true;
		}
	}	
}
