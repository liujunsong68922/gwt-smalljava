package com.smalljava.workflow.core.engine;

import java.util.HashMap;
import java.util.Iterator;

import com.smalljava.core.analyse.l5_expression.ExpressionASTAnalyse;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.eval.l5_expression.SmallJavaExpressionEval;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
import com.smalljava.core.l9_space.vartable.IVarTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassStaticVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L3_HashMapMethodInstanceVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L4_HashMapBlockVarTableImpl;
//import com.smalljava.test.expresstest.TestMainFrame;
import com.smalljava.workflow.core.vo.element.Transmition;

/**
 * 将转换对象转换成一个表达式，并进行运算评估
 * 此处需要调用smalljava的java表达式评估器来进行评估
 * @author liujunsong
 *
 */
public class TransmitionEval {
	Logger logger = LoggerFactory.getLogger(TransmitionEval.class);
	public boolean evaltrans(Transmition transvo,HashMap<String,VarValue> argmap) {
		//step1: 判断transvo是否有条件
		//如果无条件，则返回true.
		if(transvo.getStrcondition().equals("")) {
			logger.info("[info] This is nocondition transmition.");
			return true;
		}
		
		//step2:如果有条件，则将传入的HashMap条件来进行替换，替换以后得到一个字符串
		//这里需要考虑调用smalljava expression表达式来进行求值。
		//传入的hashmap的参数都按照字符串处理，将参数传入表达式中。
		//未来可以考虑在初始化实例时完成AST的解析工作。(TODO)
		String strcondition = transvo.getStrcondition();
		logger.info("[info] condition:"+strcondition);
		//step3.调用表达式解析器，解析strcondtion为抽象语法树对象
		
		ExpressionASTAnalyse eanlyse = new ExpressionASTAnalyse();

		RootAST root = eanlyse.analyse(strcondition);
		if (root == null) {
			logger.error("[ERROR] conditon ast failed.");
			return false;
		}else {
			String s1 = root.getShowString(0);
			logger.info("[info] condition ast analyse ok."+s1);
		}
		
		//step4.调用表达式计算器来进行计算，这里需要把传入的argmap转成特定的内部vartable.
		L2_HashMapClassStaticVarTableImpl l2_static = new L2_HashMapClassStaticVarTableImpl("");
		L2_HashMapClassInstanceVarTableImpl l2_instance = new L2_HashMapClassInstanceVarTableImpl("l2",l2_static);
		L3_HashMapMethodInstanceVarTableImpl l3 = new L3_HashMapMethodInstanceVarTableImpl("",l2_instance);
		L4_HashMapBlockVarTableImpl l4 = new L4_HashMapBlockVarTableImpl("",l3);
		IVarTable vartable=l4;
		//vartable.defineVar("i","int");
		//vartable.defineVar("map1", "HashMap");
		
		//put argmap into vartable.
		Iterator iter = argmap.keySet().iterator();
		while(iter.hasNext()) {
			//这里有个基本假设，传入的argmap目前只支持字符串的key
			//传入的value对象是已经封装为VarValue对象，不支持Object
			String key = iter.next().toString();
			VarValue value = argmap.get(key);
			//写入变量表中
			vartable.defineVar(key, value.getVartype());
			vartable.setVarValue(key, value);
			logger.info("[info] vartable set ok.");
		}
		
		SmallJavaExpressionEval eval = new SmallJavaExpressionEval();
		VarValue vv = eval.eval(root, vartable,
				new SmallJavaClassSupportEnv(),
				new SmallJavaOopSupportEnv());
		
		//判断表达式计算的结果，逻辑上应该返回一个boolean值
		if(vv==null) {
			logger.error("[ERROR] condition eval failed.");
			return false;
		}else {
			logger.info("[info] condition eval return:"+vv.getVarsvalue());
			if(vv.getVarsvalue().equalsIgnoreCase("true")) {
				//布尔值评估成功
				return true;
			}else {
				//布尔值评估失败
				return false;
			}
		}
	}
}
