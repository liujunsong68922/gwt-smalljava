package com.smalljava.jvmplugin.newinstance;

import java.util.ArrayList;
import java.util.HashMap;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.l6_vm.newinstance.INewInstance;
import com.smalljava.core.l6_vm.newinstance.NewInstancePluginManager;
import com.smalljava.jvmplugin.newinstance.impl.awt.AwtButtonNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.awt.AwtCanvasNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.awt.AwtCheckBoxGroupNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.awt.AwtCheckBoxNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.awt.AwtChoiceNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.awt.AwtFrameNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.awt.AwtLabelNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.awt.AwtListNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.awt.AwtPanelNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.swing.SwingJButtonNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.swing.SwingJCheckBoxNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.swing.SwingJComboBoxNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.swing.SwingJFrameNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.swing.SwingJLabelNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.swing.SwingJListNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.swing.SwingJPanelNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.swing.SwingJRadioButtonNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.swing.SwingJTextAreaNewInstance;
import com.smalljava.jvmplugin.newinstance.impl.swing.SwingJTextFieldNewInstance;

public class JvmNewInstancePlugin {

	/**
	 * using this method to plug jvm class into plugin hashmap
	 * this is used when create newinstance by jvm
	 * smalljava is not a full-function java env.
	 * It is a sandbox env.so that you can define sandbox class in it.
	 * impl is some demo implement.
	 * ---------------------------------------------------------
	 * JvmNewInstancePlugin是一个使用JVM进行实例化的插件，它使用的算法是使用java class
	 * I 这些放的实现需要依赖于 JavaVM，因此不包含在smalljava-core内部，这部分功能在GWT环境下不可用
	 * I Smalljava的定位不是一个全功能的Java执行环境，它是一个执行沙盒
	 * I 使用者可以根据自己的实际需要对这个Java执行沙盒进行功能限定和重新设定
	 * I impl 包下是一些demo 实现
	 * @param manager
	 */
	public void registerSwingAndAwt(NewInstancePluginManager manager) {
		this.registerSwingAndAwt(manager);
		this.registerAwtPluginManager(manager);
	}
	
	
	public void registerSwingPluginManager(NewInstancePluginManager manager) {
		//register swing implemnt.
		HashMap<String, INewInstance> map = manager.getInstancemap();
		map.put("jbutton", new SwingJButtonNewInstance());
		map.put("jcombobox", new SwingJComboBoxNewInstance());
		map.put("jcheckbox", new SwingJCheckBoxNewInstance());
		map.put("jframe", new SwingJFrameNewInstance());
		map.put("jlabel", new SwingJLabelNewInstance());
		map.put("jlist", new SwingJListNewInstance());
		map.put("jpanel", new SwingJPanelNewInstance());
		map.put("jradio", new SwingJRadioButtonNewInstance());
		map.put("jtextarea", new SwingJTextAreaNewInstance());
		map.put("jtextfield", new SwingJTextFieldNewInstance());
	}

	public void registerAwtPluginManager(NewInstancePluginManager manager) {
		//register swing implemnt.
		HashMap<String, INewInstance> map = manager.getInstancemap();
		map.put("button", new AwtButtonNewInstance());
		map.put("canvas", new AwtCanvasNewInstance());
		map.put("checkbox", new AwtCheckBoxNewInstance());
		map.put("checkboxgroup", new AwtCheckBoxGroupNewInstance());
		map.put("choice", new AwtChoiceNewInstance());
		map.put("frame", new AwtFrameNewInstance());
		map.put("label", new AwtLabelNewInstance());
		map.put("list", new AwtListNewInstance());
		map.put("panel", new AwtPanelNewInstance());
	}
}
