package com.smalljava.core.commonvo.l4_block.childblock;

import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.commonvo.l4_block.SmallJavaBlockConst;


public class IFBlock extends BasicBlock {
	
	private BasicBlock ifConditionblock;
	private BasicBlock iftrueblock;
	private BasicBlock ifelseblock;
	
	public IFBlock(String _blockcontent,BasicBlock parentblock) {
		super(SmallJavaBlockConst.Ifblock,_blockcontent,parentblock);
	}
	public BasicBlock getIfConditionblock() {
		return ifConditionblock;
	}
	public void setIfConditionblock(BasicBlock ifConditionblock) {
		this.ifConditionblock = ifConditionblock;
	}
	public BasicBlock getIftrueblock() {
		return iftrueblock;
	}
	public void setIftrueblock(BasicBlock iftrueblock) {
		this.iftrueblock = iftrueblock;
	}
	public BasicBlock getIfelseblock() {
		return ifelseblock;
	}
	public void setIfelseblock(BasicBlock ifelseblock) {
		this.ifelseblock = ifelseblock;
	}


}

