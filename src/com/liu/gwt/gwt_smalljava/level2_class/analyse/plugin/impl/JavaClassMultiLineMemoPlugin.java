package com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin.impl;

import com.liu.gwt.gwt_smalljava.common.StringFindUtil;
import com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin.IJavaClassAnalysePlugin;
import com.liu.gwt.gwt_smalljava.level2_class.vo.AbstractJavaClassElement;
import com.liu.gwt.gwt_smalljava.level2_class.vo.element.JavaClassMultiLineMemoElement;

//import com.liu.smalljavav2.common.StringFindUtil;
//import com.liu.smalljavav2.level2_class.analyse.plugin.IJavaClassAnalysePlugin;
//import com.liu.smalljavav2.level2_class.vo.AbstractJavaClassElement;
//import com.liu.smalljavav2.level2_class.vo.element.JavaClassMultiLineMemoElement;

public class JavaClassMultiLineMemoPlugin implements IJavaClassAnalysePlugin {

	@Override
	public AbstractJavaClassElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);
		
		// Step4.1 �ж��ǲ��� ��/*�� ��ʼ
		if (strdata.startsWith("/*")) {
			// ��ȡ��һ�еĽ�����
			int ilineendpos = util.findfirstStringForBlock(strdata, "*/");
			String strmemo = "";
			if (ilineendpos == -1) {
				// û���ҵ���˵���߼�����
				System.out.println("��ERRO���Ҳ���*/");
				return null;
			} else {
				strmemo = strdata.substring(0, ilineendpos + 2);
				log2(strmemo);
				
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
	
	private void log2(String s1) {
		System.out.println(s1);
	}
}
