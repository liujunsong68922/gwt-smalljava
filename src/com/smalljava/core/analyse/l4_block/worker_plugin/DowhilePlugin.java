package com.smalljava.core.analyse.l4_block.worker_plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.commonvo.l4_block.SmallJavaBlockConst;
import com.smalljava.core.commonvo.l4_block.childblock.DOWHILEBlock;

public class DowhilePlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(DowhilePlugin.class);
	
	@Override
	public boolean analyse(BasicBlock rootblock) {
		if (rootblock.computestring.startsWith("do{") || rootblock.computestring.startsWith("do ")) {
			
			return this._analyse_do(rootblock);
		}else {
			return true;
		}
	}

	private boolean _analyse_do(BasicBlock rootblock) {
		
		int ipos1 =_findfirstStringForBlock(rootblock.computestring, "{");
		if (ipos1 == -1) {
			logger.error("do while not find { ");
			return false;
		}
		int ipos2 =_findfirstStringForBlock(rootblock.computestring, "}");
		if (ipos2 == -1) {
			logger.error("do while not find }");
			return false;
		}

		String dostring = rootblock.computestring.substring(ipos1 + 1, ipos2);
		DOWHILEBlock dowhilenode = new DOWHILEBlock("",rootblock);
				
		BasicBlock donode = new BasicBlock(SmallJavaBlockConst.DoNode,dostring,dowhilenode);
		dowhilenode.setDonode(donode);
		dowhilenode.getChildren().add(donode);
		
		String sinfo = rootblock.computestring.substring(ipos2 + 1);
		sinfo = _trimReturnAndSpace(sinfo);
		if (!sinfo.startsWith("while")) {
			logger.error("dowhile not start with while " + sinfo);
			return false;
		}

		sinfo = sinfo.substring(5);
		sinfo = _trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) != '(') {
			logger.error("dowhile not start with (" + sinfo);
			return false;
		}
		int ipos3 =_findfirstStringForBlock(sinfo, ")");
		if (ipos3 == -1) {
			logger.error("dowhile not find )" + sinfo);
			return false;
		} else {
			String strwhile = sinfo.substring(0, ipos3+1);
			BasicBlock whilenode = new BasicBlock(SmallJavaBlockConst.WhileNode,strwhile,dowhilenode);
			dowhilenode.setWhilenode(whilenode);
			dowhilenode.getChildren().add(whilenode);
			//dowhilenode.setWhilestring(strwhile);
		}
		
		rootblock.computestring = sinfo.substring(ipos3 + 1);
		rootblock.getChildren().add(dowhilenode);
		return true;
	}

}
