package com.smalljava.core.l5_expression.analyse;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.vo.AbstractLeafElement;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;

public class ExpressionASTAnalyse {
	private static Logger logger = LoggerFactory.getLogger(ExpressionASTAnalyse.class);
	
	@SuppressWarnings("static-access")
	public static RootAST analyse(String strexpresion) {
		ExpressionASTPluginManager manager = new ExpressionASTPluginManager();
		RootAST root = null;
		for(IAstPlugin plugin: manager.getPluginmap()) {
			logger.debug("call "+plugin.getClass().getSimpleName());
			root = (RootAST) plugin.analyse(strexpresion);
			if(root !=null) {
				logger.info("plugin OK.");
				break;
			}
		}
		if(root == null) {
			logger.error("[error] not find right plugin !"+strexpresion);
			return null;
		}else {
			
			for(RootAST child: root.getChildren()) {
				if(child instanceof AbstractLeafElement) {
					continue;
				}
				if(child.getClass().getName().equals(MiddleAST.class.getName())) {
					RootAST newchild = ExpressionASTAnalyse.analyse(child.getStrexpression());
					if(newchild==null) {
						logger.error("[error] middleast analyse failed."+child.getStrexpression()+"��");
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
			logger.debug("---->ast uuid:"+ast.getUuid());
			ast.show(0);
		}else {
			logger.error("---->analyse failed."+s1+"");
			
		}
	}
}
