package com.smalljava.core.l4_block.blockvo.childblock;

import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;

/**|
 * While 代码块定义
 * @author liujunsong
 *
 */
public class FORBlock extends BasicBlock {
	/**
	 * 开始节点
	 */
	private BasicBlock beginNode;
	/**
	 * 循环结束条件节点
	 */
	private BasicBlock forconditionNode;
	/**
	 * 循环执行节点
	 */
	private BasicBlock loopNode;

	/**
	 * for语句的循环体,这个循环体也是一个Block
	 */
	private BasicBlock forloopBlock;

	public FORBlock(String _blockcontent,BasicBlock parentblock) {
		super(SmallJavaBlockConst.ForBlock,_blockcontent, parentblock);
	}

	public void setBeginNode(BasicBlock beginNode) {
		this.beginNode = beginNode;
		
	}

	public void setLoopNode(BasicBlock loopNode) {
		this.loopNode = loopNode;
		
	}


	public BasicBlock getForconditionNode() {
		return forconditionNode;
	}

	public void setForconditionNode(BasicBlock forconditionNode) {
		this.forconditionNode = forconditionNode;
	}

	public BasicBlock getForloopBlock() {
		return forloopBlock;
	}

	public void setForloopBlock(BasicBlock forloopBlock) {
		this.forloopBlock = forloopBlock;
	}

	public BasicBlock getBeginNode() {
		return beginNode;
	}

	public BasicBlock getLoopNode() {
		return loopNode;
	}
	

}
