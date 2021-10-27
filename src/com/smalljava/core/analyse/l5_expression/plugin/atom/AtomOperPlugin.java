package com.smalljava.core.analyse.l5_expression.plugin.atom;

import com.smalljava.core.analyse.l5_expression.AstOperAndPos;
import com.smalljava.core.analyse.l5_expression.plugin.DefaultIPluginImplement;
import com.smalljava.core.commonvo.l5_expression.AbstractAST;
import com.smalljava.core.commonvo.l5_expression.MiddleAST;
import com.smalljava.core.commonvo.l5_expression.RootAST;
import com.smalljava.core.commonvo.l5_expression.atom.AtomElement;

public class AtomOperPlugin extends DefaultIPluginImplement{

	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if(!(strcode.startsWith("(") && strcode.endsWith(""))) {
			return null;
		}
		
		String opers[] = new String[] {")"};
		AstOperAndPos oap = getFirstOperCode(strcode,opers);
		
		if(oap==null) {
			return null;
		}else if(oap.getIpos()<strcode.length()-1) {
			return null;
		}
		
		AtomElement root = new AtomElement();
		RootAST child1 = new MiddleAST();
		String s1 = strcode.substring(1,strcode.length()-1);
		child1.setStrexpression(s1);
		root.getChildren().add(child1);
		return root;
	}

}