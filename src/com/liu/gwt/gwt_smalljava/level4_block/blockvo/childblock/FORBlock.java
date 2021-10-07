package com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock;

import com.liu.gwt.gwt_smalljava.level4_block.SmallJavaBlockConst;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;

/**|
 * While ����鶨��
 * @author liujunsong
 *
 */
public class FORBlock extends BasicBlock {
	/**
	 * ��ʼ�ڵ�
	 */
	private BasicBlock beginNode;
	/**
	 * ѭ�����������ڵ�
	 */
	private BasicBlock forconditionNode;
	/**
	 * ѭ��ִ�нڵ�
	 */
	private BasicBlock loopNode;

	/**
	 * for����ѭ����,���ѭ����Ҳ��һ��Block
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
