package com.smalljava.core.l5_expression.eval.plugin.obj.impl;

import java.util.ArrayList;

public interface IObjectCall {
	public Object objcall(String classname,Object target,String methodname,Object args);
}
