package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractLeafElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.MiddleAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;

public class ExpressionASTAnalyse {
	//private static Logger logger = LoggerFactory.getLogger(ExpressionASTAnalyse.class);
	
	/**
	 * MEMO������һ���ַ����������Ժ�õ�һ����״�ṹ
	 * @param strexpresion
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static RootAST analyse(String strexpresion) {
		
		ExpressionASTPluginManager manager = new ExpressionASTPluginManager();
	
		RootAST root = null;
		for(IAstPlugin plugin: manager.getPluginmap()) {
			//logger.debug("call "+plugin.getClass().getSimpleName());
			consoleLog("call "+plugin.getClass().getSimpleName());
			root = (RootAST) plugin.analyse(strexpresion);
			if(root !=null) {
				consoleLog("表达式分析成功，跳出循环");
				break;
			}
		}
		if(root == null) {
			//logger.error("��ERROR�����в�����Ѿ�ִ����ϣ�����ʧ��!"+strexpresion);
			consoleLog("表达式解析失败.{"+strexpresion+"}");
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
						//logger.error("�ӽڵ����ʧ�ܣ��������޺��ʲ��֧��!��"+child.getStrexpression()+"��");
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
		String s1="int a = b + 1 * 5555 ";
		ExpressionASTAnalyse ea = new ExpressionASTAnalyse();
		RootAST ast = ea.analyse(s1);
		if(ast!=null) {
			//logger.debug("---->�����ɹ�:"+ast.getUuid());
			ast.show(0);
		}else {
			//logger.error("---->����ʧ�ܣ���"+s1+"��");
			
		}
	}
	
	public static native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[ExpressionASTAnalyse]:" + message );
	}-*/;


}
