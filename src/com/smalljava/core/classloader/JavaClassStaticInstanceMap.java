package com.smalljava.core.classloader;

import java.util.HashMap;

import com.smalljava.core.classloader.l2_class.vo.JavaClassStaticInstanceVO;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * MEMO：这个类用来存放所有的JavaClassStaticInstance
 * MEMO：每个类只能有一个实例，暂时不考虑class的命名冲突问题，
 * MEMO：package关键词将在后续版本支持。
 * @author liujunsong
 *
 */
public class JavaClassStaticInstanceMap {
	private static Logger logger = LoggerFactory.getLogger(JavaClassStaticInstanceMap.class);
	//对象存储定义，全局共享
	private static HashMap<String,JavaClassStaticInstanceVO> staticInstanceMap
	 = new HashMap<String,JavaClassStaticInstanceVO>();
	
	/**
	 * MEMO:构造函数
	 */
	public JavaClassStaticInstanceMap() {
		
	}

	/**
	 * get 方法
	 * @return
	 */
	public static HashMap<String, JavaClassStaticInstanceVO> getStaticInstanceMap() {
		return staticInstanceMap;
	}
	
	/**
	 * MEMO:根据classname，从全局的存储表中检索静态实例
	 * MEMO：用来访问静态实例里面封装的静态变量表
	 * @param classname
	 * @return
	 */
	public static JavaClassStaticInstanceVO getJavaClassStaticInstanceVO(String classname) {
		if (staticInstanceMap.containsKey(classname)) {
			return staticInstanceMap.get(classname);
		}else {
			return null;
		}
	}
	
	/**
	 * MEMO:添加新对象进去
	 * @param vo
	 */
	public static void add(JavaClassStaticInstanceVO vo) {
		String classname = vo.getClassname();
		if(! staticInstanceMap.containsKey(classname)) {
			staticInstanceMap.put(classname, vo);
			
		}
		logger.info("size:"+staticInstanceMap.size());
	}
}
