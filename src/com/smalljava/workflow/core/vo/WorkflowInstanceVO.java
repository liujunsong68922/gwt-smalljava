package com.smalljava.workflow.core.vo;

public class WorkflowInstanceVO {

	private WorkflowDefineVO definevo;
	private String instanceId;
	private WorkflowTokenVO token = null;

	public WorkflowDefineVO getDefinevo() {
		return definevo;
	}

	public void setDefinevo(WorkflowDefineVO definevo) {
		this.definevo = definevo;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public WorkflowTokenVO getToken() {
		return token;
	}

	public void setToken(WorkflowTokenVO token) {
		this.token = token;
	}
	
	public String toXMLString() {
		String strout = "<WorkflowInstance>\r\n";
		strout += this.getDefinevo().toXMLString() +"\r\n";
		strout += "<instanceid>"+this.instanceId+"</instanceid>\r\n";
		strout += this.token.toXMLString()+"\r\n";
		strout += "</WorkflowInstance>";
		return strout;
	}
}
