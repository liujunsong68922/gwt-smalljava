package com.smalljava.core.l5_expression.vo;

import com.smalljava.core.common.UUIDFunction;

//import java.util.UUID;

//import javax.swing.tree.DefaultMutableTreeNode;
//import javax.swing.tree.TreeNode;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.vo.constvalue.AbstractConstDataElement;
import com.smalljava.core.l5_expression.vo.obj.ObjectCallOperElement;
import com.smalljava.core.l5_expression.vo.one.AbstractSingleOperDataOperElement;
import com.smalljava.core.l5_expression.vo.two.DualOperDataOperElement;
import com.smalljava.core.l5_expression.vo.var.VarDataElement;
import com.smalljava.core.l5_expression.vo.var.VarDefineOperElement;

/**
 * RootAST����һ����Ҫ��һ�����зֽ⴦����м�״̬��������ڵ��¼������ӽڵ�
 * 
 * @author liujunsong
 *
 */
public class RootAST extends AbstractAST {
	private Logger logger = LoggerFactory.getLogger(RootAST.class);

	public String strexpression;
	/**
	 * MEMOϵͳ���ɵĴ���ڵ�Ψһ��ʶ��uuid
	 */
	private String uuid;

	// ���캯��
	public RootAST() {
		// ����Ψһ��uuid
		//this.uuid = UUID.randomUUID().toString().replaceAll("-", "");
		this.uuid = UUIDFunction.uuid();
	}

	public String getStrexpression() {
		return strexpression;
	}

