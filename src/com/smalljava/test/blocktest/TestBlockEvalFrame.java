package com.smalljava.test.blocktest;

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
import com.smalljava.core.l4_block.blockanalyse.BlockAnalyse;
import com.smalljava.core.l4_block.blockeval.BlockEvaluator;
import com.smalljava.core.l4_block.blockvo.BasicBlock;
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
import com.smalljava.swing.BasicBlockToTreeNode;
//import com.smalljava.test.expresstest.TestMainFrame;

public class TestBlockEvalFrame {
	private static TestMainFrame mainframe = new TestMainFrame();

	public TestBlockEvalFrame() {

	}

	public static void main(String args[]) {
		System.out.println("enter TestExpressEvalFrame");

		TestBlockEvalFrame frame = new TestBlockEvalFrame();
		frame.mainframe.show();
	}

}

class TestMainFrame extends JFrame {
	private Label label1 = new Label("smalljava block test frame.");

	TestFrameButtonPanel buttonpanel = new TestFrameButtonPanel();

	static JTextArea sourcecode = new JTextArea("");
	static JTextArea asttree = new JTextArea("");

	// �Ѷ����JTextArea�ŵ�JScrollPane����ȥ
	static JScrollPane scroll = new JScrollPane(asttree);

	static JPanel treepanel = new JPanel();
	static JScrollPane tree = new JScrollPane(treepanel);
	
	public TestMainFrame() {
		this.setTitle("[SmallJava]: BlockTest Main Frame");
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

		// �ֱ�����ˮƽ�ʹ�ֱ���������ǳ���
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

				BasicBlock closedblock = new BasicBlock("",stext,null);
				BlockAnalyse ba = new BlockAnalyse();

				boolean isok = ba.analyse(closedblock);
				System.out.println("");
				System.out.println("");
				closedblock.show(0);
				System.out.println("���������"+isok);	
				
				if(isok) {
					String stext2 = closedblock.getShowString(0);
					TestMainFrame.asttree.setText(stext2);
					
					BasicBlockToTreeNode bbttree = new BasicBlockToTreeNode();
					
					DefaultMutableTreeNode node = bbttree.toTreeNode(closedblock,0);
					
					JTree tree = new JTree(node);
					//TestMainFrame.rootnode.add(tree);
					TestMainFrame.treepanel.removeAll();
					TestMainFrame.treepanel.add(tree);
					tree.setVisible(true);
					TestMainFrame.treepanel.validate();
					TestMainFrame.treepanel.repaint();
					System.out.println("create tree ok.");

				}else {
					TestMainFrame.asttree.setText("AST ����ʧ��.");
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
				
				//����AST�ڵ����
				BasicBlock closedblock = new BasicBlock("",stext,null);
				BlockAnalyse ba = new BlockAnalyse();

				boolean isok = ba.analyse(closedblock);
				System.out.println("");
				System.out.println("");
				closedblock.show(0);
				System.out.println("���������"+isok);	
				
				if(isok) {
					String stext2 = closedblock.getShowString(0);
					TestMainFrame.asttree.setText(stext2);
					
					BasicBlockToTreeNode bb1 = new BasicBlockToTreeNode();
					
					DefaultMutableTreeNode node = bb1.toTreeNode(closedblock,0);
					JTree tree = new JTree(node);
					//TestMainFrame.rootnode.add(tree);
					TestMainFrame.treepanel.removeAll();
					TestMainFrame.treepanel.add(tree);
					tree.setVisible(true);
					TestMainFrame.treepanel.validate();
					TestMainFrame.treepanel.repaint();
					System.out.println("create tree ok.");

					//���ڿ�ʼ����block����������㹤��
					BlockEvaluator node1 = new BlockEvaluator();
					ClassTableImpl classtable = new ClassTableImpl();

					L2_HashMapClassStaticVarTableImpl vartable1 = new L2_HashMapClassStaticVarTableImpl("");
					//b1.setVartable(vartable1);
					L2_HashMapClassInstanceVarTableImpl vartable2 = new L2_HashMapClassInstanceVarTableImpl("",vartable1);
					L3_HashMapMethodInstanceVarTableImpl vartable3 = new L3_HashMapMethodInstanceVarTableImpl("",vartable2);
					L4_HashMapBlockVarTableImpl vartable4 = new L4_HashMapBlockVarTableImpl("",vartable3);
					try {
						boolean b2 = node1.execute(closedblock,vartable4,classtable);
						System.out.println("��������������"+b2);
						System.out.println("");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}else {
					TestMainFrame.asttree.setText("AST ����ʧ��.");
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

	static String stext1 = "int i; \r\n i = 100;";
	static String stext2 = "int i; \r\n i = i + 1;";
	static String stext3 = "int i; \r\n i = 100 * 200 + 300;";
	static String stext4 = "int i; for(i=1;i<=10;i=i+1) { int j; j = i+1;} ";
	static String stext5 = "int i; i = 1; if(i<2) {i = 1+1;}";
	static String stext6 = "int i; while(i<10) { i = i+1;} ";

	JButton analysebutton = new JButton("Analyse(�﷨����)");
	JButton evalbutton = new JButton("Eval(ִ��)");

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
