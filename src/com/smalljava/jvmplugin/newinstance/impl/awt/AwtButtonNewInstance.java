package com.smalljava.jvmplugin.newinstance.impl.awt;

import java.awt.Button;
import java.util.ArrayList;


import com.smalljava.core.common.VarValue;
import com.smalljava.core.l4_oopsupport.newinstance.INewInstance;

public class AwtButtonNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new Button();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new Button();
	}

}