	public void setStrexpression(String strexpression) {
		this.strexpression = strexpression;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void show(int ilevel) {
		if (this.getChildren() == null) {
			return;
		}
		// logger.info(this.getClass().getSimpleName());
		String blockname = this.getClass().getSimpleName();
		String strleft = "";
		for (int i = 0; i < ilevel; i++) {
			strleft += "    ";
		}

		String sinfo = this.strexpression;
		if (this instanceof DualOperDataOperElement) {
			DualOperDataOperElement e1 = (DualOperDataOperElement) this;
			sinfo = e1.getOpercode();
		}
		if (this instanceof VarDataElement) {
			VarDataElement var = (VarDataElement) this;
			sinfo = var.getVarname();
		}
		if (this.getChildren().size() == 0) {
			if (this instanceof AbstractConstDataElement) {
				AbstractConstDataElement constdata = (AbstractConstDataElement) this;
				logger.info(strleft + "---->" + blockname + ":" + constdata.getDatavalue());
			} else if (this instanceof VarDataElement) {
				VarDataElement var = (VarDataElement) this;
				logger.info(strleft + "---->" + blockname + ":" + var.getVarname());
			} else if (this instanceof VarDefineOperElement) {
				VarDefineOperElement def = (VarDefineOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
			} else if (this instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
			} else if (this instanceof ObjectCallOperElement) {
				ObjectCallOperElement objcall = (ObjectCallOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + objcall.getObjname() + "/" + objcall.getMethodname());
			} else {
				logger.info(strleft + "no child1---->" + blockname + ":" + sinfo);
			}
			return;
		} else {
			if (this instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
			} else if (this instanceof ObjectCallOperElement) {
				ObjectCallOperElement objcall = (ObjectCallOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + objcall.getObjname() + "/" + objcall.getMethodname());
			} else {
				logger.info(strleft + "---->" + blockname + ":" + sinfo);
			}
		}

		for (RootAST child : this.getChildren()) {
			if (child != null) {
				child.show(ilevel + 1);
			} else {
				logger.info("child is null" + sinfo);
			}
		}

	}

	public String getShowString(int ilevel) {
		String sret = "";
		if (this.getChildren() == null) {
			return sret;
		}

		String blockname = this.getClass().getSimpleName();
		String strleft = "";
		for (int i = 0; i < ilevel; i++) {
			strleft += "    ";
		}

		String sinfo = this.strexpression;
		if (this instanceof DualOperDataOperElement) {
			DualOperDataOperElement e1 = (DualOperDataOperElement) this;
			sinfo = e1.getOpercode();
		}
		if (this instanceof VarDataElement) {
			VarDataElement var = (VarDataElement) this;
			sinfo = var.getVarname();
		}
		if (this.getChildren().size() == 0) {
			if (this instanceof AbstractConstDataElement) {
				AbstractConstDataElement constdata = (AbstractConstDataElement) this;
				logger.info(strleft + "---->" + blockname + ":" + constdata.getDatavalue());
				sret += "\r\n";
				sret += (strleft + "---->" + blockname + ":" + constdata.getDatavalue());
			} else if (this instanceof VarDataElement) {
				VarDataElement var = (VarDataElement) this;
				logger.info(strleft + "---->" + blockname + ":" + var.getVarname());
				sret += "\r\n";
				sret += (strleft + "---->" + blockname + ":" + var.getVarname());
			} else if (this instanceof VarDefineOperElement) {
				VarDefineOperElement def = (VarDefineOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
				sret += "\r\n";
				sret += (strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
			} else if (this instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
				sret += "\r\n";
				sret += (strleft + "---->" + blockname + ":" + se.getOpercode());
			}else if (this instanceof ObjectCallOperElement) {
				ObjectCallOperElement objcall = (ObjectCallOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + objcall.getObjname() + "/" + objcall.getMethodname());
				sret += "\r\n";
				sret +=(strleft + "---->" + blockname + ":" + objcall.getObjname() + "/" + objcall.getMethodname());
			}else {
				logger.info(strleft + "no child2---->" + blockname + ":" + sinfo);
				sret += "\r\n";
				sret += (strleft + "no child2---->" + blockname + ":" + sinfo);
			}
			return sret;
		} else {
			if (this instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
				sret += "\r\n";
				sret += (strleft + "---->" + blockname + ":" + se.getOpercode());
			} else if (this instanceof ObjectCallOperElement) {
				ObjectCallOperElement objcall = (ObjectCallOperElement) this;
				logger.info(strleft + "---->" + blockname + ":" + objcall.getObjname() + "/" + objcall.getMethodname());
				sret += "\r\n";
				sret +=(strleft + "---->" + blockname + ":" + objcall.getObjname() + "/" + objcall.getMethodname());
			}else {
				logger.info(strleft + "---->" + blockname + ":" + sinfo);
				sret += "\r\n";
				sret += (strleft + "---->" + blockname + ":" + sinfo);
			}
		}

		for (RootAST child : this.getChildren()) {
			if (child != null) {
				String schild = child.getShowString(ilevel + 1);
				sret += schild;
			} else {
				logger.info("child is null" + sinfo);
				sret += "\r\n" + ("child is null" + sinfo);
			}
		}
		return sret;

	}

//	public DefaultMutableTreeNode toTreeNode(int ilevel) {
//		DefaultMutableTreeNode retTreeNode = new DefaultMutableTreeNode("");
//		if (this.getChildren() == null) {
//			return retTreeNode;
//		}
//
//		String blockname = this.getClass().getSimpleName();
//		String strleft = "";
//		for (int i = 0; i < ilevel; i++) {
//			strleft += "    ";
//		}
//
//		String sinfo = this.strexpression;
//		if (this instanceof DualOperDataOperElement) {
//			DualOperDataOperElement e1 = (DualOperDataOperElement) this;
//			sinfo = e1.getOpercode();
//		}
//		if (this instanceof VarDataElement) {
//			VarDataElement var = (VarDataElement) this;
//			sinfo = var.getVarname();
//		}
//		if (this.getChildren().size() == 0) {
//			if (this instanceof AbstractConstDataElement) {
//				AbstractConstDataElement constdata = (AbstractConstDataElement) this;
//				logger.info(strleft + "---->" + blockname + ":" + constdata.getDatavalue());
//				retTreeNode = new DefaultMutableTreeNode(
//						strleft + "---->" + blockname + ":" + constdata.getDatavalue());
//			} else if (this instanceof VarDataElement) {
//				VarDataElement var = (VarDataElement) this;
//				logger.info(strleft + "---->" + blockname + ":" + var.getVarname());
//				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" + var.getVarname());
//			} else if (this instanceof VarDefineOperElement) {
//				VarDefineOperElement def = (VarDefineOperElement) this;
//				logger.info(strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
//				retTreeNode = new DefaultMutableTreeNode(
//						strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
//			} else if (this instanceof AbstractSingleOperDataOperElement) {
//				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
//				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
//				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" + se.getOpercode());
//			}else if (this instanceof ObjectCallOperElement) {
//				ObjectCallOperElement objcall = (ObjectCallOperElement) this;
//				logger.info(strleft + "---->" + blockname + ":" + objcall.getObjname() + "/" + objcall.getMethodname());
//				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" +  objcall.getObjname() + "/" + objcall.getMethodname());
//			}
//
//			else {
//				logger.info(strleft + "no child---->" + blockname + ":" + sinfo);
//				// sretnode += "\r\n";
//				retTreeNode = new DefaultMutableTreeNode(strleft + "no child---->" + blockname + ":" + sinfo);
//			}
//			return retTreeNode;
//		} else {
//			if (this instanceof AbstractSingleOperDataOperElement) {
//				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
//				logger.info(strleft + "---->" + blockname + ":" + se.getOpercode());
//				// sretnode +="\r\n";
//				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" + se.getOpercode());
//			}else if (this instanceof ObjectCallOperElement) {
//				ObjectCallOperElement objcall = (ObjectCallOperElement) this;
//				logger.info(strleft + "---->" + blockname + ":" + objcall.getObjname() + "/" + objcall.getMethodname());
//				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" +  objcall.getObjname() + "/" + objcall.getMethodname());
//			}
//			else {
//				logger.info(strleft + "---->" + blockname + ":" + sinfo);
//				// sretnode +="\r\n";
//				retTreeNode = new DefaultMutableTreeNode(strleft + "---->" + blockname + ":" + sinfo);
//			}
//		}
//
//		for (RootAST child : this.getChildren()) {
//			if (child != null) {
//				// String schild = child.getShowString(ilevel + 1);
//				DefaultMutableTreeNode childnode = child.toTreeNode(ilevel + 1);
//				retTreeNode.add(childnode);
//			} else {
//				logger.info("child is null" + sinfo);
//				DefaultMutableTreeNode childnode = new DefaultMutableTreeNode("child is null." + sinfo);
//				retTreeNode.add(childnode);
//			}
//		}
//		return retTreeNode;
//
//	}
}
