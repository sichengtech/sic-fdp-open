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
package com.sicheng.admin.oa.entity;

import com.sicheng.common.persistence.DataEntity;

import java.util.Date;

/**
 * 通知通告 Entity 父类
 * @author fxx
 * @version 2017-02-09
 */
public class OaNotifyBase<T> extends DataEntity<T> {
	
	private static final long serialVersionUID = 1L;
	private String type;                    // 类型
	private String title;                   // 标题
	private String content;                 // 内容
	private String files;                   // 附件
	private String status;                  // 状态
	private Date beginCreateDate;           // 开始 创建时间
	private Date endCreateDate;             // 结束 创建时间
	private Date beginUpdateDate;           // 开始 更新时间
	private Date endUpdateDate;             // 结束 更新时间
	
	public OaNotifyBase() {
		super();
	}

	public OaNotifyBase(Long id){
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
	 * getter type(类型)
	 */				
	public String getType() {
		return type;
	}

	/**
	 * setter type(类型)
	 */	
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * getter title(标题)
	 */				
	public String getTitle() {
		return title;
	}

	/**
	 * setter title(标题)
	 */	
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * getter content(内容)
	 */				
	public String getContent() {
		return content;
	}

	/**
	 * setter content(内容)
	 */	
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * getter files(附件)
	 */				
	public String getFiles() {
		return files;
	}

	/**
	 * setter files(附件)
	 */	
	public void setFiles(String files) {
		this.files = files;
	}

	/**
	 * getter status(状态)
	 */				
	public String getStatus() {
		return status;
	}

	/**
	 * setter status(状态)
	 */	
	public void setStatus(String status) {
		this.status = status;
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