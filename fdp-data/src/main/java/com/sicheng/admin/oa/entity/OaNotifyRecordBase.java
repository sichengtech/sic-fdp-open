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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sicheng.admin.sys.entity.User;
import com.sicheng.common.persistence.DataEntity;

import java.util.Date;

/**
 * 通知通告发送记录 Entity 父类
 * @author fxx
 * @version 2017-02-09
 */
public class OaNotifyRecordBase<T> extends DataEntity<T> {
	
	private static final long serialVersionUID = 1L;
	private Long oaNotifyId;                // 通知通告ID
	private User user;                      // 接受人
	private String readFlag;                // 阅读标记
	private Date readDate;                  // 阅读时间
	private Date beginReadDate;             // 开始 阅读时间
	private Date endReadDate;               // 结束 阅读时间
	
	public OaNotifyRecordBase() {
		super();
	}

	public OaNotifyRecordBase(Long id){
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
	 * getter oaNotifyId(通知通告ID)
	 */	
	public Long getOaNotifyId() {
		return oaNotifyId;
	}

	/**
	 * setter oaNotifyId(通知通告ID)
	 */	
	public void setOaNotifyId(Long oaNotifyId) {
		this.oaNotifyId = oaNotifyId;
	}

	/**
	 * getter user(接受人)
	 */	
	public User getUser() {
		return user;
	}

	/**
	 * setter user(接受人)
	 */	
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * getter readFlag(阅读标记)
	 */	
	public String getReadFlag() {
		return readFlag;
	}

	/**
	 * setter readFlag(阅读标记)
	 */	
	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	/**
	 * getter readDate(阅读时间)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReadDate() {
		return readDate;
	}

	/**
	 * setter readDate(阅读时间)
	 */	
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	/**
	 * getter readDate(阅读时间)
	 */
	public Date getBeginReadDate() {
		return beginReadDate;
	}

	/**
	 * setter readDate(阅读时间)
	 */	
	public void setBeginReadDate(Date beginReadDate) {
		this.beginReadDate = beginReadDate;
	}
	
	/**
	 * getter readDate(阅读时间)
	 */		
	public Date getEndReadDate() {
		return endReadDate;
	}

	/**
	 * setter readDate(阅读时间)
	 */	
	public void setEndReadDate(Date endReadDate) {
		this.endReadDate = endReadDate;
	}
	
}