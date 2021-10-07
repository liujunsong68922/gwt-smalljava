package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.var;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarDataElement;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

/**
 * ��������Ĳ�����˴���Ҫ���ʱ���������Ҳ������򷵻ش���
 * @author liujunsong
 *
 */
public class VariableEvalPlugin implements IExpressionEval{

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
				System.out.println("������������Ч:"+varname);
				return null;
			}
			VarValue varvalue = vartable.getVarValue(varname);
			return varvalue;
		}
		
		return null;
	}

}
