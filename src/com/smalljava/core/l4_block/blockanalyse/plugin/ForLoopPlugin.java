package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.FORBlock;

/**
 * for(a;b;c){d} ������
 * @author liujunsong
 *
 */
public class ForLoopPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(ForLoopPlugin.class);
	
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
			logger.error("for����������û���ҵ�(");
			return false;
		}
		int ipos2 = _findfirstStringForBlock(rootblock.computestring, ")");
		if (ipos2 == -1) {
			logger.error("for����������û���ҵ�)");
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
			logger.error("for������ʧ�ܣ��������ʽ:" + forstring);
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
				logger.error("����}ʧ��:" + sinfo);
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
				logger.error("����;ʧ��" + sinfo);
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
}
