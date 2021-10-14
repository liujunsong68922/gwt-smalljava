package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.WHILEBlock;

/**
 * while()do{} ������
 * 
 * @author liujunsong
 *
 */
public class WhiledoPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(WhiledoPlugin.class);
	
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
			logger.error("while����������û���ҵ�(");
			return false;
		}
		int ipos2 = _findfirstStringForBlock(rootblock.computestring, ")");
		if (ipos2 == -1) {
			logger.error("while����������û���ҵ�)");
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
				logger.error("����}ʧ��:" + sinfo);
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
				logger.error("����;ʧ��" + sinfo);
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
}
