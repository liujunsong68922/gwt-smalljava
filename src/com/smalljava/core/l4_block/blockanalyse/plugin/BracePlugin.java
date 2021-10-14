package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;

public class BracePlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.7 �ԡ� {����ͷ
		if (rootblock.computestring.startsWith("{")) {
			return this._analyse_brace(rootblock);
		} else {
			return true;
		}
	}

	/**
	 * MEMO�����������ԡ�{����ͷ�Ĵ����
	 * 
	 * @param rootblock
	 * @return
	 */
	private boolean _analyse_brace(BasicBlock rootblock) {
		log("enter _analyse_brace");

		int ipos2 = this._findfirstStringForBlock(rootblock.computestring, "}");
		if (ipos2 == -1) {
			// �������{����û�������}
			// �������Ų���ƥ��Ĵ���
			log("����ʧ�ܣ��Ҳ���ƥ���}:" + rootblock.computestring);
			return false;
		} else {
			// ����һ���µ��ӽڵ㣬��{}ȥ��
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
