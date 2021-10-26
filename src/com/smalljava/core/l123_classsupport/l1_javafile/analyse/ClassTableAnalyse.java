package com.smalljava.core.l123_classsupport.l1_javafile.analyse;

//import com.smalljava.core.classloader.l1_javafile.vo.JavaFileRootVO;
//import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFileImportElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l123_classsupport.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.core.l123_classsupport.l1_javafile.vo.element.JavaFileImportElement;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.classtable.impl.ClassTableImpl;

public class ClassTableAnalyse {
	private static Logger logger = LoggerFactory.getLogger(ClassTableAnalyse.class);
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
			logger.error("��Argument Error��  filerootvo is null.");
			return null;
		}
		
		ClassTableImpl classtableimpl = new ClassTableImpl();
		StringFindUtil util = new StringFindUtil();
		
		//ѭ����ȡimport��䣬��䵽classtableimpl����ȥ
		for(JavaFileImportElement element:filerootvo.getImportlist()) {
			logger.debug(element.getStringcontent());
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
						logger.error("Cannot find classname:"+classtoimport);
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
							logger.error("ERROR",e);
							continue;
						}
						
					}
					
				}else {
					logger.error("[UNKOWN END]"+classtoimport);
					continue;
				}
				
			}else {
				logger.error("[UNKOWN START]"+element.getComputeleftstring());
			}
		}
		return classtableimpl;
		
	}
}
