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
 * ��������Ĳ�����˴���Ҫ���ʱ���������Ҳ������򷵻ش���
 * @author liujunsong
 *
 */
public class VariableEvalPlugin implements IExpressionEval{
	private Logger logger = LoggerFactory.getLogger(VariableEvalPlugin.class);
	
	/**
	 * MEMO�������������null,˵������ʧ��
	 * MEMO���������nullֵ�᷵��һ��VarValue,���е�svalueֵΪnull����""�����ָ��
	 */
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if(root == null) {
			return null;
		}
		
		//��������
		if(root instanceof VarDataElement) {
			VarDataElement varele = (VarDataElement) root;
			
			String varname = varele.getVarname();
			if(! vartable.isValid(varname)) {
				logger.error("������������Ч:"+varname);
				return null;
			}
			VarValue varvalue = vartable.getVarValue(varname);
			return varvalue;
		}
		
		return null;
	}

}
