package com.smalljava.core.l5_expression.analyse.plugin.two;

import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l5_expression.analyse.AstOperAndPos;
import com.smalljava.core.l5_expression.analyse.plugin.DefaultIPluginImplement;
import com.smalljava.core.l5_expression.analyse.plugin.var.VarSetOperPlugin;
import com.smalljava.core.l5_expression.vo.AbstractAST;
import com.smalljava.core.l5_expression.vo.MiddleAST;
import com.smalljava.core.l5_expression.vo.RootAST;
import com.smalljava.core.l5_expression.vo.two.DualOperDataOperElement;

/**
 * 逻辑比较运算符号的实现
 * @author liujunsong
 *
 */
public class LogicCompareOperPlugin extends DefaultIPluginImplement{
	Logger logger = LoggerFactory.getLogger(LogicCompareOperPlugin.class);
	
	@Override
	public AbstractAST analyse(String strcode) {
		logger.info("enter LogicCompareOperPlugin.analyse.");
		
		String opers[] = new String[] {">=","<=",">","<","==","!="};
		AstOperAndPos oap = getFirstOperCode(strcode,opers);
		
		if(oap==null) {
			//查找逻辑比较符号失败
			logger.info("Cannot find opers");
			return null;
		}
		
		String highopers[] = new String[] {"&&","||","!"};
		AstOperAndPos highoap = getFirstOperCode(strcode,highopers);
		
		if(highoap!=null) {
			//存在计算优先级更低的运算符，返回
			logger.info("find high oper,return.");
			return null;
		}
		
		//生成新对象准备返回
		//使用一个二元运算符元素来进行封装
		DualOperDataOperElement root = new DualOperDataOperElement();
		//记录操作符
		root.setOpercode(oap.getOpercode());
		
		//从操作符开始切割字符串为两部分
		String leftstring = strcode.substring(0, oap.ipos);
		String rightString = strcode.substring(oap.ipos + oap.opercode.length());

		//leftstring = trimReturnAndSpace(leftstring);
		if(leftstring.equals("")) {
			//左操作数不可为空
			logger.error("left var is null, return.");
			return null;
		}else {
			//判断结束字符串的合法性
			char lastchar1 = leftstring.charAt(leftstring.length()-1);
			if((lastchar1>='a' && lastchar1<='z') 
				|| (lastchar1>='A' && lastchar1<='Z')
				|| (lastchar1==')')
				|| (lastchar1==' ')){
				//这些是合法字符串，通过
			}else {
				//左操作数结束不合法，不处理
				logger.error("left var is not valid. return null."+ leftstring);
				return null;
			}
		}
		//检查右操作数是否合法
		if(rightString.equals("")) {
			//左操作数不可为空
			logger.error("rightstring is empty,return null");
			return null;
		}else {
			char firstchar = rightString.charAt(0);
			if((firstchar>='a' && firstchar<='z') 
					|| (firstchar>='A' && firstchar<='Z')
					|| (firstchar=='(')
					|| (firstchar==' ')){
					//这些是合法字符串，通过
				}else {
					//左操作数结束不合法，不处理
					logger.error("rightstring is invalid,return null."+rightString);
					return null;
				}
		}
		
		
		RootAST leftelement = new MiddleAST();
		RootAST rightelement = new MiddleAST();
		leftelement.setStrexpression(leftstring);
		rightelement.setStrexpression(rightString);
		
		
		root.getChildren().add(leftelement);
		root.getChildren().add(rightelement);
		
		//分析结束，返回之
		return root;
	}

}
