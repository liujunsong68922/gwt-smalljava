package com.liu.gwt.gwt_smalljava.level6_vm;

import com.liu.gwt.gwt_smalljava.level6_vm.objectcall.IJava_ObjectCall;

/**
 * IJavaVM是在GWT的运行环境上模拟实现一个Java VM
 * 之所以设计这个Java VM，是为了模拟Java，在不支持Java反射的条件下
 * 1.模拟实现newInstance()创建新对象
 * 2.模拟实现obj.method()动态方法调用
 * @author liujunsong
 *
 */
public interface IJavaVM {

}
