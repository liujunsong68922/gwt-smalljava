package com.smalljava.core.analyse.l1_analyse.plugin.impl;

import com.smalljava.core.analyse.l1_analyse.plugin.IJavaFileAnalysePlugin;
//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
//import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;
//import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFileSingleLineMemoElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l1_javafile.AbstractJavaFileElement;
import com.smalljava.core.commonvo.l1_javafile.element.JavaFileSingleLineMemoElement;

/**
 * ����JavaԴ�����ļ��еĵ���ע��
 * @author liujunsong
 *
 */
public class JavaFileSingleLineMemoPlugin implements IJavaFileAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaFileSingleLineMemoPlugin.class);
	
	@Override
	public AbstractJavaFileElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);
		
		// Step4.1 �ж��ǲ��� ��//�� ��ʼ
		if (strdata.startsWith("//")) {
			// ��ȡ��һ�еĽ�����
			int ilineendpos = this._getFirstLineEndPos(strdata);
			String strmemo = "";
			if (ilineendpos == -1) {
				// û���ҵ��س�����˵��ȫ���Ǳ�ע
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

	// ���ַ��������ҵ���һ�н�����,ͨ������\r��ʵ��
	public int _getFirstLineEndPos(String s1) {
		int ipos = 0;
		for (ipos = 0; ipos < s1.length(); ipos++) {
			if (s1.charAt(ipos) == '\r') {
				return ipos;
			}
		}
		// ���ѭ�����Ҳ�Ҳ��������㣬˵��û�лس���
		return -1;
	}
	

}
