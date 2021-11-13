package com.smalljava.workflow.core.engine;

import java.util.ArrayList;
import java.util.HashMap;

import com.smalljava.core.common.UUIDFunction;
import com.smalljava.core.common.VarValue;
import com.smalljava.core.common.logging.Logger;
import com.smalljava.core.common.logging.LoggerFactory;
import com.smalljava.workflow.core.vo.AbstractWorkflowVO;
import com.smalljava.workflow.core.vo.WorkflowDefineVO;
import com.smalljava.workflow.core.vo.WorkflowInstanceVO;
import com.smalljava.workflow.core.vo.WorkflowTokenVO;
import com.smalljava.workflow.core.vo.element.ActionNode;
import com.smalljava.workflow.core.vo.element.EndNode;
import com.smalljava.workflow.core.vo.element.StartNode;
import com.smalljava.workflow.core.vo.element.Transmition;

/**
 * 工作流引擎
 * @author liujunsong
 *
 */
public class WorkflowEngine {
	Logger logger = LoggerFactory.getLogger(WorkflowEngine.class);
	/**
	 * 启动一个新的工作流实例
	 * @param wfdefinevo 工作流定义
	 * @param argmap 启动参数，以hashmap形式传入
	 * @return
	 */
	public WorkflowInstanceVO startWorkflow(WorkflowDefineVO wfdefinevo,HashMap argmap) {
		//step1:check input
		if (wfdefinevo == null) {
			logger.error("[ERROR] wfdefine is null.");
			return null;
		}
		if (argmap == null) {
			logger.error("[ERROR] argmap is null.");
			return null;
		}
		
		//step1: create a new instanceVO.
		WorkflowInstanceVO instancevo = new WorkflowInstanceVO();
		instancevo.setDefinevo(wfdefinevo);
		
		//create a uuid
		UUIDFunction uuidfunction = new UUIDFunction();
		String uuid = uuidfunction.uuid();
		instancevo.setInstanceId(uuid);
		
		//create a new token,this token will point to "START" point.
		WorkflowTokenVO token = new WorkflowTokenVO();
		token.setCurrentStepName("Start");
		instancevo.setToken(token);
		
		//try to trigger the executeWorkflow() function once.
		boolean b1 = this.executeWorkflow(instancevo, argmap);
		if(b1) {
			logger.info("[INFO] workflow start success.");
			return instancevo;
		}else {
			logger.error("[ERROR] workflow start failed.");
			return null;
		}
	}
	
	/**
	 * 执行一个工作流实例,当前版本只支持一个Token
	 * @param wfinstance 工作流实例
	 * @param argmap 执行参数，以HashMap形式传入
	 * @return
	 */
	public boolean executeWorkflow(WorkflowInstanceVO wfinstance,HashMap<String,VarValue> argmap) {
		logger.info("[info] enter executeWorkflow");
		
		//step1:check args
		if(wfinstance == null) {
			logger.error("[ERROR] wfinstance is null");
			return false;
		}
		
		if(argmap == null) {
			logger.error("[ERROR] argmap is null.");
			return false;
		}
		
		//step1.2. find token.
		WorkflowTokenVO token = wfinstance.getToken();
		if(token == null) {
			logger.error("[ERROR] token is null.");
			return false;
		}
		if(token.getCurrentStepName().equals("")) {
			logger.error("[ERROR] token.currentStep is empty.");
			return false;
		}
		
		if(token.getCurrentStepName().equalsIgnoreCase("End")) {
			logger.error("[ERROR] token.currentStep is End ");
			logger.error("[ERROR] 流程实例已经执行结束. ");
			return false;
		}
		
		logger.info("[info] token.currentStep :"+ token.getCurrentStepName());
		AbstractWorkflowVO currentnode = this.getActionVO(wfinstance.getDefinevo(), token.getCurrentStepName());
		if(currentnode == null) {
			logger.error("[ERROR] currentnode is null");
			return false;
		}else {
			logger.info("[info] find currentnode ok.");
		}
		
		String nextstep = this.getNextStepName(currentnode, argmap);
		if(nextstep.equals("")) {
			logger.error("[ERROR] compute nextstep failed.");
			return false;
		}else {
			//判断nextstep是否有效
			Object nextvo = this.getActionVO(wfinstance.getDefinevo() , nextstep);
			if(nextvo == null) {
				logger.error("[ERROR] find nextstepname, while cannot find vo.");
				return false;
			}else {
				//修改token的数据
				wfinstance.getToken().setCurrentStepName(nextstep);
				return true;
			}
		}
	}
	
	private AbstractWorkflowVO getActionVO(WorkflowDefineVO definevo,String stepname) {
		ArrayList<AbstractWorkflowVO> list1 = definevo.getChildren();
		for(AbstractWorkflowVO childnode : list1) {
			if(childnode instanceof StartNode) {
				if(stepname.equalsIgnoreCase("Start")) {
					return childnode;
				}
			}
			if(childnode instanceof EndNode) {
				if(stepname.equalsIgnoreCase("End")) {
					return childnode;
				}
			}
			if(childnode instanceof ActionNode) {
				ActionNode actionnode = (ActionNode) childnode;
				if(actionnode.getName().equalsIgnoreCase(stepname)) {
					return childnode;
				}
			}
		}
		//not found
		logger.error("[ERROR] Not found step:"+ stepname);
		return null;
		
	}
	
	private String getNextStepName(AbstractWorkflowVO currentstep,HashMap<String,VarValue> argmap) {
		//get all transmition
		ArrayList<AbstractWorkflowVO> list1 = currentstep.getChildren();
		ArrayList<Transmition> list2 = new ArrayList<Transmition>();
		for(AbstractWorkflowVO vo : list1) {
			if (vo instanceof Transmition) {
				Transmition trans = (Transmition) vo;
				list2.add(trans);
			}
		}
		
		//if no transmition
		if(list2.size()==0) {
			logger.error("[ERROR] Cannot find any transmition.");
			return null;
		}
		
		//按照优先级来进行计算
		//Transmition successTran = null;
		TransmitionEval eval = new TransmitionEval();
		for(Transmition tt : list2) {
			//判断这个转换是否可用
			boolean b3 = eval.evaltrans(tt, argmap);
			if(b3) {
				return tt.getToaction();
			}
		}
		
		return "";
	}
}
