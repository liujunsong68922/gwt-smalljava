package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse;

import java.util.List;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.MethodBlock;

/**
 * 
 * @author liujunsong
 *
 */
public class BlockAnalyse {
	//private Logger logger = LoggerFactory.getLogger(BlockAnalyse.class);
	
	private BlockAnalysePluginManager pmanager = new BlockAnalysePluginManager();

	/**
	 * ���캯��
	 */
	public BlockAnalyse() {

	}

	/**
	 * ���ַ���rootblock.computestring�ֽ��AbastractBlock,������Children���������״�ṹ
	 * 
	 * @return ture �ֽ�ɹ� false �ֽ�ʧ��
	 */
	public boolean analyse(BasicBlock rootblock) {
		// step1:����������
		if (rootblock == null) {
			//logger.error("Argument Error,rootblock is null.");
			return false;
		}
		if (rootblock.getBlockContent() == null) {
			//logger.error("Argument Error,rootblock.computestring is null.");
			return false;
		}
		// �����MethodBlock��ֹͣ����
		if (rootblock instanceof MethodBlock) {
			//logger.error("Method block stop analyse.");
			return true;
		}
		//�ж�rootblock������
		if(rootblock.getBlocktype()!=null
				&& rootblock.getBlocktype().equals(SmallJavaBlockConst.ImportBlock)) {
			//logger.error("import ��䲻�ټ�������");
			return true;
		}
		if(rootblock.getBlocktype()!=null
				&& rootblock.getBlocktype().equals(SmallJavaBlockConst.SingleLineMemo)) {
			//logger.error("singlelinememo ��䲻�ټ�������");
			return true;
		}
		if(rootblock.getBlocktype()!=null
				&& rootblock.getBlocktype().equals(SmallJavaBlockConst.MultiLineMemo)) {
			//logger.error("multilinememo ��䲻�ټ�������");
			return true;
		}
		
		// ��ʼ���������
		rootblock.computestring = rootblock.getBlockContent();
		//logger.info("---->analyse begin." + rootblock.computestring);
		// step2.д��rootblock,����������

		rootblock.computestring = this._trimReturnAndSpace(rootblock.computestring);
		rootblock.setBlockContent(rootblock.computestring);

		// step3.��ʼ�����ѭ������
		if (rootblock.computestring.equals("") && rootblock.getChildren().size()==0) {
			rootblock.setBlocktype(SmallJavaBlockConst.EmptyBlock);
			return true;
		}

		// ѭ�������ӽڵ�ʱ����Ҫ����IfBlock,ֱ�Ӵ������¼�
		if (!rootblock.getBlocktype().equals(SmallJavaBlockConst.Ifblock)) {
			// Step4.��ʼ����ѭ���жϴ���,��������ĸ�������ж�����һ���ķ�֧��
			// ÿ��ִ����Ϻ��rootblock.computestring�����з�
			while (rootblock.computestring.length() > 0) {
				// Step4.0������trim����
				rootblock.computestring = this._trimReturnAndSpace(rootblock.computestring);

				// ѭ�����ò�����д�������������false,˵����������ˣ�ִֹͣ��
				String s1 = rootblock.computestring;
				if (!pmanager.process(rootblock)) {
					//logger.info("��ERROR�������������ʧ��:" + rootblock.computestring);
					// ����ʧ�ܣ�����false
					return false;
				}

				String s2 = rootblock.computestring;
				if (s1.length() == s2.length()) {
					// �ַ���û�еõ���Ч���������£�����false
					// �������֮���߼��ϱ�����ƥ��ĲŶ�
					//logger.info("��ERROR�����еĲ��ִ���Ժ��ַ���û�еõ��κδ���." + rootblock.computestring);
					return false;
				}
			}
		}

		// Step5.�����ڵ��з���ϣ���ʼ�����¼��ڵ㣬���ж��䷵��ֵ
		for (BasicBlock child : rootblock.getChildren()) {
			//�˴���Ҫ����child���������ж��Ƿ���Ҫ����
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
				//logger.info("��ERROR���ݹ�����ӽڵ����ʱ��������.");
				return false;
			}
		}
		// ����ɹ��������
		return true;
	}

	/**
	 * ���ַ���rootblock.computestring�ֽ��AbastractBlock,������Children���������״�ṹ
	 * 
	 * @return ture �ֽ�ɹ� false �ֽ�ʧ��
	 */
