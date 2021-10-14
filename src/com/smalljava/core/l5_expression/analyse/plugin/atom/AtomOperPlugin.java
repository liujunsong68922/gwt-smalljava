package com.smalljava.core.l5_expression.analyse.plugin.atom;

import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.atom.AtomElement;

/**
 * MEMO：（）子表达式分析插件
 * 
 * @author liujunsong
 *
 */
public class AtomOperPlugin extends DefaultIPluginImplement{

	/**
	 * MEMO:用括号包裹的子表达式
	 */
	@Override
	public AbstractAST analyse(String strcode) {
		strcode = this.trimReturnAndSpace(strcode);
		if(!(strcode.startsWith("(") && strcode.endsWith(""))) {
			return null;
		}
		
		//再去查找一下),判断有没有其他的)
		String opers[] = new String[] {")"};
		AstOperAndPos oap = getFirstOperCode(strcode,opers);
		
		if(oap==null) {
			return null;
		}else if(oap.getIpos()<strcode.length()-1) {
			//存在多个),解析失败，返回
			return null;
		}
		//括号表达式在解析时不需要考虑其他的更高优先级
		
		AtomElement root = new AtomElement();
		RootAST child1 = new MiddleAST();
		String s1 = strcode.substring(1,strcode.length()-1);
		child1.setStrexpression(s1);
		root.getChildren().add(child1);
		//分析结束，返回之
		return root;
	}

}