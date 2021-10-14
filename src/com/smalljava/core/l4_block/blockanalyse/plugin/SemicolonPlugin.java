package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;

public class SemicolonPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(SemicolonPlugin.class);

	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.8 以字符开头
		if (rootblock.computestring.length() > 0) {
			char char1 = rootblock.computestring.charAt(0);
			// 有效的代码开始包括：大写字母，小写字母，左括号
			if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z') || (char1 >= '0' && char1 <= '9')
					|| (char1 == '(')) {
				// 跳过特定的关键词：for,if,do,while
				if (!rootblock.computestring.startsWith("for ") && !rootblock.computestring.startsWith("for(")
						&& !rootblock.computestring.startsWith("if ") && !rootblock.computestring.startsWith("if(")
						&& !rootblock.computestring.startsWith("do ") && !rootblock.computestring.startsWith("do{")
						&& !rootblock.computestring.startsWith("while ")
						&& !rootblock.computestring.startsWith("while(")
						&& !rootblock.computestring.startsWith("import")
						&& !rootblock.computestring.startsWith("return ")
						&& !rootblock.computestring.startsWith("return(")) {
					// 查找下一个;
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
		// 如果以public,private开头，则退出
		if (rootblock.computestring.startsWith("public") || rootblock.computestring.startsWith("private")) {
			return true;
		}

		int ipos = this._findfirstStringForBlock(rootblock.computestring, ";");
		String leftstring = "";
		if (ipos < 0) {
			logger.info("查找【;】失败,这段代码已经是一个表达式了。");
			leftstring = rootblock.computestring;
			rootblock.computestring = "";
			return true;
		} else {
			// 按照第一个[;]的位置进行切分
			leftstring = rootblock.computestring.substring(0, ipos);
			rootblock.computestring = rootblock.computestring.substring(ipos + 1);

			BasicBlock block1 = new BasicBlock(SmallJavaBlockConst.Expression, leftstring, rootblock);
			rootblock.getChildren().add(block1);
		}

		return true;
	}
}
