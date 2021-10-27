package com.smalljava.core.analyse.l4_block.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.commonvo.l4_block.SmallJavaBlockConst;

public class SemicolonPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(SemicolonPlugin.class);

	@Override
	public boolean analyse(BasicBlock rootblock) {
		//
		if (rootblock.computestring.length() > 0) {
			char char1 = rootblock.computestring.charAt(0);
			if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z') || (char1 >= '0' && char1 <= '9')
					|| (char1 == '(')) {
				if (!rootblock.computestring.startsWith("for ") && !rootblock.computestring.startsWith("for(")
						&& !rootblock.computestring.startsWith("if ") && !rootblock.computestring.startsWith("if(")
						&& !rootblock.computestring.startsWith("do ") && !rootblock.computestring.startsWith("do{")
						&& !rootblock.computestring.startsWith("while ")
						&& !rootblock.computestring.startsWith("while(")
						&& !rootblock.computestring.startsWith("import")
						&& !rootblock.computestring.startsWith("return ")
						&& !rootblock.computestring.startsWith("return(")) {
					boolean b1 = this._analyse_semicolon(rootblock);
					return b1;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @param rootblock
	 * @return
	 */
	private boolean _analyse_semicolon(BasicBlock rootblock) {
		logger.info("enter _analyse_semicolon");
		rootblock.computestring = this._trimReturnAndSpace(rootblock.computestring);
		if (rootblock.computestring.startsWith("public") || rootblock.computestring.startsWith("private")) {
			return true;
		}

		int ipos = this._findfirstStringForBlock(rootblock.computestring, ";");
		String leftstring = "";
		if (ipos < 0) {
			logger.info("cannot find ;");
			leftstring = rootblock.computestring;
			rootblock.computestring = "";
			return true;
		} else {
			//
			leftstring = rootblock.computestring.substring(0, ipos);
			rootblock.computestring = rootblock.computestring.substring(ipos + 1);

			BasicBlock block1 = new BasicBlock(SmallJavaBlockConst.Expression, leftstring, rootblock);
			rootblock.getChildren().add(block1);
		}

		return true;
	}
}
