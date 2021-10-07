package com.liu.gwt.gwt_smalljava.level4_block.test;

import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.BlockAnalyse;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;

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
		s1 += "{ public void test(String s1){ int i = 0;} }";
		s1 += "return (a+100); ";
		
		BasicBlock closedblock = new BasicBlock("",s1,null);
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		System.out.println("");
		System.out.println("");
		closedblock.show(0);
		System.out.println("���������"+isok);		
	}
	
	@SuppressWarnings("unused")
	private static void testAnalyseByElement() {
		String s1="";
		s1 += "\r\n//test \r\n";
		s1 += "/** test memo */";
		s1 += "{{{int i;//abcd\n}}}a;";
		s1 += "int i;int j;int k;{{/* hello world */}}";
		s1 += "if(2>1){int i;int j;int k;}";
		s1 += "if(2>1){ i+1;}else { i+2; } if(1==1){i++;}";
		s1 +="do {i=i+2;i=i+3;if(i>1) {i++;i++;i++;}}while(i>=0) ";
		s1 +="while(true) {just do it;i=i+1;}";
		s1 += "{while(){a1 = 1;do1 = i;} for1 = 1;(1+1);{{{{{a12;}}}}}}";
		s1 += "for(int i;i<100;i++) {System.out.println(i);(i++);}";
		s1 += "for(int i;i<100;i++){for(int j;j<100;j++){int k;k = i+j;}}";
		s1 += "{{{{{{{{{{i=i+1;}}}}}}}}}}";
		BlockAnalyse ba = new BlockAnalyse();

		
//		Document document = DocumentHelper.createDocument();
//		Element rootelement = document.addElement("Root");
//		ElementWrapper ew = new ElementWrapper();
//		ew.setElement(rootelement);
//		ew.setBlockContent(s1);
//		
//		boolean isok = ba.analyse(ew);
//		System.out.println("");
//		System.out.println("");
//		System.out.println(ew.getElement().asXML());
//		System.out.println("���������"+isok);		
	}
}
