package com.smalljava.workflow.core.analyse;

import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
//import com.liu.webui.core.vo.CWebUI_RootFlowPanel;
import com.smalljava.workflow.core.vo.WorkflowDefineVO;

public class WorkflowAnalyse {
	private Logger logger = LoggerFactory.getLogger(WorkflowAnalyse.class);
	/**
	 * 将输入的XML格式的字符串转换成一个VO对象
	 * 转换失败，返回null.
	 * @param strinfo
	 * @return
	 */
	public WorkflowDefineVO analyse(String strinfo) {
		Document doc = this._parseXML(strinfo);
		if(doc == null) {
			logger.error("[ERROR] doc is null.");
			return null;
		}
		
		WorkflowDefineVO vo = this.createByDocument(doc);
		if(vo==null) {
			logger.error("[ERROR] vo is null.");
			return null;
		}else {
			logger.info("[OK],vo:" + vo);
			return vo;
		}
		
	}
	
	
	private Document _parseXML(String strxml) {
		try {
			Document doc = XMLParser.parse(strxml);
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			Window.alert("Error when parse xml:" + e.toString());
			return null;
		}
	}
	
	public WorkflowDefineVO createByDocument(Document doc) {
		WorkflowDefineVO rootvo = new WorkflowDefineVO();
		
		//step1:find root element
		//the first node is rootnode.
		Node rootnode = doc.getFirstChild();
		if(rootnode == null) {
			logger.error("[Error] rootnode is null.");
			return rootvo;
		}else {
			//read from rootnode
			//write to rootpanel
			//this will recall other nodes.
			rootvo.readNode(rootnode);
		}
		return rootvo;
	}
}
