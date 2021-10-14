package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;

/**
 * 以//开头的注释检查
 * 
 * @author liujunsong
 *
 */
public class MultiLineMemoPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(MultiLineMemoPlugin.class);
	
	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.1 判断是不是 【/*】 开始
		if (rootblock.computestring.startsWith("/*")) {
			// 读取到一行的结束点
			int ilineendpos = this._findfirstStringForBlock(rootblock.computestring, "*/");
			String strmemo = "";
			if (ilineendpos == -1) {
				// 没有找到，说明逻辑错误
				return false;
			} else {
				strmemo = rootblock.computestring.substring(0, ilineendpos + 2);
				logger.info(strmemo);
				//设置为多行备注节点类型,此处逻辑错误，需要创建新节点
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
