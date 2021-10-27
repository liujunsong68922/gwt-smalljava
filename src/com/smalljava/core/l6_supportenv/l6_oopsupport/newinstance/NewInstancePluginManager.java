package com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.gwtuiplugin.GwtAbsolutePanelNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.gwtuiplugin.GwtButtonNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.gwtuiplugin.GwtHtmlNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.gwtuiplugin.GwtImageNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.gwtuiplugin.GwtLabelNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.gwtuiplugin.GwtTextAreaNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.gwtuiplugin.GwtTextBoxNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.javaplugin.ArrayListNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.javaplugin.BitSetNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.javaplugin.DateNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.javaplugin.HashMapNewInstance;
import com.smalljava.core.l6_supportenv.l6_oopsupport.newinstance.javaplugin.HashSetNewInstance;

/**
 * NewInstancePluginManager is all plugin 's manager. It enclose native
 * instancemap to outside. If you want to customer its action.following as
 * follow step. (1) write an NewInstancePlugin Implements INewInstance. (2)
 * register this plugin implements in instancemap. (3) When you call expression
 * eval function,this map will action.
 * ---------------------------------------------------------
 * NewInstancePluginManager 是所有插件的统一管理者。 I 这个类对外暴露了一个instancemap来供调用者进行扩展订制 I
 * 如果你需要对这个类的具体实现进行扩展，例如需要增加一个新的类的实现，或者覆盖它的默认实现 I 你需要按照如下步骤来完成 (1)
 * 基于INewInstance来编写一个具体的实现类 (2) 在instancemap里面手工注册这个实现类(如果类名重复，会覆盖原来的实现) (3)
 * 当你的代码调用到表达式的eval功能时，这个map就会发挥其作用
 * 
 * @author liujunsong
 *
 */
public class NewInstancePluginManager implements INewInstance {
	Logger logger = LoggerFactory.getLogger(NewInstancePluginManager.class);

	private HashMap<String, INewInstance> instancemap = new HashMap<String, INewInstance>();

	public HashMap<String, INewInstance> getInstancemap() {
		return instancemap;
	}

	public void setInstancemap(HashMap<String, INewInstance> instancemap) {
		this.instancemap = instancemap;
	}

	public NewInstancePluginManager() {
		// register standard INewInstance implements class.
		// register key is lower case.
		// 注册所有默认的实现类，classname全部为小写
		instancemap.put("hashmap", new HashMapNewInstance());
		instancemap.put("hashset", new HashSetNewInstance());
		instancemap.put("arraylist", new ArrayListNewInstance());
		instancemap.put("bitset", new BitSetNewInstance());
		instancemap.put("date", new DateNewInstance());

		// 注册GWT-ui 相关组件
		instancemap.put("gwtbutton", new GwtButtonNewInstance());
		instancemap.put("gwtlabel", new GwtLabelNewInstance());
		instancemap.put("gwttextbox", new GwtTextBoxNewInstance());
		instancemap.put("gwttextarea", new GwtTextAreaNewInstance());
		instancemap.put("gwtimage", new GwtImageNewInstance());
		instancemap.put("gwthtml", new GwtHtmlNewInstance());
		instancemap.put("gwtabsolutepanel", new GwtAbsolutePanelNewInstance());

		// on default condition, all ui match to gwt ui
		// 默认情况下，所有的UI组件映射到GWT UI上去
		instancemap.put("button", new GwtButtonNewInstance());
		instancemap.put("label", new GwtLabelNewInstance());
		instancemap.put("textbox", new GwtTextBoxNewInstance());
		instancemap.put("textarea", new GwtTextAreaNewInstance());
		instancemap.put("image", new GwtImageNewInstance());
		instancemap.put("html", new GwtHtmlNewInstance());
		instancemap.put("absolutepanel", new GwtAbsolutePanelNewInstance());
	}

	@Override
	public Object newInstance(String classname) {
		// convert classname to lower case.
		// classname is not sensitive case.
		String myclassname = classname.toLowerCase();

		INewInstance inst = instancemap.get(myclassname);
		if (inst != null) {
			return inst.newInstance(classname);
		} else {
			System.out.println("Unsuppored classname:" + classname);
			logger.error("Unsuppored classname:" + classname);
			return null;
		}
	}

	@Override
	public Object newInstance(String classname, ArrayList<VarValue> args) {
		// convert classname to lower case.
		// classname is not sensitive case.
		String myclassname = classname.toLowerCase();

		INewInstance inst = instancemap.get(myclassname);
		if (inst != null) {
			return inst.newInstance(classname, args);
		} else {
			System.out.println("Unsuppored classname:" + classname);
			logger.error("Unsuppored classname:" + classname);
			return null;
		}
	}
}
