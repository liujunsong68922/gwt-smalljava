package com.smalljava.core.l9_space.vartable.hashmapimpl;

import java.util.HashMap;

import com.smalljava.core.common.JSONFunction;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l9_space.vartable.IVarTable;

public abstract class AbstractHashMapVarTableImpl implements IVarTable {
	private Logger logger = LoggerFactory.getLogger(AbstractHashMapVarTableImpl.class);

	protected HashMap<String, VarValue> myvarmap = new HashMap<String, VarValue>();
	
	private String vartabletypename = "";
	private AbstractHashMapVarTableImpl parentVarTable = null;
	private boolean rootflag = false;

	public AbstractHashMapVarTableImpl(String stype, AbstractHashMapVarTableImpl parentnode) {

		this.vartabletypename = stype;
		this.parentVarTable = parentnode;
		logger.info("init,vartabletypename:"+vartabletypename);		
		if (parentnode != null) {
			// 
			this.rootflag = false;
		} else {
			// 
			this.rootflag = true;
		}
	}

	/**
	 * ���캯�����������Ͳ���,�����������ڵ㣬��������ڵ����һ�����ڵ���
	 * 
	 * @param stype
	 * @param parentnode
	 */
	public AbstractHashMapVarTableImpl(String stype) {
		this.vartabletypename = stype;
		this.parentVarTable = null;
		this.rootflag = true;
	}
	
	/**
	 * MEMO�������������Ĺ��캯�����ص�
	 */
	@SuppressWarnings("unused")
	private AbstractHashMapVarTableImpl() {
		
	}


	/**
	 * ����varname��VarMapNode�л�ȡ��ֵ������Ҳ������򷵻�null,
	 * 
	 * @param varname
	 * @return
	 */
	public VarValue getVarValue(String varname) {
		HashMap<String, VarValue> varmap = this.myvarmap;
		if (varmap == null) {
			logger.error("��ERROR�����ұ�����ʧ��.");
			return null;
		}

		AbstractHashMapVarTableImpl pvartable = this;
		// ѭ����ʼ
		while (pvartable.getMyvarmap() != null) {
			varmap = pvartable.getMyvarmap();

			if (varmap.containsKey(varname)) {
				// ���varname������Ϊһ�����ر�������ӱ���HashMap�л�ȡ��ֵ
				VarValue varvalue = varmap.get(varname);
				return varvalue;
			}
			//ָ�򸸽ڵ�
			if(pvartable.getParentVarTable()!=null) {
				pvartable = pvartable.getParentVarTable();
			}else {
				//����ѭ��
				break;
			}
		}

		// ѭ�����ң�ָ���Ҳ���varname,�򷵻�null�����Ҳ����������
		return null;
	}

	/**
	 * ������ֵ�������ҵ����������Ȼ���ٸ�ֵ
	 * 
	 * @param varname
	 * @param varvalue
	 * @return
	 */
	public boolean setVarValue(String varname, VarValue varvalue) {
		HashMap<String, VarValue> varmap = this.myvarmap;
		if (varmap == null) {
			logger.error("[ERROR]varmap is null");
			return false;
		}
		if (varname == null || varname.length() == 0) {
			logger.error("setVarValue����������󣬱�����Ϊ�ջ���Ϊ���ַ���");
			return false;
		}
		if (varvalue == null) {
			logger.error("setVarValue����������󣬱������ò���Ϊnull");
			return false;
		}
		VarValue vvalue = this.getVarValue(varname);
		if (vvalue == null) {
			logger.error("setVarValue����ִ������û���ҵ��������壺" + varname);
			return false;
		} else {
			logger.info("�ӱ������в��ұ����ɹ�."+varname);
			vvalue.setVarname(varname);
			vvalue.setVartype(varvalue.getVartype());
			vvalue.setVarsvalue(varvalue.getVarsvalue());
			return true;
		}
	}

	/**
	 * ���������á��㷨���������ҵ���һ�����õı�����������������ж������ֵ
	 * 
	 * @param varname
	 * @param vartype
	 * @return
	 */
	public boolean defineVar(String varname, String vartype) {
		HashMap<String, VarValue> varmap = this.myvarmap;
		if (varmap == null) {
			logger.error("[error] varmap is null");
			return false;
		}
		if (varmap.containsKey(varname)) {
			logger.error("[error] varmap hava contain var:" + varname);
			return false;
		}
		if (vartype == null) {
			logger.error("����������󣬱�������ʱ����Ϊ��." + varname);
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
		//д�뵱ǰ�ڵ�ı�����
		varmap.put(varname, varvalue);
		return true;
	}

	/**
	 * MEMO:check is valid varname
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

	public HashMap<String, VarValue> getMyvarmap() {
		return myvarmap;
	}

	public AbstractHashMapVarTableImpl getParentVarTable() {
		return parentVarTable;
	}

	public void setParentVarTable(AbstractHashMapVarTableImpl parentVarTable) {
		this.parentVarTable = parentVarTable;
	}

	public boolean isRootflag() {
		return rootflag;
	}

	public void setRootflag(boolean rootflag) {
		this.rootflag = rootflag;
	}

	@Override
	public String toJSONString() {
		JSONFunction jsonfunction = new JSONFunction();
		String s1=jsonfunction.hashmapToJsonString(this.myvarmap);
		return s1;
	}



	

}
