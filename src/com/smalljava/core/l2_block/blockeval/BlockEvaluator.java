package com.smalljava.core.l2_block.blockeval;

import java.util.ArrayList;
import java.util.List;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l2_block.SmallJavaBlockConst;
import com.smalljava.core.l2_block.blockvo.BasicBlock;
import com.smalljava.core.l2_block.blockvo.childblock.DOWHILEBlock;
import com.smalljava.core.l2_block.blockvo.childblock.FORBlock;
import com.smalljava.core.l2_block.blockvo.childblock.IFBlock;
import com.smalljava.core.l2_block.blockvo.childblock.MethodBlock;
import com.smalljava.core.l2_block.blockvo.childblock.WHILEBlock;
import com.smalljava.core.l3_expression.analyse.ExpressionASTAnalyse;
import com.smalljava.core.l3_expression.eval.ExpressionEval;
import com.smalljava.core.l3_expression.vo.RootAST;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.AbstractHashMapVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class BlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(BlockEvaluator.class);
	public BlockEvaluator() {

	}

	@SuppressWarnings("static-access")
	public boolean execute(BasicBlock child2,AbstractHashMapVarTableImpl vartable, IClassTable classtable) throws Exception {
		if (child2.getChildren() == null || child2.getChildren().size() == 0) {
			if (child2.getBlockContent() != null && child2.getBlockContent().equals("")) {
				logger.info("blockcontent is empty.");
				return true;
			}

			if (child2.getBlocktype() != null 
					&& child2.getBlocktype().equals(SmallJavaBlockConst.SingleLineMemo)) {
				logger.info("single line memo");
				return true;
			}

			// 
			if (child2.getBlocktype() != null 
					&& child2.getBlocktype().equals(SmallJavaBlockConst.MultiLineMemo)) {
				logger.info(" this is multiline memo");
				return true;
			}
			
			ExpressionASTAnalyse expanalyse = new ExpressionASTAnalyse();
			RootAST node = expanalyse.analyse(child2.getBlockContent());
			if(node==null) {
				logger.info("expression analyse false ."+child2.getBlockContent());
				return false;
			}else {
				logger.info("expression analyse true :"+child2.getBlockContent());
			}
			node.show(0);
			//ClassTable classtable = new ClassTable();
			//boolean b3 = node.eval(child2,classtable);
			
			
			ExpressionEval expressioneval = new ExpressionEval();
			VarValue b3 = expressioneval.eval(node, vartable, classtable);
			logger.info("eval [" + node.getStrexpression() + "]��������" + b3);
			if (b3 != null) {
					logger.error("eval return" + b3.toString());
				return true;
			} else {
				logger.error("eval false" + node.getStrexpression());
				return false;
			}

		}

		for (BasicBlock child : child2.getChildren()) {
			if(child instanceof MethodBlock) {
				continue;
			}
			
			if (child instanceof DOWHILEBlock) {
				DOWHILEBlockEvaluator doblockeval = new DOWHILEBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable1 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b1 = doblockeval.execute((DOWHILEBlock) child,vartable1,classtable);
				logger.info("doblock return" + b1);
				if (b1) {
					// �ӽڵ�ִ�гɹ�������
					continue;
				} else {
					return false;
				}

			}
			if (child instanceof WHILEBlock) {
				WHILEBlockEvaluator whileblockeval = new WHILEBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable2 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b2 = whileblockeval.execute((WHILEBlock) child,vartable2,classtable);
				logger.info("while block return:" + b2);
				if (b2) {
					continue;
				} else {
					return false;
				}
			}
			if (child instanceof IFBlock) {
				IFBlockEvaluator ifeval = new IFBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable3 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b3 = ifeval.execute((IFBlock) child,vartable3,classtable);
				logger.info("if block return" + b3);
				if (b3) {
					continue;
				} else {
					return false;
				}
			}
			if (child instanceof FORBlock) {
				FORBlockEvaluator foreval = new FORBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable4 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b4 = foreval.execute((FORBlock) child,vartable4,classtable);
				logger.info("for block return" + b4);
				if (b4) {
					continue;
				} else {
					return false;
				}
			}
			//
			if (execute(child, vartable, classtable)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
}