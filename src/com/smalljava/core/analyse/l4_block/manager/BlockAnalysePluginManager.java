package com.smalljava.core.analyse.l4_block.manager;

import java.util.ArrayList;

import com.smalljava.core.analyse.l4_block.worker_plugin.BracePlugin;
import com.smalljava.core.analyse.l4_block.worker_plugin.DowhilePlugin;
import com.smalljava.core.analyse.l4_block.worker_plugin.ForLoopPlugin;
import com.smalljava.core.analyse.l4_block.worker_plugin.IAnalysePlugin;
import com.smalljava.core.analyse.l4_block.worker_plugin.IfPlugin;
import com.smalljava.core.analyse.l4_block.worker_plugin.ImportPlugin;
import com.smalljava.core.analyse.l4_block.worker_plugin.MultiLineMemoPlugin;
import com.smalljava.core.analyse.l4_block.worker_plugin.ReturnPlugin;
import com.smalljava.core.analyse.l4_block.worker_plugin.SemicolonPlugin;
import com.smalljava.core.analyse.l4_block.worker_plugin.SingleLineMemoPlugin;
import com.smalljava.core.analyse.l4_block.worker_plugin.WhiledoPlugin;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l4_block.BasicBlock;


public class BlockAnalysePluginManager {
	private Logger logger = LoggerFactory.getLogger(BlockAnalysePluginManager.class);
	
	private ArrayList<IAnalysePlugin> workers;

	private void initWorkers() {
		if (workers == null) {
			workers = new ArrayList<IAnalysePlugin>();

			workers.add(new SingleLineMemoPlugin());
			workers.add(new MultiLineMemoPlugin());
			workers.add(new IfPlugin());
			workers.add(new DowhilePlugin());
			workers.add(new ForLoopPlugin());
			workers.add(new WhiledoPlugin());
			workers.add(new BracePlugin());
			workers.add(new SemicolonPlugin());
			workers.add(new ImportPlugin());
			workers.add(new ReturnPlugin());

		}
	}
	
	public BlockAnalysePluginManager() {
		initWorkers();
	}
	
	public boolean process(BasicBlock rootblock) {
		for (IAnalysePlugin worker : workers) {
			if (worker.analyse(rootblock)) {
				logger.info("---------->worker ok:"+rootblock.computestring);
				// do nothing
				continue;
			} else {
				logger.info("[error] worker error:" + rootblock.computestring);
				return false;
			}
		}
		//
		return true;
	}
	

	public  String _trimReturnAndSpace(String strinput) {
		// String sout = "";
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			return "";
		}

		int ipos2 = -1;
		for (int i = strinput.length() - 1; i >= 0; i--) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				continue;
			} else {
				ipos2 = i;
				break;
			}
		}

		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			logger.error("[error].ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}
}
