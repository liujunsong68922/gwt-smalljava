package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockvo.BasicBlock;

/**
 * ��//��ͷ��ע�ͼ��
 * @author liujunsong
 *
 */
public class SingleLineMemoPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin{
	private Logger logger = LoggerFactory.getLogger(SingleLineMemoPlugin.class);
	
	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.1 �ж��ǲ��� ��//�� ��ʼ
		if (rootblock.computestring.startsWith("//")) {
			// ��ȡ��һ�еĽ�����
			int ilineendpos = this._getFirstLineEndPos(rootblock.computestring);
			String strmemo = "";
			if (ilineendpos == -1) {
				// û���ҵ��س�����˵��ȫ���Ǳ�ע
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
