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
package com.sicheng.admin.customform.service;

import com.sicheng.admin.customform.dao.CustomFormDao;
import com.sicheng.admin.customform.entity.CustomForm;
import com.sicheng.admin.sys.utils.UserUtils;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.persistence.wrapper.Wrapper;
import com.sicheng.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 自定义表单Service
 * @author zhaolei
 * @version 2016-06-03
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class CustomFormService extends CrudService<CustomFormDao, CustomForm> {

	public CustomForm selectById(Long id) {
		return super.selectById(id);
	}
	
	public List<CustomForm> selectByWhere(CustomForm customForm) {
		return super.selectByWhere(new Wrapper(customForm));
	}
	
	public Page<CustomForm> findPage(Page<CustomForm> page, CustomForm customForm) {
		return super.selectByWhere(page, new Wrapper(customForm));
	}
	
	@Transactional(readOnly = false)
	public void save(CustomForm customForm) {
		if (customForm.getIsNewRecord()){
			customForm.preInsert(UserUtils.getUser());
			//插入,只把非空的值插入到对应的字段
			insertSelective(customForm);
		}else{
			customForm.preUpdate(UserUtils.getUser());
			//根据主键更新记录,只把非空的值更到对应的字段
			updateByIdSelective(customForm);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		super.deleteById(id);
	}
	
}