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

import com.sicheng.admin.sys.dao.DictDao;
import com.sicheng.admin.sys.entity.Dict;
import com.sicheng.admin.sys.utils.UserUtils;
import com.sicheng.common.persistence.wrapper.Wrapper;
import com.sicheng.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 字典Service
 * @author zhaolei
 * @version 2014-05-16
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class DictService extends CrudService<DictDao, Dict> {
	
	@Autowired
	protected DictDao dictDao;
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	//按类型查询字典记录  并排序  升序 
	public List<Dict> selectList(Dict dict){
		return dictDao.selectList(dict);
	}
	
	//按标签名查询字典记录
	public Dict selectByLabel(String label){
		return dictDao.selectByLabel(label);
	}
	
	//按数据值查询字典记录
	public Dict selectByValue(String value){
		return dictDao.selectByValue(value);
	}
	
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		dict.preInsert(UserUtils.getUser());
		dictDao.insertSelective(dict);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.deleteByWhere(new Wrapper(dict));
	}
	//过滤同类型相同键值的字典数据
	public Integer colation(Dict dict){
		return dictDao.selectSameNumber(dict);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<Dict> findList(Dict entity) {
		Wrapper w=new Wrapper(entity);
		w.orderBy("type, sort, update_date DESC");
		return dictDao.selectByWhere(null,w);
	}
}
