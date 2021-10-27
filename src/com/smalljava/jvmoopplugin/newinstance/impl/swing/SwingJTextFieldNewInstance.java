package com.smalljava.jvmoopplugin.newinstance.impl.swing;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class SwingJTextFieldNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new JTextField();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new JTextField();
	}

}
