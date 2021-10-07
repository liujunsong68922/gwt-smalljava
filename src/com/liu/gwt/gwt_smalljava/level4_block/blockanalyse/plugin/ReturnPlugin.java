package com.liu.gwt.gwt_smalljava.level4_block.blockanalyse.plugin;

import com.liu.gwt.gwt_smalljava.level4_block.blockvo.BasicBlock;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.ElementWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.JSONWrapper;
import com.liu.gwt.gwt_smalljava.level4_block.blockvo.childblock.ReturnBlock;

/**
 * MEMO�����Return�ؼ��� �����⴦��
 * @author liujunsong
 *
 */
public class ReturnPlugin extends DefaultAbstractAnalysePlugin {

	@Override
	public boolean analyse(BasicBlock rootblock) {
		if (rootblock.computestring.startsWith("return ")
			||	rootblock.computestring.startsWith("return(")) {
			String strcontent = rootblock.computestring.substring(6);
			strcontent = this._trimReturnAndSpace(strcontent);
			ReturnBlock returnblock = new ReturnBlock("ReturnBlock", strcontent, rootblock);
			rootblock.getChildren().add(returnblock);
			rootblock.computestring="";
			return true;
		} else {
			return true;
		}

	}

//	@Override
//	public boolean analyse(ElementWrapper rootelement) {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean analyse(JSONWrapper rootelement) {
//		// TODO Auto-generated method stub
//		return true;
//	}

}
