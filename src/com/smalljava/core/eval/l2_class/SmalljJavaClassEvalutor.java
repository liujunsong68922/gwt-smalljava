package com.smalljava.core.eval.l2_class;

import java.util.ArrayList;

import com.smalljava.core.analyse.l4_block.BlockAnalyse;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l2_javaclass.AbstractSmallJavaClassElement;
import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;
import com.smalljava.core.commonvo.l2_javaclass.element.SmallJavaClassMethodElement;
import com.smalljava.core.commonvo.l2_javaclass.element.SmallJavaClassVarDefineElement;
import com.smalljava.core.commonvo.l3_javamethod.SmallJavaMethodArgumentVO;
import com.smalljava.core.commonvo.l3_javamethod.SmallJavaMethodRootVO;
import com.smalljava.core.commonvo.l4_block.BasicBlock;
import com.smalljava.core.eval.l4_block.SmallJavaBlockEvaluator;
import com.smalljava.core.l6_supportenv.l6_classsupport.SmallJavaClassSupportEnv;
import com.smalljava.core.l6_supportenv.l6_oopsupport.SmallJavaOopSupportEnv;
import com.smalljava.core.l9_space.vartable.IVarTable;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L2_HashMapClassStaticVarTableImpl;
import com.smalljava.core.l9_space.vartable.hashmapimpl.L3_HashMapMethodInstanceVarTableImpl;

/**
 * class的评估器，针对class进行评估
 * 
 * @author liujunsong
 *
 */
public class SmalljJavaClassEvalutor {
	Logger logger = LoggerFactory.getLogger(SmalljJavaClassEvalutor.class);

	/**
	 * MEMO：从对象的静态变量表中检索对应的变量值返回
	 * 
	 * @param classvo
	 * @param propertyname
	 * @return
	 */
	public VarValue classPropertyGet(SmallJavaClassTemplateVO classvo, String propertyname) {
		if(classvo == null) {
			logger.error("[error][Argument error] classvo is null.");
			return null;
		}
		IVarTable vartable = classvo.getStaticvartable();
		if(vartable == null) {
			logger.error("[error] classvo donot contain static vartable.");
			return null;
		}
		VarValue vvalue = vartable.getVarValue(propertyname);
		if(vvalue == null) {
			logger.error("[error] variable not define."+propertyname);
		}
		return vvalue;

	}

	/**
	 * static method call
	 * 
	 * @param classvo    类的VO对象
	 * @param methodname 方法名
	 * @param arglist    调用参数
	 * @return
	 */
	public VarValue classStaticMethod(SmallJavaClassTemplateVO classvo, String methodname, ArrayList<VarValue> arglist,
			SmallJavaClassSupportEnv classenv, SmallJavaOopSupportEnv oopenv) {
		SmallJavaClassMethodElement method = this._getClassStaticMethod(classvo, methodname, arglist);
		if (method == null) {
			// not found method define
			// call failed.
			logger.error("[error]method is null.");
			return null;
		}

		String strcontent = method.getMethodvo().getMethodContent();
		logger.info("[info]method content:" + strcontent);

		// 分析方法字符串,使用BlockAnalyse
		BasicBlock closedblock = new BasicBlock("", strcontent, null);
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		if (!isok) {
			// analyse block failed.
			logger.error("[ERROR] Call block analyse failed." + strcontent);
			return null;
		} else {
			// analyse block success.
			SmallJavaBlockEvaluator blockeval = new SmallJavaBlockEvaluator();

			// 构建变量表
			L2_HashMapClassStaticVarTableImpl vartable1 = classvo.getStaticvartable();
			L3_HashMapMethodInstanceVarTableImpl vartable3 = new L3_HashMapMethodInstanceVarTableImpl("", vartable1);
			boolean b2 = false;
			try {
				b2 = blockeval.execute(closedblock, vartable3, classenv, oopenv);
				if (!b2) {
					logger.error("[ERROR] error when eval blockeval");
					return null;
				} else {
					// 调用block执行成功
					// block执行结束以后，从变量表中检索return
					VarValue retvalue = vartable3.getVarValue("return");
					if (retvalue == null) {
						logger.error("[ERROR] class method call ok,while return value is null.");
						return null;
					} else {
						// 从变量表中读取[return]的对象，作为调用的返回值
						return retvalue;
					}
				}

			} catch (Exception e) {
				logger.error("[ERROR] error when eval class method.");
				e.printStackTrace();
				b2 = false;
				return null;
			}

		}
	}

