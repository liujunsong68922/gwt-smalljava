package com.smalljava.core.l6_oopsupport.newinstance.javaplugin;

import java.util.ArrayList;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_oopsupport.newinstance.INewInstance;

public class ArrayListNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		
		return new ArrayList();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		return new ArrayList();
	}

}
