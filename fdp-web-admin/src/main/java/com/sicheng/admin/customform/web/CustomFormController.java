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

import com.sicheng.admin.customform.entity.CustomForm;
import com.sicheng.admin.customform.entity.Slot;
import com.sicheng.admin.customform.service.CustomFormService;
import com.sicheng.common.config.Global;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文章模型Controller
 * 通过自定义表单来实现多种文章模型
 * @author zhaolei
 * @version 2016-06-03
 */
@Controller
@RequestMapping(value = "${adminPath}/customForm")
public class CustomFormController extends BaseController {

	@Autowired
	private CustomFormService customFormService;
	/**
	 * 按实体id查询数据，类中先执行的方法
	 * @param id 实体的id
	 * @return
	 */
	@ModelAttribute
	public CustomForm get(@RequestParam(required=false) Long id) {
		CustomForm entity = null;
		if (id!=null){
			entity = customFormService.selectById(id);
		}
		if (entity == null){
			entity = new CustomForm();
		}
		return entity;
	}
	/**
	 * 进入列表页
	 * @param customForm 实体对象
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("customform:customform:view")
	@RequestMapping(value = {"list", ""})
	public String list(CustomForm customForm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CustomForm> page = customFormService.findPage(new Page<CustomForm>(request, response), customForm); 
		
		model.addAttribute("page", page);
		return "admin/customform/customFormList";
	}
	/**
	 * 进入添加页/编辑页
	 * @param customForm 实体对象
	 * @param model
	 * @return
	 */
	@RequiresPermissions("customform:customform:view")
	@RequestMapping(value = "form")
	public String form(CustomForm customForm, Model model) {
		Slot slot = CustomFormUtils.getFormMetadata();
		model.addAttribute("slot", slot);
		model.addAttribute("customForm", customForm);
		return "admin/customform/customFormForm";
	}
	/**
	 * 执行添加方法/编辑方法
	 * @param customForm 实体对象
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("customform:customform:edit")
	@RequestMapping(value = "save")
	public String save(CustomForm customForm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, customForm)){
			return form(customForm, model);
		}
		customFormService.save(customForm);
		System.out.println(customForm);
		
		addMessage(redirectAttributes, "保存自定义表单成功");
		return "redirect:"+Global.getAdminPath()+"/customForm.do?repage";
	}
	/**
	 * 执行删除方法
	 * @param customForm 实体对象
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("customform:customform:edit")
	@RequestMapping(value = "delete")
	public String delete(CustomForm customForm, RedirectAttributes redirectAttributes) {
		customFormService.delete(customForm.getId());
		addMessage(redirectAttributes, "删除自定义表单成功");
		return "redirect:"+Global.getAdminPath()+"/customForm.do?repage";
	}

}