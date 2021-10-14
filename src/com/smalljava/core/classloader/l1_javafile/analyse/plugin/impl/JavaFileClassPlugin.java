package com.smalljava.core.classloader.l1_javafile.analyse.plugin.impl;

import com.smalljava.core.classloader.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;
import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFileClassElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

public class JavaFileClassPlugin implements IJavaFileAnalysePlugin {
	private Logger logger = LoggerFactory.getLogger(JavaFileClassPlugin.class);
	
	@Override
	public AbstractJavaFileElement findFirstElement(String strdata) {
		if(strdata==null) {
			return null;
		}
		
		StringFindUtil util = new StringFindUtil();
		strdata = util.trimReturnAndSpace(strdata);
		
		if(strdata.startsWith("public ") 
				|| strdata.startsWith("private ")
				|| strdata.startsWith("class")) {
			int ipos1 = strdata.indexOf("class ");
			if(ipos1<0) {
				logger.error("【ERROR】 找不到class关键字.");
				return null;
			}
			strdata = strdata.substring(ipos1+5);
			strdata = util.trimReturnAndSpace(strdata);
			int ipos2 = util.findfirstStringForBlock(strdata, "{");
			if (ipos2<0) {
				logger.error("【ERROR】 找不到{.");
				return null;
			}
			//解析得到class的名称定义
			//暂时不考虑类的继承，类的接口实现这些高级东西
			String classname = strdata.substring(0,ipos2);
			classname = util.trimReturnAndSpace(classname);
			
			int ipos3 = util.findfirstStringForBlock(strdata, "}");
			if (ipos3<0) {
				logger.error("【ERROR】 找不到}.");
				return null;
			}
			//解析得到class的内容定义
			String classcontent = strdata.substring(ipos2, ipos3+1);
			classcontent = util.trimReturnAndSpace(classcontent);
			
			String strleftdata;
			if(ipos3<strdata.length()-1) {
				strleftdata = strdata.substring(ipos3+1);
			}else {
				strleftdata ="";
			}
			
			//定义VO对象并返回
			JavaFileClassElement ele = new JavaFileClassElement();
			ele.setClassname(classname);
			ele.setStringcontent(classcontent);
			ele.setComputeleftstring(strleftdata);
			return ele;
		}
		return null;
	}

}
