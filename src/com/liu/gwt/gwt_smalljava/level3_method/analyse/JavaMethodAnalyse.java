package com.liu.gwt.gwt_smalljava.level3_method.analyse;

import com.liu.gwt.gwt_smalljava.common.StringFindUtil;
import com.liu.gwt.gwt_smalljava.level2_class.vo.element.JavaClassMethodElement;
import com.liu.gwt.gwt_smalljava.level3_method.vo.JavaMethodArgumentVO;
import com.liu.gwt.gwt_smalljava.level3_method.vo.JavaMethodRootVO;

/**
 * JAVA��method����������
 * @author liujunsong
 *
 */
public class JavaMethodAnalyse {
	//private Logger logger = LoggerFactory.getLogger(JavaMethodAnalyse.class);
	/**
	 * ��һ�������Ķ����ַ������������ƣ�����������������
	 * @param strcontent
	 * @return
	 */
	public JavaMethodRootVO analyseMethod(JavaClassMethodElement methodelement) {
		if(methodelement==null ) {
			//logger.info("��ERROR��strcontent is null.");
			return null;
		}

		String strcontent = methodelement.getStringcontent();
		if(strcontent == null) {
			//logger.error("��ERROR��strcontent is null.");
			return null;			
		}else {
			//logger.debug("method content:"+strcontent);
		}
		StringFindUtil util = new StringFindUtil();
		strcontent = util.trimReturnAndSpace(strcontent);
		if(strcontent.equals("")) {
			//logger.error("��ERROR��strcontent is emtyp.");
			return null;			
		}
		
		if(! strcontent.endsWith("}")) {
			//logger.error("��ERROR��strcontent is not ended by ��}��.");
			return null;			
			
		}
		
		JavaMethodRootVO rootvo = new JavaMethodRootVO();
		//TODO:�����г�ȡ����������������������������
		int ipos1 = util.findfirstStringForBlock(strcontent, "{");
		if(ipos1<0) {
			//logger.error("��ERROR��strcontent cannot find ��{��.");
			return null;
		}
		
		//����Method������ַ�������
		String methodcontent = strcontent.substring(ipos1);
		rootvo.setMethodContent(methodcontent);
		
		
		int ipos2 = strcontent.indexOf("(");
		int ipos3 = strcontent.indexOf(")");
		if(ipos2<0 || ipos3<0) {
			//logger.error("��ERROR��strcontent ����()ʧ�ܣ�"+strcontent);
			return null;
		}
		if(ipos2>ipos3) {
			//logger.error("��ERROR��strcontent ipos2>ipos3");
			return null;
			
		}
		
		String argdefine = strcontent.substring(ipos2+1,ipos3);
		String args[] = argdefine.split(",");
		for(String arg:args) {
			//���տո����ֽ�arg
			arg = arg.trim();
			if(arg.length()==0) {
				//���ַ�������ѭ��
				continue;
			}
			String argvalue[] = arg.split(" ");
			if(argvalue.length !=2) {
				//logger.error("��ERROR��Method arg analyse error:"+arg);
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
		//���һ���ַ����Ƿ�����
		if(sdata.length>0) {
			rootvo.setMethodname(sdata[sdata.length-1]);
		}else {
			//logger.error("��ERROR�� leftdata is empty."+leftdata);
			return null;
		}
		
		return rootvo;
	}
}
