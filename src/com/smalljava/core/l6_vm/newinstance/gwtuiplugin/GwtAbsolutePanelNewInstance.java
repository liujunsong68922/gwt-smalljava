package com.smalljava.core.l6_vm.newinstance.gwtuiplugin;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_vm.newinstance.INewInstance;

public class GwtAbsolutePanelNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new AbsolutePanel();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		return new AbsolutePanel();
	}

}
