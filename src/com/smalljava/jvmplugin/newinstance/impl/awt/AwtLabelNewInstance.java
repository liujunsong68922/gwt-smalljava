package com.smalljava.jvmplugin.newinstance.impl.awt;

import java.awt.Button;
import java.awt.Label;
import java.util.ArrayList;


import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_vm.newinstance.INewInstance;

public class AwtLabelNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new Label();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		//TODO: implements constructor with argument.
		return new Label();
	}

}
