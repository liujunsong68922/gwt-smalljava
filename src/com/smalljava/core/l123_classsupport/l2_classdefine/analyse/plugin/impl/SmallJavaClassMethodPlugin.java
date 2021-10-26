package com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin.impl;

//import com.smalljava.core.classloader.l2_class.analyse.plugin.IJavaClassAnalysePlugin;
//import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;
//import com.smalljava.core.classloader.l2_class.vo.element.JavaClassMethodElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin.ISmallJavaClassAnalysePlugin;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.AbstractSmallJavaClassElement;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.element.SmallJavaClassMethodElement;

public class SmallJavaClassMethodPlugin implements ISmallJavaClassAnalysePlugin {

	@Override
	public AbstractSmallJavaClassElement findFirstElement(String strdata) {
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);

		// MEMO: 查找第一个大的代码块，查找} 和 ;比较位置
		String sfirstcode = this.getFirstCodeBlock(strdata);
		if (sfirstcode == null) {
			// MEMO: 找不到代码块，返回null
			return null;
		}

		// 在找到代码块的情况下，判断返回的是;还是}
		// 如果是;，代表是变量定义
		// 如果是}，代表是方法名
		if (sfirstcode.endsWith("}")) {
			String strleftcode = strdata.substring(sfirstcode.length());

			SmallJavaClassMethodElement methoddefine = new SmallJavaClassMethodElement();
			methoddefine.setStringcontent(sfirstcode);
			methoddefine.setComputeleftstring(strleftcode);
			// TODO: 从method定义中抽取methodname ，method arg出来
			// 这部分工作在level3_method里面完成
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
		if (ipos2 > 0) {
			if (ipos < 0) {
				ipos = ipos2;
			} else if (ipos2 < ipos) {
				ipos = ipos2;
			}
		}

		if (ipos == -1) {
			return null;
		} else {
			// MEMO: 返回第一个代码块
			return strdata.substring(0, ipos + 1);
		}
	}

}
