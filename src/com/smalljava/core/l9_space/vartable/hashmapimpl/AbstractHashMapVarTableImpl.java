package com.smalljava.core.l9_space.vartable.hashmapimpl;

import java.util.HashMap;

import com.smalljava.core.common.JSONFunction;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l9_space.vartable.IVarTable;

public abstract class AbstractHashMapVarTableImpl implements IVarTable {
	private Logger logger = LoggerFactory.getLogger(AbstractHashMapVarTableImpl.class);

	/**
	 * 内部的HashMap存储，这个存储目前只存储基础类型，因此使用String来存储具体数据
	 */
	protected HashMap<String, VarValue> myvarmap = new HashMap<String, VarValue>();
	
	/**
	 * 变量表的类型
	 */
	private String vartabletypename = "";
	/**
	 * 变量表的父节点的定义
	 */
	private AbstractHashMapVarTableImpl parentVarTable = null;
	/**
	 * 是否是根的变量表节点
	 */
	private boolean rootflag = false;

	/**
	 * 构造函数，带有类型参数
	 * 
	 * @param stype
	 * @param parentnode
	 */
	public AbstractHashMapVarTableImpl(String stype, AbstractHashMapVarTableImpl parentnode) {

		this.vartabletypename = stype;
		this.parentVarTable = parentnode;
		logger.info("init,vartabletypename:"+vartabletypename);		
		if (parentnode != null) {
			// 设置为不是根节点
			this.rootflag = false;
		} else {
			// 设置为根节点
			this.rootflag = true;
		}
	}

	/**
	 * 构造函数，带有类型参数,但不带父级节点，这样这个节点就是一个根节点了
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
	 * MEMO：将不带参数的构造函数隐藏掉
	 */
	@SuppressWarnings("unused")
	private AbstractHashMapVarTableImpl() {
		
	}


	/**
	 * 根据varname从VarMapNode中获取数值，如果找不到，则返回null,
	 * 
	 * @param varname
	 * @return
	 */
	public VarValue getVarValue(String varname) {
		HashMap<String, VarValue> varmap = this.myvarmap;
		if (varmap == null) {
			logger.error("【ERROR】查找变量表失败.");
			return null;
		}

		AbstractHashMapVarTableImpl pvartable = this;
		// 循环开始
		while (pvartable.getMyvarmap() != null) {
			varmap = pvartable.getMyvarmap();

			if (varmap.containsKey(varname)) {
				// 如果varname被定义为一个本地变量，则从本地HashMap中获取其值
				VarValue varvalue = varmap.get(varname);
				return varvalue;
			}
			//指向父节点
			if(pvartable.getParentVarTable()!=null) {
				pvartable = pvartable.getParentVarTable();
			}else {
				//跳出循环
				break;
			}
		}

		// 循环查找，指到找不到varname,则返回null代表找不到这个变量
		return null;
	}

	/**
	 * 变量赋值，首先找到这个变量，然后再赋值
	 * 
	 * @param varname
	 * @param varvalue
	 * @return
	 */
	public boolean setVarValue(String varname, VarValue varvalue) {
		HashMap<String, VarValue> varmap = this.myvarmap;
		if (varmap == null) {
			logger.error("查找变量表失败.");
			return false;
		}
		if (varname == null || varname.length() == 0) {
			logger.error("setVarValue程序代码有误，变量名为空或者为空字符串");
			return false;
		}
		if (varvalue == null) {
			logger.error("setVarValue程序代码有误，变量设置参数为null");
			return false;
		}
		VarValue vvalue = this.getVarValue(varname);
		if (vvalue == null) {
			logger.error("setVarValue程序执行有误，没有找到变量定义：" + varname);
			return false;
		} else {
			logger.info("从变量表中查找变量成功."+varname);
			vvalue.setVarname(varname);
			vvalue.setVartype(varvalue.getVartype());
			vvalue.setVarsvalue(varvalue.getVarsvalue());
			return true;
		}
	}

	/**
	 * 【变量设置】算法描述：先找到第一个可用的变量表，在这个变量表中定义变量值
	 * 
	 * @param varname
	 * @param vartype
	 * @return
	 */
	public boolean defineVar(String varname, String vartype) {
		HashMap<String, VarValue> varmap = this.myvarmap;
		if (varmap == null) {
			logger.error("查找变量表失败.");
			return false;
		}
		if (varmap.containsKey(varname)) {
			logger.error("程序代码有误，此处有重复定义的变量名:" + varname);
			return false;
		}
		if (vartype == null) {
			logger.error("程序代码有误，变量定义时类型为空." + varname);
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
			// 类型为对象时，svalue代表映射地址,""就代表是一个null值
			varvalue.setVarsvalue("");
		}
		//写入当前节点的变量表
		varmap.put(varname, varvalue);
		return true;
	}

	/**
	 * MEMO:判断是否是有效变量
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
