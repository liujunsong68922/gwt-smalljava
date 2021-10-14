package com.smalljava.core.l5_expression.eval.plugin.var;

import java.util.UUID;

import com.smalljava.core.common.UUIDFunction;
import com.smalljava.core.common.UuidObjectManager;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.eval.IExpressionEval;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.var.NewOperElement;
import com.smalljava.core.l6_vm.newinstance.NewInstancePluginManager;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.vartable.IVarTable;

public class NewOperEvalPlugin implements IExpressionEval {
	private Logger logger = LoggerFactory.getLogger(NewOperEvalPlugin.class);

	@SuppressWarnings("rawtypes")
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, IClassTable classtable) {
		//nullֵ���
		if(root == null || vartable == null || classtable == null) {
			return null;
		}
		
		if(root instanceof NewOperElement) {
			NewOperElement newoper = (NewOperElement) root;
			String classname = newoper.getClassname();
			//����classtable����ȡclass
			Class class1 = classtable.getClass(classname);
			
			NewInstancePluginManager manager = new NewInstancePluginManager();
			
			if(class1 == null) {
				logger.error("��ERROR�� classname not found."+classname);
				return null;
			}else {
				try {
					
					//Object obj1 = class1.newInstance();
					Object obj1 = manager.newInstance(classname);
					
					//String uuid = UUID.randomUUID().toString().replaceAll("-","");
					UUIDFunction uuidf = new UUIDFunction();
					String uuid = uuidf.uuid();
					
					UuidObjectManager.setObject(uuid, obj1);
					VarValue vvalue = new VarValue();
					vvalue.setVartype(classname);
					vvalue.setVarsvalue(uuid);
					return vvalue;
				} catch (Exception e) {
					logger.error("��ERROR��error when create new instance");
					e.printStackTrace();
					return null;
				} 
			}
		}
		return null;
	}

}
