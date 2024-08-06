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
package com.sicheng.admin.sys.entity;

import com.sicheng.common.persistence.DataEntity;

import java.util.Date;

/**
 * 角色 Entity 父类
 * @author fxx
 * @version 2017-02-08
 */
public class RoleBase<T> extends DataEntity<T> {
	
	private static final long serialVersionUID = 1L;
	private Office office;                  // 机构id
	private String name;                    // 角色名称
	private String enname;                  // 英文名称
	private String roleType;                // 权限类型
	private String dataScope;               // 数据范围
	private String sysData;                 // 是否是系统数据
	private String useable;                 // 是否是可用
	private Date beginCreateDate;           // 开始 create_date
	private Date endCreateDate;             // 结束 create_date
	private Date beginUpdateDate;           // 开始 update_date
	private Date endUpdateDate;             // 结束 update_date
	
	public RoleBase() {
		super();
	}

	public RoleBase(Long id){
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
	 * @see com.sicheng.common.persistence.BaseEntity#setId(java.lang.Long)   
	 */	
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * getter office(机构id)
	 */				
	public Office getOffice() {
		return office;
	}

	/**
	 * setter office(机构id)
	 */	
	public void setOffice(Office office) {
		this.office = office;
	}

	/**
	 * getter name(角色名称)
	 */				
	public String getName() {
		return name;
	}

	/**
	 * setter name(角色名称)
	 */	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter enname(英文名称)
	 */				
	public String getEnname() {
		return enname;
	}

	/**
	 * setter enname(英文名称)
	 */	
	public void setEnname(String enname) {
		this.enname = enname;
	}

	/**
	 * getter roleType(权限类型)
	 */				
	public String getRoleType() {
		return roleType;
	}

	/**
	 * setter roleType(权限类型)
	 */	
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	/**
	 * getter dataScope(数据范围)
	 */				
	public String getDataScope() {
		return dataScope;
	}

	/**
	 * setter dataScope(数据范围)
	 */	
	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	/**
	 * getter isSys(是否是系统数据)
	 */				
	public String getSysData() {
		return sysData;
	}

	/**
	 * setter isSys(是否是系统数据)
	 */	
	public void setSysData(String sysData) {
		this.sysData = sysData;
	}

	/**
	 * getter useable(是否是可用)
	 */				
	public String getUseable() {
		return useable;
	}

	/**
	 * setter useable(是否是可用)
	 */	
	public void setUseable(String useable) {
		this.useable = useable;
	}

	/**
	 * getter createDate(create_date)
	 */
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	/**
	 * setter createDate(create_date)
	 */	
	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	/**
	 * getter createDate(create_date)
	 */		
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	/**
	 * setter createDate(create_date)
	 */	
	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	/**
	 * getter updateDate(update_date)
	 */
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	/**
	 * setter updateDate(update_date)
	 */	
	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	/**
	 * getter updateDate(update_date)
	 */		
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	/**
	 * setter updateDate(update_date)
	 */	
	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
	
}