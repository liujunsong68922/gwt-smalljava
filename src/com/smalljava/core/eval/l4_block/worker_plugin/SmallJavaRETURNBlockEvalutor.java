package com.smalljava.core.eval.l4_block.worker_plugin;

import com.smalljava.core.analyse.l5_expression.ExpressionASTAnalyse;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l4_block.childblock.ReturnBlock;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.eval.l5_expression.SmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class SmallJavaRETURNBlockEvalutor {

	private Logger logger = LoggerFactory.getLogger(SmallJavaRETURNBlockEvalutor.class);
	ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
	SmallJavaExpressionEval expressionEval = new SmallJavaExpressionEval();

	@SuppressWarnings("static-access")
	public boolean execute(ReturnBlock block, L4_HashMapBlockVarTableImpl vartable, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) throws Exception {
		String strcode = block.getBlockContent();
		strcode = strcode.substring(6);
		strcode = strcode.trim();
		
		logger.info("return statement:" + strcode);
		if(strcode.equals("")) {
			//直接return时的处理方法
			//不做任何处理，直接返回
			return true;
		}
		
		//将字符串转换成一个Expression定义
		//并评估计算之。
		ExpressionASTAnalyse expressionASTAnalyse = new ExpressionASTAnalyse();
		SmallJavaExpressionEval expressionEval = new SmallJavaExpressionEval();
		
		RootAST node = expressionASTAnalyse.analyse(strcode);
		if(node == null) {
			logger.error("[ERROR]analyse failed."+strcode);
		}
		
		VarValue b1 = expressionEval.eval(node, vartable, classenv,oopenv);
		if(b1 == null) {
			logger.error("[ERROR] eval failed."+strcode);
			return false;
		}else {
			logger.info("[info] return value compute ok.");
			
			//在变量表中定义一个叫return的变量
			vartable.defineVar("return", b1.getVartype());
			vartable.setVarValue("return",b1);
			return true;
		}
		

	}
}
