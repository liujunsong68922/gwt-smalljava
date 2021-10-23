package com.smalljava.core.l4_oopsupport.newinstance.javaplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l4_oopsupport.newinstance.INewInstance;

public class StringNewInstance implements INewInstance {

	@Override
	public Object newInstance(String classname) {
		return new String();
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		if(args.size()==1) {
			VarValue arg1 = args.get(0);
			String s1 = arg1.getVarsvalue();
			if(arg1.getVartype().equals("String")) {
				return newString(s1);
			}
			if(arg1.getVartype().equals("int")) {
				return newString(s1);
			}
			if(arg1.getVartype().equals("long")) {
				return newString(s1);
			}
			if(arg1.getVartype().equals("float")) {
				return newString(s1);
			}
			if(arg1.getVartype().equals("double")) {
				return newString(s1);
			}
		}
		
		//in other condition,return empty string.
		return new String("");
	}
	
	public Object newString(String s1) {
		return new String(s1);
	}
}
