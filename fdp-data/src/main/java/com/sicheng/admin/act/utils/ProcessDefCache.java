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
package com.sicheng.admin.act.utils;

import com.sicheng.common.web.SpringContextHolder;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * 流程定义缓存
 * @author zhaolei
 * @version 2013-12-05
 */
public class ProcessDefCache {

//	private static final String ACT_CACHE = "actCache";
//	private static final String ACT_CACHE_PD_ID_ = "pd_id_";
	
	/**
	 * 获得流程定义对象
	 * @param procDefId
	 * @return
	 */
	public static ProcessDefinition get(String procDefId) {
//		ProcessDefinition pd = (ProcessDefinition)CacheUtils.get(ACT_CACHE, ACT_CACHE_PD_ID_ + procDefId);
		ProcessDefinition pd = null;
//		if (pd == null) {
			RepositoryService repositoryService = SpringContextHolder.getBean(RepositoryService.class);
//			pd = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(pd);
			pd = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
//			if (pd != null) {
//				CacheUtils.put(ACT_CACHE, ACT_CACHE_PD_ID_ + procDefId, pd);
//			}
//		}
		return pd;
	}

	/**
	 * 获得流程定义的所有活动节点
	 * @param procDefId
	 * @return
	 */
	public static List<ActivityImpl> getActivitys(String procDefId) {
		ProcessDefinition pd = get(procDefId);
		if (pd != null) {
			return ((ProcessDefinitionEntity) pd).getActivities();
		}
		return null;
	}
	
	/**
	 * 获得流程定义活动节点
	 * @param procDefId
	 * @param activityId
	 * @return
	 */
	public static ActivityImpl getActivity(String procDefId, String activityId) {
		ProcessDefinition pd = get(procDefId);
		if (pd != null) {
			List<ActivityImpl> list = getActivitys(procDefId);
			if (list != null){
				for (ActivityImpl activityImpl : list) {
					if (activityId.equals(activityImpl.getId())){
						return activityImpl;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 获取流程定义活动节点名称
	 * @param procDefId
	 * @param activityId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getActivityName(String procDefId, String activityId) {
		ActivityImpl activity = getActivity(procDefId, activityId);
		if (activity != null) {
			return ObjectUtils.toString(activity.getProperty("name"));
		}
		return null;
	}

}
