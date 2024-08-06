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
package com.sicheng.admin.oa.web;

import com.sicheng.admin.oa.entity.TestAudit;
import com.sicheng.admin.oa.service.TestAuditService;
import com.sicheng.admin.sys.entity.User;
import com.sicheng.admin.sys.utils.UserUtils;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.persistence.wrapper.Wrapper;
import com.sicheng.common.web.BaseController;
import org.apache.commons.lang3.StringUtils;
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
 * 审批Controller
 * @author zhaolei
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/testAudit")
public class TestAuditController extends BaseController {

	@Autowired
	private TestAuditService testAuditService;

	@ModelAttribute
	public TestAudit get(@RequestParam(required=false) Long id){//, 
		TestAudit testAudit = null;
		if (id!=null){
			testAudit = testAuditService.get(id);
		}
		if (testAudit == null){
			testAudit = new TestAudit();
		}
		return testAudit;
	}
	
	@RequiresPermissions("oa:testAudit:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestAudit testAudit, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			testAudit.setCreateBy(user);
		}
		Page<TestAudit> page = testAuditService.findPage(new Page<TestAudit>(request, response), testAudit); 
		model.addAttribute("page", page);
		return "admin/oa/testAuditList";
	}
	
	/**
	 * 申请单填写
	 * @param testAudit
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:testAudit:view")
	@RequestMapping(value = "form")
	public String form(TestAudit testAudit, Model model) {
		
		String view = "testAuditForm";
		
		// 查看审批申请单
		if (testAudit.getId()!=null){//.getAct().getProcInsId())){

			// 环节编号
			String taskDefKey = testAudit.getAct().getTaskDefKey();
			
			// 查看工单
			if(testAudit.getAct().isFinishTask()){
				view = "testAuditView";
			}
			// 修改环节
			else if ("modify".equals(taskDefKey)){
				view = "testAuditForm";
			}
			// 审核环节
			else if ("audit".equals(taskDefKey)){
				view = "testAuditAudit";
//				String formKey = "/oa/testAudit";
//				return "redirect:" + ActUtils.getFormUrl(formKey, testAudit.getAct());
			}
			// 审核环节2
			else if ("audit2".equals(taskDefKey)){
				view = "testAuditAudit";
			}
			// 审核环节3
			else if ("audit3".equals(taskDefKey)){
				view = "testAuditAudit";
			}
			// 审核环节4
			else if ("audit4".equals(taskDefKey)){
				view = "testAuditAudit";
			}
			// 兑现环节
			else if ("apply_end".equals(taskDefKey)){
				view = "testAuditAudit";
			}
		}

		model.addAttribute("testAudit", testAudit);
		return "admin/oa/" + view;
	}
	
	/**
	 * 申请单保存/修改
	 * @param testAudit
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:testAudit:edit")
	@RequestMapping(value = "save")
	public String save(TestAudit testAudit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testAudit)){
			return form(testAudit, model);
		}
		testAuditService.save(testAudit);
		addMessage(redirectAttributes, "提交审批'" + testAudit.getUser().getName() + "'成功");
		return "redirect:" + adminPath + "/act/task/todo.do";
	}

	/**
	 * 工单执行（完成任务）
	 * @param testAudit
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:testAudit:edit")
	@RequestMapping(value = "saveAudit")
	public String saveAudit(TestAudit testAudit, Model model) {
		if (StringUtils.isBlank(testAudit.getAct().getFlag())
				|| StringUtils.isBlank(testAudit.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(testAudit, model);
		}
		testAuditService.auditSave(testAudit);
		return "redirect:" + adminPath + "/act/task/todo.do";
	}
	
	/**
	 * 删除工单
	 * @param testAudit 实体
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:testAudit:edit")
	@RequestMapping(value = "delete")
	public String delete(TestAudit testAudit, RedirectAttributes redirectAttributes) {
		testAuditService.deleteByWhere(new Wrapper(testAudit));
		addMessage(redirectAttributes, "删除审批成功");
		return "redirect:" + adminPath + "/oa/testAudit.do?repage";
	}

}
