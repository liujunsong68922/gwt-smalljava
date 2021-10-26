package com.smalljava.core.l4_block.blockvo.childblock;

import java.util.HashMap;

import com.smalljava.core.l4_block.blockvo.BasicBlock;

public class MethodBlock extends BasicBlock {
	private String returntype;
	private String methodname;
	private String methodcontent;
	private HashMap<String,String> args= new HashMap<String,String>();
	
	public MethodBlock(String _blocktype, String _blockcontent, BasicBlock parentblock) {
		super(_blocktype, _blockcontent, parentblock);
		// TODO Auto-generated constructor stub
	}

	public String getMethodname() {
		return methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	public String getMethodcontent() {
		return methodcontent;
	}

	public void setMethodcontent(String methodcontent) {
		this.methodcontent = methodcontent;
	}

	public HashMap<String, String> getArgs() {
		return args;
	}

	public void setArgs(HashMap<String, String> args) {
		this.args = args;
	}

	public String getReturntype() {
		return returntype;
	}

	public void setReturntype(String returntype) {
		this.returntype = returntype;
	}

	
	
}
