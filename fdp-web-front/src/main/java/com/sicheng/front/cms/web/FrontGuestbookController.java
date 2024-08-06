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
package com.sicheng.front.cms.web;

import com.sicheng.admin.cms.entity.Guestbook;
import com.sicheng.admin.cms.entity.Site;
import com.sicheng.common.config.Global;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.web.BaseController;
import com.sicheng.front.cms.service.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 留言板Controller
 *
 * @author zhaolei
 * @version 2013-3-15
 */
@Controller
@RequestMapping(value = "${frontPath}/guestbook")
public class FrontGuestbookController extends BaseController {

    @Autowired
    private GuestbookService guestbookService;

    /**
     * 留言板
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String guestbook(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                            @RequestParam(required = false, defaultValue = "30") Integer pageSize, Model model,
                            HttpServletRequest request, HttpServletResponse response) {
        Site site = null;// CmsUtils2.getSite(Site.defaultSiteId());
        model.addAttribute("site", site);

        Page<Guestbook> page = new Page<Guestbook>(request, response);
        Guestbook guestbook = new Guestbook();
        guestbook.setDelFlag(Guestbook.DEL_FLAG_NORMAL);
        page = guestbookService.findPage(page, guestbook);
        model.addAttribute("page", page);
        return "front/cms/themes/" + site.getTheme() + "/frontGuestbook";
    }

    /**
     * 留言板-保存留言信息
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String guestbookSave(Guestbook guestbook, String validateCode, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		if (StringUtils.isNotBlank(validateCode)){
//			if (ValidateCodeServlet.validate(request, validateCode)){
        guestbook.setIp(request.getRemoteAddr());
        guestbook.setCreateDate(new Date());
        guestbook.setDelFlag(Guestbook.DEL_FLAG_AUDIT);
        guestbookService.save(guestbook);
        addMessage(redirectAttributes, "提交成功，谢谢！");
//			}else{
//				addMessage(redirectAttributes, "验证码不正确。");
//			}
//		}else{
//			addMessage(redirectAttributes, "验证码不能为空。");
//		}
        return "redirect:" + Global.getFrontPath() + "/guestbook" + Global.getUrlSuffix();
    }

}
