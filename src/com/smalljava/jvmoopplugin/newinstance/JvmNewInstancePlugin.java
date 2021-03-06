package com.smalljava.jvmoopplugin.newinstance;

import java.util.HashMap;

import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.INewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.NewInstancePluginManager;
import com.smalljava.jvmoopplugin.newinstance.impl.awt.AwtButtonNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.awt.AwtCanvasNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.awt.AwtCheckBoxGroupNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.awt.AwtCheckBoxNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.awt.AwtChoiceNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.awt.AwtFrameNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.awt.AwtLabelNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.awt.AwtListNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.awt.AwtPanelNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.swing.SwingJButtonNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.swing.SwingJCheckBoxNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.swing.SwingJComboBoxNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.swing.SwingJFrameNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.swing.SwingJLabelNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.swing.SwingJListNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.swing.SwingJPanelNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.swing.SwingJRadioButtonNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.swing.SwingJTextAreaNewInstance;
import com.smalljava.jvmoopplugin.newinstance.impl.swing.SwingJTextFieldNewInstance;

public class JvmNewInstancePlugin {

	/**
	 * using this method to plug jvm class into plugin hashmap
	 * this is used when create newinstance by jvm
	 * smalljava is not a full-function java env.
	 * It is a sandbox env.so that you can define sandbox class in it.
	 * impl is some demo implement.
	 * ---------------------------------------------------------
	 * JvmNewInstancePlugin???????????????JVM??????????????????????????????????????????????????????java class
	 * I ????????????????????????????????? JavaVM?????????????????????smalljava-core???????????????????????????GWT??????????????????
	 * I Smalljava?????????????????????????????????Java???????????????????????????????????????
	 * I ???????????????????????????????????????????????????Java?????????????????????????????????????????????
	 * I impl ???????????????demo ??????
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
