package com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin.impl;

import com.liu.gwt.gwt_smalljava.common.StringFindUtil;
import com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin.IJavaClassAnalysePlugin;
import com.liu.gwt.gwt_smalljava.level2_class.vo.AbstractJavaClassElement;
import com.liu.gwt.gwt_smalljava.level2_class.vo.element.JavaClassMethodElement;

//import com.liu.smalljavav2.common.StringFindUtil;
//import com.liu.smalljavav2.level2_class.analyse.plugin.IJavaClassAnalysePlugin;
//import com.liu.smalljavav2.level2_class.vo.AbstractJavaClassElement;
//import com.liu.smalljavav2.level2_class.vo.element.JavaClassMethodElement;

public class JavaClassMethodPlugin implements IJavaClassAnalysePlugin {

	@Override
	public AbstractJavaClassElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);

		// ���ҵ�һ����Ĵ���飬����} �� ;�Ƚ�λ��
		String sfirstcode = this.getFirstCodeBlock(strdata);
		if (sfirstcode == null) {
			// �Ҳ�������飬����null
			return null;
		}

		// ���ҵ�����������£��жϷ��ص���;����}
		// �����;�������Ǳ�������
		// �����}�������Ƿ�����
		if (sfirstcode.endsWith("}")) {
			String strleftcode = strdata.substring(sfirstcode.length());

			JavaClassMethodElement methoddefine = new JavaClassMethodElement();
			methoddefine.setStringcontent(sfirstcode);
			methoddefine.setComputeleftstring(strleftcode);
			
			//TODO: ��method�����г�ȡmethodname ��method arg����
			//�ⲿ�ֹ�����level3_method�������
			
			return methoddefine;
		}

		return null;
	}

	private String getFirstCodeBlock(String strdata) {
		StringFindUtil util = new StringFindUtil();
		int ipos1 = util.findfirstStringForBlock(strdata, ";");
		int ipos2 = util.findfirstStringForBlock(strdata, "}");
		int ipos = -1;
		if (ipos1 > 0) {
			ipos = ipos1;
		}
		if(ipos2 >0) {
			if(ipos<0) {
				ipos = ipos2;
			}else if(ipos2<ipos) {
				ipos = ipos2;
			}
		}

		if (ipos == -1) {
			return null;
		} else {
			// ���ص�һ�������
			return strdata.substring(0, ipos + 1);
		}
	}

}
