package com.smalljava.workflow.core.vo;


public class WorkflowTokenVO {
	private String currentStepName="";

	public String getCurrentStepName() {
		return currentStepName;
	}

	public void setCurrentStepName(String currentStepName) {
		this.currentStepName = currentStepName;
	}
	
	public String toXMLString() {
		String s1 ="<Token currentStepName='"+this.currentStepName+"'>";
		s1 += "</Token>";
		return s1;
	}
	
}
