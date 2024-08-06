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
package com.sicheng.admin.customform.web;

import com.sicheng.admin.customform.entity.Form;
import com.sicheng.admin.customform.entity.Slot;
import com.sicheng.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>标题: 表单设计器</p>
 * <p>描述: </p>
 * <p>公司: 思程科技 www.sicheng.net</p>
 * @author zhaolei
 * @date 2016年5月28日 下午4:02:07
 */
@Controller
@RequestMapping(value = "${adminPath}/customform/design")
public class DesignController extends BaseController {

	/**
	 * 列表
	 */
	@RequiresPermissions("customform:design:view")
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/admin/customform/editA";
	}
	/**
	 * 进入创建页
	 * @return
	 */
	public String create1(){
		return "";
	}
	/**
	 * 保存创建
	 * @return
	 */
	public String create2(){
		return "";
	}
	/**
	 * 进入编辑页A
	 * @return
	 */
	@RequiresPermissions("customform:design:edit")
	@RequestMapping(value = {"editA1"})
	public String editA1(HttpServletRequest request, HttpServletResponse response, Model model){
		
		return "/admin/customform/editA";
	}
	/**
	 * 保存编辑
	 * @return
	 */
	@RequiresPermissions("customform:design:edit")
	@RequestMapping(value = {"editA2"})
	public String editA2(){
		return "";
	}
	/**
	 * 进入编辑页B
	 * @return
	 */
	@RequiresPermissions("customform:design:edit")
	@RequestMapping(value = {"editB1"})
	public String editB1(HttpServletRequest request, HttpServletResponse response, Model model){
		Slot slot = CustomFormUtils.getFormMetadata();
		model.addAttribute("slot", slot);
		return "/admin/customform/editB";
	}
	/**
	 * 保存编辑B
	 * @return
	 */
	@RequiresPermissions("customform:design:edit")
	@RequestMapping(value = {"editB2"})
	public String editB2(Model model,Form form, RedirectAttributes redirectAttributes){
		System.out.println(form);
		model.addAttribute("form", form);
		
		addMessage(redirectAttributes, "保存成功");
		//Slot slot = FormUtils.getFormMetadata();
		//model.addAttribute("slot", slot);
		return "redirect:" + adminPath + "/customform/design/editB1.do";
	}
	/**
	 * 删除
	 * @return
	 */
	@RequiresPermissions("customform:design:edit")
	@RequestMapping(value = {"delete"})
	public String delete(){
		return "";
	}
}