	/**
	 * MEMO: 从classvo里面按照方法名和参数类型的签名来检索符合条件的方法定义 MEMO: 暂时不考虑数据类型自动扩展升级的问题 MEMO：
	 * 暂时不考虑作为类定义参数的包名匹配问题
	 * 
	 * @return
	 */
	private SmallJavaClassMethodElement _getClassStaticMethod(SmallJavaClassTemplateVO classvo, String methodname,
			ArrayList<VarValue> arglist) {

		ArrayList<SmallJavaClassMethodElement> staticmethodlist = new ArrayList<SmallJavaClassMethodElement>();
		for (AbstractSmallJavaClassElement child : classvo.getChildren()) {
			if (child instanceof SmallJavaClassMethodElement) {
				SmallJavaClassMethodElement method = (SmallJavaClassMethodElement) child;
				if (method.getStringcontent().contains("static")) {
					staticmethodlist.add(method);
				}
			}
		}

		if (staticmethodlist.size() == 0) {
			// 未找到符合条件的Method定义
			return null;
		}

		// 计算输入的方法签名
		String inputsignature = methodname;
		for (VarValue arg : arglist) {
			inputsignature += "/" + arg.getVartype();
		}
		logger.info("[info]Siganature:" + inputsignature);

		// 循环判断方法是否符合条件
		for (SmallJavaClassMethodElement method1 : staticmethodlist) {
			SmallJavaMethodRootVO methodvo = method1.getMethodvo();
			if (methodvo == null) {
				continue;
			} else {
				String methodsign = methodvo.getMethodname();
				for (SmallJavaMethodArgumentVO arg1 : methodvo.getArgArray()) {
					methodsign += "/" + arg1.getArgtype();
				}

				// 判断两个方法的签名是否相同
				if (inputsignature.equals(methodsign)) {
					return method1;
				}

			}
		}
		logger.info("[info]method not found." + methodname);
		return null;
	}

	/**
	 * MEMO：调用类模板的初始化方法，初始化类的静态变量表
	 * 
	 * @param classvo
	 */
	public boolean classInitVartable(SmallJavaClassTemplateVO classvo, SmallJavaClassSupportEnv classenv,
			SmallJavaOopSupportEnv oopenv) {

		// 定义代表static 变量定义的语句
		String strStaticDefine = "";
		for (AbstractSmallJavaClassElement child : classvo.getChildren()) {
			if (child instanceof SmallJavaClassVarDefineElement) {
				SmallJavaClassVarDefineElement define = (SmallJavaClassVarDefineElement) child;
				if (define.getStringcontent().contains("static ")) {
					strStaticDefine += define + ";";
				}
			}
		}
		logger.info("[info]strStaticDefine: " + strStaticDefine);
		if (strStaticDefine.length() == 0) {
			return true;
		}

		// 分析方法字符串,使用BlockAnalyse
		BasicBlock closedblock = new BasicBlock("", strStaticDefine, null);
		BlockAnalyse ba = new BlockAnalyse();

		boolean isok = ba.analyse(closedblock);
		if (!isok) {
			// analyse block failed.
			logger.error("[ERROR] Call block analyse failed." + strStaticDefine);
			return false;
		} else {
			// analyse block success.
			SmallJavaBlockEvaluator blockeval = new SmallJavaBlockEvaluator();

			// 构建变量表
			L2_HashMapClassStaticVarTableImpl vartable1 = classvo.getStaticvartable();
			L3_HashMapMethodInstanceVarTableImpl vartable3 = new L3_HashMapMethodInstanceVarTableImpl("", vartable1);
			boolean b2 = false;
			try {
				b2 = blockeval.execute(closedblock, vartable3, classenv, oopenv);
				if (!b2) {
					logger.error("[ERROR] error when eval blockeval");
					return false;
				} else {
					// 调用block执行成功
					// block执行结束以后，从变量表中检索return
					VarValue retvalue = vartable3.getVarValue("return");
					if (retvalue == null) {
						logger.error("[ERROR] class method call ok,while return value is null.");
						return false;
					} else {
						// 从变量表中读取[return]的对象，作为调用的返回值
						return true;
					}
				}

			} catch (Exception e) {
				logger.error("[ERROR] error when eval class method.");
				e.printStackTrace();
				b2 = false;
				return false;
			}

		}
	}
}
