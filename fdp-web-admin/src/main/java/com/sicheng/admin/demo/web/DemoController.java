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
package com.sicheng.admin.demo.web;

import com.sicheng.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>标题: 组件演示</p>
 * <p>描述: </p>
 * <p>公司: 思程科技 www.sicheng.net</p>
 * @author zhaolei
 * @date 2017年8月23日 下午5:16:05
 */
@Controller
@RequestMapping(value = "${adminPath}/demo")
public class DemoController extends BaseController {
	
	/**
	 * 进入xxx演示页面
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/demo1")
	public String demo1(HttpServletRequest request, HttpServletResponse response,Model model) {
		return "admin/demo/demoFdpJs";
	}
	
	/**
	 * 执行xxx动作
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/demo2")
	public String demo2(HttpServletRequest request, HttpServletResponse response,Model model) {
		System.out.println("已执行");
		return "redirect:" + adminPath + "/demo/demo1.do";
	}


}
