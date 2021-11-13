package com.smalljava.core.eval.l5_expression.worker_plugin.oop;


import com.smalljava.core.common.UUIDFunction;
import com.smalljava.core.common.UuidObjectManager;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.oop.ObjectCallOperElement;
import com.smalljava.core.eval.l5_expression.ISmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.IClassObject;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class ObjectPropertyGetPlugin implements ISmallJavaExpressionEval {
	private Logger logger = LoggerFactory.getLogger(ObjectCallEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if (root == null) {
			logger.error("root is null");
			return null;
		}

		if (!(root instanceof ObjectCallOperElement)) {
			// call argument error.
			logger.error("root is not ObjectCallOperElement");
			return null;
		} else {
			logger.info("This is ObjectCallOperElement.");
			ObjectCallOperElement objcall = (ObjectCallOperElement) root;
			String objname = objcall.getObjname();
			String methodname = objcall.getMethodname();
			logger.info("objname:" + objname);

			// ���ȷ��ʱ�������ȡ����VarValue
			VarValue objvar = vartable.getVarValue(objname);
			if (objvar == null) {
				logger.error("[ERROR] find var in vartable is null:" + objname);
				return null;
			}
			String classname = objvar.getVartype();
			String objuuid = objvar.getVarsvalue();
			UuidObjectManager uuidmanager = new UuidObjectManager();
			Object targetobj = uuidmanager.getObject(objuuid);
			if (targetobj == null) {
				// �˴�����ִ�з����˴���
				logger.error("[ERROR]targetobj is null object.");
				return null;
			}

			return this.objectPropertyGet(classname,targetobj, methodname, oopenv);

		}
	}
	
	public VarValue objectPropertyGet(String classname,Object targetobj, String propertyname,SmallJavaOopSupportEnv oopenv) {

		Object retobj;
		try {
			// ObjectPropertyGetPluginManager manager = oopenv.getObjectpropertyManager();
			IClassObject class1 = oopenv.getIClassByName(classname);
			retobj = class1.objPropertyGet(classname, targetobj, propertyname, null, oopenv);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if (retobj instanceof Integer) {
			VarValue var1 = new VarValue();
			var1.setVarname("");
			var1.setVartype("int");
			var1.setVarsvalue("" + retobj.toString());
			return var1;
		} else if (retobj instanceof Long) {
			VarValue var2 = new VarValue();
			var2.setVarname("");
			var2.setVartype("long");
			var2.setVarsvalue("" + retobj.toString());
			return var2;
		} else if (retobj instanceof Float) {
			VarValue var3 = new VarValue();
			var3.setVarname("");
			var3.setVartype("float");
			var3.setVarsvalue("" + retobj.toString());
			return var3;
		} else if (retobj instanceof Double) {
			VarValue var4 = new VarValue();
			var4.setVarname("");
			var4.setVartype("double");
			var4.setVarsvalue("" + retobj.toString());
			return var4;
		} else if (retobj instanceof String) {
			VarValue var4 = new VarValue();
			var4.setVarname("");
			var4.setVartype("String");
			var4.setVarsvalue("" + retobj.toString());
			return var4;
		} else if (retobj instanceof Object) {
			// create new uuid
			UUIDFunction uuidf = new UUIDFunction();
			String uuid = uuidf.uuid();
			UuidObjectManager uuidmanager = new UuidObjectManager();
			uuidmanager.setObject(uuid, retobj);

			VarValue var5 = new VarValue();
			var5.setVarname("");
			var5.setVartype("Object");
			var5.setVarsvalue(uuid);
			return var5;
		}

		return null;
	}

}
