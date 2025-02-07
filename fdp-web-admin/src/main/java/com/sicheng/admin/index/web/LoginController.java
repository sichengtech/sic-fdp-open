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
package com.sicheng.admin.index.web;

import com.sicheng.admin.sys.shiro.FormAuthenticationFilter;
import com.sicheng.admin.sys.shiro.SystemAuthorizingRealm.Principal;
import com.sicheng.admin.sys.utils.UserUtils;
import com.sicheng.common.config.Global;
import com.sicheng.common.shiro.FdpSessionDAOI;
import com.sicheng.common.utils.CookieUtils;
import com.sicheng.common.utils.StringUtils;
import com.sicheng.common.web.BaseController;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * admin系统管理员登录Controller
 * @author zhaolei
 * @version 2013-5-31
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class LoginController extends BaseController{
	
	@Autowired
	private FdpSessionDAOI sessionDAO;
	
	/**
	 * 进入登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		if (logger.isDebugEnabled()){
			logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			CookieUtils.setCookie(request,response, "LOGINED", "false");
		}
		
		// 如果已经登录，则跳转到管理后台首页
		if(principal != null && !principal.isMobileLogin()){
			return "redirect:" + adminPath+"/index.do";
		}
		
		return "admin/sys/login";
	}

	/**
	 * 登录失败
	 * 登录页面提交表单给shiro,真正登录的POST请求由Filter完成
	 * 若登录失败后，才会走到这里
	 * 若登录成功后，由shiro跳转到的“successUrl”
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		// 如果已经登录，则跳转到管理后台首页
		if(principal != null && !principal.isMobileLogin()){
			return "redirect:" + adminPath+"/index.do";
		}
		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		
		if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
		
		if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}", 
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		// 非授权异常，登录失败，验证码加1。
		if (!UnauthorizedException.class.getName().equals(exception)){
			model.addAttribute("isValidateCodeLogin", UserUtils.isValidateCodeLogin(username, true, false));
		}
		// 如果是手机登录，则返回JSON字符串
		if (mobile){
			return renderString(response, model);
		}
		return "admin/sys/login";
	}

	/**
	 * 登录成功
	 * 登录成功后，shiro会走到这里
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}/loginSuccess")
	public String loginSuccess(HttpServletRequest request, HttpServletResponse response,Model model) {
		Principal principal = UserUtils.getPrincipal();

		// 登录成功后，验证码计算器清零
		UserUtils.isValidateCodeLogin(principal.getLoginName(), false, true);
		
		if (logger.isDebugEnabled()){
			logger.debug("show index, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		
		// 如果已登录，再次访问主页，则退出原账号。
		// 是否不允许刷新主页，不允许情况下，刷新主页会导致重新登录。 值在配置文件中。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			String logined = CookieUtils.getCookie(request, "LOGINED");
			if (StringUtils.isBlank(logined) || "false".equals(logined)){
				CookieUtils.setCookie(request,response, "LOGINED", "true");
			}else if (StringUtils.equals(logined, "true")){
				UserUtils.getSubject().logout();
				return "redirect:" + adminPath + "/login.do";
			}
		}
		
		// 如果是手机登录，则返回JSON字符串
		if (principal.isMobileLogin()){
			if (request.getParameter("login") != null){
				return renderString(response, principal);
			}
			if (request.getParameter("index") != null){
				return "admin/index";
			}
			return "redirect:" + adminPath + "/login.do";
		}
		
		// shiro 登录成功后，跳转到登录前的页面
		// shiro在跳转前会把跳转过来的页面链接保存到session的attribute中，key的值叫shiroSavedRequest，我们可以能过WebUtils类拿到
		// 当用户登录成功后，可能通过String url = WebUtils.getSavedRequest(request).getRequestUrl();，拿到跳转到登录页面前的url，然后redirect到这个url。
		SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
		if(savedRequest!=null){
			String url=savedRequest.getRequestUrl();
			if(url!=null){
				String ctx=request.getContextPath();
				if(ctx!=null && url.startsWith(ctx)){
					url=url.substring(ctx.length());
				}
				return "redirect:" + url;
			}
		}
		
		//进入admin管理后台首页
		return "redirect:" + adminPath + "/index.do";
	}

}
