package com.smalljava.core.l4_oopsupport.newinstance.gwtuiplugin;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.l4_oopsupport.newinstance.INewInstance;

public class GwtLabelNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new Label();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		return new Label();
	}

}
