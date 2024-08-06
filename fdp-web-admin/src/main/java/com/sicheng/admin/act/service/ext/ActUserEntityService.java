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
import com.sicheng.admin.sys.service.RoleService;
import com.sicheng.admin.sys.service.SystemService;
import com.sicheng.admin.sys.service.UserService;
import com.sicheng.common.persistence.wrapper.Wrapper;
import com.sicheng.common.web.SpringContextHolder;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Activiti User Entity Service
 * @author zhaolei
 */
@Service
public class ActUserEntityService extends UserEntityManager {

	private UserService sysUserService;
	private RoleService sysRoleService;
	private SystemService systemService;

	public SystemService getSystemService() {
		if (systemService == null){
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}
	
	public UserService getSysUserService() {
		if (sysUserService == null){
			sysUserService = SpringContextHolder.getBean(UserService.class);
		}
		return sysUserService;
	}

	public RoleService getSysRoleService() {
		if (sysRoleService == null){
			sysRoleService = SpringContextHolder.getBean(RoleService.class);
		}
		return sysRoleService;
	}
	public User createNewUser(String userId) {
		return new UserEntity(userId);
	}

	public void insertUser(User user) {
		throw new RuntimeException("not implement method.");
	}

	public void updateUser(UserEntity updatedUser) {
		throw new RuntimeException("not implement method.");
	}

	public UserEntity findUserById(Long userId) {
		com.sicheng.admin.sys.entity.User userQuery=new com.sicheng.admin.sys.entity.User();
		//userQuery.setLoginName(userId);
		userQuery.setId(userId);
		com.sicheng.admin.sys.entity.User user = getSysUserService().selectOne(new Wrapper(userQuery));
		return ActUtils.toActivitiUser(user);
	}

	public void deleteUser(Long userId) {
		User user = findUserById(userId);
		if (user != null) {
			String id=user.getId();
			Long id2=Long.valueOf(id);
			getSystemService().deleteUser(new com.sicheng.admin.sys.entity.User(id2));
		}
	}

	public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
		throw new RuntimeException("not implement method.");
	}

	public long findUserCountByQueryCriteria(UserQueryImpl query) {
		throw new RuntimeException("not implement method.");
	}

	public List<Group> findGroupsByUser(String userId) {
		List<Group> list = Lists.newArrayList();
		//getSysRoleService().selectxxxxxx
//		for (Role role : getSysRoleService().findRole(new Role(new com.sicheng.admin.sys.entity.User(null, userId)))){
//			list.add(ActUtils.toActivitiGroup(role));
//		}
		throw new RuntimeException("findGroupsByUser未实现");
//		return list;
	}

	public UserQuery createNewUserQuery() {
		throw new RuntimeException("not implement method.");
	}

	public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
		throw new RuntimeException("not implement method.");
	}

	public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
		throw new RuntimeException("not implement method.");
	}

	public Boolean checkPassword(String userId, String password) {
		throw new RuntimeException("not implement method.");
	}

	public List<User> findPotentialStarterUsers(String proceDefId) {
		throw new RuntimeException("not implement method.");

	}

	public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
		throw new RuntimeException("not implement method.");
	}

	public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
		throw new RuntimeException("not implement method.");
	}
	
}
