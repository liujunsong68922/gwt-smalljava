package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.FORBlock;

/**
 * for(a;b;c){d} 解析器
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
			logger.error("for语句分析错误，没有找到(");
			return false;
		}
		int ipos2 = _findfirstStringForBlock(rootblock.computestring, ")");
		if (ipos2 == -1) {
			logger.error("for语句分析错误，没有找到)");
			return false;
		}

		//挂载fornode的变量表的父变量表到当前block的变量表
		//fornode.setParentVarmaps(this.varmaps);
		//fornode.setChildren(new ArrayList<AbstractBlock>());
		// 设置IF节点的判断表达式条件
		String forstring = rootblock.computestring.substring(ipos1 + 1, ipos2);
		FORBlock fornode = new FORBlock("",rootblock);

		// 将for()括号里面的部分切分成三个node，然后设置到fornode里面去
		// 用【;】 进行分隔
		String forstr[] = forstring.split(";");
		if (forstr.length != 3) {
			logger.error("for语句解析失败，条件表达式:" + forstring);
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

		// sinfo是切除if + ifcondition的部分
		String sinfo = rootblock.computestring.substring(ipos2 + 1);
		// 去掉空格和回车符号
		sinfo = _trimReturnAndSpace(sinfo);
		if (sinfo.charAt(0) == '{') {
			// 如果if后面跟踪的是{，则查找对应的}字符串
			int ipos3 = _findfirstStringForBlock(sinfo, "}");
			if (ipos3 == -1) {
				logger.error("查找}失败:" + sinfo);
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
				logger.error("查找;失败" + sinfo);
				return false;
			}
			String sifblock = sinfo.substring(0, ipos4 + 1);
			BasicBlock loopnode1 = new BasicBlock(SmallJavaBlockConst.ForLoopBlock ,sifblock,fornode);
			fornode.setForloopBlock(loopnode1);
			fornode.getChildren().add(loopnode1);
			sinfo = sinfo.substring(ipos4 + 1);
		}

		rootblock.computestring = sinfo;
		// 将fornode加入到children里面
		rootblock.getChildren().add(fornode);
		return true;

	}
}
