package com.smalljava.core.analyse.l1_analyse;

import java.util.ArrayList;

import com.smalljava.core.analyse.l1_analyse.plugin.IJavaFileAnalysePlugin;
import com.smalljava.core.analyse.l1_analyse.plugin.JavaFileAnalysePluginManager;
import com.smalljava.core.analyse.l2_classdefine.SmallJavaClassAnalyse;
//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.IJavaFileAnalysePlugin;
//import com.smalljava.core.classloader.l1_javafile.analyse.plugin.JavaFileAnalysePluginManager;
//import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;
//import com.smalljava.core.classloader.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l1_javafile.AbstractJavaFileElement;
import com.smalljava.core.commonvo.l1_javafile.JavaFileRootVO;
import com.smalljava.core.commonvo.l1_javafile.element.JavaFileClassElement;
import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;

public class JavaFileAnalyse {
	private Logger logger = LoggerFactory.getLogger(JavaFileAnalyse.class);

	// 字符串解析算法，输入一个代表Java源文件的字符串，解析成一个JavaFileRootVO对象
	@SuppressWarnings("static-access")
	public JavaFileRootVO analyse(String strcontent) {

		if (strcontent == null) {
			logger.info("[ERROR] strcontent is null.");
			return null;
		}
		StringFindUtil util = new StringFindUtil();
		strcontent = util.trimReturnAndSpace(strcontent);

		if (strcontent.equals("")) {
			logger.info("[ERROR] strcontent is empty.");
			return null;
		}

		JavaFileAnalysePluginManager manager = new JavaFileAnalysePluginManager();
		JavaFileRootVO rootvo = new JavaFileRootVO();

		while (strcontent.length() > 0) {
			strcontent = util.trimReturnAndSpace(strcontent);
			// 循环调用分析器
			boolean loopflag = false;
			for (IJavaFileAnalysePlugin plugin : manager.getPluginarray()) {
				AbstractJavaFileElement newele = plugin.findFirstElement(strcontent);
				if (newele == null) {
					continue;
				} else {
					loopflag = true;
					rootvo.getChildren().add(newele);
					strcontent = newele.getComputeleftstring();
					// 跳出此次循环
					break;
				}
			}
			if (loopflag) {
				// 继续外部循环
				continue;
			} else {
				logger.error("[ERROR] 在调用所有插件后，数据仍未得到处理.");
				logger.error("[ERROR] 调用插件解析失败:" + strcontent);
				return null;
			}
		}

		// Add loop call to analyse javaclass
		// 增加循环调用，来解析其中的Java Class信息
		boolean b2 = this.callClassAnalyse(rootvo);
		if (!b2) {
			return null;
		} else {
			// 返回循环调用结果
			return rootvo;
		}
	}

	private boolean callClassAnalyse(JavaFileRootVO rootvo) {
		ArrayList<AbstractJavaFileElement> children = rootvo.getChildren();
		for (AbstractJavaFileElement child : children) {
			if (child instanceof JavaFileClassElement) {
				// 强制类型转换
				JavaFileClassElement classelement = (JavaFileClassElement) child;
				String strcontent = classelement.getStringcontent();
				logger.info("strcontent:" + strcontent);

				// 调用解析器进行解析
				SmallJavaClassAnalyse classanalyse = new SmallJavaClassAnalyse();
				SmallJavaClassTemplateVO classtemplatevo = classanalyse.analyse(strcontent);

				
				// 判断解析是否成功
				if (classtemplatevo != null) {
					logger.info("class 解析成功!");
					//save classname and packagename in classtemplatevo 
					classtemplatevo.setClassname(classelement.getClassname());
					classtemplatevo.setPackagename(classtemplatevo.getPackagename());
					//save classtemplatevo in classelement.
					classelement.setClasstemplatevo(classtemplatevo);
				} else {
					logger.error("class 解析失败!");
					return false;
				}
			}
		}

		return true;
	}
}
