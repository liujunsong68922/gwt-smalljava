package com.smalljava.workflow.core.vo.element;

import com.google.gwt.xml.client.Node;
import com.smalljava.workflow.core.vo.AbstractWorkflowVO;
import com.smalljava.workflow.core.vo.NodeFunction;

public class ActionNode extends AbstractActionNode {
	private String name="";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActionNode(AbstractWorkflowVO parent) {
		super(parent);
	}

	@Override
	public void readLocalNode(Node node) {
		String sname = NodeFunction.readStringAttribute(node, "name");
		this.name = sname;
		
	}

	@Override
	public String getLocalXMLString() {
		String sret ="<Action";
		sret +=" name='"+this.name +"'" ;
		sret +=">";
		return sret;
	}



}
