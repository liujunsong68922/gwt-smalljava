package com.smalljava.core.l4_block.test;

import com.smalljava.core.l4_block.blockanalyse.BlockAnalyse;
import com.smalljava.core.l4_block.blockvo.BasicBlock;

public class TestBlockAnalyse {

	/*
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		//test anylyse based on blockvo.
		testAnalyseByBlock();
		//testAnalyseByElement();
	}
	
	//@SuppressWarnings("unused")
	private static void testAnalyseByBlock() {
		String s1="";
		//s1 += "\r\n//test \r\n";
		//s1 += "{{{int i;//abcd\n}}} int a=100;";
		//s1 += "int i;int j;int k;{{/* hello world */}}";
		//s1 += "if(2>1){int i;int j;int k;}";
		//s1 += "if(2>1){ i+1;}else { i+2; } if(1==1){i++;}";
		//s1 +="do {i=i+2;i=i+3;if(i>1) {i++;i++;i++;}}while(i>=0) ";
		//s1 +="while(true) {just do it;i=i+1;}";
		//s1 += "{while(){a1 = 1;do1 = i;} for1 = 1;(1+1);{{{{{a12;}}}}}}";
		//s1 += "for(int i;i<100;i++) {System.out.println(i);(i++);}";
		s1 += "{ { int i = 0;} }";
		s1 += "return (a+100); ";
		
		BasicBlock closedblock = new BasicBlock("",s1,null);
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		System.out.println("");
		System.out.println("");
		closedblock.show(0);
		System.out.println("·ÖÎö½á¹û£º"+isok);		
	}
}
