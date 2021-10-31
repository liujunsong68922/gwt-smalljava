package com.smalljava.jvmoopplugin.newinstance.impl.swing;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class SwingJCheckBoxNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new JCheckBox();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new JCheckBox();
	}

}
