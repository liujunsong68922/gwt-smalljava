package com.smalljava.core.l4_block.blockvo;

import java.util.ArrayList;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import javax.swing.tree.DefaultMutableTreeNode;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockvo.childblock.MethodBlock;

/**
 * 所有JAVA代码块的超类
 * 
 * @author liujunsong
 *
 */
public class BasicBlock {
	private Logger logger = LoggerFactory.getLogger(BasicBlock.class);

	/**
	 * 块的类型，在Analyse的时候赋值，在Eval的时候作为参考，默认为空
	 */
	public String blocktype;

	/**
	 * 要进行分析的字符串
	 */
	public String blockContent;

	/**
	 * block计算的中间计算用变量
	 */
	public String computestring;

	/**
	 * 这个属性定义了当前节点的上级节点，定义了这个节点以后， 在执行变量表时，如果本地变量表中查找不到，则需要按照这个父节点来进行上溯
	 * 如果父节点为null，则停止上溯， 变量表本身不再具有父子层次关系，父子层次关系依托在AbstractBlock来实现
	 */
	private BasicBlock parentblock;
	/**
	 * 子节点定义
	 */
	public ArrayList<BasicBlock> children;

	/**
	 * classVarTable代表的是class级别（Object级别的变量表)
	 * MEMO:这个变量表只有在parentblock=null的时候是可读可写的，但不可定义变量。
	 */
	// private IVarTable classVarTable;

	public BasicBlock getParentblock() {
		return parentblock;
	}

	public void setParentblock(BasicBlock parentblock) {
		this.parentblock = parentblock;
	}

	/**
	 * 内部的HashMap存储，这个存储目前只存储基础类型，因此使用String来存储具体数据
	 */
//	private HashMap<String, VarValue> myvarmap = new HashMap<String, VarValue>();

	/**
	 * MEMO：为了将Class的解析和block的解析合并起来，在这里增加methodList
	 */
	// private ArrayList<MethodBlock> methodList;
	/**
	 * 构造函数，必须传入一个字符串
	 * 
	 * @param blockcontent
	 */
	public BasicBlock(String _blocktype, String _blockcontent, BasicBlock parentblock) {

		this.blocktype = _blocktype;
		// 初始化blockcontent
		this.blockContent = _blockcontent;
		// 此处化Children
		this.children = new ArrayList<BasicBlock>();
		// 创建时需要设置上级节点
		this.parentblock = parentblock;

		// this.vartable = _vartable;
		// 这里需要做一个额外判断，
		// 如果parentblock==null
		// 就在变量表中增加一个名为"RETURN"的变量
		// if(parentblock == null) {
		// this.getVartable().defineVar("RETURN", "String");
		// }else {
		// 不是根的Block节点，不可定义这个变量
		// do nothing.
		// }
		// 初始化methodList
		// this.methodList = new ArrayList<MethodBlock>();
	}

	/**
	 * 构造函数，必须传入一个字符串
	 * 
	 * @param blockcontent
	 */
//	public BasicBlock(String _blocktype, String _blockcontent,BasicBlock parentblock) {
//		this.blocktype = _blocktype;
//		// 初始化blockcontent
//		this.blockContent = _blockcontent;
//		// 此处化Children
//		this.children = new ArrayList<BasicBlock>();
//		//创建时需要设置上级节点
//		this.parentblock = parentblock;
//		//这里需要做一个额外判断，
//		//如果parentblock==null
//		//就在变量表中增加一个名为"RETURN"的变量
//		if(parentblock == null) {
//			
//			this.getVartable().defineVar("RETURN", "String");
//		}else {
//			//不是根的Block节点，不可定义这个变量
//			//do nothing.
//			this.setClassVarTable(parentblock.getClassVarTable());
//		}
//		// 初始化methodList
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
