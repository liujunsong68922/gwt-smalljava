package com.smalljava.core.classloader.l1_javafile.analyse;

import com.smalljava.core.classloader.l1_javafile.vo.JavaFileRootVO;
import com.smalljava.core.classloader.l1_javafile.vo.element.JavaFileImportElement;
import com.smalljava.core.common.StringFindUtil;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l9_space.classtable.IClassTable;
import com.smalljava.core.l9_space.classtable.impl.ClassTableImpl;

public class ClassTableAnalyse {
	private static Logger logger = LoggerFactory.getLogger(ClassTableAnalyse.class);
	/**
	 * MEMO：通过读取filerootvo中的import语句，输出一个特定的IClassTable.
	 * MEMO:这里产生的IClassTable对象在后面所有环节被使用
	 * @param filerootvo
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public IClassTable analyse(JavaFileRootVO filerootvo) {
		if(filerootvo == null) {
			logger.error("【Argument Error】  filerootvo is null.");
			return null;
		}
		
		ClassTableImpl classtableimpl = new ClassTableImpl();
		StringFindUtil util = new StringFindUtil();
		
		//循环读取import语句，填充到classtableimpl里面去
		for(JavaFileImportElement element:filerootvo.getImportlist()) {
			logger.debug(element.getStringcontent());
			if(element.getStringcontent().startsWith("import ")) {
				//去import
				String classtoimport = element.getStringcontent().substring(6);
				//去空格
				classtoimport = util.trimReturnAndSpace(classtoimport);
				if(classtoimport.endsWith(";")) {
					//去掉;去掉空格
					classtoimport = classtoimport.substring(0,classtoimport.length()-1);
					classtoimport = util.trimReturnAndSpace(classtoimport);
					
					int ipos = classtoimport.lastIndexOf('.');
					if(ipos<0) {
						logger.error("Cannot find classname:"+classtoimport);
						continue;
					}else {
						String strclassname = classtoimport.substring(ipos+1);
						//读取完毕，写入到ClassTable
						Class cl;
						try {
							cl = Class.forName(classtoimport);
							Object obj1 = cl.newInstance();
							//执行import语句，将类定义写入classmap中
							classtableimpl.getClassmap().put(strclassname, cl);
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
							//发生异常，跳过这一行
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
