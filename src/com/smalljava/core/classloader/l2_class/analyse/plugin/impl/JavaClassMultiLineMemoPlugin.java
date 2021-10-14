package com.smalljava.core.classloader.l2_class.analyse.plugin.impl;

import com.smalljava.core.classloader.l2_class.analyse.plugin.IJavaClassAnalysePlugin;
import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassMultiLineMemoElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

public class JavaClassMultiLineMemoPlugin implements IJavaClassAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaClassMultiLineMemoPlugin.class);
	
	@Override
	public AbstractJavaClassElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);
		
		// Step4.1 判断是不是 【/*】 开始
		if (strdata.startsWith("/*")) {
			// 读取到一行的结束点
			int ilineendpos = util.findfirstStringForBlock(strdata, "*/");
			String strmemo = "";
			if (ilineendpos == -1) {
				// 没有找到，说明逻辑错误
				logger.error("【ERROR】找不到*/");
				return null;
			} else {
				strmemo = strdata.substring(0, ilineendpos + 2);
				logger.debug(strmemo);
				
				String strleftstring;
				if(ilineendpos<strdata.length()-2) {
					strleftstring = strdata.substring(ilineendpos + 2);
				}else {
					strleftstring = "";
				}

				JavaClassMultiLineMemoElement ele = new JavaClassMultiLineMemoElement();
				ele.setStringcontent(strmemo);
				ele.setComputeleftstring(strleftstring);
				return ele;
			}
		} else {
			return null;
		}
	}


}
