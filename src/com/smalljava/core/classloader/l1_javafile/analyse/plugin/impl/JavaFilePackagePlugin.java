package com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl;

import com.smalljava.core.classloader.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;
import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFilePackageElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

public class JavaFilePackagePlugin implements IJavaFileAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaFilePackagePlugin.class);
	@Override
	public AbstractJavaFileElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);
		
		// Step4.1 判断是不是 【/*】 开始
		if (strdata.startsWith("package")) {
			// 读取到一行的结束点
			int ilineendpos = util.findfirstStringForBlock(strdata, ";");
			String strmemo = "";
			if (ilineendpos == -1) {
				// 没有找到，说明逻辑错误
				logger.error("【ERROR】 package找不到;");
				return null;
			} else {
				strmemo = strdata.substring(0, ilineendpos + 1);
				logger.debug(strmemo);
				
				String strleftdata;
				if(ilineendpos < strdata.length()-1) {
					strleftdata = strdata.substring(ilineendpos+1);
				}else {
					strleftdata ="";
				}
				
				JavaFilePackageElement ele = new JavaFilePackageElement();
				ele.setStringcontent(strmemo);
				ele.setComputeleftstring(strleftdata);
				return ele;
			}
		} else {
			return null;
		}
	}
}
