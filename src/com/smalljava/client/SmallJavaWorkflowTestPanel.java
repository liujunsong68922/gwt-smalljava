package com.smalljava.client;

import java.util.HashMap;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smalljava.core.analyse.l4_block.BlockAnalyse;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.workflow.core.analyse.WorkflowAnalyse;
import com.smalljava.workflow.core.engine.WorkflowEngine;
import com.smalljava.workflow.core.vo.WorkflowDefineVO;
import com.smalljava.workflow.core.vo.WorkflowInstanceVO;

/**
 * In Default way,using FlowPanel as RootPanel
 * @author liujunsong
 *
 */
@SuppressWarnings("deprecation")
public class SmallJavaWorkflowTestPanel extends FlowPanel {
	private final String strreadme="这是一个测试用的Panel,用来测试GWT-SmallJava的代码执行功能. ";
	
	private SmallJavaWorkflowTestPanel_VerticalPanel vpanel= new SmallJavaWorkflowTestPanel_VerticalPanel();
	/**
	 * This is the default constructor
	 */
	public SmallJavaWorkflowTestPanel() {
		this.add(vpanel);
	}
}

class SmallJavaWorkflowTestPanel_VerticalPanel extends VerticalPanel{
	private final String stext1 = "<Workflow>\r\n"
			+"<Start><Transmition toaction='step1'></Transmition></Start>\r\n"
			+"<Action name='step1'>"
			+"<Transmition toaction='step2'>"
			+"</Transmition></Action>\r\n"
			+"<Action name='step2'><Transmition toaction='End'></Transmition></Action>\r\n"
			+"<End></End>\r\n"
			+"</Workflow>";
	private final String stext2 = "";
	//private final String stext3 = "";
	//private final String stext4 = "";
	
	final HorizontalPanel buttonpanel = new HorizontalPanel();
	final TextArea textarea = new TextArea();
	final TextArea asttext = new TextArea();

	WorkflowInstanceVO currentInstanceVO;
	public SmallJavaWorkflowTestPanel_VerticalPanel() {
		
		initButtonPanel();
		
		textarea.setWidth("600px");
		textarea.setHeight("150px");
		asttext.setWidth("600px");
		asttext.setHeight("450px");
		this.add(buttonpanel);
		this.add(textarea);
		this.add(asttext);
	}

	@SuppressWarnings("deprecation")
	private void initButtonPanel() {
		Button button1 = new Button("text1");
		Button button2 = new Button("text2");
		//Button button3 = new Button("text3");
		//Button button4 = new Button("text4");
		Button anaylseButton = new Button("Workflow语法分析");
		Button evalButton1 = new Button("Workflow 实例化");
		Button evalButton2 = new Button("Workflow 执行");
		
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

//		button3.addClickListener(new ClickListener() {
//			@Override
//			public void onClick(Widget sender) {
//				textarea.setText(stext3);
//			}
//		});

//		button4.addClickListener(new ClickListener() {
//			@Override
//			public void onClick(Widget sender) {
//				textarea.setText(stext4);
//			}
//		});
		
		anaylseButton.addClickListener(new ClickListener() {
			@Override
			public void onClick(Widget sender) {
				String stext = textarea.getText();
				WorkflowAnalyse analyse = new WorkflowAnalyse();
				WorkflowDefineVO vo = analyse.analyse(stext);
				if(vo == null) {
					Window.alert("vo is null");
					return;
				}else {
					Window.alert("XML分析成功!");
					String s1 = vo.toXMLString();
					Window.alert(s1);
					asttext.setText(s1);
					return;
				}
			}
		});

		evalButton1.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				String stext = textarea.getText();
				WorkflowAnalyse analyse = new WorkflowAnalyse();
				WorkflowDefineVO wfdefinevo = analyse.analyse(stext);
				if(wfdefinevo == null) {
					Window.alert("vo is null");
					return;
				}else {
					Window.alert("XML分析成功!");
					String s1 = wfdefinevo.toXMLString();
					Window.alert(s1);
					asttext.setText(s1);
				}
				
				//利用工作流模板来实例化
				WorkflowEngine wfengine = new WorkflowEngine();
				WorkflowInstanceVO instancevo = wfengine.startWorkflow(wfdefinevo, new HashMap());
				if(instancevo == null) {
					Window.alert("工作流初始化失败!");
					return;
				}else {
					currentInstanceVO = instancevo;
					
					Window.alert("工作流启动成功");
					String s2 = instancevo.toXMLString();
					Window.alert(s2);
					asttext.setText(s2);
					return;
				}
			}
		});		
		
		evalButton2.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				WorkflowInstanceVO vo = currentInstanceVO;
				if(vo == null) {
					Window.alert("current vo is null");
					return;
				}
				
				//调用工作流执行引擎
				WorkflowEngine wfengine = new WorkflowEngine();
				boolean b1 = wfengine.executeWorkflow(vo, new HashMap<String,VarValue>());
				if(! b1) {
					Window.alert("workflow execute failed.");
					return;
				}else {
					Window.alert("工作流执行成功!");
					String s2 = vo.toXMLString();
					Window.alert(s2);
					asttext.setText(s2);
				}
			}});
		
		buttonpanel.add(button1);
		buttonpanel.add(button2);
//		buttonpanel.add(button3);
//		buttonpanel.add(button4);
		buttonpanel.add(anaylseButton);
		buttonpanel.add(evalButton1);
		buttonpanel.add(evalButton2);
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

