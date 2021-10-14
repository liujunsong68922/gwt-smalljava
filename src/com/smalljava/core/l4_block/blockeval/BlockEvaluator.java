package com.smalljava.core.l4_block.blockeval;

import java.util.ArrayList;
import java.util.List;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l4_block.SmallJavaBlockConst;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l4_block.blockvo.childblock.DOWHILEBlock;
import com.smalljava.core.l4_block.blockvo.childblock.FORBlock;
import com.smalljava.core.l4_block.blockvo.childblock.IFBlock;
import com.smalljava.core.l4_block.blockvo.childblock.MethodBlock;
import com.smalljava.core.l4_block.blockvo.childblock.WHILEBlock;
import com.smalljava.core.l5_expression.analyse.ExpressionASTAnalyse;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.AbstractHashMapVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class BlockEvaluator {
	private Logger logger = LoggerFactory.getLogger(BlockEvaluator.class);
	// 本地的变量表
	//

	// 默认的构造函数
	public BlockEvaluator() {

	}

	/**
	 * 对rootblock进行计算评估
	 * 
	 * @param child2
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public boolean execute(BasicBlock child2,AbstractHashMapVarTableImpl vartable, IClassTable classtable) throws Exception {

		
		// 首先判断这个node有没有child
		if (child2.getChildren() == null || child2.getChildren().size() == 0) {
			// 空节点不用返回
			if (child2.getBlockContent() != null && child2.getBlockContent().equals("")) {
				logger.info("block计算，空节点不用计算");
				return true;
			}

			// 备注节点不用处理，直接返回
			if (child2.getBlocktype() != null 
					&& child2.getBlocktype().equals(SmallJavaBlockConst.SingleLineMemo)) {
				logger.info("备注节点不用计算");
				return true;
			}

			// 多行备注节点不用处理，直接返回
			if (child2.getBlocktype() != null 
					&& child2.getBlocktype().equals(SmallJavaBlockConst.MultiLineMemo)) {
				logger.info("多行备注节点不用计算");
				return true;
			}
			
			

			// 这是一个没有下级节点的节点，已经是最底层的节点了
			// 把这个节点转换成一个ASTTree
			//ASTTreeNode node = new ASTTreeNode(child2.getBlockContent(), 0);
			ExpressionASTAnalyse expanalyse = new ExpressionASTAnalyse();
			RootAST node = expanalyse.analyse(child2.getBlockContent());
			if(node==null) {
				logger.info("表达式解析失败."+child2.getBlockContent());
				return false;
			}else {
				logger.info("表达式解析成功:"+child2.getBlockContent());
			}
			node.show(0);
			//ClassTable classtable = new ClassTable();
			//boolean b3 = node.eval(child2,classtable);
			
			
			ExpressionEval expressioneval = new ExpressionEval();
			VarValue b3 = expressioneval.eval(node, vartable, classtable);
			logger.info("eval [" + node.getStrexpression() + "]计算结果：" + b3);
			if (b3 != null) {
					logger.error("计算结果：" + b3.toString());
				return true;
			} else {
				logger.error("计算失败！" + node.getStrexpression());
				return false;
			}

		}

		// 如果是BaseBlockNode的节点类型，那么就顺序执行各子节点
		// 其他不同类型的节点的执行方法，在对应类型的节点中进行定义
		for (BasicBlock child : child2.getChildren()) {
			// child.setParent(this);
			// child.execute();
			// 如果child是 MethodBlock,则退出执行
			if(child instanceof MethodBlock) {
				//MethodBlock不可以作为一个children来执行
				//需要作为一个主节点来调用
				//MethodBlock作为一个Child的时候不可执行
				continue;
			}
			
			if (child instanceof DOWHILEBlock) {
				DOWHILEBlockEvaluator doblockeval = new DOWHILEBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable1 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b1 = doblockeval.execute((DOWHILEBlock) child,vartable1,classtable);
				logger.info("doblock 计算结果：" + b1);
				if (b1) {
					// 子节点执行成功，继续
					continue;
				} else {
					return false;
				}

			}
			if (child instanceof WHILEBlock) {
				WHILEBlockEvaluator whileblockeval = new WHILEBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable2 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b2 = whileblockeval.execute((WHILEBlock) child,vartable2,classtable);
				logger.info("while block 计算结果:" + b2);
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
				logger.info("if block 计算结果" + b3);
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
				logger.info("for block 计算结果" + b4);
				if (b4) {
					continue;
				} else {
					return false;
				}
			}
			//暂时假设所有child使用统一的变量表
			if (execute(child, vartable, classtable)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

//	@SuppressWarnings("unused")
//	private List<Element> getChildren(Element root){
//		@SuppressWarnings("rawtypes")
//		List list1 = root.elements();
//		List<Element> retlist = new ArrayList<Element>();
//		//从List里面删除VARTABLE节点
//		for(Object obj:list1) {
//			if(obj instanceof Element) {
//				Element element = (Element)obj;
//				if(! element.getName().equals("VATTABLE")) {
//					retlist.add(element);
//				}
//			}
//		}
//		return retlist;
//	}
}
