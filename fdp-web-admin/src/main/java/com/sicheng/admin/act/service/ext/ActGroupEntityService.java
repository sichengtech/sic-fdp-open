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
package com.sicheng.admin.act.service.ext;

import com.google.common.collect.Lists;
import com.sicheng.admin.act.utils.ActUtils;
import com.sicheng.admin.sys.entity.Role;
import com.sicheng.admin.sys.entity.User;
import com.sicheng.admin.sys.service.UserService;
import com.sicheng.common.persistence.wrapper.Wrapper;
import com.sicheng.common.web.SpringContextHolder;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Activiti Group Entity Service
 * @author zhaolei
 */
@Service
public class ActGroupEntityService extends GroupEntityManager {

	private UserService sysUserService;

	public UserService getSysUserService() {
		if (sysUserService == null){
			sysUserService = SpringContextHolder.getBean(UserService.class);
		}
		return sysUserService;
	}
	
	public Group createNewGroup(String groupId) {
		return new GroupEntity(groupId);
	}

	public void insertGroup(Group group) {
//		getDbSqlSession().insert((PersistentObject) group);
		throw new RuntimeException("not implement method.");
	}

	public void updateGroup(GroupEntity updatedGroup) {
//		CommandContext commandContext = Context.getCommandContext();
//		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
//		dbSqlSession.update(updatedGroup);
		throw new RuntimeException("not implement method.");
	}

	public void deleteGroup(String groupId) {
//		GroupEntity group = getDbSqlSession().selectById(GroupEntity.class, groupId);
//		getDbSqlSession().delete("deleteMembershipsByGroupId", groupId);
//		getDbSqlSession().delete(group);
		throw new RuntimeException("not implement method.");
	}

	public GroupQuery createNewGroupQuery() {
//		return new GroupQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
		throw new RuntimeException("not implement method.");
	}

//	@SuppressWarnings("unchecked")
	public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
//		return getDbSqlSession().selectList("selectGroupByQueryCriteria", query, page);
		throw new RuntimeException("not implement method.");
	}

	public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
//		return (Long) getDbSqlSession().selectOne("selectGroupCountByQueryCriteria", query);
		throw new RuntimeException("not implement method.");
	}

	public List<Group> findGroupsByUser(String loginName) {
//		return getDbSqlSession().selectList("selectGroupsByUserId", userId);
		List<Group> list = Lists.newArrayList();
		User userQuery=new User();
		userQuery.setLoginName(loginName);
//		userQuery.setId(userId);
		User user = getSysUserService().selectOne(new Wrapper(userQuery));
//		User user = getSysUserService().getUserByLoginName(String.valueOf(userId));
		if (user != null && user.getRoleList() != null){
			for (Role role : user.getRoleList()){
				list.add(ActUtils.toActivitiGroup(role));
			}
		}
		return list;
	}

	public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
//		return getDbSqlSession().selectListWithRawParameter("selectGroupByNativeQuery", parameterMap, firstResult, maxResults);
		throw new RuntimeException("not implement method.");
	}

	public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
//		return (Long) getDbSqlSession().selectOne("selectGroupCountByNativeQuery", parameterMap);
		throw new RuntimeException("not implement method.");
	}

}