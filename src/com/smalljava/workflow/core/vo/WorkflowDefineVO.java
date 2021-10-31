package com.smalljava.workflow.core.vo;

import com.google.gwt.xml.client.Node;

public class WorkflowDefineVO extends AbstractWorkflowVO {

	public WorkflowDefineVO(AbstractWorkflowVO parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
	public WorkflowDefineVO() {
		super(null);
	}

	@Override
	public void readLocalNode(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLocalXMLString() {
		String sret="<Workflow>";
		return sret;
	}

}
