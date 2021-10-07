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

public class JavaFilePackagePlugin implements IJavaFileAnalysePlugin {
	//private Logger logger = LoggerFactory.getLogger(JavaFilePackagePlugin.class);
	@Override
	public AbstractJavaFileElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);
		
		// Step4.1 �ж��ǲ��� ��/*�� ��ʼ
		if (strdata.startsWith("package")) {
			// ��ȡ��һ�еĽ�����
			int ilineendpos = util.findfirstStringForBlock(strdata, ";");
			String strmemo = "";
			if (ilineendpos == -1) {
				// û���ҵ���˵���߼�����
				//logger.error("��ERROR�� package�Ҳ���;");
				return null;
			} else {
				strmemo = strdata.substring(0, ilineendpos + 1);
				//logger.debug(strmemo);
				
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
