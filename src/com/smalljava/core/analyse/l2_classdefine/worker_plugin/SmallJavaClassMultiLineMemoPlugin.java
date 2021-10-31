package com.smalljava.core.analyse.l2_classdefine.worker_plugin;

import com.smalljava.core.analyse.l2_classdefine.manager.ISmallJavaClassAnalysePlugin;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l2_javaclass.AbstractSmallJavaClassElement;
import com.smalljava.core.commonvo.l2_javaclass.element.SmallJavaClassMultiLineMemoElement;

public class SmallJavaClassMultiLineMemoPlugin implements ISmallJavaClassAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(SmallJavaClassMultiLineMemoPlugin.class);

	@Override
	public AbstractSmallJavaClassElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);

		// Step4.1 判断是不是 【/*】 开始
		if (strdata.startsWith("/*")) {
			// MEMO：读取到一行的结束点
			int ilineendpos = util.findfirstStringForBlock(strdata, "*/");
			String strmemo = "";
			if (ilineendpos == -1) {
				// 没有找到，说明逻辑错误
				logger.error("[ERROR]找不到*/ .  ");
				return null;
			} else {
				strmemo = strdata.substring(0, ilineendpos + 2);
				logger.debug(strmemo);

				String strleftstring;
				if (ilineendpos < strdata.length() - 2) {
					strleftstring = strdata.substring(ilineendpos + 2);
				} else {
					strleftstring = "";
				}

				SmallJavaClassMultiLineMemoElement ele = new SmallJavaClassMultiLineMemoElement();
				ele.setStringcontent(strmemo);
				ele.setComputeleftstring(strleftstring);
				return ele;
			}
		} else {
			return null;
		}
	}
}
