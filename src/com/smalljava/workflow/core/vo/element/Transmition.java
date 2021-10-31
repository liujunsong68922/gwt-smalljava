package com.smalljava.workflow.core.vo.element;

import com.google.gwt.xml.client.Node;
import com.smalljava.workflow.core.vo.AbstractWorkflowVO;
import com.smalljava.workflow.core.vo.NodeFunction;

public class Transmition extends AbstractTransmition{
	private String toaction="";
	private String strcondition="";
	
	public String getToaction() {
		return toaction;
	}

	public void setToaction(String toaction) {
		this.toaction = toaction;
	}
	
	public String getStrcondition() {
		return strcondition;
	}

	public void setStrcondition(String strcondition) {
		this.strcondition = strcondition;
	}

	public Transmition(AbstractWorkflowVO parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void readLocalNode(Node node) {
		this.toaction = NodeFunction.readStringAttribute(node, "toaction");
		
	}

	@Override
	public String getLocalXMLString() {
		return "<Transmition toaction='"+this.toaction+"'>";
	}

}
