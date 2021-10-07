package com.liu.gwt.gwt_smalljava.level1_javafile.analyse;

import com.liu.gwt.gwt_smalljava.common.StringFindUtil;
import com.liu.gwt.gwt_smalljava.level1_javafile.vo.JavaFileRootVO;
import com.liu.gwt.gwt_smalljava.level1_javafile.vo.element.JavaFileImportElement;
import com.liu.gwt.gwt_smalljava.space.IClassTable;
import com.liu.gwt.gwt_smalljava.space.impl.ClassTableImpl;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import com.liu.smalljavav2.common.StringFindUtil;
//import com.liu.smalljavav2.level1_javafile.vo.JavaFileRootVO;
//import com.liu.smalljavav2.level1_javafile.vo.element.JavaFileImportElement;
//import com.liu.smalljavav2.space.IClassTable;
//import com.liu.smalljavav2.space.impl.ClassTableImpl;

public class ClassTableAnalyse {
	//private static Logger logger = LoggerFactory.getLogger(ClassTableAnalyse.class);
	/**
	 * MEMO��ͨ����ȡfilerootvo�е�import��䣬���һ���ض���IClassTable.
	 * MEMO:���������IClassTable�����ں������л��ڱ�ʹ��
	 * @param filerootvo
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public IClassTable analyse(JavaFileRootVO filerootvo) {
		if(filerootvo == null) {
			//logger.error("��Argument Error��  filerootvo is null.");
			return null;
		}
		
		ClassTableImpl classtableimpl = new ClassTableImpl();
		StringFindUtil util = new StringFindUtil();
		
		//ѭ����ȡimport��䣬��䵽classtableimpl����ȥ
		for(JavaFileImportElement element:filerootvo.getImportlist()) {
			//logger.debug(element.getStringcontent());
			if(element.getStringcontent().startsWith("import ")) {
				//ȥimport
				String classtoimport = element.getStringcontent().substring(6);
				//ȥ�ո�
				classtoimport = util.trimReturnAndSpace(classtoimport);
				if(classtoimport.endsWith(";")) {
					//ȥ��;ȥ���ո�
					classtoimport = classtoimport.substring(0,classtoimport.length()-1);
					classtoimport = util.trimReturnAndSpace(classtoimport);
					
					int ipos = classtoimport.lastIndexOf('.');
					if(ipos<0) {
						//logger.error("Cannot find classname:"+classtoimport);
						continue;
					}else {
						String strclassname = classtoimport.substring(ipos+1);
						//��ȡ��ϣ�д�뵽ClassTable
						Class cl;
						try {
							cl = Class.forName(classtoimport);
							Object obj1 = cl.newInstance();
							//ִ��import��䣬���ඨ��д��classmap��
							classtableimpl.getClassmap().put(strclassname, cl);
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
							//�����쳣��������һ��
							//logger.error("ERROR",e);
							continue;
						}
						
					}
					
				}else {
					//logger.error("[UNKOWN END]"+classtoimport);
					continue;
				}
				
			}else {
				//logger.error("[UNKOWN START]"+element.getComputeleftstring());
			}
		}
		return classtableimpl;
		
	}
}
