package com.smalljava.jvmoopplugin.newinstance.impl.swing;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextArea;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class SwingJTextAreaNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new JTextArea();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new JTextArea();
	}

}
