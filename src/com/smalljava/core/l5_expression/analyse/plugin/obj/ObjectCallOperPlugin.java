package com.smalljava.core.l5_expression.analyse.plugin.obj;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.obj.ObjectCallOperElement;

/**
 * MEMO:对象调用元素的识别
 * 
 * @author liujunsong
 *
 */
public class ObjectCallOperPlugin extends DefaultIPluginImplement {
	private Logger logger = LoggerFactory.getLogger(ObjectCallOperPlugin.class);

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if (strcode.length() == 0) {
			return null;
		}
		char char1 = strcode.charAt(0);
		if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z')) {
			// 判断其中有无其他的运算符
			String opers[] = new String[] { "+", "-", "*", "/", ">=", "==", "<=", ">", "<", "&&", "||", "!" };
			AstOperAndPos oap = getFirstOperCode(strcode, opers);
			if (oap != null) {
				// 有任何一个运算符，不处理
				return null;
			} else {
				// 判断变量中是否带有.运算符
				int ipos = strcode.indexOf('.');
				if (ipos < 0) {
					return null;
				} else {
					// 定义这是一个对象调用
					ObjectCallOperElement objcall = new ObjectCallOperElement();
					String objname = strcode.substring(0, ipos);
					logger.info("objname:" + objname);
					objcall.setObjname(objname);

					String strleft = strcode.substring(ipos + 1);
					logger.info("strleft:" + strleft);
					// 查找()
					// 这里做一个简单化处理，假设对象的方法调用中没有子方法
					// 后期再考虑进行复杂化的处理
					int ipos1 = strleft.indexOf("(");
					int ipos2 = strleft.indexOf(")");

					if (ipos1 > 0 && ipos2 > ipos1) {
						String methodname = strleft.substring(0, ipos1);
						logger.info("methodname:" + methodname);
						objcall.setMethodname(methodname);

						String args = strleft.substring(ipos1 + 1, ipos2);
						logger.info("args:{" + args + "}");
						objcall.setArgs(args);
						if (args.length() > 0) {
							String argarray[] = args.split(",");
							for (String arg : argarray) {
								// 将每个参数设置成一个childNode.
								// WARNING:目前我们假设所有参数里面不带(),否则会有错误
								logger.info("---->arg:" + arg);
								RootAST argelement = new MiddleAST();
								argelement.setStrexpression(arg);
								// 加入子节点中
								objcall.getChildren().add(argelement);
							}
						}
						logger.info("analyse ok.");
						return objcall;
					} else {
						logger.info("[ERROR]Cannot find ( and )");
						return null;
					}
				}
			}

		}
		return null;
	}

}
