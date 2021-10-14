package com.smalljava.core.l5_expression.eval.plugin.constvalue;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.constvalue.ConstNumberElement;
import com.smalljava.core.l5_expression.vo.constvalue.ConstStringElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class ConstEvalPlugin implements IExpressionEval {

	/**
	 * ���㳣���ı��ʽ,����null��ʾδ����
	 * 
	 */
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
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
