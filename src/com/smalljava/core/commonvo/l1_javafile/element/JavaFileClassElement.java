package com.smalljava.core.commonvo.l1_javafile.element;

import com.smalljava.core.commonvo.l1_javafile.AbstractJavaFileElement;
import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;

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
