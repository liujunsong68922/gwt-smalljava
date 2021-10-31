package com.smalljava.jvmoopplugin.newinstance.impl.awt;

import java.awt.Panel;
import java.util.ArrayList;


import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class AwtPanelNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new Panel();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new Panel();
	}

}