//	public boolean analyse(ElementWrapper rootelement) {
//		// step1:����������
//		if (rootelement == null) {
//			logger.error("Argument Error,rootblock is null.");
//			return false;
//		}
//		logger.error(rootelement.getElement().asXML());
//		if (rootelement.getBlockContent() == null) {
//			logger.error("Argument Error,rootblock.computestring is null.");
//			return false;
//		}
//
//		// ��ʼ���������
//		rootelement.setComputestring(rootelement.getBlockContent());
//		logger.info("---->analyse begin." + rootelement.getComputestring());
//		// step2.д��rootblock,����������
//
//		rootelement.setComputestring(this._trimReturnAndSpace(rootelement.getComputestring()));
//		rootelement.setBlockContent(rootelement.getComputestring());
//
//		// step3.��ʼ�����ѭ������
//		if (rootelement.getComputestring().equals("")
//				&& rootelement.getElement().elements().size()==0) {
//			return true;
//		}
//
//		// Step4.��ʼ����ѭ���жϴ���,��������ĸ�������ж�����һ���ķ�֧��
//		// ÿ��ִ����Ϻ��rootblock.computestring�����з�
//		while (rootelement.getComputestring().length() > 0) {
//			// Step4.0������trim����
//			rootelement.setComputestring(this._trimReturnAndSpace(rootelement.getComputestring()));
//
//			// ѭ�����ò�����д�������������false,˵����������ˣ�ִֹͣ��
//			String s1 = rootelement.getComputestring();
//			if (!pmanager.process(rootelement)) {
//				logger.info("��ERROR�������������ʧ��:" + rootelement.getComputestring());
//				// ����ʧ�ܣ�����false
//				return false;
//			}
//			String s2 = rootelement.getComputestring();
//			if (s1.length() == s2.length()) {
//				// �ַ���û�еõ���Ч���������£�����false
//				// �������֮���߼��ϱ�����ƥ��ĲŶ�
//				logger.info("��ERROR�����еĲ��ִ���Ժ��ַ���û�еõ��κδ���." + rootelement.getComputestring());
//				return false;
//			}
//		}
//
//		// Step5.�����ڵ��з���ϣ���ʼ�����¼��ڵ㣬���ж��䷵��ֵ
//		@SuppressWarnings("unchecked")
//		List<Element> list1 =rootelement.getElement().elements();
//		for (Element  child : list1) {
//			//������Ҫ����child���������ж��Ƿ���Ҫ����
//			logger.info("child.name:"+child.getName());
//			if(child.getName().equals(SmallJavaBlockConst.SingleLineMemo)
//					|| child.getName().equals(SmallJavaBlockConst.MultiLineMemo)) {
//				//����
//				continue;
//			}
//			
//			ElementWrapper childew = new ElementWrapper();
//			childew.setElement(child);
//			if(! analyse(childew)) {
//				logger.info("��ERROR���ӽڵ��������");
//				return false;
//			};
//		}
//		// �ж�child������,ֻ���ض���child����Ҫ���еݹ����
//		// }
//		// ����ɹ��������
//		return true;
//	}

	/**
	 * ���ַ���rootblock.computestring�ֽ��AbastractBlock,������Children���������״�ṹ
	 * 
	 * @return ture �ֽ�ɹ� false �ֽ�ʧ��
	 */
//	public boolean analyse(JSONWrapper rootelement) {
//		// step1:����������
//		if (rootelement == null) {
//			logger.error("Argument Error,rootblock is null.");
//			return false;
//		}
//		if (rootelement.getBlockContent() == null) {
//			logger.error("Argument Error,rootblock.computestring is null.");
//			return false;
//		}
//
//		// ��ʼ���������
//		rootelement.setComputestring(rootelement.getBlockContent());
//		logger.info("---->analyse begin." + rootelement.getComputestring());
//		// step2.д��rootblock,����������
//
//		rootelement.setComputestring(this._trimReturnAndSpace(rootelement.getComputestring()));
//		rootelement.setBlockContent(rootelement.getComputestring());
//
//		// step3.��ʼ�����ѭ������
//		if (rootelement.getComputestring().equals("")) {
//			return true;
//		}
//
//		// Step4.��ʼ����ѭ���жϴ���,��������ĸ�������ж�����һ���ķ�֧��
//		// ÿ��ִ����Ϻ��rootblock.computestring�����з�
//		while (rootelement.getComputestring().length() > 0) {
//			// Step4.0������trim����
//			rootelement.setComputestring(this._trimReturnAndSpace(rootelement.getComputestring()));
//
//			// ѭ�����ò�����д�������������false,˵����������ˣ�ִֹͣ��
//			String s1 = rootelement.getComputestring();
//			if (!pmanager.process(rootelement)) {
//				logger.error("��ERROR�������������ʧ��:" + rootelement.getComputestring());
//				// ����ʧ�ܣ�����false
//				return false;
//			}
//
//			String s2 = rootelement.getComputestring();
//			if (s1.length() == s2.length()) {
//				// �ַ���û�еõ���Ч���������£�����false
//				// �������֮���߼��ϱ�����ƥ��ĲŶ�
//				logger.error("��ERROR�����еĲ��ִ���Ժ��ַ���û�еõ��κδ���." + rootelement.getComputestring());
//				return false;
//			}
//		}
//
//		// Step5.�����ڵ��з���ϣ���ʼ�����¼��ڵ㣬���ж��䷵��ֵ
//		// for (AbstractBlock child : rootelement.getChildren()) {
//		// �ж�child������,ֻ���ض���child����Ҫ���еݹ����
//		// }
//		// ����ɹ��������
//		return true;
//	}

	/**
	 * ���ַ�����ʼ�ͽ���λ�õ�\r\n ,\r,�ո񶼹��˵�
	 * 
	 * @param strinput
	 * @return
	 */
	private String _trimReturnAndSpace(String strinput) {
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
			//logger.error("����ִ�г��ִ�����Ҫ������������.ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}

}
