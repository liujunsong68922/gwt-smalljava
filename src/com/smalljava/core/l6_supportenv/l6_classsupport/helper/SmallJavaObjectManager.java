package com.smalljava.core.l6_supportenv.l6_classsupport.helper;

import java.util.ArrayList;

import com.smalljava.core.commonvo.instancevo.JavaClassInstanceVO;

/**
 * All SmallJava object created by smalljava itself will stored on it.
 * @author liujunsong
 *
 */
public class SmallJavaObjectManager {
	private ArrayList<JavaClassInstanceVO> volist = 
			new ArrayList<JavaClassInstanceVO>();

	public ArrayList<JavaClassInstanceVO> getVolist() {
		return volist;
	}

	public void setVolist(ArrayList<JavaClassInstanceVO> volist) {
		this.volist = volist;
	}
	
}