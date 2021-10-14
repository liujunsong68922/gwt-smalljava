package com.smalljava.core.classloader.l2_class.analyse;

import com.smalljava.core.classloader.l2_class.analyse.plugin.IJavaClassAnalysePlugin;
import com.smalljava.core.classloader.l2_class.analyse.plugin.JavaClassAnalysePluginManager;
import com.smalljava.core.classloader.l2_class.vo.JavaClassTemplateVO;
import com.smalljava.core.classloader.l2_class.vo.element.AbstractJavaClassElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;

/**
 * ����ฺ��һ��class������ַ����ֽ��һ��class��VO����
 * @author liujunsong
 *
 */
public class JavaClassAnalyse {
	private Logger logger = LoggerFactory.getLogger(JavaClassAnalyse.class);
	@SuppressWarnings("static-access")
	public JavaClassTemplateVO analyse(String strcontent) {
		
		if(strcontent==null ) {
			logger.error("��ERROR��strcontent is null.");
			return null;
		}
		JavaClassTemplateVO rootvo = new JavaClassTemplateVO();

		StringFindUtil util = new StringFindUtil();
		strcontent = util.trimReturnAndSpace(strcontent);

		//���strcontent��{}��������ȥ���������
		if(strcontent.startsWith("{") && strcontent.endsWith("}")) {
			strcontent=strcontent.substring(1,strcontent.length()-1);
			strcontent = util.trimReturnAndSpace(strcontent);
		}
		
		if(strcontent.equals("")) {
			logger.debug("��INFO��strcontent is empty.");
			return rootvo;
		}		

		JavaClassAnalysePluginManager manager = new JavaClassAnalysePluginManager();
		
		while(strcontent.length()>0) {
			strcontent = util.trimReturnAndSpace(strcontent);
			//ѭ�����÷�������������ѭ����ִ��״̬��־
			boolean loopflag = false;
			for(IJavaClassAnalysePlugin plugin : manager.getPluginarray()) {
				//���ַ������������Ԫ�س���
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
				//��������ҵ���һ��Ԫ�ؼ����ⲿѭ��
				continue;
			}else {
				logger.error("��ERROR�����ò������ʧ��:"+strcontent);
				return null;
			}
		}
		//����ѭ�����ý��
		return rootvo;
	}
}
