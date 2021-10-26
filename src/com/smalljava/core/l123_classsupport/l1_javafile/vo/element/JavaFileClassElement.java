package com.smalljava.core.l123_classsupport.l1_javafile.vo.element;

import com.smalljava.core.l123_classsupport.l1_javafile.vo.AbstractJavaFileElement;
import com.smalljava.core.l123_classsupport.l2_classdefine.vo.SmallJavaClassTemplateVO;

//import com.smalljava.core.classloader.l1_javafile.vo.AbstractJavaFileElement;

public class JavaFileClassElement extends AbstractJavaFileElement {
	private String classname;

	private SmallJavaClassTemplateVO classtemplatevo = null;
	
	public SmallJavaClassTemplateVO getClasstemplatevo() {
		return classtemplatevo;
	}

	public void setClasstemplatevo(SmallJavaClassTemplateVO classtemplatevo) {
		this.classtemplatevo = classtemplatevo;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
}
