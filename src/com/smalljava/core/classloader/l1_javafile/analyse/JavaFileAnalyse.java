package com.smalljava.core.classloader.l1_javafile.analyse;

import com.smalljava.core.classloader.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
import com.smalljava.core.classloader.l1_javafile.analyse.plugin.JavaFileAnalysePluginManager;
import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;
import com.smalljava.core.classloader.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

public class JavaFileAnalyse {
	private Logger logger = LoggerFactory.getLogger(JavaFileAnalyse.class);
	/**
	 * 字符串解析算法，输入一个代表Java源文件的字符串，解析成一个JavaFileRootVO对象
	 * @param stringcontent
	 * @return
	 */
	@SuppressWarnings("static-access")
	public JavaFileRootVO analyse(String strcontent) {
		
		if(strcontent==null ) {
			logger.info("【ERROR】strcontent is null.");
			return null;
		}
		StringFindUtil util = new StringFindUtil();
		strcontent = util.trimReturnAndSpace(strcontent);
		
		if(strcontent.equals("")) {
			logger.info("【ERROR】strcontent is empty.");
			return null;
		}
		
		JavaFileAnalysePluginManager manager = new JavaFileAnalysePluginManager();
		JavaFileRootVO rootvo = new JavaFileRootVO();
		
		while(strcontent.length()>0) {
			strcontent = util.trimReturnAndSpace(strcontent);
			//循环调用分析器
			boolean loopflag = false;
			for(IJavaFileAnalysePlugin plugin : manager.getPluginarray()) {
				AbstractJavaFileElement newele = plugin.findFirstElement(strcontent);
				if(newele == null) {
					continue;
				}else {
					loopflag = true;
					rootvo.getChildren().add(newele);
					strcontent = newele.getComputeleftstring();
					//跳出此次循环
					break;
				}
			}
			if(loopflag) {
				//继续外部循环
				continue;
			}else {
				logger.error("【ERROR】调用插件解析失败:"+strcontent);
				return null;
			}
		}
		//返回循环调用结果
		return rootvo;
	}
}
