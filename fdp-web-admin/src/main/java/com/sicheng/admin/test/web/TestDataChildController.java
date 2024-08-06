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
package com.sicheng.admin.test.web;

import com.sicheng.admin.test.entity.TestDataChild;
import com.sicheng.admin.test.service.TestDataChildService;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.persistence.wrapper.Wrapper;
import com.sicheng.common.utils.StringUtils;
import com.sicheng.common.web.BaseController;
import com.sicheng.common.web.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务数据子表管理 Controller
 * 所属模块：test 
 * @author zhaolei
 * @version 2022-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testDataChild")
public class TestDataChildController extends BaseController {

	@Autowired
	private TestDataChildService testDataChildService;

	/**
	 * 进入列表页
	 * @param testDataChild 实体对象
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("test:testDataChild:view")
	@RequestMapping(value = "list")
	public String list(TestDataChild testDataChild, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TestDataChild> page = testDataChildService.selectByWhere(new Page<TestDataChild>(request, response,5), new Wrapper(testDataChild));
		model.addAttribute("page", page);
		return "admin/test/testDataChildList";
	}

	/**
	 * 进入保存页面
	 * @param testDataChild 实体对象
	 * @param model
	 * @return
	 */
	@RequiresPermissions("test:testDataChild:save")
	@RequestMapping(value = "save1")
	public String save1(TestDataChild testDataChild, Model model) {
		if (testDataChild == null){
			testDataChild = new TestDataChild();
		}
		model.addAttribute("testDataChild", testDataChild);
		return "admin/test/testDataChildForm";
	}

	/**
	 * 执行保存
	 * @param testDataChild 实体对象
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:testDataChild:save")
	@RequestMapping(value = "save2")
	public String save2(TestDataChild testDataChild, Model model, RedirectAttributes redirectAttributes) {
		//表单验证
		List<String> errorList=validate(testDataChild, model);
		if(errorList.size()>0){
			errorList.add(0, "数据验证失败：");
			addMessage(model, errorList.toArray(new String[]{}));
			return save1(testDataChild, model);//回显错误提示
		}
		
		//业务处理
		testDataChildService.insertSelective(testDataChild);
		addMessage(redirectAttributes, "保存业务数据子表成功");
		return "redirect:"+adminPath+"/test/testDataChild/list.do?repage";
	}

	/**
	 * 进入编辑页面
	 * @param testDataChild 实体对象
	 * @param model
	 * @return
	 */	
	@RequiresPermissions("test:testDataChild:edit")
	@RequestMapping(value = "edit1")
	public String edit1(TestDataChild testDataChild, Model model) {
		TestDataChild entity = null;
		if(testDataChild!=null){
			if (testDataChild.getId()!=null){
				entity = testDataChildService.selectById(testDataChild.getId());
			}
		}
		model.addAttribute("testDataChild", entity);
		return "admin/test/testDataChildForm";
	}

	/**
	 * 执行编辑
	 * @param testDataChild 实体对象
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:testDataChild:edit")
	@RequestMapping(value = "edit2")
	public String edit2(TestDataChild testDataChild, Model model, RedirectAttributes redirectAttributes) {
		//表单验证
		List<String> errorList=validate(testDataChild, model);
		if(errorList.size()>0){
			errorList.add(0, "数据验证失败：");
			addMessage(model, errorList.toArray(new String[]{}));
			return edit1(testDataChild, model);//回显错误提示
		}		
		
		//业务处理
		testDataChildService.updateByIdSelective(testDataChild);
		addMessage(redirectAttributes, "编辑业务数据子表成功");
		return "redirect:"+adminPath+"/test/testDataChild/list.do?repage";
	}	

	/**
	 * 删除
	 * @param testDataChild 实体对象
	 * @param redirectAttributes
	 * @return
	 */	
	@RequiresPermissions("test:testDataChild:drop")
	@RequestMapping(value = "delete")
	public String delete(TestDataChild testDataChild, RedirectAttributes redirectAttributes) {
		testDataChildService.deleteById(testDataChild.getId());
		addMessage(redirectAttributes, "删除业务数据子表成功");
		return "redirect:"+adminPath+"/test/testDataChild/list.do?repage";
	}

	/**
	 * 表单验证
	 * @param testDataChild 实体对象
	 * @param model 
	 * @return List<String> 错误提示信息
	 */
	private List<String> validate(TestDataChild testDataChild, Model model){
		List<String> errorList = new ArrayList<String>();
		if(StringUtils.isNotBlank(R.get("testDataMainId")) && R.get("testDataMainId").length() > 19){
			errorList.add("业务主表ID最大长度不能超过19字符");
		}
		if(StringUtils.isNotBlank(R.get("name")) && R.get("name").length() > 100){
			errorList.add("名称最大长度不能超过100字符");
		}
		if(StringUtils.isNotBlank(R.get("remarks")) && R.get("remarks").length() > 255){
			errorList.add("备注信息最大长度不能超过255字符");
		}
		return errorList;
	}

}