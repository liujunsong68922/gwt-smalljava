package com.smalljava.core.l5_expression.analyse.plugin;

import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.IAstPlugin;

/**
 * MEMO：这个抽象类的设计目的是包装所有插件的公用方法，避免重复代码 MEMO：这个类没有具体的实现 MEMO：各个插件之间应该是完全正交的，不会相互影响
 * MEMO:各个插件不需要考虑别人的存在
 * 
 * @author liujunsong
 *
 */
public abstract class DefaultIPluginImplement implements IAstPlugin {
	private Logger logger = LoggerFactory.getLogger(DefaultIPluginImplement.class);

	/**
	 * 输入：nodeString 输出：比较多个同等优先级的操作符，返回第一个位置的运算符
	 * 
	 * @return
	 */
	public AstOperAndPos getFirstOperCode(String nodeString, String opers[]) {
		AstOperAndPos retaop = null;
		for (String oper1 : opers) {
			// 查找某一个操作符的位置
			AstOperAndPos aop1 = this.getFirstOperCode(nodeString, oper1);
			if (aop1 == null) {
				//logger.info("aop1 is null,nodeString:"+nodeString+"oper1:"+oper1);
				// 未找到继续循环
				continue;
			} else {
				if (retaop == null) {
					retaop = aop1;
				} else {
					// 比较两个aop的位置
					if (retaop.getIpos() > aop1.getIpos()) {
						retaop = aop1;
					}
				}
			}

		}

		return retaop;
	}

	/**
	 * 输入：nodeString 输出：比较多个同等优先级的操作符，返回第一个位置的运算符
	 * 
	 * @return
	 */
	public AstOperAndPos getLastOperCode(String nodeString, String opers[]) {
		AstOperAndPos retaop = null;
		for (String oper1 : opers) {
			// 查找某一个操作符的位置
			AstOperAndPos aop1 = this.getLastOperCode(nodeString, oper1);
			if (aop1 == null) {
				// 未找到继续循环
				continue;
			} else {
				if (retaop == null) {
					retaop = aop1;
				} else {
					// 比较两个aop的位置
					if (retaop.getIpos() < aop1.getIpos()) {
						retaop = aop1;
					}
				}
			}

		}

		return retaop;
	}

	/**
	 * 输入：nodeString 输出：查找到的第一个操作符（按照操作符的优先级别来查找）
	 * 
	 * @return
	 */
	public AstOperAndPos getFirstOperCode(String nodeString, String opers) {
		// 如果本身要查找的内容已经是一个操作符，则退出查找
		// 不要在一个运算符里面查找
		if (nodeString.equals("&&") || nodeString.equals("||") || nodeString.equals("==") || nodeString.equals(">=")
				|| nodeString.equals("<=")) {
			return null;
		}

//		int i = 0;
		StringFindUtil util = new StringFindUtil();
		int ipos = util.findfirstStringForAST(nodeString, opers);
		if (ipos >= 0) {

			AstOperAndPos oap = new AstOperAndPos();
			oap.setIpos(ipos);
			oap.setOpercode(opers);
			return oap;
		}

		// 找不到操作符，返回一个空值
		return null;
	}

	/**
	 * 输入：nodeString 输出：查找到的第一个操作符（按照操作符的优先级别来查找）
	 * 
	 * @return
	 */
	public AstOperAndPos getLastOperCode(String nodeString, String opers) {
		// 如果本身要查找的内容已经是一个操作符，则退出查找
		// 不要在一个运算符里面查找
		if (nodeString.equals("&&") || nodeString.equals("||") || nodeString.equals("==") || nodeString.equals(">=")
				|| nodeString.equals("<=")) {
			return null;
		}

//		int i = 0;
		StringFindUtil util = new StringFindUtil();
		int ipos = util.findLastStringForAST(nodeString, opers);
		if (ipos >= 0) {

			AstOperAndPos oap = new AstOperAndPos();
			oap.setIpos(ipos);
			oap.setOpercode(opers);
			return oap;
		}

		// 找不到操作符，返回一个空值
		return null;
	}

	/**
	 * 将字符串开始和结束位置的\r\n ,\r,空格都过滤掉
	 * 
	 * @param strinput
	 * @return
	 */
	public String trimReturnAndSpace(String strinput) {
		// String sout = "";
		// 先查找第一个不是\r\n \r 空格的位置
		int ipos = -1;
		for (int i = 0; i < strinput.length(); i++) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// 继续循环
				continue;
			} else {
				ipos = i;
				break;
			}
		}

		if (ipos == -1) {
			// 没有找到有效字符
			return "";
		}

		// 开始从后往前查找第一个有效字符
		int ipos2 = -1;
		for (int i = strinput.length() - 1; i >= 0; i--) {
			if (strinput.charAt(i) == '\r' || strinput.charAt(i) == '\n' || strinput.charAt(i) == ' ') {
				// 继续循环
				continue;
			} else {
				ipos2 = i;
				break;
			}
		}

		// 由于ipos有效，所以ipos2一定也是有效的
		if (ipos2 >= ipos) {
			return strinput.substring(ipos, ipos2 + 1);
		} else {
			logger.error("程序执行出现错误，需要查找问题所在.ipos,ipos2=" + ipos + "," + ipos2);
			return "";
		}
	}
}
