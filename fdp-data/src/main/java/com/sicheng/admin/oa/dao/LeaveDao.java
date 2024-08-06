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
package com.sicheng.admin.oa.dao;

import com.sicheng.admin.oa.entity.Leave;
import com.sicheng.common.persistence.CrudDao;
import com.sicheng.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 请假DAO接口
 * @author zhaolei
 * @version 2013-8-23
 */
@MyBatisDao
public interface LeaveDao extends CrudDao<Leave> {
	
	/**
	 * 更新流程实例ID
	 * @param leave 实体
	 * @return int
	 */
	public int updateProcessInstanceId(Leave leave);
	
	/**
	 * 更新实际开始结束时间
	 * @param leave 实体
	 * @return int
	 */
	public int updateRealityTime(Leave leave);
	
	/**
	 * 获取单条数据
	 * @param id 主键
	 * @return Leave
	 */
	public Leave get(Long id);
	
	/**
	 * 获取单条数据
	 * @param leave 实体
	 * @return Leave
	 */
	public Leave get(Leave leave);
	
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param leave 实体
	 * @return List<Leave>
	 */
	public List<Leave> findList(Leave leave);
	
}
