package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;

public class SemicolonPlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(SemicolonPlugin.class);

	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.8 ���ַ���ͷ
		if (rootblock.computestring.length() > 0) {
			char char1 = rootblock.computestring.charAt(0);
			// ��Ч�Ĵ��뿪ʼ��������д��ĸ��Сд��ĸ��������
			if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z') || (char1 >= '0' && char1 <= '9')
					|| (char1 == '(')) {
				// �����ض��Ĺؼ��ʣ�for,if,do,while
				if (!rootblock.computestring.startsWith("for ") && !rootblock.computestring.startsWith("for(")
						&& !rootblock.computestring.startsWith("if ") && !rootblock.computestring.startsWith("if(")
						&& !rootblock.computestring.startsWith("do ") && !rootblock.computestring.startsWith("do{")
						&& !rootblock.computestring.startsWith("while ")
						&& !rootblock.computestring.startsWith("while(")
						&& !rootblock.computestring.startsWith("import")
						&& !rootblock.computestring.startsWith("return ")
						&& !rootblock.computestring.startsWith("return(")) {
					// ������һ��;
					boolean b1 = this._analyse_semicolon(rootblock);
					return b1;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @param rootblock
	 * @return
	 */
	private boolean _analyse_semicolon(BasicBlock rootblock) {
		logger.info("enter _analyse_semicolon");
		rootblock.computestring = this._trimReturnAndSpace(rootblock.computestring);
		// �����public,private��ͷ�����˳�
		if (rootblock.computestring.startsWith("public") || rootblock.computestring.startsWith("private")) {
			return true;
		}

		int ipos = this._findfirstStringForBlock(rootblock.computestring, ";");
		String leftstring = "";
		if (ipos < 0) {
			logger.info("���ҡ�;��ʧ��,��δ����Ѿ���һ�����ʽ�ˡ�");
			leftstring = rootblock.computestring;
			rootblock.computestring = "";
			return true;
		} else {
			// ���յ�һ��[;]��λ�ý����з�
			leftstring = rootblock.computestring.substring(0, ipos);
			rootblock.computestring = rootblock.computestring.substring(ipos + 1);

			BasicBlock block1 = new BasicBlock(SmallJavaBlockConst.Expression, leftstring, rootblock);
			rootblock.getChildren().add(block1);
		}

		return true;
	}
}
