package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockvo.BasicBlock;

/**
 * 以//开头的注释检查
 * @author liujunsong
 *
 */
public class SingleLineMemoPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin{
	private Logger logger = LoggerFactory.getLogger(SingleLineMemoPlugin.class);
	
	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.1 判断是不是 【//】 开始
		if (rootblock.computestring.startsWith("//")) {
			// 读取到一行的结束点
			int ilineendpos = this._getFirstLineEndPos(rootblock.computestring);
			String strmemo = "";
			if (ilineendpos == -1) {
				// 没有找到回车符，说明全部是备注
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
