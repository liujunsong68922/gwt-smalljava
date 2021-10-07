package com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.var;

import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.AstOperAndPos;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionanalyse.plugin.DefaultIPluginImplement;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.AbstractAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.MiddleAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.RootAST;
import com.liu.gwt.gwt_smalljava.level5_expression.expressionvo.var.VarSetOperElement;

public class VarSetOperPlugin extends DefaultIPluginImplement{

	@Override
	public AbstractAST analyse(String strcode) {
		String opers[] = new String[] {"="};
		AstOperAndPos oap = getFirstOperCode(strcode,opers);
		
		if(oap==null) {
			//查找=失败,说明这个不是 = 表达式
			return null;
		}
		
		//生成新对象准备返回
		//使用VarSetOperElement
		VarSetOperElement root = new VarSetOperElement();
		//记录操作符
		root.setOpercode(oap.getOpercode());
		
		//从操作符开始切割字符串为两部分
		String leftstring = strcode.substring(0, oap.ipos);
		String rightString = strcode.substring(oap.ipos + oap.opercode.length());

		//leftstring = trimReturnAndSpace(leftstring);
		if(leftstring.equals("")) {
			//左操作数不可为空
			return null;
		}else {
			//判断结束字符串的合法性
			char lastchar1 = leftstring.charAt(leftstring.length()-1);
			if((lastchar1>='a' && lastchar1<='z') 
				|| (lastchar1>='A' && lastchar1<='Z')
				|| (lastchar1>='0' && lastchar1<='9')
				|| (lastchar1==' ')){
				//这些是合法字符串，通过
			}else {
				//左操作数结束不合法，不处理
				return null;
			}
		}
		//检查右操作数是否合法
		if(rightString.equals("")) {
			//左操作数不可为空
			return null;
		}else {
			char firstchar = rightString.charAt(0);
			if((firstchar>='a' && firstchar<='z') 
					|| (firstchar>='A' && firstchar<='Z')
					|| (firstchar>='0' && firstchar<='9')
					|| (firstchar=='(')
					|| (firstchar=='\"') 
					|| (firstchar==' ')){
				//这些是合法字符串，通过
				}else {
					//左操作数结束不合法，不处理
					return null;
				}
		}
		
		
		RootAST leftelement = new MiddleAST();
		RootAST rightelement = new MiddleAST();
		leftelement.setStrexpression(leftstring);
		rightelement.setStrexpression(rightString);
		
		root.getChildren().add(leftelement);
		root.getChildren().add(rightelement);
		
		return root;
	}
}
