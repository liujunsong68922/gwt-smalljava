package com.smalljava.core.l5_expression.eval.plugin.var.impl.classplugin;

import java.util.HashMap;

import com.smalljava.core.l5_expression.eval.plugin.var.impl.INewInstance;

public class HashMapNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new HashMap();
	}

}
