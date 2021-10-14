package com.smalljava.swing;

import javax.swing.tree.DefaultMutableTreeNode;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.MethodBlock;

public class BasicBlockToTreeNode {
	private Logger logger = LoggerFactory.getLogger(BasicBlockToTreeNode.class);
	
	public DefaultMutableTreeNode toTreeNode(BasicBlock bblock,int ilevel) {
	
		{
			DefaultMutableTreeNode retTreeNode = new DefaultMutableTreeNode("");
			if (bblock.getChildren() == null) {
				return retTreeNode;
			}
			// logger.error(bblock.getClass().getSimpleName());
			String blockname = bblock.getClass().getSimpleName();
			if (bblock.blocktype != null && bblock.blocktype.length() > 0) {
				blockname = bblock.blocktype;
			}
			String strleft = "";
			for (int i = 0; i < ilevel; i++) {
				strleft += "    ";
			}

			if (bblock.children.size() == 0) {
				if (bblock instanceof MethodBlock) {
					MethodBlock mb = (MethodBlock) bblock;
					logger.error(strleft + "---->" + blockname + " --> " + mb.getMethodname() + " : "
							+ mb.getMethodcontent());
					// sret += "\r\n";
					// sret += (strleft + "---->" + blockname + " --> " + mb.getMethodname()+" : " +
					// mb.getMethodcontent());
					retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + " --> "
							+ mb.getMethodname() + " : " + mb.getMethodcontent());
				} else {
					logger.error(strleft + "---->" + blockname + ":" + bblock.blockContent);
					// sret += "\r\n";
					// sret += (strleft + "---->" + blockname + ":" + bblock.blockContent);
					retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" + bblock.blockContent);
				}
				return retTreeNode;
			} else {
				logger.error(strleft + "---->" + blockname);
				// sret += "\r\n";
				// sret += (strleft + "---->" + blockname);
				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname);
			}

			for (BasicBlock child : bblock.children) {
				// String s1 = child.getShowString(ilevel + 1);
				// sret += s1;
				//DefaultMutableTreeNode childnode = child.toTreeNode(ilevel + 1);
				DefaultMutableTreeNode childnode = this.toTreeNode(child, ilevel+1);
				retTreeNode.add(childnode);
			}
			// return sret;
			return retTreeNode;
		}
	}
}
