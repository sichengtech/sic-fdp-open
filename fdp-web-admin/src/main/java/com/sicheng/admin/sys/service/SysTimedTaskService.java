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
package com.sicheng.admin.sys.service;

import com.sicheng.admin.sys.dao.SysTimedTaskDao;
import com.sicheng.admin.sys.entity.SysTimedTask;
import com.sicheng.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理定时任务 Service
 * @author zjl
 * @version 2017-01-10
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class SysTimedTaskService extends CrudService<SysTimedTaskDao, SysTimedTask> {

	//请在这里编写业务逻辑
	
	//14条单表操作的通用SQL调用方法都在父类中，全继承下来了，可直接使用。
	
	//注意：把多条业务sql包在一个事务中
	
}