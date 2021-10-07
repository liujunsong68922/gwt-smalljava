package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;

public class ImportPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.7 �ԡ� {����ͷ
		if (rootblock.computestring.startsWith("import ")) {
			return this._analyse_import(rootblock);
		} else {
			return true;
		}
	}
	
	/**
	 * MEMO�����������ԡ�import����ͷ�Ĵ����
	 * 
	 * @param rootblock
	 * @return
	 */
	private boolean _analyse_import(BasicBlock rootblock) {
		log("enter _analyse_import");

		int ipos2 = this._findfirstStringForBlock(rootblock.computestring, ";");
		if (ipos2 == -1) {
			// �������{����û�������}
			// �������Ų���ƥ��Ĵ���
			log("����ʧ�ܣ��Ҳ���ƥ���;:" + rootblock.computestring);
			return false;
		} else {
			// ����һ���µ��ӽڵ㣬��{}ȥ��
			String sinfo = rootblock.computestring.substring(0, ipos2);
			BasicBlock child1 = new BasicBlock(SmallJavaBlockConst.ImportBlock,sinfo,rootblock);
			rootblock.getChildren().add(child1);
			rootblock.computestring = rootblock.computestring.substring(ipos2 + 1);
			log("_analyse_import ok.");
			return true;
		}
	}	

//	@Override
//	public boolean analyse(ElementWrapper rootelement) {
//		// �ݲ�ʵ��
//		return true;
//	}
//
//	@Override
//	public boolean analyse(JSONWrapper rootelement) {
//		// �ݲ�ʵ��
//		return true;
//	}

}
