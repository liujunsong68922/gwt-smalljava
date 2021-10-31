package com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.gwtuiplugin;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.TextBox;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class GwtTextBoxNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new TextBox();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		return new TextBox();
	}

}
