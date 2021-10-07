package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;

/**
 * JAVA�﷨�������Ĳ�����Բ����ʽ�ṩ����������������
 * MEMO�����еĲ��ʵ��ͳһ�Ľӿڣ�ʵ��ͳһ�Ĺ���
 * @author liujunsong
 *
 */
public interface IAnalysePlugin {
	
	public boolean analyse(BasicBlock rootblock);
	
//	public boolean analyse(ElementWrapper rootelement);
	
//	public boolean analyse(JSONWrapper rootelement);
}
