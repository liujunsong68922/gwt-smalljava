package com.smalljava.core.l4_block.blockvo.childblock;

import com.smalljava.core.l4_block.blockvo.BasicBlock;

/**
 * ReturnBlock在执行时将打断当前Block的执行链条
 * return执行时在return 之后，将终止block的执行
 * MEMO:需要在block的执行条件中加上额外的判断条件
 * @author liujunsong
 *
 */
public class ReturnBlock extends BasicBlock {

	public ReturnBlock(String _blocktype, String _blockcontent, BasicBlock parentblock) {
		super(_blocktype, _blockcontent, parentblock);
		// TODO Auto-generated constructor stub
	}

}
