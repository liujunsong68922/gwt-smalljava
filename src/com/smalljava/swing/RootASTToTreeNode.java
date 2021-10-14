package com.smalljava.swing;

import javax.swing.tree.DefaultMutableTreeNode;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.constvalue.AbstractConstDataElement;
import com.smalljava.core.l5_expression.vo.obj.ObjectCallOperElement;
import com.smalljava.core.l5_expression.vo.one.AbstractSingleOperDataOperElement;
import com.smalljava.core.l5_expression.vo.two.DualOperDataOperElement;
import com.smalljava.core.l5_expression.vo.var.VarDataElement;
import com.smalljava.core.l5_expression.vo.var.VarDefineOperElement;

public class RootASTToTreeNode {
	private Logger logger = LoggerFactory.getLogger(RootASTToTreeNode.class);

	public DefaultMutableTreeNode toTreeNode(RootAST rootast,int ilevel) {
		DefaultMutableTreeNode retTreeNode = new DefaultMutableTreeNode("");
		if (rootast.getChildren() == null) {
			return retTreeNode;
		}

		String blockname = rootast.getClass().getSimpleName();
		String strleft = "";
		for (int i = 0; i < ilevel; i++) {
			strleft += "    ";
		}

		String sinfo = rootast.strexpression;
		if (rootast instanceof DualOperDataOperElement) {
			DualOperDataOperElement e1 = (DualOperDataOperElement) rootast;
			sinfo = e1.getOpercode();
		}
		if (rootast instanceof VarDataElement) {
			VarDataElement var = (VarDataElement) rootast;
			sinfo = var.getVarname();
		}
		if (rootast.getChildren().size() == 0) {
			if (rootast instanceof AbstractConstDataElement) {
				AbstractConstDataElement constdata = (AbstractConstDataElement) rootast;
				logger.info(strleft + "---->" + blockname + ":" + constdata.getDatavalue());
				retTreeNode = new DefaultMutableTreeNode(
						strleft + "---->" + blockname + ":" + constdata.getDatavalue());
			} else if (rootast instanceof VarDataElement) {
				VarDataElement var = (VarDataElement) rootast;
				logger.info(strleft + "---->" + blockname + ":" + var.getVarname());
				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" + var.getVarname());
			} else if (rootast instanceof VarDefineOperElement) {
				VarDefineOperElement def = (VarDefineOperElement) rootast;
				logger.info(strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
				retTreeNode = new DefaultMutableTreeNode(
						strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
			} else if (rootast instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) rootast;
				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" + se.getOpercode());
			}else if (rootast instanceof ObjectCallOperElement) {
				ObjectCallOperElement objcall = (ObjectCallOperElement) rootast;
				logger.info(strleft + "---->" + blockname + ":" + objcall.getObjname() + "/" + objcall.getMethodname());
				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" +  objcall.getObjname() + "/" + objcall.getMethodname());
			}

			else {
				logger.info(strleft + "no child---->" + blockname + ":" + sinfo);
				// sretnode += "\r\n";
				retTreeNode = new DefaultMutableTreeNode(strleft + "no child---->" + blockname + ":" + sinfo);
			}
			return retTreeNode;
		} else {
			if (rootast instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) rootast;
				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
				// sretnode +="\r\n";
				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" + se.getOpercode());
			}else if (rootast instanceof ObjectCallOperElement) {
				ObjectCallOperElement objcall = (ObjectCallOperElement) rootast;
				logger.info(strleft + "---->" + blockname + ":" + objcall.getObjname() + "/" + objcall.getMethodname());
				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" +  objcall.getObjname() + "/" + objcall.getMethodname());
			}
			else {
				logger.info(strleft + "---->" + blockname + ":" + sinfo);
				// sretnode +="\r\n";
				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" + sinfo);
			}
		}

		for (RootAST child : rootast.getChildren()) {
			if (child != null) {
				// String schild = child.getShowString(ilevel + 1);
				// DefaultMutableTreeNode childnode = child.toTreeNode(ilevel + 1);
				DefaultMutableTreeNode childnode = this.toTreeNode(rootast, ilevel+1);
				
				retTreeNode.add(childnode);
			} else {
				logger.info("child is null" + sinfo);
				DefaultMutableTreeNode childnode = new DefaultMutableTreeNode("child is null." + sinfo);
				retTreeNode.add(childnode);
			}
		}
		return retTreeNode;

	}
}
