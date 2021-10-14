package com.smalljava.core.l5_expression.analyse;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.vo.AbstractLeafElement;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;

public class ExpressionASTAnalyse {
	private static Logger logger = LoggerFactory.getLogger(ExpressionASTAnalyse.class);
	
	/**
	 * MEMO：输入一个字符串，分析以后得到一个树状结构
	 * @param strexpresion
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static RootAST analyse(String strexpresion) {
		ExpressionASTPluginManager manager = new ExpressionASTPluginManager();
		//循环调用各个插件，检查其返回结果
		RootAST root = null;
		for(IAstPlugin plugin: manager.getPluginmap()) {
			logger.debug("call "+plugin.getClass().getSimpleName());
			root = (RootAST) plugin.analyse(strexpresion);
			if(root !=null) {
				//终止循环
				logger.info("plugin OK.");
				break;
			}
		}
		if(root == null) {
			logger.error("【ERROR】所有插件均已经执行完毕，分析失败!"+strexpresion);
			return null;
		}else {
			
			//开始循环调用其children
			for(RootAST child: root.getChildren()) {
				//跳过child本身是leaf节点的类型
				//这类子节点不再递归调用
				if(child instanceof AbstractLeafElement) {
					continue;
				}
				//如果这个child本身是RootAST
				if(child.getClass().getName().equals(MiddleAST.class.getName())) {
					RootAST newchild = ExpressionASTAnalyse.analyse(child.getStrexpression());
					if(newchild==null) {
						logger.error("子节点解析失败，请检查有无合适插件支持!【"+child.getStrexpression()+"】");
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
			logger.debug("---->分析成功:"+ast.getUuid());
			ast.show(0);
		}else {
			logger.error("---->分析失败！【"+s1+"】");
			
		}
	}
}
