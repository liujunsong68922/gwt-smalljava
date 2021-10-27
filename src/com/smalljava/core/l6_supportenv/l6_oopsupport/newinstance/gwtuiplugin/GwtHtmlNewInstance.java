package com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.gwtuiplugin;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class GwtHtmlNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new HTML();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		return new HTML();
	}

}