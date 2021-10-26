package com.smalljava.core.l123_classsupport.l1_javafile.analyse.plugin.impl;

//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
//import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;
//import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFilePackageElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l123_classsupport.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
import com.smalljava.core.l123_classsupport.l1_javafile.vo.AbstractJavaFileElement;
import com.smalljava.core.l123_classsupport.l1_javafile.vo.element.JavaFilePackageElement;

public class JavaFilePackagePlugin implements IJavaFileAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaFilePackagePlugin.class);
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
				logger.error("��ERROR�� package�Ҳ���;");
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
