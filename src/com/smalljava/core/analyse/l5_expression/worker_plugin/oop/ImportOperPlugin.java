package com.smalljava.core.analyse.l5_expression.worker_plugin.oop;

import com.smalljava.core.analyse.l5_expression.worker_plugin.DefaultIPluginImplement;
import com.smalljava.core.commonvo.l5_expression.AbstractAST;
import com.smalljava.core.commonvo.l5_expression.oop.ImportExpressionElement;

public class ImportOperPlugin extends DefaultIPluginImplement{

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		
		//检查开始条件
		if(! strcode.startsWith("import")) {
			return null;
		}
		
		//如果是以import开头，则转换成一个import元素
		ImportExpressionElement importvo = new ImportExpressionElement();
		importvo.setStrexpression(strcode);
		
		return importvo;
	}

}
