package com.smalljava.jvmoopplugin.newinstance.impl.swing;

import java.util.ArrayList;
import javax.swing.JComboBox;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class SwingJComboBoxNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new JComboBox();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new JComboBox();
	}

}
