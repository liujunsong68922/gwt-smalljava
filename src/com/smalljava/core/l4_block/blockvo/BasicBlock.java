package com.smalljava.core.l4_block.blockvo;

import java.util.ArrayList;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import javax.swing.tree.DefaultMutableTreeNode;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockvo.childblock.MethodBlock;


public class BasicBlock {
	private Logger logger = LoggerFactory.getLogger(BasicBlock.class);
	public String blocktype;
	public String blockContent;
	public String computestring;

	private BasicBlock parentblock;
	public ArrayList<BasicBlock> children;

	public BasicBlock getParentblock() {
		return parentblock;
	}

	public void setParentblock(BasicBlock parentblock) {
		this.parentblock = parentblock;
	}

	public BasicBlock(String _blocktype, String _blockcontent, BasicBlock parentblock) {
		this.blocktype = _blocktype;
		this.blockContent = _blockcontent;
		this.children = new ArrayList<BasicBlock>();
		this.parentblock = parentblock;
	}

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
}
