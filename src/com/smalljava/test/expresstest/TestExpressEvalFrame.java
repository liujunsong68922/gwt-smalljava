package com.smalljava.test.expresstest;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

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
import com.smalljava.swing.RootASTToTreeNode;

public class TestExpressEvalFrame {
	private static TestMainFrame mainframe = new TestMainFrame();

	public TestExpressEvalFrame() {

	}

	public static void main(String args[]) {
		System.out.println("enter TestExpressEvalFrame");

		TestExpressEvalFrame frame = new TestExpressEvalFrame();
		frame.mainframe.show();
	}

}

class TestMainFrame extends JFrame {
	private Label label1 = new Label("这个页面用来测试SmallJava的表达式解析与执行功能.");

	TestFrameButtonPanel buttonpanel = new TestFrameButtonPanel();

	static JTextArea sourcecode = new JTextArea("");
	static JTextArea asttree = new JTextArea("");

	// 把定义的JTextArea放到JScrollPane里面去
	static JScrollPane scroll = new JScrollPane(asttree);

	static JPanel treepanel = new JPanel();
	static JScrollPane tree = new JScrollPane(treepanel);
	
	public TestMainFrame() {
		this.setTitle("[SmallJava]: Test Main Frame");
		this.setBounds(300, 60, 800, 630);
		this.setLayout(null);
		this.add(label1);
		Font font1 = new Font("Times New Roman Italic", Font.BOLD, 20);
		label1.setFont(font1);
		label1.setBounds(0, 0, 600, 50);

		this.add(buttonpanel);
		buttonpanel.setBounds(0, 50, 800, 50);

		this.add(sourcecode);
		sourcecode.setBorder(BorderFactory.createLineBorder(Color.green, 2));
		sourcecode.setBounds(10, 100, 760, 80);

		// this.add(asttree);
		// asttree.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		// asttree.setBounds(10, 250, 760, 150);

		// 分别设置水平和垂直滚动条总是出现
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scroll);
		scroll.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		scroll.setBounds(10, 185, 760, 200);

		tree.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(tree);
		tree.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		tree.setBounds(10, 390, 700, 200);
		
		// add buttonclick1
		this.addButtonClick1();

		// add analyse button click event listener
		this.addAnalyseButtonListener();
		
		// add eval button click event listener
		this.addEvalButtonListener();
	}

	private void addButtonClick1() {
		this.buttonpanel.b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestMainFrame.sourcecode.setText(TestFrameButtonPanel.stext1);

			}
		});
		this.buttonpanel.b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestMainFrame.sourcecode.setText(TestFrameButtonPanel.stext2);

			}
		});
		this.buttonpanel.b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestMainFrame.sourcecode.setText(TestFrameButtonPanel.stext3);

			}
		});
		this.buttonpanel.b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestMainFrame.sourcecode.setText(TestFrameButtonPanel.stext4);

			}
		});
		this.buttonpanel.b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestMainFrame.sourcecode.setText(TestFrameButtonPanel.stext5);

			}
		});
		this.buttonpanel.b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestMainFrame.sourcecode.setText(TestFrameButtonPanel.stext6);
			}
		});

	}

	private void addAnalyseButtonListener() {
		this.buttonpanel.analysebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You clicked analyse Button");
				String stext = TestMainFrame.sourcecode.getText();
				System.out.println("{" + stext + "}");

				ExpressionASTAnalyse eanlyse = new ExpressionASTAnalyse();

				RootAST root = eanlyse.analyse(stext);
				if (root == null) {
					System.out.println("---->ast 失败");
					//String s1 = root.getShowString(0);
					String s2 = "----->ast 失败\r\n" ;
					TestMainFrame.asttree.setText(s2);
					return;
				} else {
					System.out.println("---->ast 成功");
					root.show(0);
					String s1 = root.getShowString(0);
					System.out.println(s1);
					TestMainFrame.asttree.setText(s1);
					
					RootASTToTreeNode tonode2 = new RootASTToTreeNode();
					DefaultMutableTreeNode node = tonode2.toTreeNode(root,0);
					JTree tree = new JTree(node);
					//TestMainFrame.rootnode.add(tree);
					TestMainFrame.treepanel.removeAll();
					TestMainFrame.treepanel.add(tree);
					tree.setVisible(true);
					TestMainFrame.treepanel.validate();
					TestMainFrame.treepanel.repaint();
					System.out.println("create tree ok.");
				}

			}
		});
	}

	private void addEvalButtonListener() {
		this.buttonpanel.evalbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You clicked analyse Button");
				String stext = TestMainFrame.sourcecode.getText();
				System.out.println("{" + stext + "}");

				ExpressionASTAnalyse eanlyse = new ExpressionASTAnalyse();

				RootAST root = eanlyse.analyse(stext);
				if (root == null) {
					System.out.println("---->ast 失败");
					//String s1 = root.getShowString(0);
					String s2 = "----->ast 失败\r\n" ;
					TestMainFrame.asttree.setText(s2);
					return;
				} else {
					System.out.println("---->ast 成功");

					//调用执行工具来进行计算
					//从L2级别开始构建变量表
					L2_HashMapClassStaticVarTableImpl l2_static = new L2_HashMapClassStaticVarTableImpl("");
					L2_HashMapClassInstanceVarTableImpl l2_instance = new L2_HashMapClassInstanceVarTableImpl("l2",l2_static);
					L3_HashMapMethodInstanceVarTableImpl l3 = new L3_HashMapMethodInstanceVarTableImpl("",l2_instance);
					L4_HashMapBlockVarTableImpl l4 = new L4_HashMapBlockVarTableImpl("",l3);
					IClassTable classtable = new ClassTableImpl();
					IVarTable vartable=l4;
					vartable.defineVar("i","int");
					vartable.defineVar("map1", "HashMap");
					
					ExpressionEval eval = new ExpressionEval();
					VarValue vv = eval.eval(root, vartable, classtable);
					if(vv == null) {
						System.out.println("表达式计算失败:vv is null");
					}else {
						System.out.println("计算结果:"+vv.toString());
					}

				}

			}
		});
	}
	
}

class TestFrameButtonPanel extends JPanel {
	JButton b1 = new JButton("text1");
	JButton b2 = new JButton("text2");
	JButton b3 = new JButton("text3");
	JButton b4 = new JButton("text4");
	JButton b5 = new JButton("text5");
	JButton b6 = new JButton("text6");

	static String stext1 = "1+2";
	static String stext2 = "1+2*3";
	static String stext3 = "(1+2)*(3-4)/100";
	static String stext4 = "100.1 + a * b -c ";
	static String stext5 = "1+1";
	static String stext6 = "1*1";

	JButton analysebutton = new JButton("Analyse(语法分析)");
	JButton evalbutton = new JButton("Eval(执行)");

	public TestFrameButtonPanel() {
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		this.add(b6);

		this.add(analysebutton);
		this.add(evalbutton);
	}
}
