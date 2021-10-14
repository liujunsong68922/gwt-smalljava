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
	// ���صı�����
	//

	// Ĭ�ϵĹ��캯��
	public BlockEvaluator() {

	}

	/**
	 * ��rootblock���м�������
	 * 
	 * @param child2
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public boolean execute(BasicBlock child2,AbstractHashMapVarTableImpl vartable, IClassTable classtable) throws Exception {

		
		// �����ж����node��û��child
		if (child2.getChildren() == null || child2.getChildren().size() == 0) {
			// �սڵ㲻�÷���
			if (child2.getBlockContent() != null && child2.getBlockContent().equals("")) {
				logger.info("block���㣬�սڵ㲻�ü���");
				return true;
			}

			// ��ע�ڵ㲻�ô���ֱ�ӷ���
			if (child2.getBlocktype() != null 
					&& child2.getBlocktype().equals(SmallJavaBlockConst.SingleLineMemo)) {
				logger.info("��ע�ڵ㲻�ü���");
				return true;
			}

			// ���б�ע�ڵ㲻�ô���ֱ�ӷ���
			if (child2.getBlocktype() != null 
					&& child2.getBlocktype().equals(SmallJavaBlockConst.MultiLineMemo)) {
				logger.info("���б�ע�ڵ㲻�ü���");
				return true;
			}
			
			

			// ����һ��û���¼��ڵ�Ľڵ㣬�Ѿ�����ײ�Ľڵ���
			// ������ڵ�ת����һ��ASTTree
			//ASTTreeNode node = new ASTTreeNode(child2.getBlockContent(), 0);
			ExpressionASTAnalyse expanalyse = new ExpressionASTAnalyse();
			RootAST node = expanalyse.analyse(child2.getBlockContent());
			if(node==null) {
				logger.info("���ʽ����ʧ��."+child2.getBlockContent());
				return false;
			}else {
				logger.info("���ʽ�����ɹ�:"+child2.getBlockContent());
			}
			node.show(0);
			//ClassTable classtable = new ClassTable();
			//boolean b3 = node.eval(child2,classtable);
			
			
			ExpressionEval expressioneval = new ExpressionEval();
			VarValue b3 = expressioneval.eval(node, vartable, classtable);
			logger.info("eval [" + node.getStrexpression() + "]��������" + b3);
			if (b3 != null) {
					logger.error("��������" + b3.toString());
				return true;
			} else {
				logger.error("����ʧ�ܣ�" + node.getStrexpression());
				return false;
			}

		}

		// �����BaseBlockNode�Ľڵ����ͣ���ô��˳��ִ�и��ӽڵ�
		// ������ͬ���͵Ľڵ��ִ�з������ڶ�Ӧ���͵Ľڵ��н��ж���
		for (BasicBlock child : child2.getChildren()) {
			// child.setParent(this);
			// child.execute();
			// ���child�� MethodBlock,���˳�ִ��
			if(child instanceof MethodBlock) {
				//MethodBlock��������Ϊһ��children��ִ��
				//��Ҫ��Ϊһ�����ڵ�������
				//MethodBlock��Ϊһ��Child��ʱ�򲻿�ִ��
				continue;
			}
			
			if (child instanceof DOWHILEBlock) {
				DOWHILEBlockEvaluator doblockeval = new DOWHILEBlockEvaluator();
				L4_HashMapBlockVarTableImpl vartable1 = new L4_HashMapBlockVarTableImpl("",vartable);
				boolean b1 = doblockeval.execute((DOWHILEBlock) child,vartable1,classtable);
				logger.info("doblock ��������" + b1);
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
				logger.info("while block ������:" + b2);
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
				logger.info("if block ������" + b3);
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
				logger.info("for block ������" + b4);
				if (b4) {
					continue;
				} else {
					return false;
				}
			}
			//��ʱ��������childʹ��ͳһ�ı�����
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
//		//��List����ɾ��VARTABLE�ڵ�
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
