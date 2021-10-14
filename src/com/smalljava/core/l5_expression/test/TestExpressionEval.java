package com.smalljava.core.l5_expression.test;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l5_expression.analyse.ExpressionASTAnalyse;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.classtable.impl.ClassTableImpl;
import com.smalljava.core.l9_space.vartable.IVarTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassStaticVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L3_HashMapMethodInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class TestExpressionEval {

	@SuppressWarnings("static-access")
	public static void main(String args[]) {
		//String s1="{int i=0}";
		//BasicBlock closedblock = new BasicBlock("",s1,null);
		//BlockAnalyse ba = new BlockAnalyse();
		//boolean isok = ba.analyse(closedblock);		
		//System.out.println("result:"+isok);
		ExpressionASTAnalyse eanlyse = new ExpressionASTAnalyse();
		
		String s2="i=1+2";
		RootAST root = eanlyse.analyse(s2);
		if(root == null) {
			System.out.println("ast 失败");
			return;
		}else {
			System.out.println("ast 成功");
			root.show(0);
		}
		
		//从L2级别开始构建变量表
		L2_HashMapClassStaticVarTableImpl l2_static = new L2_HashMapClassStaticVarTableImpl("");
		L2_HashMapClassInstanceVarTableImpl l2_instance = new L2_HashMapClassInstanceVarTableImpl("l2",l2_static);
		L3_HashMapMethodInstanceVarTableImpl l3 = new L3_HashMapMethodInstanceVarTableImpl("",l2_instance);
		L4_HashMapBlockVarTableImpl l4 = new L4_HashMapBlockVarTableImpl("",l3);
		IClassTable classtable = new ClassTableImpl();
		IVarTable vartable=l4;
		vartable.defineVar("i","int");
		ExpressionEval eval = new ExpressionEval();
		VarValue vv = eval.eval(root, vartable, classtable);
		if(vv == null) {
			System.out.println("表达式计算失败:vv is null");
		}else {
			System.out.println(vv.toString());
		}
		
	}
}
