package com.smalljava.core.eval.l4_block;

import com.smalljava.core.analyse.l5_expression.ExpressionASTAnalyse;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.commonvo.l4_block.SmallJavaBlockConst;
import com.smalljava.core.commonvo.l4_block.childblock.DOWHILEBlock;
import com.smalljava.core.commonvo.l4_block.childblock.FORBlock;
import com.smalljava.core.commonvo.l4_block.childblock.IFBlock;
import com.smalljava.core.commonvo.l4_block.childblock.MethodBlock;
import com.smalljava.core.commonvo.l4_block.childblock.WHILEBlock;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.eval.l4_block.worker_plugin.SmallJavaDOWHILEBlockEvaluator;
import com.smalljava.core.eval.l4_block.worker_plugin.SmallJavaFORBlockEvaluator;
import com.smalljava.core.eval.l4_block.worker_plugin.SmallJavaIFBlockEvaluator;
import com.smalljava.core.eval.l4_block.worker_plugin.SmallJavaWHILEBlockEvaluator;
import com.smalljava.core.eval.l5_expression.SmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.AbstractHashMapVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class SmallJavaBlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(SmallJavaBlockEvaluator.class);
	public SmallJavaBlockEvaluator() {

	}

	@SuppressWarnings("static-access")
	public boolean execute(BasicBlock child2,
				AbstractHashMapVarTableImpl vartable, 
				SmallJavaClassSupportEnv classenv,
				SmallJavaOopSupportEnv oopenv) throws Exception {
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
			
			
			SmallJavaExpressionEval expressioneval = new SmallJavaExpressionEval();
			VarValue b3 = expressioneval.eval(node, vartable, classenv,oopenv);
			logger.info("eval [" + node.getStrexpression() + "] result:" + b3);
			if (b3 != null) {
					logger.error("eval return: " + b3.toString());
				return true;
			} else {
				logger.error("eval false:" + node.getStrexpression());
				return false;
			}

		}

		for (BasicBlock child : child2.getChildren()) {
			if(child instanceof MethodBlock) {
				continue;
			}
			
			if (child instanceof DOWHILEBlock) {
				SmallJavaDOWHILEBlockEvaluator doblockeval = new SmallJavaDOWHILEBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable1 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b1 = doblockeval.execute((DOWHILEBlock) child,vartable1,classenv,oopenv);
				logger.info("doblock return" + b1);
				if (b1) {
					// continue
					continue;
				} else {
					return false;
				}

			}
			if (child instanceof WHILEBlock) {
				SmallJavaWHILEBlockEvaluator whileblockeval = new SmallJavaWHILEBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable2 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b2 = whileblockeval.execute((WHILEBlock) child,vartable2,classenv,oopenv);
				logger.info("while block return:" + b2);
				if (b2) {
					continue;
				} else {
					return false;
				}
			}
			if (child instanceof IFBlock) {
				SmallJavaIFBlockEvaluator ifeval = new SmallJavaIFBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable3 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b3 = ifeval.execute((IFBlock) child,vartable3,classenv,oopenv);
				logger.info("if block return" + b3);
				if (b3) {
					continue;
				} else {
					return false;
				}
			}
			if (child instanceof FORBlock) {
				SmallJavaFORBlockEvaluator foreval = new SmallJavaFORBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable4 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b4 = foreval.execute((FORBlock) child,vartable4,classenv,oopenv);
				logger.info("for block return" + b4);
				if (b4) {
					continue;
				} else {
					return false;
				}
			}
			//
			if (execute(child, vartable, classenv,oopenv)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
}
