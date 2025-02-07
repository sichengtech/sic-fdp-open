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
package com.sicheng.admin.sys.web;

import com.sicheng.common.filter.PageCachingFilter;
import com.sicheng.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.Set;

/**
 * 缓存管理 Controller
 * 所属模块：sys 
 * @author cl
 * @version 2017-10-22
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysCache")
public class SysCacheController extends BaseController {

	/**
	 * 进入缓存管理页面
	 * @return
	 */
	@RequiresPermissions("sys:sysCache:view")
	@RequestMapping(value = "index")
	public String index() {
		return "admin/sys/sysCacheIndex";
	}

	/**
	 * 清理页面缓存
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:sysCache:view")
	@RequestMapping(value = "delPageCache")
	public String delPageCache(RedirectAttributes redirectAttributes) {
		Set<Object> set = cache.keys(PageCachingFilter.KEY_PREFIX+"*");
		Iterator<Object> it = set.iterator();  
		while (it.hasNext()) { 
			cache.del(it.next());
		}
		addMessage(redirectAttributes, "清理页面缓存成功");
		return "redirect:"+adminPath+"/sys/sysCache/index.do?repage";
	}
	
	/**
	 * 清理SQL数据缓存
	 */
	@RequiresPermissions("sys:sysCache:view")
	@RequestMapping(value = "delSQLCache")
	public String delSQLCache(RedirectAttributes redirectAttributes) {
		Set<Object> set = cache.keys("com.sicheng.admin.*");
		Iterator<Object> it = set.iterator();  
		while (it.hasNext()) { 
			cache.del(it.next());
		}
		addMessage(redirectAttributes, "清理SQL数据缓存成功");
		return "redirect:"+adminPath+"/sys/sysCache/index.do?repage";
	}
}