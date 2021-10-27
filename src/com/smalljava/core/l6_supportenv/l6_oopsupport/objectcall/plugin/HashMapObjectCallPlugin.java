package com.smalljava.core.l6_supportenv.l6_oopsupport.objectcall.plugin;

import java.util.ArrayList;
import java.util.HashMap;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_supportenv.l6_oopsupport.objectcall.IObjectCall;

public class HashMapObjectCallPlugin implements IObjectCall {


	
	private int size(Object target) {
		if (target instanceof HashMap) {
			HashMap map1 = (HashMap) target;
			return map1.size();
		}else {
			System.out.println("Error: target object is not HashMap.");
			return 0;
		}
	}

	@Override
	public Object objcall(String classname, Object target, String methodname, ArrayList<VarValue> args) {
		// TODO Auto-generated method stub
		return null;
	}

}
