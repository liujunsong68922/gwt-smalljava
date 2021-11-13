package com.smalljava.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smalljava.core.analyse.l4_block.BlockAnalyse;
import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.eval.l4_block.SmallJavaBlockEvaluator;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
//import com.smalljava.core.l9_space.classtable.impl.ClassTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassStaticVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L3_HashMapMethodInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;

/**
 * In Default way,using FlowPanel as RootPanel
 * @author liujunsong
 *
 */
@SuppressWarnings("deprecation")
public class SmallJavaTestPanel extends FlowPanel {
	//private final String strreadme="这是一个测试用的Panel,用来测试GWT-SmallJava的代码执行功能. ";
	
	private SmallJavaTestPanel_VerticalPanel vpanel= new SmallJavaTestPanel_VerticalPanel();
	/**
	 * This is the default constructor
	 */
	public SmallJavaTestPanel() {
		this.add(vpanel);
	}
}

class SmallJavaTestPanel_VerticalPanel extends VerticalPanel{
	private final String stext1 = "int i=0;\r\n" + "if ( i< 10 ){ \r\n" + "  i = 100; \r\n" + "}";
	private final String stext2 = "int i; \r\n" + "for(i=0; i < 10 ; i=i+1){ \r\n" + "   i = i + 100; \r\n" + "}";
	private final String stext3 = "int i= 100 + 200;";
	private final String stext4 = "int i = 100 + 200 * 2;";
	
	final HorizontalPanel buttonpanel = new HorizontalPanel();
	final TextArea textarea = new TextArea();
	final TextArea asttext = new TextArea();
	
	public SmallJavaTestPanel_VerticalPanel() {
		
		initButtonPanel();
		
		textarea.setWidth("600px");
		textarea.setHeight("150px");
		asttext.setWidth("600px");
		asttext.setHeight("150px");
		this.add(buttonpanel);
		this.add(textarea);
		this.add(asttext);
	}

	@SuppressWarnings("deprecation")
	private void initButtonPanel() {
		Button button1 = new Button("text1");
		Button button2 = new Button("text2");
		Button button3 = new Button("text3");
		Button button4 = new Button("text4");
		Button anaylseButton = new Button("Analyse(语法分析)");
		Button evalButton = new Button("eval(代码执行)");
		
		button1.addClickListener(new ClickListener() {
			@Override
			public void onClick(Widget sender) {
				textarea.setText(stext1);
			}
		});

		button2.addClickListener(new ClickListener() {
			@Override
			public void onClick(Widget sender) {
				textarea.setText(stext2);
			}
		});

		button3.addClickListener(new ClickListener() {
			@Override
			public void onClick(Widget sender) {
				textarea.setText(stext3);
			}
		});

		button4.addClickListener(new ClickListener() {
			@Override
			public void onClick(Widget sender) {
				textarea.setText(stext4);
			}
		});
		
		anaylseButton.addClickListener(new ClickListener() {
			@Override
			public void onClick(Widget sender) {
				String stext = textarea.getText();
				BasicBlock bb = testAnalyseByBlock(stext);
//			Window.alert(bb.getVarString());
				asttext.setText(bb.getShowString(0));
			}
		});

		evalButton.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				String stext = textarea.getText();
				BasicBlock bb = testAnalyseByBlock(stext);
				asttext.setText(bb.getShowString(0));

				SmallJavaBlockEvaluator node = new SmallJavaBlockEvaluator();
				// classtable 目前在GWT里面是一个无效接口，后续考虑去掉
				// 因为GWT不支持Java类的反射机制，因此必须手工进行调用
				//ClassTableImpl classtable = new ClassTableImpl();
				L2_HashMapClassStaticVarTableImpl vartable1 = new L2_HashMapClassStaticVarTableImpl("");
				//b1.setVartable(vartable1);
				L2_HashMapClassInstanceVarTableImpl vartable2 = new L2_HashMapClassInstanceVarTableImpl("",vartable1);
				L3_HashMapMethodInstanceVarTableImpl vartable3 = new L3_HashMapMethodInstanceVarTableImpl("",vartable2);
				L4_HashMapBlockVarTableImpl vartable4 = new L4_HashMapBlockVarTableImpl("",vartable3);
				
				try {
					boolean b2 = node.execute(bb,vartable4, new SmallJavaClassSupportEnv(),new SmallJavaOopSupportEnv());
					Window.alert("计算结果:" + b2);
				} catch (Exception e) {
					Window.alert("计算错误:" + e.getMessage());
				}

				Window.alert("代码执行完毕!");
				//Window.alert(bb.getVarString());
				Window.alert(bb.getShowString(0));
			}
		});		
		
		buttonpanel.add(button1);
		buttonpanel.add(button2);
		buttonpanel.add(button3);
		buttonpanel.add(button4);
		buttonpanel.add(anaylseButton);
		buttonpanel.add(evalButton);
	}
	
	public TextArea getTextarea() {
		return textarea;
	}

	public TextArea getAsttext() {
		return asttext;
	}
	
	private static BasicBlock testAnalyseByBlock(String stext) {
		// String s1="{";
		// s1 += "\r\n//test \r\n";
		// s1 += " int i=10; i=i+1;int j;int k;";
		// s1 += "{int k=100;k=k+k;}";
		// s1 += "do{i=i+1;int j=100; j =i*j;}while(i<100)";
		// s1 += "{i=i*i; j=i+i;k=j+j;}";
		// s1 += "while(i>8000){i=i-1;}";
		// s1 += "if(i>9000){int kkk = 10000;int jjj=666;}else{int jjj=8888;}";
		// s1 += "for(int i=0;i<=1000;i=i+1) {int j=100;j=i*i;}";
		// s1 += "HashMap map1= new HashMap();";
		// s1 += "map1.put(\"a\",\"a\");";
		// s1 += "import java.util.HashMap;";
		// s1 += "/** aaa */";
		// s1 += " int longvalue = 100+200*30;";
		// s1 += "{{{int i;//abcd\n}}}int a=100;";
		// s1 += "int i;int j;int k;{{/* hello world */}}";
		// s1 += "if(2>1){int i;int j;int k;}";
		// s1 += "if(2>1){ i+1;}else { i+2; } int i=100*100; if(1==1){i=i+1;}";
		// s1 += "{int i; int j=10; do{j=j*i;i=i+1;}while(i<10) j=j+1; }";
		// s1 +="do {i=i+2;i=i+3;if(i>1) {i++;i++;i++;}}while(i>=0) ";
		// s1 +="while(true) {just do it;i=i+1;}";
		// s1 += "{while(){a1 = 1;do1 = i;} for1 = 1;(1+1);{{{{{a12;}}}}}}";

		// s1+="}";
		BasicBlock closedblock = new BasicBlock("", stext, null);
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		System.out.println("isok:"+isok);
		closedblock.show(0);
		return closedblock;
	}
}

