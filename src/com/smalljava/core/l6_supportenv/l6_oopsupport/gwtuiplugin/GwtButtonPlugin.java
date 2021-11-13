package com.smalljava.core.l6_supportenv.l6_oopsupport.gwtuiplugin;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l6_supportenv.IClassObject;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;

public class GwtButtonPlugin implements IClassObject {
	Logger logger = LoggerFactory.getLogger(GwtButtonPlugin.class);
	private String classname="com.gwt.ui.Button";
	
	@Override
	public String getClassFullname() {
		return classname;
	}

	@Override
	public VarValue newInstance(String classname, SmallJavaClassSupportEnv classenv, SmallJavaOopSupportEnv oopenv) {
		// TODO Auto-generated method stub
		VarValue vv = new VarValue();
		return vv;
	}

	@Override
	public VarValue objcall(String classname, Object target, String methodname, ArrayList<VarValue> args,
			SmallJavaClassSupportEnv classenv, SmallJavaOopSupportEnv oopenv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VarValue classcall(String classname, IClassObject target, String methodname, ArrayList<VarValue> args,
			SmallJavaClassSupportEnv classenv, SmallJavaOopSupportEnv oopenv) {
		// Not Support.
		logger.error("[ERROR] Not support classcall.");
		return null;
	}

	@Override
	public VarValue objPropertyGet(String classname, Object target, String propertyname,
			SmallJavaClassSupportEnv classenv, SmallJavaOopSupportEnv oopenv) {
		// TODO Auto-generated method stub
		if (! (target instanceof Button)) {
			return null;
		}
		Button b1 = (Button) target;
		String svalue ="";
		if (propertyname.equalsIgnoreCase("text")) {
			svalue = b1.getText();
		}
		if (propertyname.equals("html")) {
			svalue = b1.getHTML();
		}
		
		VarValue vvalue = new VarValue();
		vvalue.setVartype("String");
		vvalue.setVarsvalue(svalue);
		return vvalue;
	}

	@Override
	public VarValue classPropertyGet(String classname, IClassObject target, String propertyname,
			SmallJavaClassSupportEnv classenv, SmallJavaOopSupportEnv oopenv) {
		// Not Support
		logger.error("[ERROR] Not support class propertyget.");
		return null;
	}
}
