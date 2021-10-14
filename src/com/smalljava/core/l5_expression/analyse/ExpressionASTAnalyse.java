package com.smalljava.core.l5_expression.analyse;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.vo.AbstractLeafElement;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;

public class ExpressionASTAnalyse {
	private static Logger logger = LoggerFactory.getLogger(ExpressionASTAnalyse.class);
	
	/**
	 * MEMO������һ���ַ����������Ժ�õ�һ����״�ṹ
	 * @param strexpresion
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static RootAST analyse(String strexpresion) {
		ExpressionASTPluginManager manager = new ExpressionASTPluginManager();
		//ѭ�����ø������������䷵�ؽ��
		RootAST root = null;
		for(IAstPlugin plugin: manager.getPluginmap()) {
			logger.debug("call "+plugin.getClass().getSimpleName());
			root = (RootAST) plugin.analyse(strexpresion);
			if(root !=null) {
				//��ֹѭ��
				logger.info("plugin OK.");
				break;
			}
		}
		if(root == null) {
			logger.error("��ERROR�����в�����Ѿ�ִ����ϣ�����ʧ��!"+strexpresion);
			return null;
		}else {
			
			//��ʼѭ��������children
			for(RootAST child: root.getChildren()) {
				//����child������leaf�ڵ������
				//�����ӽڵ㲻�ٵݹ����
				if(child instanceof AbstractLeafElement) {
					continue;
				}
				//������child������RootAST
				if(child.getClass().getName().equals(MiddleAST.class.getName())) {
					RootAST newchild = ExpressionASTAnalyse.analyse(child.getStrexpression());
					if(newchild==null) {
						logger.error("�ӽڵ����ʧ�ܣ��������޺��ʲ��֧��!��"+child.getStrexpression()+"��");
						return null;
					}else {
						child.getChildren().add(newchild);
					}
				}
			}
			return root;
		}
	}
	
	@SuppressWarnings("static-access")
	public static void main(String args[]) {
		String s1="1+2+3+4+5+6+7+8";
		ExpressionASTAnalyse ea = new ExpressionASTAnalyse();
		RootAST ast = ea.analyse(s1);
		if(ast!=null) {
			logger.debug("---->�����ɹ�:"+ast.getUuid());
			ast.show(0);
		}else {
			logger.error("---->����ʧ�ܣ���"+s1+"��");
			
		}
	}
}
