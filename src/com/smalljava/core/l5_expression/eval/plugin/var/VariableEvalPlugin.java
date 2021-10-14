package com.smalljava.core.l5_expression.eval.plugin.var;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.var.VarDataElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * 变量计算的插件，此处需要访问变量表，如果找不到，则返回错误
 * @author liujunsong
 *
 */
public class VariableEvalPlugin implements IExpressionEval{
	private Logger logger = LoggerFactory.getLogger(VariableEvalPlugin.class);
	
	/**
	 * MEMO：评估结果出现null,说明计算失败
	 * MEMO：正常情况null值会返回一个VarValue,其中的svalue值为null或者""代表空指针
	 */
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if(root == null) {
			return null;
		}
		
		//变量定义
		if(root instanceof VarDataElement) {
			VarDataElement varele = (VarDataElement) root;
			
			String varname = varele.getVarname();
			if(! vartable.isValid(varname)) {
				logger.error("变量名解析无效:"+varname);
				return null;
			}
			VarValue varvalue = vartable.getVarValue(varname);
			return varvalue;
		}
		
		return null;
	}

}
