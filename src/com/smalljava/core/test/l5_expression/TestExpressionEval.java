package com.smalljava.core.test.l5_expression;

import com.smalljava.core.analyse.l5_expression.ExpressionASTAnalyse;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.eval.l5_expression.ExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.IClassTable;
//import com.smalljava.core.l9_space.classtable.impl.ClassTableImpl;
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
			System.out.println("ast fail.");
			return;
		}else {
			System.out.println("ast OK.");
			root.show(0);
		}
		
		L2_HashMapClassStaticVarTableImpl l2_static = new L2_HashMapClassStaticVarTableImpl("");
		L2_HashMapClassInstanceVarTableImpl l2_instance = new L2_HashMapClassInstanceVarTableImpl("l2",l2_static);
		L3_HashMapMethodInstanceVarTableImpl l3 = new L3_HashMapMethodInstanceVarTableImpl("",l2_instance);
		L4_HashMapBlockVarTableImpl l4 = new L4_HashMapBlockVarTableImpl("",l3);
		//IClassTable classtable = new ClassTableImpl();
		IVarTable vartable=l4;
		vartable.defineVar("i","int");
		ExpressionEval eval = new ExpressionEval();
		
		VarValue vv = eval.eval(root, vartable,
				new SmallJavaClassSupportEnv() ,
				new SmallJavaOopSupportEnv());
		if(vv == null) {
			System.out.println("[error]vv is null");
		}else {
			System.out.println(vv.toString());
		}
		
	}
}
