package com.smalljava.core.commonvo.instancevo;


//import com.smalljava.core.common.logging.Logger;
//import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.commonvo.l2_javaclass.SmallJavaClassTemplateVO;
import com.smalljava.core.l9_space.vartable.IVarTable;

/**
 * 
 * @author liujunsong
 *
 */
public class JavaClassInstanceVO {
	//logger
//	private Logger logger = LoggerFactory.getLogger(JavaClassInstanceVO.class);

	/**
	 * each object instance has its own vartable,
	 * while will connect to static class 's vartable as parent
	 * so that the static variable defined in class file could be supported.
	 */
	private IVarTable vartable;
	
	//This is the class template vo define.
	private SmallJavaClassTemplateVO classtemplatevo;

	public IVarTable getVartable() {
		return vartable;
	}

	public void setVartable(IVarTable vartable) {
		this.vartable = vartable;
	}

	
	public SmallJavaClassTemplateVO getClasstemplatevo() {
		return classtemplatevo;
	}

	public void setClasstemplatevo(SmallJavaClassTemplateVO classtemplatevo) {
		this.classtemplatevo = classtemplatevo;
	}

	public void show() {
//		for (AbstractSmallJavaClassElement child : children) {
//			logger.info(child.getClass().getSimpleName() + ":" + child.getStringcontent());
//		}
	}

}
