package com.smalljava.core.classloader.l2_class.analyse.plugin.impl;

import com.smalljava.core.classloader.l2_class.analyse.plugin.IJavaClassAnalysePlugin;
import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassSingleLineMemoElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * 分析Java源代码文件中的单行注释
 * 
 * @author liujunsong
 *
 */
public class JavaClassSingleLineMemoPlugin implements IJavaClassAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaClassSingleLineMemoPlugin.class);

	@Override
	public AbstractJavaClassElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);

		// Step4.1 判断是不是 【//】 开始
		if (strdata.startsWith("//")) {
			// 读取到一行的结束点
			int ilineendpos = this._getFirstLineEndPos(strdata);
			String strmemo = "";
			if (ilineendpos == -1) {
				// 没有找到回车符，说明全部是备注
				strmemo = strdata;
				logger.info(strmemo);
				strdata = "";
				JavaClassSingleLineMemoElement element1 = new JavaClassSingleLineMemoElement();
				element1.setStringcontent(strmemo);
				element1.setComputeleftstring("");
				return element1;
			} else {
				strmemo = strdata.substring(0, ilineendpos);
				logger.info(strmemo);
				String strleftdata = strdata.substring(ilineendpos + 1);

				JavaClassSingleLineMemoElement element1 = new JavaClassSingleLineMemoElement();
				element1.setStringcontent(strmemo);
				element1.setComputeleftstring(strleftdata);

				return element1;
			}

		} else {
			return null;
		}
	}

	// 从字符串里面找到第一行结束点,通过查找\r来实现
	public int _getFirstLineEndPos(String s1) {
		int ipos = 0;
		for (ipos = 0; ipos < s1.length(); ipos++) {
			if (s1.charAt(ipos) == '\r') {
				return ipos;
			}
		}
		// 如果循环完毕也找不到结束点，说明没有回车符
		return -1;
	}

}
