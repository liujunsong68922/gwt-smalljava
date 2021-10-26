package com.smalljava.jvmplugin.newinstance.impl.swing;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_oopsupport.newinstance.INewInstance;

public class SwingJListNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new JList();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new JList();
	}

}
