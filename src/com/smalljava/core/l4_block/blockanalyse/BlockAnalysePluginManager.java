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
 * �����ʱ�����еĲ��������
 * @author liujunsong
 *
 */
public class BlockAnalysePluginManager {
	
	
	private Logger logger = LoggerFactory.getLogger(BlockAnalysePluginManager.class);
	
	private ArrayList<IAnalysePlugin> workers;

	/**
	 * ע�����п��Ըɻ�Ĺ���
	 */
	private void initWorkers() {
		if (workers == null) {
			workers = new ArrayList<IAnalysePlugin>();

			// ����ע�ͼ����
			workers.add(new SingleLineMemoPlugin());
			// ����ע�ͼ����
			workers.add(new MultiLineMemoPlugin());
			// IF������
			workers.add(new IfPlugin());
			// DoWhile������
			workers.add(new DowhilePlugin());
			// For������
			workers.add(new ForLoopPlugin());
			// While������
			workers.add(new WhiledoPlugin());
			// {������
			workers.add(new BracePlugin());
			// ;������
			workers.add(new SemicolonPlugin());
			// import��䴦����
			workers.add(new ImportPlugin());
			// return��䴦����
			workers.add(new ReturnPlugin());

		}
	}
	
	public BlockAnalysePluginManager() {
		initWorkers();
	}
	
	/**
	 * ����ʽ���ò���ķ�ʽ��ʵ�ִ������з֣�����һ��������
	 * @param rootblock
	 * @return
	 */
	public boolean process(BasicBlock rootblock) {
		for (IAnalysePlugin worker : workers) {
			if (worker.analyse(rootblock)) {
				logger.info("---------->worker �����Ժ�:"+rootblock.computestring);
				// donothing,����ѭ��
				// ���ʹ��continue;�������еĽڵ�˳��ִ��һ�Σ������Ƿ�ƥ�䣩��
				// ���ܻ�ִ�ж���ڵ�
				// ���ʹ��break,����һ��ѭ��ֻҪ��һ���ڵ�ƥ�䣬���˳�ѭ��
				// ��Ҫ���ǽ����ַ�����ȥ�ո�
				continue;
			} else {
				logger.info("��ERROR�������������ʧ��:" + rootblock.computestring);
				//�ӽڵ����ʧ�ܣ�����false;
				return false;
			}
		}
		//ѭ��������δ�������򷵻�true,����trueֻ�Ǵ���û�г����߼����󣬲���������һ���������ˡ�
		return true;
	}
	
	/**
	 * ���ַ�����ʼ�ͽ���λ�õ�\r\n ,\r,�ո񶼹��˵�
	 * 
	 * @param strinput
	 * @return
	 */
	public  String _trimReturnAndSpace(String strinput) {
		// String sout = "";
		// �Ȳ��ҵ�һ������\r\n \r �ո��λ��
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// ����ѭ��
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			// û���ҵ���Ч�ַ�
			return "";
		}

		// ��ʼ�Ӻ���ǰ���ҵ�һ����Ч�ַ�
		int ipos2 = -1;
		for (int i = strinput.length() - 1; i >= 0; i--) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// ����ѭ��
				continue;
			} else {
				ipos2 = i;
				break;
			}
		}

		// ����ipos��Ч������ipos2һ��Ҳ����Ч��
		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			logger.error("����ִ�г��ִ�����Ҫ������������.ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}
}
