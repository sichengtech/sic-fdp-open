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
package com.sicheng.admin.sys.dao;

import com.sicheng.admin.sys.entity.Dict;
import com.sicheng.common.persistence.CrudDao;
import com.sicheng.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 字典DAO接口
 * @author zhaolei
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	//请在这里增加你自己的DAO层方法

	//14条单表操作的通用SQL调用方法都在父类中，全继承下来了，可直接使用。

	public List<String> findTypeList(Dict dict);
	//按类型查询字典记录  并排序  升序 
	public List<Dict> selectList(Dict dict);
	//按标签名查询字典记录
	public Dict selectByLabel(String label);
	//按数据值查询字典记录
	public Dict selectByValue(String value);
	//查询同类型的键值相同的字典数据 的数量
	public Integer selectSameNumber(Dict dict);

}
