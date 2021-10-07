package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.FORBlock;

/**
 * for(a;b;c){d} ������
 * @author liujunsong
 *
 */
public class ForLoopPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		if (rootblock.computestring.startsWith("for(") || rootblock.computestring.startsWith("for ")) {
			return  this._analyse_for(rootblock);
		}else {
			return true;
		}

	}

	private boolean _analyse_for(BasicBlock rootblock) {
		int ipos1 = _findfirstStringForBlock(rootblock.computestring, "(");
		if (ipos1 == -1) {
			System.out.println("for����������û���ҵ�(");
			return false;
		}
		int ipos2 = _findfirstStringForBlock(rootblock.computestring, ")");
		if (ipos2 == -1) {
			System.out.println("for����������û���ҵ�)");
			return false;
		}

		//����fornode�ı�����ĸ���������ǰblock�ı�����
		//fornode.setParentVarmaps(this.varmaps);
		//fornode.setChildren(new ArrayList<AbstractBlock>());
		// ����IF�ڵ���жϱ��ʽ����
		String forstring = rootblock.computestring.substring(ipos1 + 1, ipos2);
		FORBlock fornode = new FORBlock("",rootblock);

		// ��for()��������Ĳ����зֳ�����node��Ȼ�����õ�fornode����ȥ
		// �á�;�� ���зָ�
		String forstr[] = forstring.split(";");
		if (forstr.length != 3) {
			System.out.println("for������ʧ�ܣ��������ʽ:" + forstring);
			return false;
		}
		BasicBlock beginNode = new BasicBlock(SmallJavaBlockConst.ForBeginNode,forstr[0],fornode);
		BasicBlock forconditionNode = new BasicBlock(SmallJavaBlockConst.ForConditionNode,forstr[1],fornode);
		BasicBlock loopNode = new BasicBlock(SmallJavaBlockConst.ForLoopNode,forstr[2],fornode);
		
		fornode.setBeginNode(beginNode);
		fornode.setForconditionNode(forconditionNode);
		fornode.setLoopNode(loopNode);

		fornode.getChildren().add(beginNode);
		fornode.getChildren().add(forconditionNode);
		fornode.getChildren().add(loopNode);

		// sinfo���г�if + ifcondition�Ĳ���
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
			String sifblock = sinfo.substring(0, ipos3 + 1);
			BasicBlock loopnode1 = new BasicBlock(SmallJavaBlockConst.ForLoopBlock,sifblock,fornode);
		
			fornode.setForloopBlock(loopnode1);
			fornode.getChildren().add(loopnode1);
			sinfo = sinfo.substring(ipos3 + 1);
		} else {
			int ipos4 = _findfirstStringForBlock(sinfo, ";");
			if (ipos4 == -1) {
				System.out.println("����;ʧ��" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos4 + 1);
			BasicBlock loopnode1 = new BasicBlock(SmallJavaBlockConst.ForLoopBlock ,sifblock,fornode);
			fornode.setForloopBlock(loopnode1);
			fornode.getChildren().add(loopnode1);
			sinfo = sinfo.substring(ipos4 + 1);
		}

		rootblock.computestring = sinfo;
		// ��fornode���뵽children����
		rootblock.getChildren().add(fornode);
		return true;

	}


//	@Override
//	public boolean analyse(ElementWrapper rootelement) {
//		if (rootelement.getComputestring().startsWith("for(") 
//				|| rootelement.getComputestring().startsWith("for ")) {
//			return  this._analyse_for(rootelement);
//		}else {
//			return true;
//		}
//	}

//	@Override
//	public boolean analyse(JSONWrapper rootelement) {
//		// TODO Auto-generated method stub
//		return true;
//	}	
	
//	@SuppressWarnings("deprecation")
//	private boolean _analyse_for(ElementWrapper rootblock) {
//		int ipos1 = _findfirstStringForBlock(rootblock.getComputestring(), "(");
//		if (ipos1 == -1) {
//			System.out.println("for����������û���ҵ�(");
//			return false;
//		}
//		int ipos2 = _findfirstStringForBlock(rootblock.getComputestring(), ")");
//		if (ipos2 == -1) {
//			System.out.println("for����������û���ҵ�)");
//			return false;
//		}
//
//		//����fornode�ı�����ĸ���������ǰblock�ı�����
//		//fornode.setParentVarmaps(this.varmaps);
//		//fornode.setChildren(new ArrayList<AbstractBlock>());
//		// ����IF�ڵ���жϱ��ʽ����
//		String forstring = rootblock.getComputestring().substring(ipos1 + 1, ipos2);
//		//FORBlock fornode = new FORBlock("");
//		Element fornode = rootblock.getElement().addElement(SmallJavaBlockConst.ForBlock);
//		fornode.setAttributeValue("BlockContent", "");
//		// ��for()��������Ĳ����зֳ�����node��Ȼ�����õ�fornode����ȥ
//		// �á�;�� ���зָ�
//		String forstr[] = forstring.split(";");
//		if (forstr.length != 3) {
//			System.out.println("for������ʧ�ܣ��������ʽ:" + forstring);
//			return false;
//		}
//		Element beginNode = fornode.addElement(SmallJavaBlockConst.ForBeginNode);
//		Element forconditionNode = fornode.addElement(SmallJavaBlockConst.ForConditionNode);
//		Element loopNode = fornode.addElement(SmallJavaBlockConst.ForLoopNode);
//		beginNode.setAttributeValue("BlockContent", forstr[0]);
//		forconditionNode.setAttributeValue("BlockContent", forstr[1]);
//		loopNode.setAttributeValue("BlockContent", forstr[2]);
//
//		// sinfo���г�if + ifcondition�Ĳ���
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
//			String sifblock = sinfo.substring(0, ipos3 + 1);
//			Element loopnode1 = fornode.addElement(SmallJavaBlockConst.ForLoopBlock);
//			loopnode1.setAttributeValue("BlockContent", sifblock);
//			sinfo = sinfo.substring(ipos3 + 1);
//		} else {
//			int ipos4 = _findfirstStringForBlock(sinfo, ";");
//			if (ipos4 == -1) {
//				System.out.println("����;ʧ��" + sinfo);
//				return false;
//			}
//			String sifblock = sinfo.substring(0, ipos4 + 1);
//			Element loopnode1 = fornode.addElement(SmallJavaBlockConst.ForLoopBlock);
//			loopnode1.setAttributeValue("BlockContent", sifblock);
//			sinfo = sinfo.substring(ipos4 + 1);
//		}
//
//		rootblock.setComputestring ( sinfo);
//		// ��fornode���뵽children����
//		//rootblock.getChildren().add(fornode);
//		return true;
//
//	}
}
