package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse;

import java.util.ArrayList;

import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.BracePlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.DowhilePlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.ForLoopPlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.IAnalysePlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.IfPlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.ImportPlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.MethodPlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.MultiLineMemoPlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.ReturnPlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.SemicolonPlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.SingleLineMemoPlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin.WhiledoPlugin;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;

/**
 * �����ʱ�����еĲ��������
 * @author liujunsong
 *
 */
public class BlockAnalysePluginManager {
	//private Logger logger = LoggerFactory.getLogger(BlockAnalysePluginManager.class);
	
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
			// method��䴦����
			workers.add(new MethodPlugin());
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
				//logger.info("---------->worker �����Ժ�:"+rootblock.computestring);
				// donothing,����ѭ��
				// ���ʹ��continue;�������еĽڵ�˳��ִ��һ�Σ������Ƿ�ƥ�䣩��
				// ���ܻ�ִ�ж���ڵ�
				// ���ʹ��break,����һ��ѭ��ֻҪ��һ���ڵ�ƥ�䣬���˳�ѭ��
				// ��Ҫ���ǽ����ַ�����ȥ�ո�
				continue;
			} else {
				//logger.info("��ERROR�������������ʧ��:" + rootblock.computestring);
				//�ӽڵ����ʧ�ܣ�����false;
				return false;
			}
		}
		//ѭ��������δ�������򷵻�true,����trueֻ�Ǵ���û�г����߼����󣬲���������һ���������ˡ�
		return true;
	}

	
	/**
	 * ����ʽ���ò���ķ�ʽ��ʵ�ִ������з֣�����һ��������
	 * @param rootblock
	 * @return
	 */
//	public boolean process(ElementWrapper rootblock) {
//		for (IAnalysePlugin worker : workers) {
//			if (worker.analyse(rootblock)) {
//				// donothing,����ѭ��
//				// ���ʹ��continue;�������еĽڵ�˳��ִ��һ�Σ������Ƿ�ƥ�䣩��
//				// ���ܻ�ִ�ж���ڵ�
//				// ���ʹ��break,����һ��ѭ��ֻҪ��һ���ڵ�ƥ�䣬���˳�ѭ��
//				continue;
//			} else {
//				//logger.info("��ERROR�������������ʧ��:" + rootblock.computestring);
//				//�ӽڵ����ʧ�ܣ�����false;
//				return false;
//			}
//		}
//		//ѭ��������δ�������򷵻�true,����trueֻ�Ǵ���û�г����߼����󣬲���������һ���������ˡ�
//		return true;
//	}
	
	/**
	 * ����ʽ���ò���ķ�ʽ��ʵ�ִ������з֣�����һ��������
	 * @param rootblock
	 * @return
	 */
//	public boolean process(JSONWrapper rootblock) {
//		for (IAnalysePlugin worker : workers) {
//			if (worker.analyse(rootblock)) {
//				// donothing,����ѭ��
//				// ���ʹ��continue;�������еĽڵ�˳��ִ��һ�Σ������Ƿ�ƥ�䣩��
//				// ���ܻ�ִ�ж���ڵ�
//				// ���ʹ��break,����һ��ѭ��ֻҪ��һ���ڵ�ƥ�䣬���˳�ѭ��
//				rootblock.setComputestring(this._trimReturnAndSpace( rootblock.getComputestring()));
//				continue;
//			} else {
//				//logger.info("��ERROR�������������ʧ��:" + rootblock.computestring);
//				//�ӽڵ����ʧ�ܣ�����false;
//				return false;
//			}
//		}
//		//ѭ��������δ�������򷵻�true,����trueֻ�Ǵ���û�г����߼����󣬲���������һ���������ˡ�
//		logger.info("1");
//		return true;
//	}	
//	

	
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
			System.out.println("����ִ�г��ִ�����Ҫ������������.ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}
}
