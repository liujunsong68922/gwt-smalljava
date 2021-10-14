package com.smalljava.core.l4_block.blockvo;

import java.util.ArrayList;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import javax.swing.tree.DefaultMutableTreeNode;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockvo.childblock.MethodBlock;

/**
 * ����JAVA�����ĳ���
 * 
 * @author liujunsong
 *
 */
public class BasicBlock {
	private Logger logger = LoggerFactory.getLogger(BasicBlock.class);

	/**
	 * ������ͣ���Analyse��ʱ��ֵ����Eval��ʱ����Ϊ�ο���Ĭ��Ϊ��
	 */
	public String blocktype;

	/**
	 * Ҫ���з������ַ���
	 */
	public String blockContent;

	/**
	 * block������м�����ñ���
	 */
	public String computestring;

	/**
	 * ������Զ����˵�ǰ�ڵ���ϼ��ڵ㣬����������ڵ��Ժ� ��ִ�б�����ʱ��������ر������в��Ҳ���������Ҫ����������ڵ�����������
	 * ������ڵ�Ϊnull����ֹͣ���ݣ� ���������پ��и��Ӳ�ι�ϵ�����Ӳ�ι�ϵ������AbstractBlock��ʵ��
	 */
	private BasicBlock parentblock;
	/**
	 * �ӽڵ㶨��
	 */
	public ArrayList<BasicBlock> children;

	/**
	 * classVarTable�������class����Object����ı�����)
	 * MEMO:���������ֻ����parentblock=null��ʱ���ǿɶ���д�ģ������ɶ��������
	 */
	// private IVarTable classVarTable;

	public BasicBlock getParentblock() {
		return parentblock;
	}

	public void setParentblock(BasicBlock parentblock) {
		this.parentblock = parentblock;
	}

	/**
	 * �ڲ���HashMap�洢������洢Ŀǰֻ�洢�������ͣ����ʹ��String���洢��������
	 */
//	private HashMap<String, VarValue> myvarmap = new HashMap<String, VarValue>();

	/**
	 * MEMO��Ϊ�˽�Class�Ľ�����block�Ľ����ϲ�����������������methodList
	 */
	// private ArrayList<MethodBlock> methodList;
	/**
	 * ���캯�������봫��һ���ַ���
	 * 
	 * @param blockcontent
	 */
	public BasicBlock(String _blocktype, String _blockcontent, BasicBlock parentblock) {

		this.blocktype = _blocktype;
		// ��ʼ��blockcontent
		this.blockContent = _blockcontent;
		// �˴���Children
		this.children = new ArrayList<BasicBlock>();
		// ����ʱ��Ҫ�����ϼ��ڵ�
		this.parentblock = parentblock;

		// this.vartable = _vartable;
		// ������Ҫ��һ�������жϣ�
		// ���parentblock==null
		// ���ڱ�����������һ����Ϊ"RETURN"�ı���
		// if(parentblock == null) {
		// this.getVartable().defineVar("RETURN", "String");
		// }else {
		// ���Ǹ���Block�ڵ㣬���ɶ����������
		// do nothing.
		// }
		// ��ʼ��methodList
		// this.methodList = new ArrayList<MethodBlock>();
	}

	/**
	 * ���캯�������봫��һ���ַ���
	 * 
	 * @param blockcontent
	 */
//	public BasicBlock(String _blocktype, String _blockcontent,BasicBlock parentblock) {
//		this.blocktype = _blocktype;
//		// ��ʼ��blockcontent
//		this.blockContent = _blockcontent;
//		// �˴���Children
//		this.children = new ArrayList<BasicBlock>();
//		//����ʱ��Ҫ�����ϼ��ڵ�
//		this.parentblock = parentblock;
//		//������Ҫ��һ�������жϣ�
//		//���parentblock==null
//		//���ڱ�����������һ����Ϊ"RETURN"�ı���
//		if(parentblock == null) {
//			
//			this.getVartable().defineVar("RETURN", "String");
//		}else {
//			//���Ǹ���Block�ڵ㣬���ɶ����������
//			//do nothing.
//			this.setClassVarTable(parentblock.getClassVarTable());
//		}
//		// ��ʼ��methodList
//		//this.methodList = new ArrayList<MethodBlock>();
//	}

	public String getBlockContent() {
		return blockContent;
	}

	public void setBlockContent(String blockContent) {
		this.blockContent = blockContent;
	}

