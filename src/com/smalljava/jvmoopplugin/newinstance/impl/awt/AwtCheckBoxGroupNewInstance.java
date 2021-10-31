package com.smalljava.jvmoopplugin.newinstance.impl.awt;

import java.awt.CheckboxGroup;
import java.util.ArrayList;


import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class AwtCheckBoxGroupNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new CheckboxGroup();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new CheckboxGroup();
	}

}
