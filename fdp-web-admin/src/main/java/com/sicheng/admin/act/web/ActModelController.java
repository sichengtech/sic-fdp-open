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
package com.sicheng.admin.act.web;

import com.sicheng.admin.act.service.ActModelService;
import com.sicheng.admin.sys.service.MenuService;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 流程模型相关Controller
 * @author zhaolei
 */
@Controller
@RequestMapping(value = "${adminPath}/act/model")
public class ActModelController extends BaseController {

	@Autowired
	private ActModelService actModelService;
	@Autowired
	private MenuService sysMenuService;
	
	/**
	 * 流程模型列表
	 */
	@RequiresPermissions("act:model:edit")
	@RequestMapping(value = { "list", "" })
	public String modelList(String category, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<org.activiti.engine.repository.Model> page = actModelService.modelList(
				new Page<org.activiti.engine.repository.Model>(request, response), category);

		model.addAttribute("page", page);
		model.addAttribute("category", category);

		return "admin/act/actModelList";
	}

	/**
	 * 创建模型
	 */
	@RequiresPermissions("act:model:edit")
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		return "admin/act/actModelCreate";
	}
	
	/**
	 * 创建模型
	 */
	@RequiresPermissions("act:model:edit")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public void create(String name, String key, String description, String category,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			org.activiti.engine.repository.Model modelData = actModelService.create(name, key, description, category);
			response.sendRedirect(request.getContextPath() + "/act/rest/service/editor?id=" + modelData.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("创建模型失败：", e);
		}
	}

	/**
	 * 根据Model部署流程
	 */
	@RequiresPermissions("act:model:edit")
	@RequestMapping(value = "deploy")
	public String deploy(String id, RedirectAttributes redirectAttributes) {
		String message = actModelService.deploy(id);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:" + adminPath + "/act/process.do";
	}
	
	/**
	 * 导出model的xml文件
	 */
	@RequiresPermissions("act:model:edit")
	@RequestMapping(value = "export")
	public void export(String id, HttpServletResponse response) {
		actModelService.export(id, response);
	}

	/**
	 * 更新Model分类
	 */
	@RequiresPermissions("act:model:edit")
	@RequestMapping(value = "updateCategory")
	public String updateCategory(String id, String category, RedirectAttributes redirectAttributes) {
		actModelService.updateCategory(id, category);
		redirectAttributes.addFlashAttribute("message", "设置成功，模块ID=" + id);
		return "redirect:" + adminPath + "/act/model.do";
	}
	
	/**
	 * 删除Model
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("act:model:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		actModelService.delete(id);
		redirectAttributes.addFlashAttribute("message", "删除成功，模型ID=" + id);
		return "redirect:" + adminPath + "/act/model.do";
	}
}
