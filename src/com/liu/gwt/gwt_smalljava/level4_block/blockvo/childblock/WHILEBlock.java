package com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;

/**|
 * While ����鶨��
 * @author liujunsong
 *
 */
public class WHILEBlock extends BasicBlock {
//	private String whilecondition1;
	private BasicBlock whilecondition;
	private BasicBlock whileloopnode;
	
	public WHILEBlock(String _blockcontent,BasicBlock parentblock) {
		super(SmallJavaBlockConst.WhileBlock,_blockcontent,parentblock);
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
