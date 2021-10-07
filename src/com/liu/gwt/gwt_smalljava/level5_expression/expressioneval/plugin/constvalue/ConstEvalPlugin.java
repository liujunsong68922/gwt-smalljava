package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.constvalue;

import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.constvalue.ConstNumberElement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.constvalue.ConstStringElement;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

//import com.liu.smalljavav2.common.VarValue;
//import com.liu.smalljavav2.level5_expression.expressioneval.IExpressionEval;
//import com.liu.smalljavav2.level5_expression.expressionvo.RootAST;
//import com.liu.smalljavav2.level5_expression.expressionvo.constvalue.ConstNumberElement;
//import com.liu.smalljavav2.level5_expression.expressionvo.constvalue.ConstStringElement;
//import com.liu.smalljavav2.space.IClassTable;
//import com.liu.smalljavav2.space.IVarTable;

public class ConstEvalPlugin implements IExpressionEval {

	/**
	 * ���㳣���ı��ʽ,����null��ʾδ����
	 * 
	 */
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if(root == null) {
			return null;
		}
		//������ֵ����
		if(root instanceof ConstNumberElement) {
			//ǿ��ת��
			ConstNumberElement const1 = (ConstNumberElement)root;
			//����һ���ַ�����ʽ�����ݣ������\"��ͷ�����ַ�������������ֵ
			VarValue v1 = new VarValue();
			v1.setVarname("");
			v1.setVartype("double");
			v1.setVarsvalue(const1.getDatavalue());
			return v1;
		}
		//�����ַ�������
		if(root instanceof ConstStringElement) {
			//ǿ��ת��
			ConstNumberElement const2 = (ConstNumberElement)root;
			//����һ���ַ�����ʽ�����ݣ������\"��ͷ�����ַ�������������ֵ
			VarValue v1 = new VarValue();
			v1.setVarname("");
			v1.setVartype("String");
			v1.setVarsvalue(const2.getDatavalue());
			return v1;			
		}
		
		//�������������
		return null;
	}
	
}
