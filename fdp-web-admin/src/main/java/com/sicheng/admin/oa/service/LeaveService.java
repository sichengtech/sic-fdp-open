/**
 * 本作品使用 木兰公共许可证,第2版（Mulan PubL v2） 开源协议，请遵守相关条款，或者联系sicheng.net获取商用授权。
 * Copyright (c) 2016 SiCheng.Net
 * This software is licensed under Mulan PubL v2.
 * You can use this software according to the terms and conditions of the Mulan PubL v2.
 * You may obtain a copy of Mulan PubL v2 at:
 *          http://license.coscl.org.cn/MulanPubL-2.0
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PubL v2 for more details.
 */
package com.sicheng.admin.oa.service;

import com.sicheng.admin.act.utils.ActUtils;
import com.sicheng.admin.oa.dao.LeaveDao;
import com.sicheng.admin.oa.entity.Leave;
import com.sicheng.admin.sys.utils.UserUtils;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.persistence.wrapper.Wrapper;
import com.sicheng.common.service.CrudService;
import com.sicheng.common.utils.Collections3;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 请假Service
 * @author zhaolei
 */
@Service
@Transactional(readOnly = true)
public class LeaveService extends CrudService<LeaveDao,Leave>{

	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;

	/**
	 * 获取流程详细及工作流参数
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public Leave get(Long id) {
		Leave leave = leaveDao.get(id);
		Map<String,Object> variables=null;
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(leave.getProcessInstanceId().toString()).singleResult();
		if(historicProcessInstance!=null) {
			variables = Collections3.extractToMap(historyService.createHistoricVariableInstanceQuery().processInstanceId(historicProcessInstance.getId()).list(), "variableName", "value");
		} else {
			variables = runtimeService.getVariables(runtimeService.createProcessInstanceQuery().processInstanceId(leave.getProcessInstanceId().toString()).active().singleResult().getId());
		}
		leave.setVariables(variables);
		return leave;
	}
	
	/**
	 * 启动流程
	 * @param leave
	 * @param variables
	 */
	@Transactional(rollbackFor=Exception.class,readOnly = false)
	public void save(Leave leave, Map<String, Object> variables) {
		
		// 保存业务数据
		if (leave.getId()==null){
			leave.preInsert();
			leaveDao.insert(leave);
		}else{
			leave.preUpdate();
			leaveDao.updateByWhere(leave, new Wrapper(leave));
		}
		logger.debug("save entity: {}", leave);
		
		// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
		identityService.setAuthenticatedUserId(UserUtils.getUser().getLoginName());
		
		// 启动流程
		String businessKey = leave.getId().toString();
		variables.put("type", "leave");
		variables.put("busId", businessKey);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ActUtils.PD_LEAVE[0], businessKey, variables);
		leave.setProcessInstance(processInstance);
		
		// 更新流程实例ID
		leave.setProcessInstanceId(Long.parseLong(processInstance.getId()));
		leaveDao.updateProcessInstanceId(leave);
		
		logger.debug("start process of {key={}, bkey={}, pid={}, variables={}}", new Object[] { 
				ActUtils.PD_LEAVE[0], businessKey, processInstance.getId(), variables });
	}

	/**
	 * 查询待办任务
	 * @param userId 用户ID
	 * @return
	 */
	public List<Leave> findTodoTasks(String userId) {
		
		List<Leave> results = new ArrayList<Leave>();
		List<Task> tasks = new ArrayList<Task>();
		// 根据当前人的ID查询
		List<Task> todoList = taskService.createTaskQuery().processDefinitionKey(ActUtils.PD_LEAVE[0]).taskAssignee(userId).active().orderByTaskPriority().desc().orderByTaskCreateTime().desc().list();
		// 根据当前人未签收的任务
		List<Task> unsignedTasks = taskService.createTaskQuery().processDefinitionKey(ActUtils.PD_LEAVE[0]).taskCandidateUser(userId).active().orderByTaskPriority().desc().orderByTaskCreateTime().desc().list();
		// 合并
		tasks.addAll(todoList);
		tasks.addAll(unsignedTasks);
		// 根据流程的业务ID查询实体并关联
		for (Task task : tasks) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
			String businessKey = processInstance.getBusinessKey();
			Leave leave = leaveDao.get(Long.valueOf(businessKey));
			leave.setTask(task);
			leave.setProcessInstance(processInstance);
			leave.setProcessDefinition(repositoryService.createProcessDefinitionQuery().processDefinitionId((processInstance.getProcessDefinitionId())).singleResult());
			results.add(leave);
		}
		return results;
	}

	public Page<Leave> find(Page<Leave> page, Leave leave) {
		leave.getSqlMap().put("dsf", dataScopeFilter(UserUtils.getUser(), "o", "u"));
		Wrapper w =new Wrapper(leave);
		page.setList(leaveDao.selectByWhere(page,w));
		for(Leave item : page.getList()) {
			String processInstanceId = item.getProcessInstanceId().toString();
			Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
			item.setTask(task);
			HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			if(historicProcessInstance!=null) {
				item.setHistoricProcessInstance(historicProcessInstance);
				item.setProcessDefinition(repositoryService.createProcessDefinitionQuery().processDefinitionId(historicProcessInstance.getProcessDefinitionId()).singleResult());
			} else {
				ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
				if (processInstance != null){
					item.setProcessInstance(processInstance);
					item.setProcessDefinition(repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult());
				}
			}
		}
		return page;
	}
}
