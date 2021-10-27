package com.smalljava.core.commonvo.l4_block.childblock;

import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.commonvo.l4_block.SmallJavaBlockConst;

public class WHILEBlock extends BasicBlock {
	private BasicBlock whilecondition;
	private BasicBlock whileloopnode;

	public WHILEBlock(String _blockcontent, BasicBlock parentblock) {
		super(SmallJavaBlockConst.WhileBlock, _blockcontent, parentblock);
	}

	public BasicBlock getWhilecondition() {
		return whilecondition;
	}

	public BasicBlock getWhileloopnode() {
		return whileloopnode;
	}

	public void setWhileloopnode(BasicBlock whileloopnode) {
		this.whileloopnode = whileloopnode;
	}

	public void setWhilecondition(BasicBlock whilecondition) {
		this.whilecondition = whilecondition;
	}
}
