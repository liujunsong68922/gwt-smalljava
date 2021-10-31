package com.smalljava.core.eval.l5_expression.worker_plugin.oop;

import java.util.HashMap;

import com.smalljava.core.common.UUIDFunction;
import com.smalljava.core.common.UuidObjectManager;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.oop.ImportExpressionElement;
import com.smalljava.core.eval.l5_expression.ISmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * import 表达式的处理插件
 * @author liujunsong
 *
 */
public class ImportExpressionPlugin implements ISmallJavaExpressionEval {
	private Logger logger = LoggerFactory.getLogger(ImportExpressionPlugin.class);
	
	private final String classtablename = "SYSTEM_CLASSTABLE";
	
	@SuppressWarnings("unchecked")
	@Override
	public VarValue eval(RootAST root, IVarTable vartable, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {
		// TODO Auto-generated method stub
		if (root instanceof ImportExpressionElement) {
			ImportExpressionElement importelement = (ImportExpressionElement) root;
			String importstatement = importelement.getStrexpression();
			if(importstatement.startsWith("import")) {
				importstatement = importstatement.substring(6);
			}
			String classname = importstatement.trim();
			UUIDFunction uuidfunction = new UUIDFunction();
			UuidObjectManager uuidobjmanager = new UuidObjectManager();
			//import语句执行以后，要在执行环境中设置一个class映射的mapping
			//这个mapping现在设计挂载在变量表上
			if (! vartable.isValid(classtablename)) {
				vartable.defineVar(this.classtablename, "HashMap");
				HashMap<String,String> map1 = new HashMap<String,String>();
				String uuid = uuidfunction.uuid();
				uuidobjmanager.setObject(uuid, map1);
				VarValue vvalue = new VarValue();
				vvalue.setVarname("");
				vvalue.setVarsvalue(uuid);
				vvalue.setVartype("HashMap");
				//存储变量
				vartable.setVarValue(this.classtablename, vvalue);
			}
			VarValue varvalue2 = vartable.getVarValue(this.classtablename);
			if(varvalue2 == null) {
				logger.error("[ERROR] varvalue2 is null");
				return null;
			}else {
				String uuid = varvalue2.getVarsvalue();
				Object obj2 = uuidobjmanager.getObject(uuid);
				HashMap<String,String> map2 = (HashMap<String,String>) obj2;
				if(! map2.containsKey(classname)) {
					String shortname = this.getShortName(classname);
					if(shortname.length()>0) {
						map2.put(classname, shortname);
						map2.put(shortname, classname);
						logger.info("[info] classname set ok.");
					}
				}
			}
			//return a new VarValue,means execute ok.
			return new VarValue();
		}
		return null;
	}

	private String getShortName(String classname) {
		
		return "";
	}
}
