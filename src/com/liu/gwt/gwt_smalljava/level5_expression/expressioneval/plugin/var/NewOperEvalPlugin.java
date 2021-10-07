package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.var;

import com.liu.gwt.gwt_smalljava.common.UuidObjectManager;
import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.NewOperElement;
import com.liu.gwt.gwt_smalljava.level6_vm.JavaVMImpl;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

public class NewOperEvalPlugin implements IExpressionEval {

	@SuppressWarnings("rawtypes")
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		consoleLog("enter NewOperEvalPlugin.");

		if (root == null || vartable == null || classtable == null) {
			return null;
		}

		if (root instanceof NewOperElement) {
			NewOperElement newoper = (NewOperElement) root;
			String classname = newoper.getClassname();
			consoleLog("classname:" + classname);
			try {
				//NewInstanceManager newmanager = new NewInstanceManager();
				//Object obj1 = newmanager.newInstance(classname);
				//if(obj1==null) {
				//	consoleLog("[Warning] Create a null object.");
				//}
				JavaVMImpl jvm = new JavaVMImpl();
				Object obj1 = jvm.newInstance(classname);
				if(obj1==null) {
					consoleLog("[Warning] Create a null object.");
				}
				
				
				String uuid = uuid();
				UuidObjectManager.setObject(uuid, obj1);
				VarValue vvalue = new VarValue();
				vvalue.setVartype(classname);
				vvalue.setVarsvalue(uuid);
				return vvalue;
				
			} catch (Exception e) {
				System.out.println("Error when create new Instance.");
				e.printStackTrace();
				consoleLog("Error when create new Instance.");
				consoleLog("errorinfo:" + e.getMessage());
				return null;
			}
		}
		return null;
	}

	public native String uuid() /*-{
								return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
								var r = Math.random() * 16 | 0,
								v = c == 'x' ? r : (r & 0x3 | 0x8);
								return v.toString(16);
								});
								}-*/;

	public native void consoleLog(String message) /*-{
													//alert(message);
													console.log( "[NewOperEvalPlugin]:" + message );
													}-*/;

}
