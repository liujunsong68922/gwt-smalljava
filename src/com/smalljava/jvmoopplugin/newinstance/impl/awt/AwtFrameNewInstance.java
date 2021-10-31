package com.smalljava.jvmoopplugin.newinstance.impl.awt;

import java.awt.Frame;
import java.util.ArrayList;


import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class AwtFrameNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new Frame();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new Frame();
	}

}
