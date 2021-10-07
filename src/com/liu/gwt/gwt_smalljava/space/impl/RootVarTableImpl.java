package com.liu.gwt.gwt_smalljava.space.impl;

import java.util.HashMap;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

public class RootVarTableImpl implements IVarTable {

	private HashMap<String, VarValue> myvarmap = new HashMap<String, VarValue>();

	public VarValue getVarValue(String varname) {
		HashMap<String, VarValue> varmap = this.myvarmap;
		if (varmap == null) {
			System.out.println("��ERROR�����ұ�����ʧ��.");
			return null;
		}

		if (varmap.containsKey(varname)) {
			VarValue varvalue = varmap.get(varname);
			return varvalue;
		} else {
			return null;
		}
	}

	public boolean setVarValue(String varname, VarValue varvalue) {
		HashMap<String, VarValue> varmap = this.myvarmap;
		if (varmap == null) {
			System.out.println("���ұ�����ʧ��.");
			return false;
		}
		if (varname == null || varname.length() == 0) {
			System.out.println("setVarValue����������󣬱�����Ϊ�ջ���Ϊ���ַ���");
			return false;
		}
		if (varvalue == null) {
			System.out.println("setVarValue����������󣬱������ò���Ϊnull");
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

	public boolean defineVar(String varname, String vartype) {
		HashMap<String, VarValue> varmap = this.myvarmap;
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

	@Override
	public boolean isValid(String varname) {
		VarValue vvalue = this.getVarValue(varname);
		if (vvalue == null) {
			return false;
		} else {
			return true;
		}
	}

	public HashMap<String, VarValue> getMyvarmap() {
		return myvarmap;
	}
}
