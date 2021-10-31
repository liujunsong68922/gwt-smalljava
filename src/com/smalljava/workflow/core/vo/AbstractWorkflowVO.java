package com.smalljava.workflow.core.vo;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.workflow.core.vo.element.ActionNode;
import com.smalljava.workflow.core.vo.element.EndNode;
import com.smalljava.workflow.core.vo.element.StartNode;
import com.smalljava.workflow.core.vo.element.Transmition;

public abstract class AbstractWorkflowVO {
	private Logger logger = LoggerFactory.getLogger(AbstractWorkflowVO.class);
	
	//children
	private ArrayList<AbstractWorkflowVO> children = new ArrayList<AbstractWorkflowVO>();
	
	
	public ArrayList<AbstractWorkflowVO> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<AbstractWorkflowVO> children) {
		this.children = children;
	}

	private AbstractWorkflowVO parent = null;
	
	public AbstractWorkflowVO(AbstractWorkflowVO parent) {
		this.parent = parent;
	}
	
	public AbstractWorkflowVO getParent() {
		return parent;
	}

	public void setParent(AbstractWorkflowVO parent) {
		this.parent = parent;
	}

	public AbstractWorkflowVO getRootVO() {
		if (this.parent == null) {
			return this;
		}
		AbstractWorkflowVO pp = this;
		
		//up to root workflowvo
		while(pp.getParent() !=null) {
			pp = pp.getParent();
		}
		//return root workflowvo
		return pp;
	}
	
	// this function to read node information to fullfill this object
	public abstract void readLocalNode(Node node);
	
	public void readNode(Node node) {
		logger.info("---->enter readNode:"+node.getNodeName());

		// step1:read current level node data
		// from node, to this object
		String nodename = node.getNodeName();

		// check wheath the nodename value is valid.
		// if it is,then read node value to this object.
		// there is a one-level operation
		if (_isValidNodeName(nodename)) {
			//Window.alert("nodename:"+nodename);
			logger.info("nodename:"+nodename);
			this.readLocalNode(node);
		} else {
			Window.alert("Unknown nodename:" + nodename);
			System.out.println("Unknown nodename.");
			return;
		}

		// 2.read node childvalue
		NodeList childnodes = node.getChildNodes();
		if (childnodes.getLength() > 0) {
			for (int index = 0; index < childnodes.getLength(); index++) {
				Node childnode = childnodes.item(index);
				// 
				String nodename2 = childnode.getNodeName();
				AbstractWorkflowVO wfchildelemnt = this._createByNodename(nodename2,this);
				if (wfchildelemnt != null) {
					this.getChildren().add(wfchildelemnt);

					// call this function in loop way.
					// 以递归方式来读取节点信息到VO对象
					wfchildelemnt.readNode(childnode);
				}
			}
		}
	};
	
	private boolean _isValidNodeName(String nodename) {
		return true;
	}	

	private AbstractWorkflowVO _createByNodename(String nodename,AbstractWorkflowVO parent) {
		//创建一个START节点
		if(nodename.equalsIgnoreCase("START")) {
			return new StartNode(parent);
		}
		if(nodename.equalsIgnoreCase("END")) {
			return new EndNode(parent);
		}
		if(nodename.equalsIgnoreCase("Action")) {
			return new ActionNode(parent);
		}
		if(nodename.equalsIgnoreCase("Transmition")) {
			return new Transmition(parent);
		}
		
		if(nodename.equalsIgnoreCase("#text")) {
			//skip it.this may be \r\n
			return null;
		}
		
		//其余情况为不支持的异常情况,返回null.
		logger.error("[ERROR] Unknown nodename: "+nodename);
		logger.error("[ERROR] 不支持的节点名称 : "+nodename);
		return null;
	}	
	
	public abstract String getLocalXMLString();
	/**
	 * 转换成JSON格式的字符串输出信息
	 * @return
	 */
	public String toXMLString() {
		String sret="";
		String shead="";
		String sbody="";
		String sfooter="";
		shead = this.getLocalXMLString();
		if(this instanceof WorkflowDefineVO) {
			sfooter="</Workflow>";
		}else if(this instanceof StartNode) {
			sfooter="</Start>";
		}else if(this instanceof EndNode) {
			sfooter="</End>";
		}else if(this instanceof ActionNode) {
			sfooter="</Action>";
		}else if(this instanceof Transmition){
			sfooter="</Transmition>";
		}else {
			logger.error("[ERROR] Unknown workflow nodetype.");
			return "";
		}
		
		//开始循环处理子节点
		for(AbstractWorkflowVO child : this.getChildren()) {
			sbody+= child.toXMLString()+"\r\n";
		}
		return shead+"\r\n"+sbody+sfooter;
	}
}
