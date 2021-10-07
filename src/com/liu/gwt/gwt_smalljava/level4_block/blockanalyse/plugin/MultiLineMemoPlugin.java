package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;

/**
 * ��//��ͷ��ע�ͼ��
 * 
 * @author liujunsong
 *
 */
public class MultiLineMemoPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.1 �ж��ǲ��� ��/*�� ��ʼ
		if (rootblock.computestring.startsWith("/*")) {
			// ��ȡ��һ�еĽ�����
			int ilineendpos = this._findfirstStringForBlock(rootblock.computestring, "*/");
			String strmemo = "";
			if (ilineendpos == -1) {
				// û���ҵ���˵���߼�����
				return false;
			} else {
				strmemo = rootblock.computestring.substring(0, ilineendpos + 2);
				log2(strmemo);
				//����Ϊ���б�ע�ڵ�����,�˴��߼�������Ҫ�����½ڵ�
				BasicBlock block1 = new BasicBlock(SmallJavaBlockConst.MultiLineMemo, strmemo, rootblock);
				rootblock.getChildren().add(block1);
				rootblock.computestring = rootblock.computestring.substring(ilineendpos + 2);
				return true;
			}
		} else {
			return true;
		}
	}

//	@SuppressWarnings("deprecation")
//	@Override
//	public boolean analyse(ElementWrapper rootblock) {
//		// Step4.1 �ж��ǲ��� ��/*�� ��ʼ
//		if (rootblock.getComputestring().startsWith("/*")) {
//			// ��ȡ��һ�еĽ�����
//			int ilineendpos = this._findfirstStringForBlock(rootblock.getComputestring(), "*/");
//			String strmemo = "";
//			if (ilineendpos == -1) {
//				// û���ҵ���˵���߼�����
//				return false;
//			} else {
//				strmemo = rootblock.getComputestring().substring(0, ilineendpos + 2);
//				rootblock.setComputestring(rootblock.getComputestring().substring(ilineendpos + 2));
//			}
//			// ����һ��MultiLineMemo,��д�뵽rootblock����ȥ
//			Element mulitlineelement = rootblock.getElement().addElement(SmallJavaBlockConst.MultiLineMemo);
//			mulitlineelement.setAttributeValue("BlockContent", strmemo);
//			// ����ѭ��
//			return true;
//		} else {
//			return true;
//		}
//	}
//
//	@Override
//	public boolean analyse(JSONWrapper rootelement) {
//		// TODO Auto-generated method stub
//		return true;
//	}
	
	private void log2(String s1) {
		System.out.println(s1);
	}

}
