package com.smalljava.core.l6_supportenv.l6_classsupport;

import java.util.ArrayList;
import java.util.HashMap;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.instancevo.JavaClassInstanceVO;
import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;
import com.smalljava.core.l6_supportenv.IClassObject;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;

public class SmallJavaIClassImpl implements IClassObject {
	private Logger logger = LoggerFactory.getLogger(SmallJavaIClassImpl.class);
	
	/**
	 * class内部定义对应的hashmap，用来映射shortname,fullname.
	 * MEMO:这个map由import语句解析加载得到
	 */
	private HashMap<String,String> classnamemap = new HashMap<String,String>();
	private SmallJavaClassTemplateVO templatevo = null;
	private JavaClassInstanceVO instancevo = null;
	
	public SmallJavaClassTemplateVO getTemplatevo() {
		return templatevo;
	}

	public void setTemplatevo(SmallJavaClassTemplateVO templatevo) {
		this.templatevo = templatevo;
	}

	public JavaClassInstanceVO getInstancevo() {
		return instancevo;
	}

	public void setInstancevo(JavaClassInstanceVO instancevo) {
		this.instancevo = instancevo;
	}

	@Override
	public String getClassFullname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VarValue newInstance(String classname, SmallJavaClassSupportEnv classenv, SmallJavaOopSupportEnv oopenv) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VarValue objPropertyGet(String classname, Object target, String propertyname,
			SmallJavaClassSupportEnv classenv, SmallJavaOopSupportEnv oopenv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VarValue classPropertyGet(String classname, IClassObject target, String propertyname,
			SmallJavaClassSupportEnv classenv, SmallJavaOopSupportEnv oopenv) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean importClassDefine(String fullname) {
		String shortname = this.getShortName(fullname);
		if(shortname.length()>0) {
			this.classnamemap.put(shortname, fullname);
			return true;
		}else {
			logger.error("[ERROR] import class define failed."+fullname);
			return false;
		}
	}
	
	private String getShortName(String fullname) {
		String sname[]= fullname.split(".");
		if(sname.length>0) {
			return sname[sname.length-1];
		}else {
			return "";
		}
	}

}
