package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.DOWHILEBlock;

/**
 * do{}while() ������
 * @author liujunsong
 *
 */
public class DowhilePlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(DowhilePlugin.class);
	
	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.4 do-whileѭ���ж�
		if (rootblock.computestring.startsWith("do{") || rootblock.computestring.startsWith("do ")) {
			
			return this._analyse_do(rootblock);
		}else {
			return true;
		}
	}

	private boolean _analyse_do(BasicBlock rootblock) {
		
		int ipos1 =_findfirstStringForBlock(rootblock.computestring, "{");
		if (ipos1 == -1) {
			logger.error("do����������û���ҵ�{");
			return false;
		}
		int ipos2 =_findfirstStringForBlock(rootblock.computestring, "}");
		if (ipos2 == -1) {
			logger.error("do����������û���ҵ�}");
			return false;
		}

		String dostring = rootblock.computestring.substring(ipos1 + 1, ipos2);
		DOWHILEBlock dowhilenode = new DOWHILEBlock("",rootblock);
				
		//do�ڵ�
		BasicBlock donode = new BasicBlock(SmallJavaBlockConst.DoNode,dostring,dowhilenode);
		dowhilenode.setDonode(donode);
		dowhilenode.getChildren().add(donode);
		
		// sinfo���г�do()�Ĳ���
		String sinfo = rootblock.computestring.substring(ipos2 + 1);
		// ȥ���ո�ͻس�����
		sinfo = _trimReturnAndSpace(sinfo);
		// �ж�sinfo�Ƿ��� while��ͷ
		if (!sinfo.startsWith("while")) {
			logger.error("dowhile����ʧ�ܣ�û���ҵ�while�ؼ��ʡ�" + sinfo);
			return false;
		}

		// �г�while�ؼ���
		sinfo = sinfo.substring(5);
		sinfo = _trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) != '(') {
			logger.error("dowhile����ʧ�ܣ�whileû���ҵ�(" + sinfo);
			return false;
		}
		int ipos3 =_findfirstStringForBlock(sinfo, ")");
		if (ipos3 == -1) {
			logger.error("dowhile����ʧ�ܣ�whileû���ҵ�)" + sinfo);
			return false;
		} else {
			String strwhile = sinfo.substring(0, ipos3+1);
			//����һ���µ��ӽڵ�
			BasicBlock whilenode = new BasicBlock(SmallJavaBlockConst.WhileNode,strwhile,dowhilenode);
			dowhilenode.setWhilenode(whilenode);
			dowhilenode.getChildren().add(whilenode);
			//dowhilenode.setWhilestring(strwhile);
		}
		
		rootblock.computestring = sinfo.substring(ipos3 + 1);
		// ��dowhilenode���뵽children����
		rootblock.getChildren().add(dowhilenode);
		return true;
	}




}
