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
package com.sicheng.common.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * <p>标题: SiteOpenFilter</p>
 * <p>描述: 检查网站是否开放,若网站已关闭则把访问拦住并给出关闭提示</p>
 * <p>公司: 思程科技 www.sicheng.net</p>
 * @author fxx
 * @date 2017年8月5日 下午5:40:45
 */
public class ShopOpenFilter implements Filter {
//	SiteSwitchDao siteSwitchDao=SpringContextHolder.getBean(SiteSwitchDao.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, resp);
//		List<SiteSwitch> siteSwitchList=siteSwitchDao.selectAll(new Wrapper().orderBy("id desc"));//有缓存，mybatis二级缓存
//		if(siteSwitchList.size()==0 || "1".equals(siteSwitchList.get(0).getIsOpen())){
//			chain.doFilter(request, resp);
//		}else{
//			String msg="系统维护中，请稍后再试...";
//			if(siteSwitchList.size()>0 && !"".equals(siteSwitchList.get(0).getMsg())){
//				msg=siteSwitchList.get(0).getMsg();
//			}
//			try {
//				HttpServletResponse response=(HttpServletResponse)resp;
//				response.setContentType("text/html;charset=UTF-8"); //设置编码
//				response.setCharacterEncoding("UTF-8"); //设置编码
//				response.setHeader("Pragma", "No-cache");
//				response.setHeader("Cache-Control", "no-cache");
//				response.setDateHeader("Expires", 0);
//				PrintWriter out = response.getWriter();
//				out.write(msg);
//				out.flush();
//				out.close();
//			} catch (IOException e) {
//				//logger.error("向浏览器输出HTML异常", e);
//			}
//		}
	}

	@Override
	public void destroy() {
	}
}