	public ArrayList<BasicBlock> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<BasicBlock> children) {
		this.children = children;
	}

	public void show(int ilevel) {
		if (this.children == null) {
			return;
		}
		// logger.error(this.getClass().getSimpleName());
		String blockname = this.getClass().getSimpleName();
		if (this.blocktype != null && this.blocktype.length() > 0) {
			blockname = this.blocktype;
		}
		String strleft = "";
		for (int i = 0; i < ilevel; i++) {
			strleft += "    ";
		}

		if (this.children.size() == 0) {
			if (this instanceof MethodBlock) {
				MethodBlock mb = (MethodBlock) this;
				logger.error(
						strleft + "---->" + blockname + " --> " + mb.getMethodname() + " : " + mb.getMethodcontent());
			} else {
				logger.error(strleft + "---->" + blockname + ":" + this.blockContent);
			}
			return;
		} else {
			logger.error(strleft + "---->" + blockname);
		}

		for (BasicBlock child : this.children) {
			child.show(ilevel + 1);
		}
	}

	public void showvar(int ilevel) {
		if (this.children == null) {
			return;
		}
		// logger.error(this.getClass().getSimpleName());
		String blockname = this.getClass().getSimpleName();
		if (this.blocktype != null && this.blocktype.length() > 0) {
			blockname = this.blocktype;
		}
		String strleft = "";
		for (int i = 0; i < ilevel; i++) {
			strleft += "    ";
		}

		if (this.children.size() == 0) {
			String s1 = this.toString();
			logger.error(strleft + "---->" + blockname + ":" + s1);
			return;
		} else {
			String s1 = this.toString();
			logger.error(strleft + "---->" + blockname + s1);
		}

		for (BasicBlock child : this.children) {
			child.showvar(ilevel + 1);
		}
	}

	public String getBlocktype() {
		return blocktype;
	}

	public void setBlocktype(String blocktype) {
		this.blocktype = blocktype;
	}

//	public ArrayList<MethodBlock> getMethodList() {
//		return methodList;
//	}
//
//	public void setMethodList(ArrayList<MethodBlock> methodList) {
//		this.methodList = methodList;
//	}

//	public IVarTable getClassVarTable() {
//		return classVarTable;
//	}

//	public void setClassVarTable(IVarTable classVarTable) {
//		this.classVarTable = classVarTable;
//	}

	public String getShowString(int ilevel) {
		String sret = "";
		if (this.children == null) {
			return sret;
		}
		// logger.error(this.getClass().getSimpleName());
		String blockname = this.getClass().getSimpleName();
		if (this.blocktype != null && this.blocktype.length() > 0) {
			blockname = this.blocktype;
		}
		String strleft = "";
		for (int i = 0; i < ilevel; i++) {
			strleft += "    ";
		}

		if (this.children.size() == 0) {
			if (this instanceof MethodBlock) {
				MethodBlock mb = (MethodBlock) this;
				logger.error(
						strleft + "---->" + blockname + " --> " + mb.getMethodname() + " : " + mb.getMethodcontent());
				sret += "\r\n";
				sret += (strleft + "---->" + blockname + " --> " + mb.getMethodname() + " : " + mb.getMethodcontent());
			} else {
				logger.error(strleft + "---->" + blockname + ":" + this.blockContent);
				sret += "\r\n";
				sret += (strleft + "---->" + blockname + ":" + this.blockContent);
			}
			return sret;
		} else {
			logger.error(strleft + "---->" + blockname);
			sret += "\r\n";
			sret += (strleft + "---->" + blockname);
		}

		for (BasicBlock child : this.children) {
			String s1 = child.getShowString(ilevel + 1);
			sret += s1;
		}
		return sret;
	}

//	public DefaultMutableTreeNode toTreeNode(int ilevel) {
//		{
//			DefaultMutableTreeNode retTreeNode = new DefaultMutableTreeNode("");
//			if (this.children == null) {
//				return retTreeNode;
//			}
//			// logger.error(this.getClass().getSimpleName());
//			String blockname = this.getClass().getSimpleName();
//			if (this.blocktype != null && this.blocktype.length() > 0) {
//				blockname = this.blocktype;
//			}
//			String strleft = "";
//			for (int i = 0; i < ilevel; i++) {
//				strleft += "    ";
//			}
//
//			if (this.children.size() == 0) {
//				if (this instanceof MethodBlock) {
//					MethodBlock mb = (MethodBlock) this;
//					logger.error(strleft + "---->" + blockname + " --> " + mb.getMethodname() + " : "
//							+ mb.getMethodcontent());
//					// sret += "\r\n";
//					// sret += (strleft + "---->" + blockname + " --> " + mb.getMethodname()+" : " +
//					// mb.getMethodcontent());
//					retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + " --> "
//							+ mb.getMethodname() + " : " + mb.getMethodcontent());
//				} else {
//					logger.error(strleft + "---->" + blockname + ":" + this.blockContent);
//					// sret += "\r\n";
//					// sret += (strleft + "---->" + blockname + ":" + this.blockContent);
//					retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" + this.blockContent);
//				}
//				return retTreeNode;
//			} else {
//				logger.error(strleft + "---->" + blockname);
//				// sret += "\r\n";
//				// sret += (strleft + "---->" + blockname);
//				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname);
//			}
//
//			for (BasicBlock child : this.children) {
//				// String s1 = child.getShowString(ilevel + 1);
//				// sret += s1;
//				DefaultMutableTreeNode childnode = child.toTreeNode(ilevel + 1);
//				retTreeNode.add(childnode);
//			}
//			// return sret;
//			return retTreeNode;
//		}
//	}
}
