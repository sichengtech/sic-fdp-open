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
import com.sicheng.common.persistence.DataEntity;

import java.util.Date;

/**
 * 请假工作流 Entity 父类
 * @author fxx
 * @version 2017-02-09
 */
public class LeaveBase<T> extends DataEntity<T> {
	
	private static final long serialVersionUID = 1L;
	private Long processInstanceId;         // 流程实例编号
	private Date startTime;                 // 开始时间
	private Date endTime;                   // 结束时间
	private String leaveType;               // 请假类型
	private String reason;                  // 请假理由
	private Date applyTime;                 // 申请时间
	private Date realityStartTime;          // 实际开始时间
	private Date realityEndTime;            // 实际结束时间
	private Date beginStartTime;            // 开始 开始时间
	private Date endStartTime;              // 结束 开始时间
	private Date beginEndTime;              // 开始 结束时间
	private Date endEndTime;                // 结束 结束时间
	private Date beginApplyTime;            // 开始 申请时间
	private Date endApplyTime;              // 结束 申请时间
	private Date beginRealityStartTime;     // 开始 实际开始时间
	private Date endRealityStartTime;       // 结束 实际开始时间
	private Date beginRealityEndTime;       // 开始 实际结束时间
	private Date endRealityEndTime;         // 结束 实际结束时间
	private Date beginCreateDate;           // 开始 创建时间
	private Date endCreateDate;             // 结束 创建时间
	private Date beginUpdateDate;           // 开始 更新时间
	private Date endUpdateDate;             // 结束 更新时间
	
	public LeaveBase() {
		super();
	}

	public LeaveBase(Long id){
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
	 * getter processInstanceId(流程实例编号)
	 */	
	public Long getProcessInstanceId() {
		return processInstanceId;
	}

	/**
	 * setter processInstanceId(流程实例编号)
	 */	
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	/**
	 * getter startTime(开始时间)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * setter startTime(开始时间)
	 */	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * getter endTime(结束时间)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * setter endTime(结束时间)
	 */	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * getter leaveType(请假类型)
	 */	
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * setter leaveType(请假类型)
	 */	
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	/**
	 * getter reason(请假理由)
	 */	
	public String getReason() {
		return reason;
	}

	/**
	 * setter reason(请假理由)
	 */	
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * getter applyTime(申请时间)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyTime() {
		return applyTime;
	}

	/**
	 * setter applyTime(申请时间)
	 */	
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * getter realityStartTime(实际开始时间)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRealityStartTime() {
		return realityStartTime;
	}

	/**
	 * setter realityStartTime(实际开始时间)
	 */	
	public void setRealityStartTime(Date realityStartTime) {
		this.realityStartTime = realityStartTime;
	}


	/**
	 * getter realityEndTime(实际结束时间)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRealityEndTime() {
		return realityEndTime;
	}

	/**
	 * setter realityEndTime(实际结束时间)
	 */	
	public void setRealityEndTime(Date realityEndTime) {
		this.realityEndTime = realityEndTime;
	}

	/**
	 * getter startTime(开始时间)
	 */
	public Date getBeginStartTime() {
		return beginStartTime;
	}

	/**
	 * setter startTime(开始时间)
	 */	
	public void setBeginStartTime(Date beginStartTime) {
		this.beginStartTime = beginStartTime;
	}
	
	/**
	 * getter startTime(开始时间)
	 */		
	public Date getEndStartTime() {
		return endStartTime;
	}

	/**
	 * setter startTime(开始时间)
	 */	
	public void setEndStartTime(Date endStartTime) {
		this.endStartTime = endStartTime;
	}
	/**
	 * getter endTime(结束时间)
	 */
	public Date getBeginEndTime() {
		return beginEndTime;
	}

	/**
	 * setter endTime(结束时间)
	 */	
	public void setBeginEndTime(Date beginEndTime) {
		this.beginEndTime = beginEndTime;
	}
	
	/**
	 * getter endTime(结束时间)
	 */		
	public Date getEndEndTime() {
		return endEndTime;
	}

	/**
	 * setter endTime(结束时间)
	 */	
	public void setEndEndTime(Date endEndTime) {
		this.endEndTime = endEndTime;
	}
	/**
	 * getter applyTime(申请时间)
	 */
	public Date getBeginApplyTime() {
		return beginApplyTime;
	}

	/**
	 * setter applyTime(申请时间)
	 */	
	public void setBeginApplyTime(Date beginApplyTime) {
		this.beginApplyTime = beginApplyTime;
	}
	
	/**
	 * getter applyTime(申请时间)
	 */		
	public Date getEndApplyTime() {
		return endApplyTime;
	}

	/**
	 * setter applyTime(申请时间)
	 */	
	public void setEndApplyTime(Date endApplyTime) {
		this.endApplyTime = endApplyTime;
	}
	/**
	 * getter realityStartTime(实际开始时间)
	 */
	public Date getBeginRealityStartTime() {
		return beginRealityStartTime;
	}

	/**
	 * setter realityStartTime(实际开始时间)
	 */	
	public void setBeginRealityStartTime(Date beginRealityStartTime) {
		this.beginRealityStartTime = beginRealityStartTime;
	}
	
	/**
	 * getter realityStartTime(实际开始时间)
	 */		
	public Date getEndRealityStartTime() {
		return endRealityStartTime;
	}

	/**
	 * setter realityStartTime(实际开始时间)
	 */	
	public void setEndRealityStartTime(Date endRealityStartTime) {
		this.endRealityStartTime = endRealityStartTime;
	}
	/**
	 * getter realityEndTime(实际结束时间)
	 */
	public Date getBeginRealityEndTime() {
		return beginRealityEndTime;
	}

	/**
	 * setter realityEndTime(实际结束时间)
	 */	
	public void setBeginRealityEndTime(Date beginRealityEndTime) {
		this.beginRealityEndTime = beginRealityEndTime;
	}
	
	/**
	 * getter realityEndTime(实际结束时间)
	 */		
	public Date getEndRealityEndTime() {
		return endRealityEndTime;
	}

	/**
	 * setter realityEndTime(实际结束时间)
	 */	
	public void setEndRealityEndTime(Date endRealityEndTime) {
		this.endRealityEndTime = endRealityEndTime;
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