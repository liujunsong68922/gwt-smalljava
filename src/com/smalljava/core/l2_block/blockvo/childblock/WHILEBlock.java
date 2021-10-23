package com.smalljava.core.l2_block.blockvo.childblock;

import com.smalljava.core.l2_block.SmallJavaBlockConst;
import com.smalljava.core.l2_block.blockvo.BasicBlock;

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