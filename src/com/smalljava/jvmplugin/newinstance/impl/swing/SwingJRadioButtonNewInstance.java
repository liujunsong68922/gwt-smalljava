package com.smalljava.jvmplugin.newinstance.impl.swing;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_vm.newinstance.INewInstance;

public class SwingJRadioButtonNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new JRadioButton();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new JRadioButton();
	}

}
