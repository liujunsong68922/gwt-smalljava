package com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl;

import com.smalljava.core.classloader.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;
import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFileSingleLineMemoElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * 分析Java源代码文件中的单行注释
 * @author liujunsong
 *
 */
public class JavaFileSingleLineMemoPlugin implements IJavaFileAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaFileSingleLineMemoPlugin.class);
	
	@Override
	public AbstractJavaFileElement findFirstElement(String strdata) {
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
				logger.debug(strmemo);
				strdata = "";
				JavaFileSingleLineMemoElement element1 = new JavaFileSingleLineMemoElement();
				element1.setStringcontent(strmemo);
				element1.setComputeleftstring("");
				return element1;
			} else {
				strmemo = strdata.substring(0, ilineendpos);
				logger.debug(strmemo);
				String strleftdata = strdata.substring(ilineendpos + 1);
				
				JavaFileSingleLineMemoElement element1 = new JavaFileSingleLineMemoElement();
				element1.setStringcontent(strmemo);
				element1.setComputeleftstring(strleftdata);
				
				return element1;
			}
			
		}else {
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
