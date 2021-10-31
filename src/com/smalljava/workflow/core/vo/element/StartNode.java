package com.smalljava.workflow.core.vo.element;

import com.google.gwt.xml.client.Node;
import com.smalljava.workflow.core.vo.AbstractWorkflowVO;

public class StartNode extends AbstractSystemNode {

	
	public StartNode(AbstractWorkflowVO parent) {
		super(parent);
	}

	@Override
	public void readLocalNode(Node node) {
		//StartNode没有任何本地的属性需要读取
	}

	@Override
	public String getLocalXMLString() {
		
		return "<Start>";
	}

}
