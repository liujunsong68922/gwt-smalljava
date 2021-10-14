package com.smalljava.core.l4_block.test;

import com.smalljava.core.l4_block.blockanalyse.BlockAnalyse;
import com.smalljava.core.l4_block.blockeval.BlockEvaluator;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
import com.smalljava.core.l9_space.classtable.impl.ClassTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassStaticVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L3_HashMapMethodInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

public class TestBlockEvalutor {
	
	@SuppressWarnings("unused")
	public static void main(String args[]) throws Exception {
		
		BasicBlock b1 = testAnalyseByBlock();
		b1.show(0);
		BlockEvaluator node = new BlockEvaluator();
		ClassTableImpl classtable = new ClassTableImpl();

		L2_HashMapClassStaticVarTableImpl vartable1 = new L2_HashMapClassStaticVarTableImpl("");
		//b1.setVartable(vartable1);
		L2_HashMapClassInstanceVarTableImpl vartable2 = new L2_HashMapClassInstanceVarTableImpl("",vartable1);
		L3_HashMapMethodInstanceVarTableImpl vartable3 = new L3_HashMapMethodInstanceVarTableImpl("",vartable2);
		L4_HashMapBlockVarTableImpl vartable4 = new L4_HashMapBlockVarTableImpl("",vartable3);
		boolean b2 = node.execute(b1,vartable4,classtable);
//		System.out.println("计算结束！结果："+b2);
//		System.out.println("");
//		System.out.println("显示Var:");
//		b1.showvar(0);
	}
	
	private static BasicBlock testAnalyseByBlock() {
		String s1="{";
		//s1 += "\r\n//test \r\n";
		//s1 += " int i=10; i=i+1;int j;int k;";
		//s1 += "{int k=100;k=k+k;}";
		//s1 += "do{i=i+1;int j=100; j =i*j;}while(i<100)";
		//s1 += "{i=i*i; j=i+i;k=j+j;}";
		//s1 += "while(i>8000){i=i-1;}";
		//s1 += "if(i>9000){int kkk = 10000;int jjj=666;}else{int jjj=8888;}";
		s1 += "int j; int i; for(i=0;i < 5; i = i + 1) {j=i*i;}";
		//s1 += "HashMap map1= new HashMap();";
		//s1 += "map1.put(\"a\",\"a\");";
		//s1 += "import java.util.HashMap;";
		s1 += "/** aaa */";
		s1 += " int longvalue = 100 + 200*30 + 0;";
		//s1 += "{{{int i;//abcd\n}}}int a=100;";
		//s1 += "int i;int j;int k;{{/* hello world */}}";
		//s1 += "if(2>1){int i;int j;int k;}";
		//s1 += "if(2>1){ i+1;}else { i+2; } int i=100*100; if(1==1){i=i+1;}";
		//s1 += "{int i; int j=10; do{j=j*i;i=i+1;}while(i<10) j=j+1; }";
		//s1 +="do {i=i+2;i=i+3;if(i>1) {i++;i++;i++;}}while(i>=0) ";
		//s1 +="while(true) {just do it;i=i+1;}";
		//s1 += "{while(){a1 = 1;do1 = i;} for1 = 1;(1+1);{{{{{a12;}}}}}}";
		
		s1+="}";
		BasicBlock closedblock = new BasicBlock("",s1,null);
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		System.out.println("");
		System.out.println("");
		closedblock.show(0);
		System.out.println("分析结果："+isok);	
		
		return closedblock;
	}
}
