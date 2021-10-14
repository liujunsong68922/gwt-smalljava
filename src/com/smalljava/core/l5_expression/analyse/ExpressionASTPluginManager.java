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

/**
 * AST解析和Block解析一样，选择使用插件模式来进行
 * MEMO：每个不同的插件按照自己的逻辑进行独立的计算拆解
 * @author liujunsong
 *
 */
public class ExpressionASTPluginManager {
	private static ArrayList<IAstPlugin> pluginmap=new ArrayList<IAstPlugin>();
	
	public ExpressionASTPluginManager() {
		initMap();
	}
	
	private static void initMap() {
		if(pluginmap.size()==0) {
			//将各个处理插件加进来，按照优先级来添加
			//优先级高的放在前面，优先级低的放在后面

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
