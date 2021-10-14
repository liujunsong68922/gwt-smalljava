package com.smalljava.core.classloader.l3_method.analyse;

import com.smalljava.core.classloader.l2_class.vo.element.JavaClassMethodElement;
import com.smalljava.core.classloader.l3_method.vo.JavaMethodArgumentVO;
import com.smalljava.core.classloader.l3_method.vo.JavaMethodRootVO;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * JAVA的method方法分析器
 * @author liujunsong
 *
 */
public class JavaMethodAnalyse {
	private Logger logger = LoggerFactory.getLogger(JavaMethodAnalyse.class);
	/**
	 * 将一个方法的定义字符串解析成名称，参数，内容三部分
	 * @param strcontent
	 * @return
	 */
	public JavaMethodRootVO analyse(JavaClassMethodElement methodelement) {
		if(methodelement==null ) {
			logger.info("【ERROR】strcontent is null.");
			return null;
		}

		String strcontent = methodelement.getStringcontent();
		if(strcontent == null) {
			logger.error("【ERROR】strcontent is null.");
			return null;			
		}else {
			logger.debug("method content:"+strcontent);
		}
		StringFindUtil util = new StringFindUtil();
		strcontent = util.trimReturnAndSpace(strcontent);
		if(strcontent.equals("")) {
			logger.error("【ERROR】strcontent is emtyp.");
			return null;			
		}
		
		if(! strcontent.endsWith("}")) {
			logger.error("【ERROR】strcontent is not ended by 【}】.");
			return null;			
			
		}
		
		JavaMethodRootVO rootvo = new JavaMethodRootVO();
		//TODO:从其中抽取方法名，变量参数，方法名定义
		int ipos1 = util.findfirstStringForBlock(strcontent, "{");
		if(ipos1<0) {
			logger.error("【ERROR】strcontent cannot find 【{】.");
			return null;
		}
		
		//设置Method对象的字符串内容
		String methodcontent = strcontent.substring(ipos1);
		rootvo.setMethodContent(methodcontent);
		
		
		int ipos2 = strcontent.indexOf("(");
		int ipos3 = strcontent.indexOf(")");
		if(ipos2<0 || ipos3<0) {
			logger.error("【ERROR】strcontent 查找()失败！"+strcontent);
			return null;
		}
		if(ipos2>ipos3) {
			logger.error("【ERROR】strcontent ipos2>ipos3");
			return null;
			
		}
		
		String argdefine = strcontent.substring(ipos2+1,ipos3);
		String args[] = argdefine.split(",");
		for(String arg:args) {
			//按照空格来分解arg
			arg = arg.trim();
			if(arg.length()==0) {
				//空字符串跳过循环
				continue;
			}
			String argvalue[] = arg.split(" ");
			if(argvalue.length !=2) {
				logger.error("【ERROR】Method arg analyse error:"+arg);
				return null;
			}else {
				JavaMethodArgumentVO argvo = new JavaMethodArgumentVO();
				argvo.setArgtype(argvalue[0]);
				argvo.setArgname(argvalue[1]);
				rootvo.getArgArray().add(argvo);
			}
		}
		
		String leftdata = strcontent.substring(0,ipos2);
		leftdata = util.trimReturnAndSpace(leftdata);
		String sdata[] = leftdata.split(" ");
		//最后一个字符串是方法名
		if(sdata.length>0) {
			rootvo.setMethodname(sdata[sdata.length-1]);
		}else {
			logger.error("【ERROR】 leftdata is empty."+leftdata);
			return null;
		}
		
		return rootvo;
	}
}
