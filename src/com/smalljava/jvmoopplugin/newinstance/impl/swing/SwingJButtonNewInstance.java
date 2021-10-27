package com.smalljava.jvmoopplugin.newinstance.impl.swing;

import java.util.ArrayList;

import javax.swing.JButton;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class SwingJButtonNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new JButton();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new JButton();
	}

}
