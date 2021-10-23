package com.smalljava.jvmplugin.newinstance.impl.awt;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Label;
import java.util.ArrayList;


import com.smalljava.core.common.VarValue;
import com.smalljava.core.l4_oopsupport.newinstance.INewInstance;

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
