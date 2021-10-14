package com.smalljava.core.l5_expression.analyse.plugin.one;

import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.one.LogicNotOperElement;

/**
 * 逻辑比较运算符号的实现
 * @author liujunsong
 *
 */
public class LogicNotOperPlugin extends DefaultIPluginImplement{

	@Override
	public AbstractAST analyse(String strcode) {
		//有效的逻辑取反有两种形式隔开，一种是空格，一种是括号，其余的忽略
		String opers[] = new String[] {"! ","!("};
		AstOperAndPos oap = getFirstOperCode(strcode,opers);
		
		if(oap==null) {
			//查找逻辑比较符号失败
			return null;
		}
		
		String highopers[] = new String[] {"&&","||","="};
		AstOperAndPos highoap = getFirstOperCode(strcode,highopers);
		
		if(highoap!=null) {
			//存在计算优先级更低的运算符，返回
			return null;
		}
		
		
		//生成新对象准备返回
		//使用一个1元运算符元素来进行封装
		LogicNotOperElement root = new LogicNotOperElement();
		//记录操作符
		root.setOpercode("!");
		
		//从操作符开始切割字符串为两部分
		String leftstring = strcode.substring(0, oap.ipos);
		String rightString = strcode.substring(oap.ipos + 1);
		leftstring = this.trimReturnAndSpace(leftstring);
		if(! leftstring.equals("")) {
			//左操作数必须为空,否则不处理
			return null;
		}
		
		//检查右操作数是否合法
		if(rightString.equals("")) {
			//右操作数不可为空
			return null;
		}else {
			char firstchar = rightString.charAt(0);
			if((firstchar>='a' && firstchar<='z') 
					|| (firstchar>='A' && firstchar<='Z')
					|| (firstchar=='(')
					|| (firstchar==' ')
					|| (firstchar=='!')){
					//这些是合法字符串，通过
				}else {
					//右操作数结束不合法，不处理
					return null;
				}
		}
		
		
		RootAST rightelement = new MiddleAST();
		rightelement.setStrexpression(rightString);
		
		root.getChildren().add(rightelement);
		
		//分析结束，返回之
		return root;
	}

}
