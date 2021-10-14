package com.smalljava.core.classloader.l2_class.analyse;

import com.smalljava.core.classloader.l2_class.analyse.plugin.IJavaClassAnalysePlugin;
import com.smalljava.core.classloader.l2_class.analyse.plugin.JavaClassAnalysePluginManager;
import com.smalljava.core.classloader.l2_class.vo.JavaClassTemplateVO;
import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * 这个类负责将一个class定义的字符串分解成一个class的VO对象
 * @author liujunsong
 *
 */
public class JavaClassAnalyse {
	private Logger logger = LoggerFactory.getLogger(JavaClassAnalyse.class);
	@SuppressWarnings("static-access")
	public JavaClassTemplateVO analyse(String strcontent) {
		
		if(strcontent==null ) {
			logger.error("【ERROR】strcontent is null.");
			return null;
		}
		JavaClassTemplateVO rootvo = new JavaClassTemplateVO();

		StringFindUtil util = new StringFindUtil();
		strcontent = util.trimReturnAndSpace(strcontent);

		//如果strcontent被{}包裹，则去掉这个包裹
		if(strcontent.startsWith("{") && strcontent.endsWith("}")) {
			strcontent=strcontent.substring(1,strcontent.length()-1);
			strcontent = util.trimReturnAndSpace(strcontent);
		}
		
		if(strcontent.equals("")) {
			logger.debug("【INFO】strcontent is empty.");
			return rootvo;
		}		

		JavaClassAnalysePluginManager manager = new JavaClassAnalysePluginManager();
		
		while(strcontent.length()>0) {
			strcontent = util.trimReturnAndSpace(strcontent);
			//循环调用分析器，先设置循环的执行状态标志
			boolean loopflag = false;
			for(IJavaClassAnalysePlugin plugin : manager.getPluginarray()) {
				//从字符串里面解析新元素出来
				AbstractJavaClassElement newele = plugin.findFirstElement(strcontent);
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
				//各个插件找到了一个元素继续外部循环
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
