package com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.plugin.obj;

import com.google.gwt.user.client.Window;
import com.liu.gwt.gwt_smalljava.common.UuidObjectManager;
import com.liu.gwt.gwt_smalljava.common.VarValue;
import com.liu.gwt.gwt_smalljava.level5_expression.expressioneval.IExpressionEval;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.obj.ObjectCallOperElement;
import com.liu.gwt.gwt_smalljava.level6_vm.JavaVMImpl;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.IVarTable;

public class ObjectCallEvalPlugin implements IExpressionEval {

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		//loginfo
		
		ObjectCallOperElement objcall = (ObjectCallOperElement) root;
		consoleLog(objcall.getObjname());
		consoleLog(objcall.getMethodname());
		String objname = objcall.getObjname();
		String methodname = objcall.getMethodname();
		
		//鍒╃敤鍙橀噺琛紝灏唎bjname杞垚鐗瑰畾瀵硅薄
		VarValue varvalue = vartable.getVarValue(objname);
		consoleLog("var data:"+varvalue.getVarsvalue());
		
		Object targetobj = UuidObjectManager.getObject(varvalue.getVarsvalue());
		if(targetobj != null) {
				JavaVMImpl jvm = new JavaVMImpl();
				Object retobj = jvm.objectCall(targetobj, methodname);
				
				Window.alert(retobj.toString());
				//鏍规嵁杩斿洖鍊兼潵缁勭粐杩斿洖鍊�
				//濡傛灉杩斿洖鍊兼槸Integer
				if(retobj instanceof Integer) {
					Window.alert("is int.");
					VarValue varvalue1 = new VarValue();
					varvalue1.setVarname("ret");
					varvalue1.setVartype("int");
					varvalue1.setVarsvalue(""+(int)retobj);
					return varvalue1;
				}
				if(retobj instanceof Long) {
					Window.alert("is long.");
					VarValue varvalue2 = new VarValue();
					varvalue2.setVarname("ret");
					varvalue2.setVartype("long");
					varvalue2.setVarsvalue(""+(long)retobj);
					return varvalue2;
				}
				if(retobj instanceof Float) {
					Window.alert("is float.");
					VarValue varvalue3 = new VarValue();
					varvalue3.setVarname("ret");
					varvalue3.setVartype("float");
					varvalue3.setVarsvalue(""+(float)retobj);
					return varvalue3;
				}
				if(retobj instanceof Double) {
					Window.alert("is double.");
					VarValue varvalue4 = new VarValue();
					varvalue4.setVarname("ret");
					varvalue4.setVartype("double");
					varvalue4.setVarsvalue(""+(double)retobj);
					return varvalue4;
				}
				if(retobj instanceof Boolean) {
					Window.alert("is boolean.");
					VarValue varvalue5 = new VarValue();
					varvalue5.setVarname("ret");
					varvalue5.setVartype("boolean");
					varvalue5.setVarsvalue(""+(boolean)retobj);
					return varvalue5;
				}
				if(retobj instanceof String) {
					Window.alert("is String.");
					VarValue varvalue6 = new VarValue();
					varvalue6.setVarname("ret");
					varvalue6.setVartype("String");
					varvalue6.setVarsvalue(""+(String)retobj);
					return varvalue6;
				}

				String uuid = uuid();
				
				UuidObjectManager objmanage = new UuidObjectManager();
				objmanage.setObject(uuid, retobj);
				
				VarValue varvalue1 = new VarValue();
				varvalue1.setVarname("ret");
				varvalue1.setVartype("Object");
				varvalue1.setVarsvalue(uuid);
				
				return varvalue1;
			
		}else {
			eval(objname,methodname);
			return new VarValue();
		}

	}

	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "[ObjectCallEvalPlugin]:" + message );
	}-*/;

	public native void eval(String obj,String method) /*-{
	//alert(message);
	var strcommand = obj+"."+method;
	alert(strcommand);
	var abc = $wnd.eval(strcommand);
	alert(abc);
	}-*/;

	
	public void callWebUIAppFunction(Object obj,String method) {
		//WebUIApp app = (WebUIApp) obj;
		//if(method.equals("getCount")) {
		//	int i = app.getCount();
		//}
	}
	
	public native String uuid() /*-{
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
	var r = Math.random() * 16 | 0,
	v = c == 'x' ? r : (r & 0x3 | 0x8);
	return v.toString(16);
	});
	}-*/;
}
