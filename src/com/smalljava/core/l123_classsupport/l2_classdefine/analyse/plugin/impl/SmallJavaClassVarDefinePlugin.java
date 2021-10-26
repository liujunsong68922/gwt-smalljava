package com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin.impl;

//import com.smalljava.core.classloader.l2_class.analyse.plugin.IJavaClassAnalysePlugin;
//import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;
//import com.smalljava.core.classloader.l2_class.vo.element.JavaClassVarDefineElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin.ISmallJavaClassAnalysePlugin;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.AbstractSmallJavaClassElement;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.element.SmallJavaClassVarDefineElement;

/**
 * MEMO: 在类定义里面读取包含;的部分，定义为变量元素
 * 
 * @author liujunsong
 *
 */
public class SmallJavaClassVarDefinePlugin implements ISmallJavaClassAnalysePlugin {

	@Override
	public AbstractSmallJavaClassElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);

		// MEMO: 查找第一个大的代码块，查找} 和 ;比较位置
		String sfirstcode = this.getFirstCodeBlock(strdata);
		if (sfirstcode == null) {
			// 找不到代码块，返回null
			return null;
		}

		// 在找到代码块的情况下，判断返回的是;还是}
		// 如果是;，代表是变量定义
		// 如果是}，代表是方法名
		if (sfirstcode.endsWith(";")) {
			String strleftcode = strdata.substring(sfirstcode.length());

			SmallJavaClassVarDefineElement vardefine = new SmallJavaClassVarDefineElement();
			vardefine.setStringcontent(sfirstcode);
			vardefine.setComputeleftstring(strleftcode);
			return vardefine;
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
		if (ipos2 > 0 && ipos2 < ipos1) {
			ipos = ipos2;
		}

		if (ipos == -1) {
			return null;
		} else {
			// ���ص�һ�������
			return strdata.substring(0, ipos + 1);
		}
	}
}
