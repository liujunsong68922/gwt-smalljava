package com.smalljava.core.eval.l5_expression.plugin.oop;

//import java.util.UUID;

import com.smalljava.core.common.UUIDFunction;
import com.smalljava.core.common.UuidObjectManager;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.instancevo.JavaClassInstanceVO;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.oop.NewOperElement;
import com.smalljava.core.eval.l5_expression.IExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.NewInstancePluginManager;
//import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * NewOperEvalPlugin is designed to support {new} keyword. SmallJava using {new}
 * keyword to create a new object according {class}. SmallJava support the
 * following class type 1. java.lang.* and java.util.* class 2. if using
 * javavm,support create new object using jvm function 3. if using gwt,support
 * using gwt.ui class to create object 4. if using smalljava special class,
 * create a new object in smalljava itself.
 * 
 * @author liujunsong
 *
 */
public class NewOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(NewOperEvalPlugin.class);

	@SuppressWarnings("rawtypes")
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		if (root == null || vartable == null) {
			return null;
		}
		if(classenv == null || oopenv == null) {
			return null;
		}

		if (root instanceof NewOperElement) {
			NewOperElement newoper = (NewOperElement) root;
			String classname = newoper.getClassname();
			
			//if this classname is register by classenv
			//then using classenv to create it.
			if(classenv.getClassmanager().getClassdefinemap().containsKey(classname)) {
				//调用接口，创建对象
				JavaClassInstanceVO vo = classenv.newInstance(classname);
				String uuid = this.uuid();
				
				UuidObjectManager.setObject(uuid, vo);
				VarValue vvalue1 = new VarValue();
				vvalue1.setVartype(classname);
				vvalue1.setVarsvalue(uuid);
				return vvalue1;
			}
			
			//in other condition,using OopPluginmanager
			NewInstancePluginManager manager = oopenv.getNewinstanceManager();
			try {
				Object obj1 = manager.newInstance(classname);
				String uuid = uuid();
				UuidObjectManager.setObject(uuid, obj1);
				VarValue vvalue = new VarValue();
				vvalue.setVartype(classname);
				vvalue.setVarsvalue(uuid);
				return vvalue;
			} catch (Exception e) {
				logger.error("[ERROR]error when create new instance");
				e.printStackTrace();
				return null;
			}
		}else {
			//This is not a new operation.
			return null;
		}
	}

	private String uuid() {
		UUIDFunction uuidf = new UUIDFunction();
		String uuid = uuidf.uuid();
		return uuid;
	}

}
