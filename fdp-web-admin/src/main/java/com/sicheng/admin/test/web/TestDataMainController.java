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

import com.sicheng.admin.test.entity.TestDataMain;
import com.sicheng.admin.test.service.TestDataMainJoinService1线2表JOIN$test_data_main$test_data_child;
import com.sicheng.admin.test.service.TestDataMainService;
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
 * 业务数据表管理 Controller
 * 所属模块：test 
 * @author zhaolei
 * @version 2022-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testDataMain")
public class TestDataMainController extends BaseController {

	@Autowired
	private TestDataMainService testDataMainService;
	@Autowired
	private TestDataMainJoinService1线2表JOIN$test_data_main$test_data_child testDataMainJoinService;


	/**
	 * 进入列表页
	 * @param testDataMain 实体对象
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("test:testDataMain:view")
	@RequestMapping(value = "list")
	public String list(TestDataMain testDataMain, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<TestDataMain> page = testDataMainService.selectByWhere(new Page<TestDataMain>(request, response,5), new Wrapper(testDataMain));
		Page<TestDataMain> page = testDataMainJoinService.joinSelectByWhere(new Page<TestDataMain>(request, response,5), new Wrapper(testDataMain));
		model.addAttribute("page", page);
		return "admin/test/testDataMainList";
	}

	/**
	 * 进入保存页面
	 * @param testDataMain 实体对象
	 * @param model
	 * @return
	 */
	@RequiresPermissions("test:testDataMain:save")
	@RequestMapping(value = "save1")
	public String save1(TestDataMain testDataMain, Model model) {
		if (testDataMain == null){
			testDataMain = new TestDataMain();
		}
		model.addAttribute("testDataMain", testDataMain);
		return "admin/test/testDataMainForm";
	}

	/**
	 * 执行保存
	 * @param testDataMain 实体对象
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:testDataMain:save")
	@RequestMapping(value = "save2")
	public String save2(TestDataMain testDataMain, Model model, RedirectAttributes redirectAttributes) {
		//表单验证
		List<String> errorList=validate(testDataMain, model);
		if(errorList.size()>0){
			errorList.add(0, "数据验证失败：");
			addMessage(model, errorList.toArray(new String[]{}));
			return save1(testDataMain, model);//回显错误提示
		}
		
		//业务处理
		testDataMainService.insertSelective(testDataMain);
		addMessage(redirectAttributes, "保存业务数据表成功");
		return "redirect:"+adminPath+"/test/testDataMain/list.do?repage";
	}

	/**
	 * 进入编辑页面
	 * @param testDataMain 实体对象
	 * @param model
	 * @return
	 */	
	@RequiresPermissions("test:testDataMain:edit")
	@RequestMapping(value = "edit1")
	public String edit1(TestDataMain testDataMain, Model model) {
		TestDataMain entity = null;
		if(testDataMain!=null){
			if (testDataMain.getId()!=null){
				entity = testDataMainService.selectById(testDataMain.getId());
			}
		}
		model.addAttribute("testDataMain", entity);
		return "admin/test/testDataMainForm";
	}

	/**
	 * 执行编辑
	 * @param testDataMain 实体对象
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("test:testDataMain:edit")
	@RequestMapping(value = "edit2")
	public String edit2(TestDataMain testDataMain, Model model, RedirectAttributes redirectAttributes) {
		//表单验证
		List<String> errorList=validate(testDataMain, model);
		if(errorList.size()>0){
			errorList.add(0, "数据验证失败：");
			addMessage(model, errorList.toArray(new String[]{}));
			return edit1(testDataMain, model);//回显错误提示
		}		
		
		//业务处理
		testDataMainService.updateByIdSelective(testDataMain);
		addMessage(redirectAttributes, "编辑业务数据表成功");
		return "redirect:"+adminPath+"/test/testDataMain/list.do?repage";
	}	

	/**
	 * 删除
	 * @param testDataMain 实体对象
	 * @param redirectAttributes
	 * @return
	 */	
	@RequiresPermissions("test:testDataMain:drop")
	@RequestMapping(value = "delete")
	public String delete(TestDataMain testDataMain, RedirectAttributes redirectAttributes) {
		testDataMainService.deleteById(testDataMain.getId());
		addMessage(redirectAttributes, "删除业务数据表成功");
		return "redirect:"+adminPath+"/test/testDataMain/list.do?repage";
	}

	/**
	 * 表单验证
	 * @param testDataMain 实体对象
	 * @param model 
	 * @return List<String> 错误提示信息
	 */
	private List<String> validate(TestDataMain testDataMain, Model model){
		List<String> errorList = new ArrayList<String>();
		if(StringUtils.isNotBlank(R.get("name")) && R.get("name").length() > 100){
			errorList.add("名称最大长度不能超过100字符");
		}
		if(StringUtils.isNotBlank(R.get("sex")) && R.get("sex").length() > 1){
			errorList.add("性别最大长度不能超过1字符");
		}
		return errorList;
	}

}