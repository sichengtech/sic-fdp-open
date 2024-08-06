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
package com.sicheng.wap.test.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * 业务数据子表管理 Vo 父类
 * @author zhaolei
 * @version 2022-04-11
 */
@ApiModel(value="业务数据子表管理-接收参数的TestDataChildVo类")
public class TestDataChildVoBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="编号")
    private Long id;                        // 编号
    @ApiModelProperty(value="业务主表ID")
    private Long testDataMainId;            // 业务主表ID
    @ApiModelProperty(value="名称")
    private String name;                    // 名称
    @ApiModelProperty(value="备注信息")
    private String remarks;                 // 备注信息
    @ApiModelProperty(value="开始 创建时间")
    private Date beginCreateDate;           // 开始 创建时间
    @ApiModelProperty(value="结束 创建时间")
    private Date endCreateDate;             // 结束 创建时间
    @ApiModelProperty(value="开始 更新时间")
    private Date beginUpdateDate;           // 开始 更新时间
    @ApiModelProperty(value="结束 更新时间")
    private Date endUpdateDate;             // 结束 更新时间


    /**
     * getter id(编号)
     */
    public Long getId() {
        return id;
    }

    /**
     * setter id(编号)
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter testDataMainId(业务主表ID)
     */
    public Long getTestDataMainId() {
        return testDataMainId;
    }

    /**
     * setter testDataMainId(业务主表ID)
     */
    public void setTestDataMainId(Long testDataMainId) {
        this.testDataMainId = testDataMainId;
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
     * getter remarks(备注信息)
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * setter remarks(备注信息)
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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