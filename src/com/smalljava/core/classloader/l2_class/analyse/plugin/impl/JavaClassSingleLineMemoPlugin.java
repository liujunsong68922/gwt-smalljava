package com.smalljava.core.classloader.l2_class.analyse.plugin.impl;

import com.smalljava.core.classloader.l2_class.analyse.plugin.IJavaClassAnalysePlugin;
import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;
import com.smalljava.core.classloader.l2_class.vo.element.JavaClassSingleLineMemoElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * ����JavaԴ�����ļ��еĵ���ע��
 * 
 * @author liujunsong
 *
 */
public class JavaClassSingleLineMemoPlugin implements IJavaClassAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaClassSingleLineMemoPlugin.class);

	@Override
	public AbstractJavaClassElement findFirstElement(String strdata) {
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
				logger.info(strmemo);
				strdata = "";
				JavaClassSingleLineMemoElement element1 = new JavaClassSingleLineMemoElement();
				element1.setStringcontent(strmemo);
				element1.setComputeleftstring("");
				return element1;
			} else {
				strmemo = strdata.substring(0, ilineendpos);
				logger.info(strmemo);
				String strleftdata = strdata.substring(ilineendpos + 1);

				JavaClassSingleLineMemoElement element1 = new JavaClassSingleLineMemoElement();
				element1.setStringcontent(strmemo);
				element1.setComputeleftstring(strleftdata);

				return element1;
			}

		} else {
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
