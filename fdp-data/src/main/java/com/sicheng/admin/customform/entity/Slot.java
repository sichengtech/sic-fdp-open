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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>标题:自定义表单业务的槽位表信息 </p>
 * <p>描述:“槽位”的意思是：有3张有N个字段的表，表中有多种类型的字段，字段可为空，这些字段等待被占用，这个空字段就叫“槽位”。
 * 通过“表单编辑器”去占用槽位，并设定：表单控件的名称、验证规则、数据存储到哪一个槽位。 </p>
 * <p>公司: 思程科技 www.sicheng.net</p>
 * @author zhaolei
 * @date 2016年5月28日 下午3:23:01
 */
@XmlType(name = "slot", propOrder = { "tableList", "ruleList", "showTypeList"}) 
@XmlRootElement(name="slot")
public class Slot {
	
	private List<SlotTable> tableList;//槽位表集合
	private List<SlotRule> ruleList;//表单验证规则集合
	private List<SlotShowType> showTypeList;// 显示类型集合
	
	/**
	 * 槽位表共有3张表，获取第1张表
	 * @return
	 */
	public SlotTable getTable1(){
		if(tableList!=null){
			return tableList.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 槽位表共有3张表，获取第2张表
	 * @return
	 */
	public SlotTable getTable2(){
		if(tableList!=null){
			return tableList.get(1);
		}else{
			return null;
		}
	}
	/**
	 * 槽位表共有3张表，获取第3张表
	 * @return
	 */
	public SlotTable getTable3(){
		if(tableList!=null){
			return tableList.get(2);
		}else{
			return null;
		}
	}
	
	/**
	 * 取3个表的所有列表，方便页面中使用
	 * @return
	 */
	public List<SlotColumn> getAllColumns(){
		List<SlotColumn> all=new ArrayList<SlotColumn>();
		for(SlotTable table:tableList){
			List<SlotColumn> columnList=table.getColumnList();
			all.addAll(columnList);
		}
		return all;
	}
	
	@XmlElementWrapper(name = "tables")
	@XmlElement(name = "table")
	public List<SlotTable> getTableList() {
		return tableList;
	}

	public void setTableList(List<SlotTable> tableList) {
		this.tableList = tableList;
	}

	@XmlElementWrapper(name = "rules")
	@XmlElement(name = "rule")
	public List<SlotRule> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<SlotRule> ruleList) {
		this.ruleList = ruleList;
	}
	
	@XmlElementWrapper(name = "showTypes")
	@XmlElement(name = "showType")
	public List<SlotShowType> getShowTypeList() {
		return showTypeList;
	}

	public void setShowTypeList(List<SlotShowType> showTypeList) {
		this.showTypeList = showTypeList;
	}
	
}
