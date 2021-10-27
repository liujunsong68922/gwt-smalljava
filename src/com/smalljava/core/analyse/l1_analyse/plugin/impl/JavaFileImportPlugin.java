package com.smalljava.core.analyse.l1_analyse.plugin.impl;

import com.smalljava.core.analyse.l1_analyse.plugin.IJavaFileAnalysePlugin;
//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
//import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;
//import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFilePackageElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l1_javafile.AbstractJavaFileElement;
import com.smalljava.core.commonvo.l1_javafile.element.JavaFilePackageElement;

public class JavaFileImportPlugin implements IJavaFileAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaFileImportPlugin.class);

	@Override
	public AbstractJavaFileElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);

		// Step4.1 �ж��ǲ��� ��/*�� ��ʼ
		if (strdata.startsWith("import ")) {
			// ��ȡ��һ�еĽ�����
			int ilineendpos = util.findfirstStringForBlock(strdata, ";");
			String strmemo = "";
			if (ilineendpos == -1) {
				// û���ҵ���˵���߼�����
				logger.error("��ERROR�� import�Ҳ���;");
				return null;
			} else {
				strmemo = strdata.substring(0, ilineendpos + 1);
				logger.debug(strmemo);
				JavaFilePackageElement ele = new JavaFilePackageElement();
				ele.setStringcontent(strmemo);
				String strleft;
				if (ilineendpos < strdata.length() - 1) {
					strleft = strdata.substring(ilineendpos + 1);
				} else {
					strleft = "";
				}
				// ��¼�зֺ��ʣ�ಿ��
				ele.setComputeleftstring(strleft);
				return ele;
			}
		} else {
			return null;
		}
	}
}
