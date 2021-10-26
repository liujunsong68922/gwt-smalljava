package com.smalljava.core.l123_classsupport.helper;

import java.util.ArrayList;

import com.smalljava.core.l123_classsupport.instancevo.JavaClassInstanceVO;

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
