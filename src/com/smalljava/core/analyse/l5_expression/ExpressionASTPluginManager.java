package com.smalljava.core.analyse.l5_expression;

import java.util.ArrayList;

import com.smalljava.core.analyse.l5_expression.plugin.atom.AtomOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.constvalue.ConstNumberOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.constvalue.ConstStringOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.one.LogicNotOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.oop.ImportOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.oop.NewObjectOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.oop.ObjectCallOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.oop.ObjectPropertyPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.two.LogicCompareOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.two.LogicComputeOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.two.MathAddDeaddOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.two.MathMultiDevideOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.var.VarSetOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.var.VariableDefineOperPlugin;
import com.smalljava.core.analyse.l5_expression.plugin.var.VariableOperPlugin;

public class ExpressionASTPluginManager {
	private static ArrayList<IAstPlugin> pluginmap = new ArrayList<IAstPlugin>();

	public ExpressionASTPluginManager() {
		initMap();
	}

	private static void initMap() {
		if (pluginmap.size() == 0) {

			pluginmap.add(new MathAddDeaddOperPlugin());
			pluginmap.add(new MathMultiDevideOperPlugin());
			pluginmap.add(new AtomOperPlugin());
			pluginmap.add(new ConstNumberOperPlugin());
			pluginmap.add(new ConstStringOperPlugin());
			pluginmap.add(new VariableOperPlugin());
			pluginmap.add(new VariableDefineOperPlugin());
			pluginmap.add(new LogicCompareOperPlugin());
			pluginmap.add(new LogicComputeOperPlugin());
			pluginmap.add(new ObjectCallOperPlugin());
			pluginmap.add(new LogicNotOperPlugin());
			pluginmap.add(new VarSetOperPlugin());
			pluginmap.add(new NewObjectOperPlugin());
			pluginmap.add(new ImportOperPlugin());
			pluginmap.add(new ObjectPropertyPlugin());
		}
	}

	public static ArrayList<IAstPlugin> getPluginmap() {
		return pluginmap;
	}

	public static void setPluginmap(ArrayList<IAstPlugin> pluginmap) {
		ExpressionASTPluginManager.pluginmap = pluginmap;
	}

}
