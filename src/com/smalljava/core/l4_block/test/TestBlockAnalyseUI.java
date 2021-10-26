package com.smalljava.core.l4_block.test;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TestBlockAnalyseUI {
	public static void main(String args[]) {
		TestBlockAnalyseUIFrame f1 = new TestBlockAnalyseUIFrame("Anylase test frame");
		f1.setBounds(0, 0, 1000, 600);
		f1.setVisible(true);
		
	}
}

class TestBlockAnalyseUIFrame extends Frame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel1 = new JPanel();
	
	public TestBlockAnalyseUIFrame(String s1) {
		super(s1);
		panel1.setBounds(0,0, 950, 550);
		panel1.setLayout(null);
		this.add(panel1);
		
		JTextArea a1 = new JTextArea();
		a1.setBounds(10, 10, 400, 500);
		panel1.add(a1);
		
		JTextArea a2 = new JTextArea();
		a2.setBounds(430, 10, 400, 500);
		panel1.add(a2);
		
		JButton b1 = new JButton("Analyse");
		b1.setBounds(180, 520, 100, 40);
		panel1.add(b1);
		
		
	}
	
	
}