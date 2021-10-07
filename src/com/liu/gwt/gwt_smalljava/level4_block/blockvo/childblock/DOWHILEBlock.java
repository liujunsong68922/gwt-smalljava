package com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;

/**
 * Do...while ����ζ���
 * @author liujunsong
 *
 */
public class DOWHILEBlock extends BasicBlock {
	private BasicBlock donode;
	private BasicBlock whilenode;
	
	public DOWHILEBlock(String _blockcontent,BasicBlock parentblock) {
		super(SmallJavaBlockConst.DoWhileBlock,_blockcontent,parentblock);
	}

	public void setDonode(BasicBlock donode) {
		this.donode = donode;
	}


	public BasicBlock getWhilenode() {
		return whilenode;
	}

	public void setWhilenode(BasicBlock whilenode) {
		this.whilenode = whilenode;
	}

	public BasicBlock getDonode() {
		return donode;
	}
}
