package com.liu.gwt.gwt_smalljava.level1_javafile.analyse.plugin.impl;

import com.liu.gwt.gwt_smalljava.common.StringFindUtil;
import com.liu.gwt.gwt_smalljava.level1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
import com.liu.gwt.gwt_smalljava.level1_javafile.vo.AbstractJavaFileElement;
import com.liu.gwt.gwt_smalljava.level1_javafile.vo.element.JavaFilePackageElement;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import com.liu.smalljavav2.block.SmallJavaBlockConst;
//import com.liu.smalljavav2.block.blockvo.BasicBlock;
//import com.liu.smalljavav2.common.StringFindUtil;
//import com.liu.smalljavav2.level1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
//import com.liu.smalljavav2.level1_javafile.vo.AbstractJavaFileElement;
//import com.liu.smalljavav2.level1_javafile.vo.element.JavaFilePackageElement;

public class JavaFileImportPlugin implements IJavaFileAnalysePlugin {
	//private Logger logger = LoggerFactory.getLogger(JavaFileImportPlugin.class);

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
				//logger.error("��ERROR�� import�Ҳ���;");
				return null;
			} else {
				strmemo = strdata.substring(0, ilineendpos + 1);
				//logger.debug(strmemo);
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
