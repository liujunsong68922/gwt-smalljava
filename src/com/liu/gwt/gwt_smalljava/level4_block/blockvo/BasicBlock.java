package com.liu.gwt.gwt_smalljava.level4_block.blockvo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.MethodBlock;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

public class BasicBlock implements IVarTable {
	private String blocktype;
	private String blockContent;
	public String computestring;

	private BasicBlock parentblock;
	private ArrayList<BasicBlock> children;
	private IVarTable classVarTable;

	public BasicBlock getParentblock() {
		return parentblock;
	}

	public void setParentblock(BasicBlock parentblock) {
		this.parentblock = parentblock;
	}

	/**
	 * �ڲ���HashMap�洢������洢Ŀǰֻ�洢�������ͣ����ʹ��String���洢��������
	 */
	private HashMap<String, VarValue> myvarmap = new HashMap<String, VarValue>();

	/**
	 * MEMO��Ϊ�˽�Class�Ľ�����block�Ľ����ϲ�����������������methodList
	 */
	private ArrayList<MethodBlock> methodList;

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
		// ������Ҫ��һ�������жϣ�
		// ���parentblock==null
		// ���ڱ�����������һ����Ϊ"RETURN"�ı���
		if (parentblock == null) {
			this.defineVar("RETURN", "String");
		} else {
			// ���Ǹ���Block�ڵ㣬���ɶ����������
			// do nothing.
		}
		// ��ʼ��methodList
		this.methodList = new ArrayList<MethodBlock>();
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
		// System.out.println(this.getClass().getSimpleName());
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
				System.out.println(
						strleft + "---->" + blockname + " --> " + mb.getMethodname() + " : " + mb.getMethodcontent());
				consoleLog(strleft + "---->" + blockname + " --> " + mb.getMethodname() + " : " + mb.getMethodcontent());
			} else {
				System.out.println(strleft + "---->" + blockname + ":" + this.blockContent);
				consoleLog(strleft + "---->" + blockname + ":" + this.blockContent);
			}
			return;
		} else {
			System.out.println(strleft + "---->" + blockname);
			consoleLog(strleft + "---->" + blockname);
		}

		for (BasicBlock child : this.children) {
			child.show(ilevel + 1);
		}
	}

	public void showvar(int ilevel) {
		if (this.children == null) {
			return;
		}
		// System.out.println(this.getClass().getSimpleName());
		String blockname = this.getClass().getSimpleName();
		if (this.blocktype != null && this.blocktype.length() > 0) {
			blockname = this.blocktype;
		}
		String strleft = "";
		for (int i = 0; i < ilevel; i++) {
			strleft += "    ";
		}

		if (this.children.size() == 0) {
			String s1 = toJsonStrig(this.myvarmap);
			System.out.println(strleft + "---->" + blockname + ":" + s1);
			consoleLog(strleft + "---->" + blockname + ":" + s1);
			return;
		} else {
			String s1 = toJsonStrig(this.myvarmap);
			System.out.println(strleft + "---->" + blockname + s1);
			consoleLog(strleft + "---->" + blockname + ":" + s1);
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

	public HashMap<String, VarValue> getMyvarmap() {
		return myvarmap;
	}

	public void setMyvarmap(HashMap<String, VarValue> myvarmap) {
		this.myvarmap = myvarmap;
	}

	/**
	 * ����varname��VarMapNode�л�ȡ��ֵ������Ҳ������򷵻�null,
	 * 
	 * @param varname
	 * @return
	 */
	public VarValue getVarValue(String varname) {
		HashMap<String, VarValue> varmap = this._getFirstValidMap();
		if (varmap == null) {
			System.out.println("��ERROR�����ұ�����ʧ��.");
			return null;
		}

		if (varmap.containsKey(varname)) {
			// ���varname������Ϊһ�����ر�������ӱ���HashMap�л�ȡ��ֵ
			VarValue varvalue = varmap.get(varname);
			return varvalue;
		}

		// �����������ζ��varname����һ�����ر�������ô���Ե����丸�ڵ�����ȡ����
		BasicBlock pnode = this.getParentblock();
		while (pnode != null) {
			if (pnode.getMyvarmap().containsKey(varname)) {
				VarValue varvalue = pnode.getMyvarmap().get(varname);
				return varvalue;
			}
			// �ƶ������洢�ڵ������д���
			pnode = pnode.getParentblock();
		}

		// ѭ�����ң�ָ���Ҳ���varname,�򷵻�null�����Ҳ����������
		// ��������һ��������жϣ���������ѭ���������㣬���Բ���classVarTable
		if (this.classVarTable != null) {
			VarValue varvalue2 = this.classVarTable.getVarValue(varname);
			return varvalue2;
		}

		return null;
	}

	/**
	 * MEMO ���������á��㷨���������ҵ���һ�����õı�����������������ж������ֵ MEMO
	 * ��������ʱ������classVarTable
	 * 
	 * @param varname
	 * @param vartype
	 * @return
	 */
	public boolean defineVar(String varname, String vartype) {
		HashMap<String, VarValue> varmap = this._getFirstValidMap();
		if (varmap == null) {
			System.out.println("���ұ�����ʧ��.");
			return false;
		}
		if (varmap.containsKey(varname)) {
			System.out.println("����������󣬴˴����ظ�����ı�����:" + varname);
			return false;
		}
		if (vartype == null) {
			System.out.println("����������󣬱�������ʱ����Ϊ��." + varname);
			return false;
		}

		VarValue varvalue = new VarValue();
		varvalue.setVarname(varname);
		varvalue.setVartype(vartype);
		if (vartype.equals("int") || vartype.equals("long") || vartype.equals("float") || vartype.equals("double")) {
			varvalue.setVarsvalue("0");
		} else if (vartype.equals("boolean")) {
			varvalue.setVarsvalue("false");
		} else if (vartype.equals("String")) {
			varvalue.setVarsvalue("");
		} else {
			// ����Ϊ����ʱ��svalue����ӳ���ַ,""�ʹ�����һ��nullֵ
			varvalue.setVarsvalue("");
		}
		varmap.put(varname, varvalue);
		return true;
	}

	/**
	 * ������ֵ�������ҵ����������Ȼ���ٸ�ֵ
	 * 
	 * @param varname
	 * @param varvalue
	 * @return
	 */
	public boolean setVarValue(String varname, VarValue varvalue) {
		HashMap<String, VarValue> varmap = this._getFirstValidMap();
		if (varmap == null) {
			System.out.println("���ұ�����ʧ��.");
			return false;
		}
		if (varname == null || varname.length() == 0) {
			System.out.println("setVarValue����������󣬱�����Ϊ�ջ���Ϊ���ַ���");
			return false;
		}
		if (varvalue == null) {
			System.out.println("[BasicBlock]setVarValue����������󣬱������ò���Ϊnull");
			return false;
		}
		VarValue vvalue = this.getVarValue(varname);
		if (vvalue == null) {
			System.out.println("setVarValue����ִ������û���ҵ��������壺" + varname);
			return false;
		} else {
			vvalue.setVarname(varname);
			vvalue.setVartype(varvalue.getVartype());
			vvalue.setVarsvalue(varvalue.getVarsvalue());
			// vvalue.setVarbvalue(varvalue.isVarbvalue());
			// vvalue.setVarobjvalue(varvalue.getVarobjvalue());
			return true;
		}
	}

	// �ӱ����ڵ�,ѭ�����ϲ��ҵ�һ����Ч�ı��������
	// ֱ�����ҵ��ϼ��ڵ�Ϊnull�Ľڵ�Ϊֹ
	// �������map�����б���ֵ��д��
	private HashMap<String, VarValue> _getFirstValidMap() {
		HashMap<String, VarValue> retmap = null;
		BasicBlock blockpoint = this;
		retmap = _getLocalvarmap(blockpoint);
		// ѭ����������ǰretmap == null
		// ���ҵ�ǰ�ڵ�ָ�벻Ϊnullָ��
		while (retmap == null && blockpoint != null) {
			blockpoint = blockpoint.getParentblock();
			retmap = _getLocalvarmap(blockpoint);
		}
		return retmap;
	}

	private HashMap<String, VarValue> _getLocalvarmap(BasicBlock block) {
		// �˴���Ҫ����AbstractBlock�������õ�blocktype�����������ж�
		// ���blocktype���ض���Χ�ڣ��򱾵ص�varmap�����ɷ���
		String s1 = block.getBlocktype();
		if (s1 == null) {
			// blocktypeδ���ã���localvarmap�����á�
			return null;
		}
		if (s1 != null) {
			// �����г������в����õ�blocktype
			if (s1.equals(SmallJavaBlockConst.Expression) || s1.equals(SmallJavaBlockConst.EmptyBlock)
					|| s1.equals(SmallJavaBlockConst.WhileNode) || s1.equals(SmallJavaBlockConst.WhileCondition)
					|| s1.equals(SmallJavaBlockConst.IfConditionBlock) || s1.equals(SmallJavaBlockConst.ForBeginNode)
					|| s1.equals(SmallJavaBlockConst.ForConditionNode) || s1.equals(SmallJavaBlockConst.ForLoopNode)) {
				// ���ʽ�Լ�����������,ʹ���ϼ��ڵ�ı�����
				return null;
			}
		}
		return block.getMyvarmap();

	}

	/**
	 * MEMO:�ж��Ƿ�����Ч����
	 */
	@Override
	public boolean isValid(String varname) {
		VarValue vvalue = this.getVarValue(varname);
		if (vvalue == null) {
			return false;
		} else {
			return true;
		}
	}

	public ArrayList<MethodBlock> getMethodList() {
		return methodList;
	}

	public void setMethodList(ArrayList<MethodBlock> methodList) {
		this.methodList = methodList;
	}

	public IVarTable getClassVarTable() {
		return classVarTable;
	}

	public void setClassVarTable(IVarTable classVarTable) {
		this.classVarTable = classVarTable;
	}

	public native String toJsonStrig(Object obj) /*-{
													//alert(message);
													return JSON.stringify(obj);
													}-*/;

	public native void consoleLog(String message) /*-{
													//alert(message);
													console.log( "[BasicBlock]:" + message );
													}-*/;
	public String getVarString() {
		String s1="";
		Set set1 = this.getMyvarmap().keySet();
		Iterator iter1 = set1.iterator();
		while(iter1.hasNext()) {
			String key = iter1.next().toString();
			VarValue value = this.getMyvarmap().get(key);
			s1 += "key:"+key+"; value :" + value.toString() +"\r\n";
		}
		return s1;
	}

}
