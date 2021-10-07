package com.liu.gwt.gwt_smalljava.level5_expression.expressionvo;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.constvalue.AbstractConstDataElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.obj.ObjectCallOperElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.one.AbstractSingleOperDataOperElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.two.DualOperDataOperElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.NewOperElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarDataElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarDefineOperElement;

/**
 * RootAST����һ����Ҫ��һ�����зֽ⴦����м�״̬��������ڵ��¼������ӽڵ�
 * 
 * @author liujunsong
 *
 */
public class RootAST extends AbstractAST {
	private String strexpression;
	private String uuid;

	public RootAST() {
		this.uuid = uuid();
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
		// System.out.println(this.getClass().getSimpleName());
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
				System.out.println(strleft + "---->" + blockname + ":" + constdata.getDatavalue());
				consoleLog(strleft + "---->" + blockname + ":" + constdata.getDatavalue());
			
			} else if (this instanceof VarDataElement) {
				VarDataElement var = (VarDataElement) this;
				System.out.println(strleft + "---->" + blockname + ":" + var.getVarname());
				consoleLog(strleft + "---->" + blockname + ":" + var.getVarname());
			
			} else if (this instanceof VarDefineOperElement) {
				VarDefineOperElement def = (VarDefineOperElement) this;
				System.out.println(strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
				consoleLog(strleft + "---->" + blockname + ":" + def.getDatatype() + " " + def.getVarname());
				
			} else if (this instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
				System.out.println(strleft + "---->" + blockname + ":" + se.getOpercode());
				consoleLog(strleft + "---->" + blockname + ":" + se.getOpercode());
			
			} else if (this instanceof ObjectCallOperElement) {
				ObjectCallOperElement objcall = (ObjectCallOperElement)this;
				
				consoleLog(strleft +"---->" + blockname +":"+ objcall.getObjname()+"/"+objcall.getMethodname());
			} else if (this instanceof NewOperElement) {
				NewOperElement newoper = (NewOperElement) this;
				consoleLog(strleft +"---->" + blockname+":" + newoper.getClassname());
			}else {
				System.out.println(strleft + "no child---->" + blockname + ":" + sinfo);
				consoleLog(strleft + "[no child]---->" + blockname + ":" + sinfo);
			}
			return;
		} else {
			if (this instanceof AbstractSingleOperDataOperElement) {
				AbstractSingleOperDataOperElement se = (AbstractSingleOperDataOperElement) this;
				System.out.println(strleft + "---->" + blockname + ":" + se.getOpercode());
				consoleLog(strleft + "---->" + blockname + ":" + se.getOpercode());
				
			} else {
				System.out.println(strleft + "---->" + blockname + ":" + sinfo);
				consoleLog(strleft + "---->" + blockname + ":" + sinfo);
			}
		}

		for (RootAST child : this.getChildren()) {
			if (child != null) {
				child.show(ilevel + 1);
			} else {
				System.out.println("child is null" + sinfo);
				consoleLog("child is null" + sinfo);
			}
		}

	}

	public native String uuid() /*-{
								return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
								var r = Math.random() * 16 | 0,
								v = c == 'x' ? r : (r & 0x3 | 0x8);
								return v.toString(16);
								});
								}-*/;
	
	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[ExpressionEval]:" + message );
	}-*/;
}
