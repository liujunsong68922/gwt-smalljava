package com.liu.gwt.client;

import com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.BlockAnalyse;
import com.liu.gwt.gwt_smalljava.level4_block.blockeval.BlockEvaluator;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.space.impl.ClassTableImpl;
import com.liu.gwt.shared.FieldVerifier;
//import com.liu.webui.client.smalljava.level4_block.blockeval.BlockEvaluator;
//import com.liu.webui.client.smalljava.space.impl.ClassTableImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SmallJavaApp implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  private final String stext1="int i=0;\r\n"
                       	+"if ( i< 10 ){ \r\n"
  						+"  i = 100; \r\n"
  						+"}";
  private final String stext2="int i; \r\n"
		  				+"for(i=0; i < 10 ; i=i+1){ \r\n"
		  				+"   i = i + 100; \r\n"
		  				+"}";
  private final String stext3="int i= 100 + 200;";
  private final String stext4="int i = 100 + 200 * 2;";
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    final Button analyseButton = new Button("Analyse(语法分析)");
    final Button evalButton = new Button("Eval(代码执行)");
    
    final Button text1Button = new Button("text1");
    final Button text2Button = new Button("text2");
    final Button text3Button = new Button("text3");
    final Button text4Button = new Button("text4");
    final TextArea textarea = new TextArea();
    textarea.setWidth("600px");
    textarea.setHeight("150px");
    final TextArea asttext = new TextArea();
    asttext.setWidth("600px");
    asttext.setHeight("150px");
    final TextBox nameField = new TextBox();
    nameField.setText("GWT User");
    final Label errorLabel = new Label();

    // We can add style names to widgets
    analyseButton.addStyleName("sendButton");
    text1Button.addClickListener(new ClickListener() {
		@Override
		public void onClick(Widget sender) {
			textarea.setText(stext1);
		}});

    text2Button.addClickListener(new ClickListener() {
		@Override
		public void onClick(Widget sender) {
			textarea.setText(stext2);
		}});

    text3Button.addClickListener(new ClickListener() {
		@Override
		public void onClick(Widget sender) {
			textarea.setText(stext3);
		}});

    text4Button.addClickListener(new ClickListener() {
		@Override
		public void onClick(Widget sender) {
			textarea.setText(stext4);
		}});
    
    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel.get("nameFieldContainer").add(nameField);
    RootPanel.get("text1buttonContainer").add(text1Button);
    RootPanel.get("text2buttonContainer").add(text2Button);
    RootPanel.get("text3buttonContainer").add(text3Button);
    RootPanel.get("text4buttonContainer").add(text4Button);
    RootPanel.get("errorLabelContainer").add(errorLabel);
    RootPanel.get("textareaContainer").add(textarea);
    RootPanel.get("asttextContainer").add(asttext);

    RootPanel.get("sendButtonContainer").add(analyseButton);
    RootPanel.get("evalButtonContainer").add(evalButton);
    
    // Focus the cursor on the name field when the app loads
    nameField.setFocus(true);
    nameField.selectAll();

    analyseButton.addClickListener(new ClickListener() {
		@Override
		public void onClick(Widget sender) {
			String stext = textarea.getText();
			BasicBlock bb = testAnalyseByBlock(stext);
//			Window.alert(bb.getVarString());
			asttext.setText(bb.getShowString(0));
		}});
    
    evalButton.addClickListener(new ClickListener() {

		@Override
		public void onClick(Widget sender) {
			String stext = textarea.getText();
			BasicBlock bb = testAnalyseByBlock(stext);
			asttext.setText(bb.getShowString(0));
			
			BlockEvaluator node = new BlockEvaluator();
			//classtable 目前在GWT里面是一个无效接口，后续考虑去掉
			//因为GWT不支持Java类的反射机制，因此必须手工进行调用
			ClassTableImpl classtable = new ClassTableImpl();
			try {
				boolean b2 = node.execute(bb,classtable);
				Window.alert("计算结果:"+b2);
			} catch (Exception e) {
				Window.alert("计算错误:"+e.getMessage());
			}
			
			Window.alert("代码执行完毕!");
			Window.alert(bb.getVarString());
		}});
    
  }
  
	private static BasicBlock testAnalyseByBlock(String stext) {
		//String s1="{";
		//s1 += "\r\n//test \r\n";
		//s1 += " int i=10; i=i+1;int j;int k;";
		//s1 += "{int k=100;k=k+k;}";
		//s1 += "do{i=i+1;int j=100; j =i*j;}while(i<100)";
		//s1 += "{i=i*i; j=i+i;k=j+j;}";
		//s1 += "while(i>8000){i=i-1;}";
		//s1 += "if(i>9000){int kkk = 10000;int jjj=666;}else{int jjj=8888;}";
		//s1 += "for(int i=0;i<=1000;i=i+1) {int j=100;j=i*i;}";
		//s1 += "HashMap map1= new HashMap();";
		//s1 += "map1.put(\"a\",\"a\");";
		//s1 += "import java.util.HashMap;";
		//s1 += "/** aaa */";
		//s1 += " int longvalue = 100+200*30;";
		//s1 += "{{{int i;//abcd\n}}}int a=100;";
		//s1 += "int i;int j;int k;{{/* hello world */}}";
		//s1 += "if(2>1){int i;int j;int k;}";
		//s1 += "if(2>1){ i+1;}else { i+2; } int i=100*100; if(1==1){i=i+1;}";
		//s1 += "{int i; int j=10; do{j=j*i;i=i+1;}while(i<10) j=j+1; }";
		//s1 +="do {i=i+2;i=i+3;if(i>1) {i++;i++;i++;}}while(i>=0) ";
		//s1 +="while(true) {just do it;i=i+1;}";
		//s1 += "{while(){a1 = 1;do1 = i;} for1 = 1;(1+1);{{{{{a12;}}}}}}";
		
		//s1+="}";
		BasicBlock closedblock = new BasicBlock("",stext,null);
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		closedblock.show(0);
		return closedblock;
	}
}
