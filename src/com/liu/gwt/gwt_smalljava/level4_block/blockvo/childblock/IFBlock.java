package com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;

/**
 * MEMO:IF����飬���ܰ���ELSE����
 * @author liujunsong
 *
 */
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

