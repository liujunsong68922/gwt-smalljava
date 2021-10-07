package com.liu.gwt.gwt_smalljava.level2_class.analyse;

import com.liu.gwt.gwt_smalljava.common.StringFindUtil;
import com.liu.gwt.gwt_smalljava.level1_javafile.vo.element.JavaFileClassElement;
import com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin.IJavaClassAnalysePlugin;
import com.liu.gwt.gwt_smalljava.level2_class.analyse.plugin.JavaClassAnalysePluginManager;
import com.liu.gwt.gwt_smalljava.level2_class.vo.AbstractJavaClassElement;
import com.liu.gwt.gwt_smalljava.level2_class.vo.JavaClassRootVO;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.liu.smalljavav2.common.StringFindUtil;
//import com.liu.smalljavav2.level1_javafile.vo.element.JavaFileClassElement;
//import com.liu.smalljavav2.level2_class.analyse.plugin.IJavaClassAnalysePlugin;
//import com.liu.smalljavav2.level2_class.analyse.plugin.JavaClassAnalysePluginManager;
//import com.liu.smalljavav2.level2_class.vo.AbstractJavaClassElement;
//import com.liu.smalljavav2.level2_class.vo.JavaClassRootVO;

/**
 * ����ฺ��һ��class������ַ����ֽ��һ��class��VO����
 * @author liujunsong
 *
 */
public class JavaClassAnalyse {
	//private Logger logger = LoggerFactory.getLogger(JavaClassAnalyse.class);
	@SuppressWarnings("static-access")
	public JavaClassRootVO analyse(JavaFileClassElement element) {
		if(element==null) {
			//logger.error("[ArgumentErro] element is null.");
		}
		String strcontent = element.getStringcontent();
		
		if(strcontent==null ) {
			//logger.error("��ERROR��strcontent is null.");
			return null;
		}
		JavaClassRootVO rootvo = new JavaClassRootVO();

		StringFindUtil util = new StringFindUtil();
		strcontent = util.trimReturnAndSpace(strcontent);

		//���strcontent��{}��������ȥ���������
		if(strcontent.startsWith("{") && strcontent.endsWith("}")) {
			strcontent=strcontent.substring(1,strcontent.length()-1);
			strcontent = util.trimReturnAndSpace(strcontent);
		}
		
		if(strcontent.equals("")) {
			//logger.debug("��INFO��strcontent is empty.");
			return rootvo;
		}		

		JavaClassAnalysePluginManager manager = new JavaClassAnalysePluginManager();
		
		while(strcontent.length()>0) {
			strcontent = util.trimReturnAndSpace(strcontent);
			//ѭ�����÷�����
			boolean loopflag = false;
			for(IJavaClassAnalysePlugin plugin : manager.getPluginarray()) {
				AbstractJavaClassElement newele = plugin.findFirstElement(strcontent);
				if(newele == null) {
					continue;
				}else {
					loopflag = true;
					rootvo.getChildren().add(newele);
					strcontent = newele.getComputeleftstring();
					//�����˴�ѭ��
					break;
				}
			}
			if(loopflag) {
				//�����ⲿѭ��
				continue;
			}else {
				//logger.error("��ERROR�����ò������ʧ��:"+strcontent);
				return null;
			}
		}
		//����ѭ�����ý��
		return rootvo;
	}
}
