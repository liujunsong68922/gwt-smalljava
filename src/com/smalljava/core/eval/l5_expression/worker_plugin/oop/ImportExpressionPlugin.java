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
 * import 表达式的处理插件，
 * import 表达式作用于当前变量表Vartable上
 * import 表达式作用于当前变量表中的classtablename上，默认为"SYSTEM_CLASSTABLE"
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
			//这里存在一个如何解决不同作用域的重名问题
			//这一问题下一步需要考虑得到解决
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
				HashMap<String,String> classnamemap = (HashMap<String,String>) obj2;
				String shortclassname = this.getShortName(classname);
				
				if(! classnamemap.containsKey(shortclassname)) {
						classnamemap.put(shortclassname, classname);
						logger.info("[info] classname set ok.");
				}else {
					logger.info("[info] import statement not execute.classname has been registered."
							+shortclassname);
				}
			}
			//return a new VarValue,means execute ok.
			return new VarValue();
		}
		return null;
	}

	private String getShortName(String classname) {
		String sname[] = classname.split(".");
		if(sname.length>0) {
			return sname[sname.length-1];
		}else {
			return "";
		}
	}
}
