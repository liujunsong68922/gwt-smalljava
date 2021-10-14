package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.IFBlock;

/**
 * do{}while() ������
 * @author liujunsong
 *
 */
public class IfPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(IfPlugin.class);
	
	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.6 if-else�ж�
		if (rootblock.computestring.startsWith("if")) {
			return this._analyse_if(rootblock);
		}else {
			return true;
		}
	}

	private boolean _analyse_if(BasicBlock rootblock) {
		
		int ipos1 = _findfirstStringForBlock(rootblock.computestring, "(");
		if (ipos1 == -1) {
			logger.error("if����������û���ҵ�(");
			return false;
		}
		int ipos2 = _findfirstStringForBlock(rootblock.computestring, ")");
		if (ipos2 == -1) {
			logger.error("if����������û���ҵ�)");
			return false;
		}
		
		String sif = rootblock.computestring.substring(ipos1, ipos2+1);
		
		IFBlock ifnode = new IFBlock(sif,rootblock);
		BasicBlock ifconditionnode = new BasicBlock(SmallJavaBlockConst.IfConditionBlock,sif,ifnode);
		ifnode.setIfConditionblock(ifconditionnode);
		ifnode.getChildren().add(ifconditionnode);
		
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
			BasicBlock ifnode1 = new BasicBlock(SmallJavaBlockConst.IfTrueBlock,sifblock,ifnode);
			ifnode.setIftrueblock(ifnode1);
			ifnode.getChildren().add(ifnode1);
			sinfo = sinfo.substring(ipos3 + 1);
		} else {
			int ipos4 = _findfirstStringForBlock(sinfo, ";");
			if (ipos4 == -1) {
				logger.error("����;ʧ��" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos4);
			BasicBlock ifnode1 = new BasicBlock(SmallJavaBlockConst.IfTrueBlock,sifblock,ifnode);

			ifnode.setIftrueblock(ifnode1);
			ifnode.getChildren().add(ifnode1);
			sinfo = sinfo.substring(ipos4 + 1);
		}

		rootblock.computestring = sinfo;

		// �������ж��ǲ���else�ؼ���
		sinfo = _trimReturnAndSpace(sinfo);
		if (sinfo.length() > 3) {
			if (sinfo.startsWith("else")) {
				// ����else�Ӿ�,��ʱ�����ǲ���{}�����
				// Ҫ��else�Ӿ����{}�����зָ�
				String sinfo2 = sinfo.substring(4);
				int ipos5 = _findfirstStringForBlock(sinfo2, "{");
				int ipos6 = _findfirstStringForBlock(sinfo2, "}");

				if (ipos5 == -1) {
					logger.error("����{ʧ��:" + sinfo2);
					return false;
				}
				if (ipos6 == -1) {
					logger.error("����}ʧ��:" + sinfo2);
					return false;
				}

				String selseblock = sinfo2.substring(ipos5 , ipos6+1);
				BasicBlock elsenode1 = new BasicBlock(SmallJavaBlockConst.IfElseBlock,selseblock,ifnode);
				// else���ʹ�õ���IF���ı�����
				//elsenode1.setVarmaps(ifnode.getVarmaps());
				ifnode.setIfelseblock(elsenode1);
				ifnode.getChildren().add(elsenode1);
				rootblock.computestring = sinfo2.substring(ipos6 + 1);

			} else {
				// û��else�Ӿ�
				ifnode.setIfelseblock(null);
			}
		}

		// ��ifnode���뵽children����
		rootblock.getChildren().add(ifnode);
		return true;

	}
}
