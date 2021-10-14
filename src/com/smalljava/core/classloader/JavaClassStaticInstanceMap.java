package com.smalljava.core.classloader;

import java.util.HashMap;

import com.smalljava.core.classloader.l2_class.vo.JavaClassStaticInstanceVO;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * MEMO�����������������е�JavaClassStaticInstance
 * MEMO��ÿ����ֻ����һ��ʵ������ʱ������class��������ͻ���⣬
 * MEMO��package�ؼ��ʽ��ں����汾֧�֡�
 * @author liujunsong
 *
 */
public class JavaClassStaticInstanceMap {
	private static Logger logger = LoggerFactory.getLogger(JavaClassStaticInstanceMap.class);
	//����洢���壬ȫ�ֹ���
	private static HashMap<String,JavaClassStaticInstanceVO> staticInstanceMap
	 = new HashMap<String,JavaClassStaticInstanceVO>();
	
	/**
	 * MEMO:���캯��
	 */
	public JavaClassStaticInstanceMap() {
		
	}

	/**
	 * get ����
	 * @return
	 */
	public static HashMap<String, JavaClassStaticInstanceVO> getStaticInstanceMap() {
		return staticInstanceMap;
	}
	
	/**
	 * MEMO:����classname����ȫ�ֵĴ洢���м�����̬ʵ��
	 * MEMO���������ʾ�̬ʵ�������װ�ľ�̬������
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
	 * MEMO:����¶����ȥ
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
