package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;

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

//	@Override
//	public boolean analyse(ElementWrapper rootelement) {
//		// Step4.7 �ԡ� {����ͷ
//		if (rootelement.getComputestring().startsWith("{")) {
//			return this._analyse_brace(rootelement);
//		} else {
//			return true;
//		}
//	}

//	@Override
//	public boolean analyse(JSONWrapper rootelement) {
//
//		return true;
//	}
	
	/**
	 * MEMO�����������ԡ�{����ͷ�Ĵ����
	 * 
	 * @param rootblock
	 * @return
	 */
//	@SuppressWarnings("deprecation")
//	private boolean _analyse_brace(ElementWrapper rootblock) {
//		log("enter _analyse_brace");
//
//		int ipos2 = this._findfirstStringForBlock(rootblock.getComputestring(), "}");
//		if (ipos2 == -1) {
//			// �������{����û�������}
//			// �������Ų���ƥ��Ĵ���
//			log("����ʧ�ܣ��Ҳ���ƥ���}:" + rootblock.getComputestring());
//			return false;
//		} else {
//			// ����һ���µ��ӽڵ㣬��{}ȥ��
//			String sinfo = rootblock.getComputestring().substring(1, ipos2);
//			Element child1 = rootblock.getElement().addElement(SmallJavaBlockConst.SubBlock);
//			child1.setAttributeValue("BlockContent", sinfo);
//			if (ipos2 > 2) {
//				rootblock.setComputestring ( rootblock.getComputestring().substring(ipos2 + 1));
//			} else {
//				rootblock.setComputestring ( "");
//			}
//			log("_analyse_brace ok.");
//			return true;
//		}
//	}	
}
