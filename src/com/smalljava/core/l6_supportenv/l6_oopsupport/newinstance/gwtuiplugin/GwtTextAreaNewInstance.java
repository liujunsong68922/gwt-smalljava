package com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.gwtuiplugin;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;

public class GwtTextAreaNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new TextArea();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		return new TextArea();
	}

}
