package com.smalljava.core.eval.l5_expression.worker_plugin.oop;

import java.util.ArrayList;

import com.smalljava.core.common.UUIDFunction;
import com.smalljava.core.common.UuidObjectManager;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.oop.ObjectCallOperElement;
import com.smalljava.core.eval.l5_expression.SmallJavaExpressionEval;
import com.smalljava.core.eval.l5_expression.ISmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.objectcall.ObjectCallPluginManager;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class ObjectCallEvalPlugin implements ISmallJavaExpressionEval {
	private Logger logger = LoggerFactory.getLogger(ObjectCallEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable,
			SmallJavaClassSupportEnv classenv,
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

			// ���ӽڵ㾭������ת����callarg
			ArrayList<VarValue> arglist = new ArrayList<VarValue>();
			for (RootAST child : root.getChildren()) {
				SmallJavaExpressionEval eval = new SmallJavaExpressionEval();
				VarValue vvalue1 = eval.eval(child, vartable, classenv,oopenv);
				logger.info("child return:" + vvalue1.toJSONString());

				// ������ֵд��callarg
				arglist.add(vvalue1);
			}
			return this.objectcall(classname,targetobj, methodname, arglist,oopenv);

		}
	}

	public VarValue objectcall(String classname,Object targobj, String methodname, ArrayList<VarValue> arglist,
			SmallJavaOopSupportEnv oopenv) {

		// step1. get Method
		int argnum = arglist.size();
		System.out.println("argnum:"+argnum);
		Object retobj;
		try {
			ObjectCallPluginManager manager = oopenv.getObjectcallManager();
			retobj = manager.objcall(classname, targobj, methodname, arglist);
		
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
