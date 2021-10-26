package com.smalljava.core.l123_classsupport.l2_classdefine.analyse;

import java.util.ArrayList;

import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin.ISmallJavaClassAnalysePlugin;
import com.smalljava.core.l123_classsupport.l2_classdefine.analyse.plugin.SmallJavaClassAnalysePluginManager;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.AbstractSmallJavaClassElement;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.SmallJavaClassTemplateVO;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.element.SmallJavaClassMethodElement;
import com.smalljava.core.l123_classsupport.l3_method.analyse.SmallJavaMethodAnalyse;
import com.smalljava.core.l123_classsupport.l3_method.vo.SmallJavaMethodRootVO;

/**
 * MEMO: 这个类负责将一个class定义的字符串分解成一个JavaClassTemplate的VO对象
 * @author liujunsong
 *
 */
public class SmallJavaClassAnalyse {
	private Logger logger = LoggerFactory.getLogger(SmallJavaClassAnalyse.class);
	@SuppressWarnings("static-access")
	public SmallJavaClassTemplateVO analyse(String strcontent) {
		
		if(strcontent==null ) {
			logger.error("[ERROR]strcontent is null.");
			return null;
		}
		SmallJavaClassTemplateVO rootvo = new SmallJavaClassTemplateVO();

		StringFindUtil util = new StringFindUtil();
		strcontent = util.trimReturnAndSpace(strcontent);

		//MEMO:如果strcontent被{}包裹，则去掉这个包裹
		if(strcontent.startsWith("{") && strcontent.endsWith("}")) {
			strcontent=strcontent.substring(1,strcontent.length()-1);
			strcontent = util.trimReturnAndSpace(strcontent);
		}
		
		if(strcontent.equals("")) {
			logger.debug("[INFO]strcontent is empty.");
			return rootvo;
		}		

		SmallJavaClassAnalysePluginManager manager = new SmallJavaClassAnalysePluginManager();
		
		while(strcontent.length()>0) {
			strcontent = util.trimReturnAndSpace(strcontent);
			//MEMO: 循环调用分析器
			//loopflag是一个标志位，判断是否执行成功
			boolean loopflag = false;
			for(ISmallJavaClassAnalysePlugin plugin : manager.getPluginarray()) {
				//调用插件来查找第一个子元素
				AbstractSmallJavaClassElement newele = plugin.findFirstElement(strcontent);
				if(newele == null) {
					//MEMO:未找到，则调用下一个插件
					continue;
				}else {
					//MEMO:找到了第一个元素
					loopflag = true;
					rootvo.getChildren().add(newele);
					strcontent = newele.getComputeleftstring();
					//MEMO:跳出此次循环
					break;
				}
			}
			if(loopflag) {
				//MEMO:继续外部循环
				continue;
			}else {
				logger.error("[ERROR]调用插件解析失败:"+strcontent);
				return null;
			}
		}
		
		//增加一个额外处理，处理class定义里面的Method定义
		boolean b2 = this.analyseMethod(rootvo);
		if(b2) {
			logger.info("[info] rootvo analyse methdo define ok.");
			return rootvo;
		}else {
			logger.error("[Error] error happened when analyse method define.");
			return null;
		}
	}
	
	/**
	 * 调用MethodAnalyse,对rootvo里面定义的Method进行进一步的分析计算
	 * @param rootvo
	 * @return
	 */
	private boolean analyseMethod(SmallJavaClassTemplateVO rootvo) {
		ArrayList<AbstractSmallJavaClassElement> list1 = rootvo.getChildren();
		for(AbstractSmallJavaClassElement child : list1) {
			// 只处理类里面的方法定义
			if(child instanceof SmallJavaClassMethodElement) {
				// 类型强制转换成methodelement
				SmallJavaClassMethodElement methodelement = (SmallJavaClassMethodElement) child;
				// 定义分析器
				SmallJavaMethodAnalyse methodanalyse = new SmallJavaMethodAnalyse();
				SmallJavaMethodRootVO vo = methodanalyse.analyse(methodelement);
				if(vo !=null) {
					//转换vo成功,继续循环
					methodelement.setMethodvo(vo);
					continue;
				}else {
					//转换VO失败，停止循环，返回false
					logger.error("[ERROR] failed when analyse method.");
					return false;
				}
			}
			
		}
		//全部执行结束，返回true
		return true;
	}
}
