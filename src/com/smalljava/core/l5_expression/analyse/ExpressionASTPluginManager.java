package com.smalljava.core.l5_expression.analyse;

import java.util.ArrayList;

import com.smalljava.core.l5_expression.analyse.plugin.atom.AtomOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.constvalue.ConstNumberOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.constvalue.ConstStringOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.obj.ObjectCallOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.one.LogicNotOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.two.LogicCompareOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.two.LogicComputeOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.two.MathAddDeaddOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.two.MathMultiDevideOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.var.NewObjectOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.var.VarSetOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.var.VariableDefineOperPlugin;
import com.smalljava.core.l5_expression.analyse.plugin.var.VariableOperPlugin;


public class ExpressionASTPluginManager {
	private static ArrayList<IAstPlugin> pluginmap=new ArrayList<IAstPlugin>();
	
	public ExpressionASTPluginManager() {
		initMap();
	}
	
	private static void initMap() {
		if(pluginmap.size()==0) {

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
		}
	}

	public static ArrayList<IAstPlugin> getPluginmap() {
		return pluginmap;
	}

	public static void setPluginmap(ArrayList<IAstPlugin> pluginmap) {
		ExpressionASTPluginManager.pluginmap = pluginmap;
	}
	
	
}
