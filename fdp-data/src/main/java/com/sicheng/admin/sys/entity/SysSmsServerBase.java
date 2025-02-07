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
 * 管理短信的发送 Entity 父类
 * @author zjl
 * @version 2017-02-06
 */
public class SysSmsServerBase<T> extends DataEntity<T> {
	
	private static final long serialVersionUID = 1L;
	private String status;                  // 状态，0停用，1启用
	private String url;                     // 短信网关URL
	private String username;                // 用户名
	private String pwd;                     // 密码
	private String accessKey;               // ACCESS KEY
	private String appId;                   // APP ID
	private String clientId;                // 客户id
	private String token;                   // 令牌
	private String bak1;                    // 备用字段1
	private String bak2;                    // 备用字段2
	private String bak3;                    // 备用字段3
	private String bak4;                    // 备用字段4
	private String bak5;                    // 备用字段5
	private Date beginCreateDate;           // 开始 创建时间
	private Date endCreateDate;             // 结束 创建时间
	private Date beginUpdateDate;           // 开始 更新时间
	private Date endUpdateDate;             // 结束 更新时间
	
	public SysSmsServerBase() {
		super();
	}

	public SysSmsServerBase(Long id){
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
	 * getter status(状态，0停用，1启用)
	 */				
	public String getStatus() {
		return status;
	}

	/**
	 * setter status(状态，0停用，1启用)
	 */	
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * getter url(短信网关URL)
	 */				
	public String getUrl() {
		return url;
	}

	/**
	 * setter url(短信网关URL)
	 */	
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * getter username(用户名)
	 */				
	public String getUsername() {
		return username;
	}

	/**
	 * setter username(用户名)
	 */	
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * getter pwd(密码)
	 */				
	public String getPwd() {
		return pwd;
	}

	/**
	 * setter pwd(密码)
	 */	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * getter accessKey(ACCESS KEY)
	 */				
	public String getAccessKey() {
		return accessKey;
	}

	/**
	 * setter accessKey(ACCESS KEY)
	 */	
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	/**
	 * getter appId(APP ID)
	 */				
	public String getAppId() {
		return appId;
	}

	/**
	 * setter appId(APP ID)
	 */	
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * getter clientId(客户id)
	 */				
	public String getClientId() {
		return clientId;
	}

	/**
	 * setter clientId(客户id)
	 */	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * getter token(令牌)
	 */				
	public String getToken() {
		return token;
	}

	/**
	 * setter token(令牌)
	 */	
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * getter bak1(备用字段1)
	 */				
	public String getBak1() {
		return bak1;
	}

	/**
	 * setter bak1(备用字段1)
	 */	
	public void setBak1(String bak1) {
		this.bak1 = bak1;
	}

	/**
	 * getter bak2(备用字段2)
	 */				
	public String getBak2() {
		return bak2;
	}

	/**
	 * setter bak2(备用字段2)
	 */	
	public void setBak2(String bak2) {
		this.bak2 = bak2;
	}

	/**
	 * getter bak3(备用字段3)
	 */				
	public String getBak3() {
		return bak3;
	}

	/**
	 * setter bak3(备用字段3)
	 */	
	public void setBak3(String bak3) {
		this.bak3 = bak3;
	}

	/**
	 * getter bak4(备用字段4)
	 */				
	public String getBak4() {
		return bak4;
	}

	/**
	 * setter bak4(备用字段4)
	 */	
	public void setBak4(String bak4) {
		this.bak4 = bak4;
	}

	/**
	 * getter bak5(备用字段5)
	 */				
	public String getBak5() {
		return bak5;
	}

	/**
	 * setter bak5(备用字段5)
	 */	
	public void setBak5(String bak5) {
		this.bak5 = bak5;
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
	
}