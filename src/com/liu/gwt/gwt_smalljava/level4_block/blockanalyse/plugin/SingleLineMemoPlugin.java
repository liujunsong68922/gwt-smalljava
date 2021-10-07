package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;

/**
 * ��//��ͷ��ע�ͼ��
 * @author liujunsong
 *
 */
public class SingleLineMemoPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin{

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
				log2(strmemo);
				rootblock.computestring = "";
				return true;
			} else {
				strmemo = rootblock.computestring.substring(0, ilineendpos);
				log2(strmemo);
				rootblock.computestring = rootblock.computestring.substring(ilineendpos + 1);
				return true;
			}
			
		}else {
			return true;
		}

	}


//	@SuppressWarnings("deprecation")
//	@Override
//	public boolean analyse(ElementWrapper rootelement) {
//		// Step4.1 �ж��ǲ��� ��//�� ��ʼ
//		if (rootelement.getComputestring().startsWith("//")) {
//			// ��ȡ��һ�еĽ�����
//			int ilineendpos = this._getFirstLineEndPos(rootelement.getComputestring());
//			String strmemo = "";
//			if (ilineendpos == -1) {
//				// û���ҵ��س�����˵��ȫ���Ǳ�ע
//				strmemo = rootelement.getComputestring();
//				//rootblock.computestring = "";
//				rootelement.setComputestring("");
//			} else {
//				strmemo = rootelement.getComputestring().substring(0, ilineendpos);
//				//rootblock.computestring = rootelement.getComputestring().substring(ilineendpos + 1);
//				rootelement.setComputestring(rootelement.getComputestring().substring(ilineendpos + 1));
//			}
//			Element singleline = rootelement.getElement().addElement(SmallJavaBlockConst.SingleLineMemo);
//			singleline.setAttributeValue("BlockContent",strmemo);
//			
//			// ����ѭ��
//			return true;
//		}else {
//			return true;
//		}
//
//
//	}


//	@Override
//	public boolean analyse(JSONWrapper rootelement) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	private void log2(String s1) {
		System.out.println(s1);
	}
}
