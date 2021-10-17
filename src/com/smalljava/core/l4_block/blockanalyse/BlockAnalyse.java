package com.smalljava.core.l4_block.blockanalyse;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.MethodBlock;


public class BlockAnalyse {
	private Logger logger = LoggerFactory.getLogger(BlockAnalyse.class);
	
	private BlockAnalysePluginManager pmanager = new BlockAnalysePluginManager();

	/**
	 * ���캯��
	 */
	public BlockAnalyse() {

	}

	public boolean analyse(BasicBlock rootblock) {
		// step1
		if (rootblock == null) {
			logger.error("Argument Error,rootblock is null.");
			return false;
		}
		if (rootblock.getBlockContent() == null) {
			logger.error("Argument Error,rootblock.computestring is null.");
			return false;
		}
		//
		if (rootblock instanceof MethodBlock) {
			logger.error("Method block stop analyse.");
			return true;
		}
		//
		if(rootblock.getBlocktype()!=null
				&& rootblock.getBlocktype().equals(SmallJavaBlockConst.ImportBlock)) {
			logger.error("import ");
			return true;
		}
		if(rootblock.getBlocktype()!=null
				&& rootblock.getBlocktype().equals(SmallJavaBlockConst.SingleLineMemo)) {
			logger.error("singlelinememo ");
			return true;
		}
		if(rootblock.getBlocktype()!=null
				&& rootblock.getBlocktype().equals(SmallJavaBlockConst.MultiLineMemo)) {
			logger.error("multilinememo ");
			return true;
		}
		
		// ��ʼ���������
		rootblock.computestring = rootblock.getBlockContent();
		logger.info("---->analyse begin." + rootblock.computestring);
		// step2.

		rootblock.computestring = this._trimReturnAndSpace(rootblock.computestring);
		rootblock.setBlockContent(rootblock.computestring);

		// step3.
		if (rootblock.computestring.equals("") && rootblock.getChildren().size()==0) {
			rootblock.setBlocktype(SmallJavaBlockConst.EmptyBlock);
			return true;
		}

		// 
		if (!rootblock.getBlocktype().equals(SmallJavaBlockConst.Ifblock)) {
			// Step4.
			// 
			while (rootblock.computestring.length() > 0) {
				// Step4.0
				rootblock.computestring = this._trimReturnAndSpace(rootblock.computestring);

				//
				String s1 = rootblock.computestring;
				if (!pmanager.process(rootblock)) {
					logger.info("[ERROR]:" + rootblock.computestring);
					//
					return false;
				}

				String s2 = rootblock.computestring;
				if (s1.length() == s2.length()) {
					// 
					// 
					logger.info("[ERROR]." + rootblock.computestring);
					return false;
				}
			}
		}

		// Step5.�����ڵ��з���ϣ���ʼ�����¼��ڵ㣬���ж��䷵��ֵ
		for (BasicBlock child : rootblock.getChildren()) {
			//
			if(child.getBlocktype().equals(SmallJavaBlockConst.SingleLineMemo)) {
				continue;
			}
			if(child.getBlocktype().equals(SmallJavaBlockConst.MultiLineMemo)) {
				continue;
			}
			if(child.getBlocktype().equals(SmallJavaBlockConst.ImportBlock)) {
				continue;
			}
			
			
			if (!analyse(child)) {
				logger.info("error analyse child.");
				return false;
			}
		}
		//
		return true;
	}


	private String _trimReturnAndSpace(String strinput) {
		// String sout = "";
		// 
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				//
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			// 
			return "";
		}

		// 
		int ipos2 = -1;
		for (int i = strinput.length() - 1; i >= 0; i--) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// 
				continue;
			} else {
				ipos2 = i;
				break;
			}
		}

		// 
		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			logger.error("[ERROR].ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}

}
