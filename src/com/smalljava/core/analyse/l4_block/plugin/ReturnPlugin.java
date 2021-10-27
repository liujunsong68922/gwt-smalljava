package com.smalljava.core.analyse.l4_block.plugin;

import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.commonvo.l4_block.childblock.ReturnBlock;

public class ReturnPlugin extends DefaultAbstractAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		if (rootblock.computestring.startsWith("return ")
			||	rootblock.computestring.startsWith("return(")) {
			String strcontent = rootblock.computestring.substring(6);
			strcontent = this._trimReturnAndSpace(strcontent);
			ReturnBlock returnblock = new ReturnBlock("ReturnBlock", strcontent, rootblock);
			rootblock.getChildren().add(returnblock);
			rootblock.computestring="";
			return true;
		} else {
			return true;
		}
	}
}
