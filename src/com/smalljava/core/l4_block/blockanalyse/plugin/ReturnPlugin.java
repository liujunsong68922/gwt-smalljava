package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.ReturnBlock;

/**
 * MEMO：针对Return关键词 做特殊处理
 * @author liujunsong
 *
 */
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
