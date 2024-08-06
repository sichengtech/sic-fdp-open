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

package com.sicheng.admin.customform.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * <p>标题:自定义表单的字段 </p>
 * <p>描述: </p>
 * <p>公司: 思程科技 www.sicheng.net</p>
 * @author zhaolei
 * @date 2016年6月2日 下午7:34:13
 */
public class FormField {
	String tableName;//表名
	String columnName;//列名
	boolean isUse;//是否占用
	String label;//标签
	String helpMsg;//帮助信息
	String validation;//验证规则
	String showType;//显示类型，使用使用表单控件
	String dictType;//字典
	String sort;//排序
	String newRow;//展示表单时，本字段新启一行，是一行的开头
	String style;
	
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public boolean isUse() {
		return isUse;
	}
	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getHelpMsg() {
		return helpMsg;
	}
	public void setHelpMsg(String helpMsg) {
		this.helpMsg = helpMsg;
	}

	public String getValidation() {
		return validation;
	}
	public void setValidation(String validation) {
		this.validation = validation;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getNewRow() {
		return newRow;
	}
	public void setNewRow(String newRow) {
		this.newRow = newRow;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
