package com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.javaplugin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class DateNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new Date();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		return new Date();
	}
}
