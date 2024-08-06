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
package com.sicheng.admin.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.sicheng.admin.sys.entity.Area;
import com.sicheng.admin.sys.entity.Office;
import com.sicheng.admin.sys.entity.User;
import com.sicheng.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 业务数据表管理 Entity 父类
 * @author zhaolei
 * @version 2022-04-05
 */
public class TestDataMainBase<T> extends DataEntity<T> {
	
	private static final long serialVersionUID = 1L;
	private User user;                      // 归属用户
	private Office office;                  // 归属部门
	private Area area;                      // 归属区域
	private String name;                    // 名称
	private String sex;                     // 性别
	private Date inDate;                    // 加入日期
	private Date beginInDate;               // 开始 加入日期
	private Date endInDate;                 // 结束 加入日期
	private Date beginCreateDate;           // 开始 创建时间
	private Date endCreateDate;             // 结束 创建时间
	private Date beginUpdateDate;           // 开始 更新时间
	private Date endUpdateDate;             // 结束 更新时间
	private List<TestDataChild> testDataChildList = Lists.newArrayList();		//业务数据子表。从test_data_main表看去（一对多）



	public TestDataMainBase() {
		super();
	}

	public TestDataMainBase(Long id){
		super(id);
	}

	/**
	 * 描述: 获取ID  
	 * @return   
	 * @see com.sicheng.common.persistence.BaseEntity#getId()   
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**   
	 * 描述: 设置ID
	 * @param id   
	 * @see com.sicheng.common.persistence.BaseEntity#setId(Long)
	 */	
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * getter user(归属用户)
	 */	
	public User getUser() {
		return user;
	}

	/**
	 * setter user(归属用户)
	 */	
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * getter office(归属部门)
	 */	
	public Office getOffice() {
		return office;
	}

	/**
	 * setter office(归属部门)
	 */	
	public void setOffice(Office office) {
		this.office = office;
	}

	/**
	 * getter area(归属区域)
	 */	
	public Area getArea() {
		return area;
	}

	/**
	 * setter area(归属区域)
	 */	
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * getter name(名称)
	 */	
	public String getName() {
		return name;
	}

	/**
	 * setter name(名称)
	 */	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter sex(性别)
	 */	
	public String getSex() {
		return sex;
	}

	/**
	 * setter sex(性别)
	 */	
	public void setSex(String sex) {
		this.sex = sex;
	}

	
	/**
	 * getter inDate(加入日期)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInDate() {
		return inDate;
	}

	/**
	 * setter inDate(加入日期)
	 */	
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	/**
	 * getter inDate(加入日期)
	 */
	public Date getBeginInDate() {
		return beginInDate;
	}

	/**
	 * setter inDate(加入日期)
	 */	
	public void setBeginInDate(Date beginInDate) {
		this.beginInDate = beginInDate;
	}
	
	/**
	 * getter inDate(加入日期)
	 */		
	public Date getEndInDate() {
		return endInDate;
	}

	/**
	 * setter inDate(加入日期)
	 */	
	public void setEndInDate(Date endInDate) {
		this.endInDate = endInDate;
	}
	/**
	 * getter createDate(创建时间)
	 */
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	/**
	 * setter createDate(创建时间)
	 */	
	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	/**
	 * getter createDate(创建时间)
	 */		
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	/**
	 * setter createDate(创建时间)
	 */	
	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	/**
	 * getter updateDate(更新时间)
	 */
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	/**
	 * setter updateDate(更新时间)
	 */	
	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	/**
	 * getter updateDate(更新时间)
	 */		
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	/**
	 * setter updateDate(更新时间)
	 */	
	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
	
	public List<TestDataChild> getTestDataChildList() {
		return testDataChildList;
	}

	public void setTestDataChildList(List<TestDataChild> testDataChildList) {
		this.testDataChildList = testDataChildList;
	}
    
}