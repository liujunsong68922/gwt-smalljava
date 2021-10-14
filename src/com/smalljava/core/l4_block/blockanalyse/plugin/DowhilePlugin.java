package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.DOWHILEBlock;

/**
 * do{}while() 解析器
 * @author liujunsong
 *
 */
public class DowhilePlugin extends DefaultAbstractAnalysePlugin implements IAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(DowhilePlugin.class);
	
	@Override
	public boolean analyse(BasicBlock rootblock) {
		// Step4.4 do-while循环判断
		if (rootblock.computestring.startsWith("do{") || rootblock.computestring.startsWith("do ")) {
			
			return this._analyse_do(rootblock);
		}else {
			return true;
		}
	}

	private boolean _analyse_do(BasicBlock rootblock) {
		
		int ipos1 =_findfirstStringForBlock(rootblock.computestring, "{");
		if (ipos1 == -1) {
			logger.error("do语句分析错误，没有找到{");
			return false;
		}
		int ipos2 =_findfirstStringForBlock(rootblock.computestring, "}");
		if (ipos2 == -1) {
			logger.error("do语句分析错误，没有找到}");
			return false;
		}

		String dostring = rootblock.computestring.substring(ipos1 + 1, ipos2);
		DOWHILEBlock dowhilenode = new DOWHILEBlock("",rootblock);
				
		//do节点
		BasicBlock donode = new BasicBlock(SmallJavaBlockConst.DoNode,dostring,dowhilenode);
		dowhilenode.setDonode(donode);
		dowhilenode.getChildren().add(donode);
		
		// sinfo是切除do()的部分
		String sinfo = rootblock.computestring.substring(ipos2 + 1);
		// 去掉空格和回车符号
		sinfo = _trimReturnAndSpace(sinfo);
		// 判断sinfo是否以 while开头
		if (!sinfo.startsWith("while")) {
			logger.error("dowhile解析失败，没有找到while关键词。" + sinfo);
			return false;
		}

		// 切除while关键词
		sinfo = sinfo.substring(5);
		sinfo = _trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) != '(') {
			logger.error("dowhile解析失败，while没有找到(" + sinfo);
			return false;
		}
		int ipos3 =_findfirstStringForBlock(sinfo, ")");
		if (ipos3 == -1) {
			logger.error("dowhile解析失败，while没有找到)" + sinfo);
			return false;
		} else {
			String strwhile = sinfo.substring(0, ipos3+1);
			//生成一个新的子节点
			BasicBlock whilenode = new BasicBlock(SmallJavaBlockConst.WhileNode,strwhile,dowhilenode);
			dowhilenode.setWhilenode(whilenode);
			dowhilenode.getChildren().add(whilenode);
			//dowhilenode.setWhilestring(strwhile);
		}
		
		rootblock.computestring = sinfo.substring(ipos3 + 1);
		// 将dowhilenode加入到children里面
		rootblock.getChildren().add(dowhilenode);
		return true;
	}




}
