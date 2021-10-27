package com.smalljava.core.l6_supportenv.l6_classsupport.helper;

import java.util.HashMap;

import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * All SmallJava Class define will stored here.
 * @author liujunsong
 *
 */
public class SmallJavaClassManager {
	private HashMap<String,SmallJavaClassTemplateVO> classdefinemap = 
			new HashMap<String,SmallJavaClassTemplateVO>();

	private HashMap<String,IVarTable> classStaticVartableMap 
	= new HashMap<String,IVarTable>();
	
	public HashMap<String, SmallJavaClassTemplateVO> getClassdefinemap() {
		return classdefinemap;
	}

	public void setClassdefinemap(HashMap<String, SmallJavaClassTemplateVO> classdefinemap) {
		this.classdefinemap = classdefinemap;
	}

	//get method
	public HashMap<String, IVarTable> getClassStaticVartableMap() {
		return classStaticVartableMap;
	}

	//set method
	public void setClassStaticVartableMap(HashMap<String, IVarTable> classStaticVartableMap) {
		this.classStaticVartableMap = classStaticVartableMap;
	}
	
	
}
