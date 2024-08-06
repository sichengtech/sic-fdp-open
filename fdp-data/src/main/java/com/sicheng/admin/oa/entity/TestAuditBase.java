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

import com.sicheng.admin.act.entity.ActEntity;
import com.sicheng.admin.sys.entity.Office;
import com.sicheng.admin.sys.entity.User;

import java.util.Date;

/**
 * 审批 Entity 父类
 * @author fxx
 * @version 2017-02-09
 */
public class TestAuditBase<T> extends ActEntity<T> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;                 // 流程实例ID
	private User user;                      // 变动用户
	private Office office;                  // 归属部门
	private String post;                    // 岗位
	private String age;                     // 性别
	private String edu;                     // 学历
	private String content;                 // 调整原因
	private String olda;                    // 现行标准 薪酬档级
	private String oldb;                    // 现行标准 月工资额
	private String oldc;                    // 现行标准 年薪总额
	private String newa;                    // 调整后标准 薪酬档级
	private String newb;                    // 调整后标准 月工资额
	private String newc;                    // 调整后标准 年薪总额
	private String addNum;                  // 月增资
	private String exeDate;                 // 执行时间
	private String hrText;                  // 人力资源部门意见
	private String leadText;                // 分管领导意见
	private String mainLeadText;            // 集团主要领导意见
	private Date beginCreateDate;           // 开始 创建时间
	private Date endCreateDate;             // 结束 创建时间
	private Date beginUpdateDate;           // 开始 更新时间
	private Date endUpdateDate;             // 结束 更新时间
	
	public TestAuditBase() {
		super();
	}

	public TestAuditBase(Long id){
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
	 * getter procInsId(流程实例ID)
	 */				
	public String getProcInsId() {
		return procInsId;
	}

	/**
	 * setter procInsId(流程实例ID)
	 */	
	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	/**
	 * getter user(变动用户)
	 */				
	public User getUser() {
		return user;
	}

	/**
	 * setter user(变动用户)
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
	 * getter post(岗位)
	 */				
	public String getPost() {
		return post;
	}

	/**
	 * setter post(岗位)
	 */	
	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * getter age(性别)
	 */				
	public String getAge() {
		return age;
	}

	/**
	 * setter age(性别)
	 */	
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * getter edu(学历)
	 */				
	public String getEdu() {
		return edu;
	}

	/**
	 * setter edu(学历)
	 */	
	public void setEdu(String edu) {
		this.edu = edu;
	}

	/**
	 * getter content(调整原因)
	 */				
	public String getContent() {
		return content;
	}

	/**
	 * setter content(调整原因)
	 */	
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * getter olda(现行标准 薪酬档级)
	 */				
	public String getOlda() {
		return olda;
	}

	/**
	 * setter olda(现行标准 薪酬档级)
	 */	
	public void setOlda(String olda) {
		this.olda = olda;
	}

	/**
	 * getter oldb(现行标准 月工资额)
	 */				
	public String getOldb() {
		return oldb;
	}

	/**
	 * setter oldb(现行标准 月工资额)
	 */	
	public void setOldb(String oldb) {
		this.oldb = oldb;
	}

	/**
	 * getter oldc(现行标准 年薪总额)
	 */				
	public String getOldc() {
		return oldc;
	}

	/**
	 * setter oldc(现行标准 年薪总额)
	 */	
	public void setOldc(String oldc) {
		this.oldc = oldc;
	}

	/**
	 * getter newa(调整后标准 薪酬档级)
	 */				
	public String getNewa() {
		return newa;
	}

	/**
	 * setter newa(调整后标准 薪酬档级)
	 */	
	public void setNewa(String newa) {
		this.newa = newa;
	}

	/**
	 * getter newb(调整后标准 月工资额)
	 */				
	public String getNewb() {
		return newb;
	}

	/**
	 * setter newb(调整后标准 月工资额)
	 */	
	public void setNewb(String newb) {
		this.newb = newb;
	}

	/**
	 * getter newc(调整后标准 年薪总额)
	 */				
	public String getNewc() {
		return newc;
	}

	/**
	 * setter newc(调整后标准 年薪总额)
	 */	
	public void setNewc(String newc) {
		this.newc = newc;
	}

	/**
	 * getter addNum(月增资)
	 */				
	public String getAddNum() {
		return addNum;
	}

	/**
	 * setter addNum(月增资)
	 */	
	public void setAddNum(String addNum) {
		this.addNum = addNum;
	}

	/**
	 * getter exeDate(执行时间)
	 */				
	public String getExeDate() {
		return exeDate;
	}

	/**
	 * setter exeDate(执行时间)
	 */	
	public void setExeDate(String exeDate) {
		this.exeDate = exeDate;
	}

	/**
	 * getter hrText(人力资源部门意见)
	 */				
	public String getHrText() {
		return hrText;
	}

	/**
	 * setter hrText(人力资源部门意见)
	 */	
	public void setHrText(String hrText) {
		this.hrText = hrText;
	}

	/**
	 * getter leadText(分管领导意见)
	 */				
	public String getLeadText() {
		return leadText;
	}

	/**
	 * setter leadText(分管领导意见)
	 */	
	public void setLeadText(String leadText) {
		this.leadText = leadText;
	}

	/**
	 * getter mainLeadText(集团主要领导意见)
	 */				
	public String getMainLeadText() {
		return mainLeadText;
	}

	/**
	 * setter mainLeadText(集团主要领导意见)
	 */	
	public void setMainLeadText(String mainLeadText) {
		this.mainLeadText = mainLeadText;
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