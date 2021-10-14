package com.smalljava.core.l4_block;

/**
 * SmallJavaConst是所有字符串常量的定义，用来统一在程序的各个地方共享字符串的定义
 * 
 * @author liujunsong
 *
 */
public class SmallJavaBlockConst {
	// MEMO：代码块部分的定义，这里定义的是代码块的类型
	/**
	 * MEMO:引入类定义的代码块
	 */
	public final static String ImportBlock ="ImportBlock";
	/**
	 * MEMO:空白代码块，这个块不计算
	 */
	public final static String EmptyBlock = "EmptyBlock";

	/**
	 * MEMO:单行备注代码块，这个块不计算
	 */
	public final static String SingleLineMemo = "SingleLineMemo";

	/**
	 * MEMO:多行备注代码块，这个块不计算
	 */
	public final static String MultiLineMemo = "MultiLineMemo";

	/**
	 * MEMO:SubBlock代表用{}闭合的部分,SubBlock有自己的变量表和子节点，但不需要单独计算 MEMO:这个块由BracePlugin产生
	 */
	public final static String SubBlock = "SubBlock";
	/**
	 * MEMO：单一的表达式，这个表达式可以计算，但没有自己的变量表 MEMO：这个块由SemicolonPlugin产生
	 */
	public final static String Expression = "Expression";

	// ----DO...WHILE...部分-
	/**
	 * MEMO:这是一个JAVA块，代表一个循环
	 */
	public final static String DoWhileBlock = "DOWHILEBLOCK";
	/**
	 * MEMO:这是DO的部分
	 */
	public final static String DoNode = "DONODE";
	/**
	 * MEMO:这是while的部分
	 */
	public final static String WhileNode = "WHILENODE";

	// ----WHILE...DO...部分
	/**
	 * MEMO:这是一个JAVA块，代表一个循环
	 */
	public final static String WhileBlock = "WHILEBLOCK";

	/**
	 * MEMO:这是While条件的部分
	 */
	public final static String WhileCondition = "WHILECONDITION";

	/**
	 * MEMO:这是while的循环部分
	 */
	public final static String WhileLoopNode = "WHILELOOPNODE";

	// ----IF...ELSE...部分
	public final static String Ifblock = "IFBLOCK";
	public final static String IfConditionBlock = "IFCONDITIONBLOCK";
	public final static String IfTrueBlock = "IFTRUEBLOCK";
	public final static String IfElseBlock = "IFElSEBLOCK";

	// ----For 循环部分 ，暂时不支持迭代器循环
	public final static String ForBlock = "FORBLOCK";
	public final static String ForBeginNode = "FORBEGINNODE";
	public final static String ForConditionNode = "FORCONDITIONNODE";
	public final static String ForLoopNode = "FORLOOPNODE";
	public final static String ForLoopBlock = "FORLOOPBLOCK";
	
	// -----VARTABLE 代表变量表
	public final static String VarTable = "VARTABLE";

}
