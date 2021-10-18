package com.smalljava.core.l6_vm.newinstance.gwtuiplugin;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_vm.newinstance.INewInstance;

public class GwtButtonNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new Button();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		return new Button();
	}

}
