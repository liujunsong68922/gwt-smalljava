package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;

public class BracePlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.7 以【 {】开头
		if (rootblock.computestring.startsWith("{")) {
			return this._analyse_brace(rootblock);
		} else {
			return true;
		}
	}

	/**
	 * MEMO：分析处理以【{】开头的代码块
	 * 
	 * @param rootblock
	 * @return
	 */
	private boolean _analyse_brace(BasicBlock rootblock) {
		log("enter _analyse_brace");

		int ipos2 = this._findfirstStringForBlock(rootblock.computestring, "}");
		if (ipos2 == -1) {
			// 有左面的{，但没有右面的}
			// 这是括号不能匹配的错误
			log("分析失败，找不到匹配的}:" + rootblock.computestring);
			return false;
		} else {
			// 生成一个新的子节点，把{}去掉
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
