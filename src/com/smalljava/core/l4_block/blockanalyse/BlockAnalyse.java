package com.smalljava.core.l4_block.blockanalyse;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.MethodBlock;

/**
 * 
 * @author liujunsong
 *
 */
public class BlockAnalyse {
	private Logger logger = LoggerFactory.getLogger(BlockAnalyse.class);
	
	private BlockAnalysePluginManager pmanager = new BlockAnalysePluginManager();

	/**
	 * 构造函数
	 */
	public BlockAnalyse() {

	}

	/**
	 * 把字符串rootblock.computestring分解成AbastractBlock,里面用Children来构造成树状结构
	 * 
	 * @return ture 分解成功 false 分解失败
	 */
	public boolean analyse(BasicBlock rootblock) {
		// step1:检查输入参数
		if (rootblock == null) {
			logger.error("Argument Error,rootblock is null.");
			return false;
		}
		if (rootblock.getBlockContent() == null) {
			logger.error("Argument Error,rootblock.computestring is null.");
			return false;
		}
		// 如果是MethodBlock，停止分析
		if (rootblock instanceof MethodBlock) {
			logger.error("Method block stop analyse.");
			return true;
		}
		//判断rootblock的类型
		if(rootblock.getBlocktype()!=null
				&& rootblock.getBlocktype().equals(SmallJavaBlockConst.ImportBlock)) {
			logger.error("import 语句不再继续处理。");
			return true;
		}
		if(rootblock.getBlocktype()!=null
				&& rootblock.getBlocktype().equals(SmallJavaBlockConst.SingleLineMemo)) {
			logger.error("singlelinememo 语句不再继续处理。");
			return true;
		}
		if(rootblock.getBlocktype()!=null
				&& rootblock.getBlocktype().equals(SmallJavaBlockConst.MultiLineMemo)) {
			logger.error("multilinememo 语句不再继续处理。");
			return true;
		}
		
		// 初始化计算参数
		rootblock.computestring = rootblock.getBlockContent();
		logger.info("---->analyse begin." + rootblock.computestring);
		// step2.写入rootblock,仅做备份用

		rootblock.computestring = this._trimReturnAndSpace(rootblock.computestring);
		rootblock.setBlockContent(rootblock.computestring);

		// step3.开始处理非循环部分
		if (rootblock.computestring.equals("") && rootblock.getChildren().size()==0) {
			rootblock.setBlocktype(SmallJavaBlockConst.EmptyBlock);
			return true;
		}

		// 循环处理子节点时，需要跳过IfBlock,直接处理其下级
		if (!rootblock.getBlocktype().equals(SmallJavaBlockConst.Ifblock)) {
			// Step4.开始进行循环判断处理,根据首字母来进行判断走下一步的分支，
			// 每次执行完毕后堆rootblock.computestring进行切分
			while (rootblock.computestring.length() > 0) {
				// Step4.0，进行trim处理
				rootblock.computestring = this._trimReturnAndSpace(rootblock.computestring);

				// 循环调用插件进行处理，如果插件返回false,说明插件出错了，停止执行
				String s1 = rootblock.computestring;
				if (!pmanager.process(rootblock)) {
					logger.info("【ERROR】解析插件调用失败:" + rootblock.computestring);
					// 调用失败，返回false
					return false;
				}

				String s2 = rootblock.computestring;
				if (s1.length() == s2.length()) {
					// 字符串没有得到有效处理的情况下，返回false
					// 各个插件之中逻辑上必须有匹配的才对
					logger.info("【ERROR】所有的插件执行以后，字符串没有得到任何处理." + rootblock.computestring);
					return false;
				}
			}
		}

		// Step5.本级节点切分完毕，开始处理下级节点，并判断其返回值
		for (BasicBlock child : rootblock.getChildren()) {
			//此处需要根据child的类型来判断是否需要继续
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
				logger.info("【ERROR】递归调用子节点分析时发生错误.");
				return false;
			}
		}
		// 程序成功运行完毕
		return true;
	}

	/**
	 * 将字符串开始和结束位置的\r\n ,\r,空格都过滤掉
	 * 
	 * @param strinput
	 * @return
	 */
	private String _trimReturnAndSpace(String strinput) {
		// String sout = "";
		// 先查找第一个不是\r\n \r 空格的位置
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// 继续循环
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			// 没有找到有效字符
			return "";
		}

		// 开始从后往前查找第一个有效字符
		int ipos2 = -1;
		for (int i = strinput.length() - 1; i >= 0; i--) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// 继续循环
				continue;
			} else {
				ipos2 = i;
				break;
			}
		}

		// 由于ipos有效，所以ipos2一定也是有效的
		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			logger.error("程序执行出现错误，需要查找问题所在.ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}

}
