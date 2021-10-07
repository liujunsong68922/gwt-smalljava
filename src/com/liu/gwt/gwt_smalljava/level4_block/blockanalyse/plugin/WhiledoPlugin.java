package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.WHILEBlock;

/**
 * while()do{} ������
 * 
 * @author liujunsong
 *
 */
public class WhiledoPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.5 while-doѭ���ж�
		if (rootblock.computestring.startsWith("while(") || rootblock.computestring.startsWith("while ")) {
			return this._analyse_while(rootblock);

		} else {
			return true;
		}
	}

	private boolean _analyse_while(BasicBlock rootblock) {
		int ipos1 = _findfirstStringForBlock(rootblock.computestring, "(");
		if (ipos1 == -1) {
			System.out.println("while����������û���ҵ�(");
			return false;
		}
		int ipos2 = _findfirstStringForBlock(rootblock.computestring, ")");
		if (ipos2 == -1) {
			System.out.println("while����������û���ҵ�)");
			return false;
		}

		WHILEBlock whilenode = new WHILEBlock(SmallJavaBlockConst.WhileBlock,rootblock);

		// ����IF�ڵ���жϱ��ʽ����
		String whilestring = rootblock.computestring.substring(ipos1 + 1, ipos2);

		BasicBlock whilecondition = new BasicBlock(SmallJavaBlockConst.WhileCondition, whilestring,whilenode);
		whilenode.setWhilecondition(whilecondition);
		whilenode.getChildren().add(whilecondition);

		// sinfo���г�while()�Ĳ���
		String sinfo = rootblock.computestring.substring(ipos2 + 1);
		// ȥ���ո�ͻس�����
		sinfo = _trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) == '{') {
			// ���if������ٵ���{������Ҷ�Ӧ��}�ַ���
			int ipos3 = _findfirstStringForBlock(sinfo, "}");
			if (ipos3 == -1) {
				System.out.println("����}ʧ��:" + sinfo);
				return false;
			}
			String strwhileloop = sinfo.substring(0, ipos3 + 1);
			BasicBlock loopnode1 = new BasicBlock(SmallJavaBlockConst.WhileLoopNode, strwhileloop,whilenode);
			// loopnode1.setParentVarmaps(whilenode.getVarmaps());
			whilenode.setWhileloopnode(loopnode1);
			whilenode.getChildren().add(loopnode1);
			sinfo = sinfo.substring(ipos3 + 1);
		} else {
			int ipos4 = _findfirstStringForBlock(sinfo, ";");
			if (ipos4 == -1) {
				System.out.println("����;ʧ��" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos4 + 1);
			BasicBlock loopnode2 = new BasicBlock(SmallJavaBlockConst.WhileLoopNode, sifblock,whilenode);
			whilenode.setWhileloopnode(loopnode2);
			whilenode.getChildren().add(loopnode2);
			sinfo = sinfo.substring(ipos4 + 1);
		}

		// ��fornode���뵽children����
		rootblock.computestring = sinfo;
		rootblock.getChildren().add(whilenode);
		return true;

	}

//	@Override
//	public boolean analyse(ElementWrapper rootelement) {
//		// Step4.5 while-doѭ���ж�
//		if (rootelement.getComputestring().startsWith("while(")
//				|| rootelement.getComputestring().startsWith("while ")) {
//			return this._analyse_while(rootelement);
//
//		} else {
//			return true;
//		}
//	}

//	@Override
//	public boolean analyse(JSONWrapper rootelement) {
//		// TODO Auto-generated method stub
//		return true;
//	}
	
//	@SuppressWarnings("deprecation")
//	private boolean _analyse_while(ElementWrapper rootblock) {
//		int ipos1 = _findfirstStringForBlock(rootblock.getComputestring(), "(");
//		if (ipos1 == -1) {
//			System.out.println("while����������û���ҵ�(");
//			return false;
//		}
//		int ipos2 = _findfirstStringForBlock(rootblock.getComputestring(), ")");
//		if (ipos2 == -1) {
//			System.out.println("while����������û���ҵ�)");
//			return false;
//		}
//
//		Element whilenode = rootblock.getElement().addElement(SmallJavaBlockConst.WhileBlock);
//		whilenode.setAttributeValue("BlockContent", "");
//		
//		// ����IF�ڵ���жϱ��ʽ����
//		String whilestring = rootblock.getComputestring().substring(ipos1 + 1, ipos2);
//
//		Element whilecondition = whilenode.addElement(SmallJavaBlockConst.WhileCondition);
//		whilecondition.setAttributeValue("BlockContent", whilestring);
//
//		// sinfo���г�while()�Ĳ���
//		String sinfo = rootblock.getComputestring().substring(ipos2 + 1);
//		// ȥ���ո�ͻس�����
//		sinfo = _trimReturnAndSpace(sinfo);
//		if (sinfo.charAt(0) == '{') {
//			// ���if������ٵ���{������Ҷ�Ӧ��}�ַ���
//			int ipos3 = _findfirstStringForBlock(sinfo, "}");
//			if (ipos3 == -1) {
//				System.out.println("����}ʧ��:" + sinfo);
//				return false;
//			}
//			String strwhileloop = sinfo.substring(0, ipos3 + 1);
//			Element loopnode1 = whilenode.addElement(SmallJavaBlockConst.WhileLoopNode);
//			loopnode1.setAttributeValue("BlockContent", strwhileloop);
//			sinfo = sinfo.substring(ipos3 + 1);
//		} else {
//			int ipos4 = _findfirstStringForBlock(sinfo, ";");
//			if (ipos4 == -1) {
//				System.out.println("����;ʧ��" + sinfo);
//				return false;
//			}
//			String sifblock = sinfo.substring(0, ipos4 + 1);
//			Element loopnode2 = whilenode.addElement(SmallJavaBlockConst.WhileLoopNode);
//			loopnode2.setAttributeValue("BlockContent", sifblock);
//
//			sinfo = sinfo.substring(ipos4 + 1);
//		}
//
//		// ��fornode���뵽children����
//		rootblock.setComputestring ( sinfo);
//		//rootblock.getChildren().add(whilenode);
//		return true;
//
//	}
}
