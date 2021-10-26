package com.smalljava.core.l123_classsupport.l1_javafile.analyse.plugin.impl;

//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
//import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;
//import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFileMultiLineMemoElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l123_classsupport.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
import com.smalljava.core.l123_classsupport.l1_javafile.vo.AbstractJavaFileElement;
import com.smalljava.core.l123_classsupport.l1_javafile.vo.element.JavaFileMultiLineMemoElement;

public class JavaFileMultiLineMemoPlugin implements IJavaFileAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaFileMultiLineMemoPlugin.class);
	
	@Override
	public AbstractJavaFileElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);
		
		// Step4.1 �ж��ǲ��� ��/*�� ��ʼ
		if (strdata.startsWith("/*")) {
			// ��ȡ��һ�еĽ�����
			int ilineendpos = util.findfirstStringForBlock(strdata, "*/");
			String strmemo = "";
			if (ilineendpos == -1) {
				// û���ҵ���˵���߼�����
				logger.error("��ERRO���Ҳ���*/");
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

				JavaFileMultiLineMemoElement ele = new JavaFileMultiLineMemoElement();
				ele.setStringcontent(strmemo);
				ele.setComputeleftstring(strleftstring);
				return ele;
			}
		} else {
			return null;
		}
	}


}
