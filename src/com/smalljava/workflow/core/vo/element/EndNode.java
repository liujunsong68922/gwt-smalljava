package com.smalljava.workflow.core.vo.element;

import com.google.gwt.xml.client.Node;
import com.smalljava.workflow.core.vo.AbstractWorkflowVO;

public class EndNode extends AbstractSystemNode {

	public EndNode(AbstractWorkflowVO parent) {
		super(parent);
	}

	@Override
	public void readLocalNode(Node node) {
		//EndNode没有任何的属性需要读取
	}

	@Override
	public String getLocalXMLString() {
		return "<End>";
	}

}
