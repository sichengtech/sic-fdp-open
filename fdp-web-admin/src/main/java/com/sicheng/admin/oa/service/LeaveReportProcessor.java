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

import com.sicheng.admin.oa.dao.LeaveDao;
import com.sicheng.admin.oa.entity.Leave;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 销假后处理器
 * @author zhaolei
 */
@Service
@Transactional
public class LeaveReportProcessor implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private RuntimeService runtimeService;
	
	/**
	 * 销假完成后执行，保存实际开始和结束时间
	 */
	public void notify(DelegateTask delegateTask) {
		String processInstanceId = delegateTask.getProcessInstanceId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		Leave leave = new Leave(Long.parseLong(processInstance.getBusinessKey()));
		leave.setRealityStartTime((Date) delegateTask.getVariable("realityStartTime"));
		leave.setRealityEndTime((Date) delegateTask.getVariable("realityEndTime"));
		leave.preUpdate();
		leaveDao.updateRealityTime(leave);
	}

}
