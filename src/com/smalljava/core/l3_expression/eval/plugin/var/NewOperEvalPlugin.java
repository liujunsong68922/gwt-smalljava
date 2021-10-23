package com.smalljava.core.l3_expression.eval.plugin.var;

//import java.util.UUID;

import com.smalljava.core.common.UUIDFunction;
import com.smalljava.core.common.UuidObjectManager;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l3_expression.eval.IExpressionEval;
import com.smalljava.core.l3_expression.vo.RootAST;
import com.smalljava.core.l3_expression.vo.var.NewOperElement;
import com.smalljava.core.l4_oopsupport.newinstance.NewInstancePluginManager;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * NewOperEvalPlugin is designed to support {new} keyword.
 * SmallJava using {new} keyword to create a new object according {class}.
 * SmallJava support the following class type
 * 1. java.lang.* and java.util.* class
 * 2. if using javavm,support create new object using jvm function
 * 3. if using gwt,support using gwt.ui class to create object
 * 4. if using smalljava special class, create a new object in smalljava itself.
 * @author liujunsong
 *
 */
public class NewOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(NewOperEvalPlugin.class);

	@SuppressWarnings("rawtypes")
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		//nullֵ value check
		if(root == null || vartable == null || classtable == null) {
			return null;
		}
		
		if(root instanceof NewOperElement) {
			NewOperElement newoper = (NewOperElement) root;
			String classname = newoper.getClassname();
			//retrive Class from classtable.
			Class class1 = classtable.getClass(classname);
			
			NewInstancePluginManager manager = new NewInstancePluginManager();
			
			if(class1 == null) {
				logger.error("[ERROR]classname is not found."+classname);
				return null;
			}else {
				try {
					
					//Object obj1 = class1.newInstance();
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
			}
		}
		return null;
	}
	
	private String uuid() {
		UUIDFunction uuidf = new UUIDFunction();
		String uuid = uuidf.uuid();
		return uuid;
	}

}
