package com.smalljava.core.l4_block.blockanalyse.plugin;

import com.smalljava.core.l4_block.blockvo.BasicBlock;

/**
 * JAVA�﷨�������Ĳ�����Բ����ʽ�ṩ����������������
 * MEMO�����еĲ��ʵ��ͳһ�Ľӿڣ�ʵ��ͳһ�Ĺ���
 * @author liujunsong
 *
 */
public interface IAnalysePlugin {
	
	public boolean analyse(BasicBlock rootblock);

}
