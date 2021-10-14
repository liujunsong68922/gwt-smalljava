package com.smalljava.core.l5_expression.eval.plugin.obj;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

import com.smalljava.core.common.UUIDFunction;
import com.smalljava.core.common.UuidObjectManager;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.eval.ExpressionEval;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.obj.ObjectCallOperElement;
import com.smalljava.core.l6_vm.objectcall.ObjectCallManager;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * Java���󷽷����õ�ִ�в��
 * 
 * @author liujunsong
 *
 */
public class ObjectCallEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(ObjectCallEvalPlugin.class);

	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		if (root == null) {
			logger.error("root is null");
			return null;
		}

		if (!(root instanceof ObjectCallOperElement)) {
			// call argument error.
			logger.error("root is not ObjectCallOperElement");
			return null;
		} else {
			// ��ʱ�������Զ���class������
			// �Զ���class�Ĳ�����Ҫ�����µĴ�����д���
			// Ŀǰֻ����java������Ѿ�֧�ֵ���Ĵ���
			ObjectCallOperElement objcall = (ObjectCallOperElement) root;
			String objname = objcall.getObjname();
			logger.info("objname:" + objname);

			// ���ȷ��ʱ�������ȡ����VarValue
			VarValue objvar = vartable.getVarValue(objname);
			if (objvar == null) {
				logger.error("[ERROR] find var in vartable is null:" + objname);
				return null;
			}
			String classname = objvar.getVarsvalue();
			String objuuid = objvar.getVarsvalue();
			Object targetobj = UuidObjectManager.getObject(objuuid);
			if (targetobj == null) {
				// �˴�����ִ�з����˴���
				logger.error("[ERROR]targetobj is null object.");
				return null;
			}

			// ���ӽڵ㾭������ת����callarg
			ArrayList<VarValue> arglist = new ArrayList<VarValue>();
			for (RootAST child : root.getChildren()) {
				ExpressionEval eval = new ExpressionEval();
				VarValue vvalue1 = eval.eval(child, vartable, classtable);
				logger.info("child return:" + vvalue1.toJSONString());

				// ������ֵд��callarg
				arglist.add(vvalue1);
			}
			return this.objectcall(classname,targetobj, objname, arglist);

			// logger.error("[ERROR] object call error happened.");
			// return null;
		}
	}

	public VarValue objectcall(String classname,Object targobj, String methodname, ArrayList<VarValue> arglist) {

		// step1. get Method
		int argnum = arglist.size();
		//Method mm = this.getMethod(targobj, methodname,argnum);

//		if (mm == null) {
//			logger.error("[ERROR] get targetobj's methodname failed." + methodname);
//		}
		// Step2. call objects by method

		Object args = null;
		Object retobj;
		try {
			//retobj = mm.invoke(targobj, args);
			ObjectCallManager manager = new ObjectCallManager();
			retobj = manager.objcall(classname, targobj, methodname, args);
		
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
			//String uuid = UUID.randomUUID().toString().replaceAll("-", "");
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

	/**
	 * MEMO������JAVA�ķ�����ƣ���Object�����ȡ��һ�����ϵ�Method������ MEMO:
	 * ��һ������Ҫ�õ�Java���Ե��ض����ԣ���˲���ֱ��Ǩ�Ƶ��������ԡ� MEMO: ��ʱ�����Ƕ�����õ����ط�����ͬ�����������ٿ��ǽ���ת��֧��
	 * 
	 * @param obj
	 * @param methodname
	 * @param argnum 
	 * @return
	 */
//	private Method getMethod(Object obj, String methodname, int argnum) {
//		// TODO: convert object to Method
//		Method methods[] = obj.getClass().getMethods();
//		for(Method m1 : methods) {
//			if(! m1.getName().equals(methodname)) {
//				continue;
//			}
//			//method name match
//			logger.info("method name match."+methodname);
//			//����ֻ����һ���������жϣ����ǲ���������
//			//��ʱ�����ǽ����������͵Ķ����ж�
//			if(m1.getParameterCount() == argnum) {
//				logger.info("find argnum match:"+argnum);
//				return m1;
//			}
//		}
//		logger.info("Cannot find match method.");
//		return null;
//	}
}
