package com.smalljava.jvmplugin.newinstance.impl.swing;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_vm.newinstance.INewInstance;

public class SwingJFrameNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new JFrame();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new JFrame();
	}

}
