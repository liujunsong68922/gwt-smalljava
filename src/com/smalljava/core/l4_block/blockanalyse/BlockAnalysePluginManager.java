package com.smalljava.core.l4_block.blockanalyse;

import java.util.ArrayList;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockanalyse.plugin.BracePlugin;
import com.smalljava.core.l4_block.blockanalyse.plugin.DowhilePlugin;
import com.smalljava.core.l4_block.blockanalyse.plugin.ForLoopPlugin;
import com.smalljava.core.l4_block.blockanalyse.plugin.IAnalysePlugin;
import com.smalljava.core.l4_block.blockanalyse.plugin.IfPlugin;
import com.smalljava.core.l4_block.blockanalyse.plugin.ImportPlugin;
import com.smalljava.core.l4_block.blockanalyse.plugin.MultiLineMemoPlugin;
import com.smalljava.core.l4_block.blockanalyse.plugin.ReturnPlugin;
import com.smalljava.core.l4_block.blockanalyse.plugin.SemicolonPlugin;
import com.smalljava.core.l4_block.blockanalyse.plugin.SingleLineMemoPlugin;
import com.smalljava.core.l4_block.blockanalyse.plugin.WhiledoPlugin;
import com.smalljava.core.l4_block.blockvo.BasicBlock;

/**
 * 块分析时的所有的插件管理器
 * @author liujunsong
 *
 */
public class BlockAnalysePluginManager {
	
	
	private Logger logger = LoggerFactory.getLogger(BlockAnalysePluginManager.class);
	
	private ArrayList<IAnalysePlugin> workers;

	/**
	 * 注册所有可以干活的工人
	 */
	private void initWorkers() {
		if (workers == null) {
			workers = new ArrayList<IAnalysePlugin>();

			// 单行注释检查器
			workers.add(new SingleLineMemoPlugin());
			// 多行注释检查器
			workers.add(new MultiLineMemoPlugin());
			// IF处理器
			workers.add(new IfPlugin());
			// DoWhile处理器
			workers.add(new DowhilePlugin());
			// For处理器
			workers.add(new ForLoopPlugin());
			// While处理器
			workers.add(new WhiledoPlugin());
			// {处理器
			workers.add(new BracePlugin());
			// ;处理器
			workers.add(new SemicolonPlugin());
			// import语句处理器
			workers.add(new ImportPlugin());
			// return语句处理器
			workers.add(new ReturnPlugin());

		}
	}
	
	public BlockAnalysePluginManager() {
		initWorkers();
	}
	
	/**
	 * 以链式调用插件的方式来实现代码块的切分，这是一个调度器
	 * @param rootblock
	 * @return
	 */
	public boolean process(BasicBlock rootblock) {
		for (IAnalysePlugin worker : workers) {
			if (worker.analyse(rootblock)) {
				logger.info("---------->worker 解析以后:"+rootblock.computestring);
				// donothing,继续循环
				// 如果使用continue;就是所有的节点顺序执行一次（无论是否匹配），
				// 可能会执行多个节点
				// 如果使用break,就是一次循环只要有一个节点匹配，就退出循环
				// 需要考虑进行字符串的去空格
				continue;
			} else {
				logger.info("【ERROR】解析插件调用失败:" + rootblock.computestring);
				//子节点调用失败，返回false;
				return false;
			}
		}
		//循环结束，未报错误，则返回true,返回true只是代表没有出现逻辑错误，不代表数据一定被处理了。
		return true;
	}
	
	/**
	 * 将字符串开始和结束位置的\r\n ,\r,空格都过滤掉
	 * 
	 * @param strinput
	 * @return
	 */
	public  String _trimReturnAndSpace(String strinput) {
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
