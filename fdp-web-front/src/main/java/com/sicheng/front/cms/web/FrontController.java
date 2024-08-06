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


import com.sicheng.admin.cms.entity.Article;
import com.sicheng.admin.cms.entity.Category;
import com.sicheng.admin.cms.entity.Comment;
import com.sicheng.admin.cms.entity.Site;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.servlet.ValidateCodeServlet;
import com.sicheng.common.utils.StringUtils;
import com.sicheng.common.web.BaseController;
import com.sicheng.front.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 网站Controller
 *
 * @author zhaolei
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}/cms")
public class FrontController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleDataService articleDataService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SiteService siteService;

    /**
     * 网站首页
     */
    @RequestMapping(value = "index")
    public String index(Model model) {
        Site site = null;// CmsUtils2.getSite(Site.defaultSiteId());
        model.addAttribute("site", site);
        model.addAttribute("isIndex", true);
//        return "front/cms/themes/" + site.getTheme() + "/frontIndex";
        return "front/cms/themes/basic/frontIndex";
    }

//	/**
//	 * 网站首页
//	 */
//	@RequestMapping(value = "index-{siteId}")
//	public String index(@PathVariable Long siteId, Model model) {
//		if (siteId.equals("1")){
//			return "redirect:"+Global.getFrontPath()+"/cms/index"+Global.getUrlSuffix();
//		}
//		Site site = CmsUtils2.getSite(siteId);
//		// 子站有独立页面，则显示独立页面
//		if (StringUtils.isNotBlank(site.getCustomIndexView())){
//			model.addAttribute("site", site);
//			model.addAttribute("isIndex", true);
//			return "front/cms/themes/"+site.getTheme()+"/frontIndex"+site.getCustomIndexView();
//		}
//		// 否则显示子站第一个栏目
//		List<Category> mainNavList = CmsUtils2.getMainNavList(siteId);
//		if (mainNavList.size() > 0){
//			Long firstCategoryId = CmsUtils2.getMainNavList(siteId).get(0).getId();
//			return "redirect:"+Global.getFrontPath()+"/cms/list-"+firstCategoryId+Global.getUrlSuffix();
//		}else{
//			model.addAttribute("site", site);
//			return "front/cms/themes/"+site.getTheme()+"/frontListCategory";
//		}
//	}

//	/**
//	 * 内容列表
//	 */
//	@RequestMapping(value = "list-{categoryId}")
//	public String list(@PathVariable Long categoryId, @RequestParam(required=false, defaultValue="1") Integer pageNo,
//			@RequestParam(required=false, defaultValue="15") Integer pageSize, Model model) {
//		Category category = categoryService.get(categoryId);
//		if (category==null){
//			Site site = CmsUtils2.getSite(Site.defaultSiteId());
//			model.addAttribute("site", site);
//			return "error/404";
//		}
//		Site site = siteService.get(category.getSite().getId());
//		model.addAttribute("site", site);
//		// 2：简介类栏目，栏目第一条内容
//		if("2".equals(category.getShowModes()) && "article".equals(category.getModule())){
//			// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
//			List<Category> categoryList = Lists.newArrayList();
//			if (category.getParent().getId().equals("1")){
//				categoryList.add(category);
//			}else{
//				categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
//			}
//			model.addAttribute("category", category);
//			model.addAttribute("categoryList", categoryList);
//			// 获取文章内容
//			Page<Article> page = new Page<Article>(1, 1, -1);
//			Article article = new Article(category);
//			page = articleService.findPage(page, article, false);
//			if (page.getList().size()>0){
//				article = page.getList().get(0);
//				article.setArticleData(articleDataService.get(article.getId()));
//				articleService.updateHitsAddOne(article.getId());
//			}
//			model.addAttribute("article", article);
//			CmsUtils2.addViewConfigAttribute(model, category);
//			CmsUtils2.addViewConfigAttribute(model, article.getViewConfig());
//			return "front/cms/themes/"+site.getTheme()+"/"+getTpl(article);
//		}else{
//			List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
//			// 展现方式为1 、无子栏目或公共模型，显示栏目内容列表
//			if("1".equals(category.getShowModes())||categoryList.size()==0){
//				// 有子栏目并展现方式为1，则获取第一个子栏目；无子栏目，则获取同级分类列表。
//				if(categoryList.size()>0){
//					category = categoryList.get(0);
//				}else{
//					// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
//					if (category.getParent().getId().equals("1")){
//						categoryList.add(category);
//					}else{
//						categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
//					}
//				}
//				model.addAttribute("category", category);
//				model.addAttribute("categoryList", categoryList);
//				// 获取内容列表
//				if ("article".equals(category.getModule())){
//					Page<Article> page = new Page<Article>(pageNo, pageSize);
//					//System.out.println(page.getPageNo());
//					page = articleService.findPage(page, new Article(category), false);
//					model.addAttribute("page", page);
//					// 如果第一个子栏目为简介类栏目，则获取该栏目第一篇文章
//					if ("2".equals(category.getShowModes())){
//						Article article = new Article(category);
//						if (page.getList().size()>0){
//							article = page.getList().get(0);
//							article.setArticleData(articleDataService.get(article.getId()));
//							articleService.updateHitsAddOne(article.getId());
//						}
//						model.addAttribute("article", article);
//						CmsUtils2.addViewConfigAttribute(model, category);
//						CmsUtils2.addViewConfigAttribute(model, article.getViewConfig());
//						return "front/cms/front/themes/"+site.getTheme()+"/"+getTpl(article);
//					}
//				}else if ("link".equals(category.getModule())){
//					Page<Link> page = new Page<Link>(1, -1);
//					page = linkService.findPage(page, new Link(category), false);
//					model.addAttribute("page", page);
//				}
//				String view = "/frontList";
//				if (StringUtils.isNotBlank(category.getCustomListView())){
//					view = "/"+category.getCustomListView();
//				}
//				CmsUtils2.addViewConfigAttribute(model, category);
//				site =siteService.get(category.getSite().getId());
//				//System.out.println("else 栏目第一条内容 _2 :"+category.getSite().getTheme()+","+site.getTheme());
//				return "front/cms/themes/"+siteService.get(category.getSite().getId()).getTheme()+view;
//				//return "front/cms/themes/"+category.getSite().getTheme()+view;
//			}
//			// 有子栏目：显示子栏目列表
//			else{
//				model.addAttribute("category", category);
//				model.addAttribute("categoryList", categoryList);
//				String view = "/frontListCategory";
//				if (StringUtils.isNotBlank(category.getCustomListView())){
//					view = "/"+category.getCustomListView();
//				}
//				CmsUtils2.addViewConfigAttribute(model, category);
//				return "front/cms/themes/"+site.getTheme()+view;
//			}
//		}
//	}
//
//	/**
//	 * 内容列表（通过url自定义视图）
//	 */
//	@RequestMapping(value = "listc-{categoryId}-{customView}")
//	public String listCustom(@PathVariable Long categoryId, @PathVariable String customView, @RequestParam(required=false, defaultValue="1") Integer pageNo,
//			@RequestParam(required=false, defaultValue="15") Integer pageSize, Model model) {
//		Category category = categoryService.get(categoryId);
//		if (category==null){
//			Site site = CmsUtils2.getSite(Site.defaultSiteId());
//			model.addAttribute("site", site);
//			return "error/404";
//		}
//		Site site = siteService.get(category.getSite().getId());
//		model.addAttribute("site", site);
//		List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
//		model.addAttribute("category", category);
//		model.addAttribute("categoryList", categoryList);
//		CmsUtils2.addViewConfigAttribute(model, category);
//		return "front/cms/themes/"+site.getTheme()+"/frontListCategory"+customView;
//	}
//
//	/**
//	 * 显示内容
//	 */
//	@RequestMapping(value = "view-{categoryId}-{contentId}")
//	public String view(@PathVariable Long categoryId, @PathVariable Long contentId, Model model) {
//		Category category = categoryService.get(categoryId);
//		if (category==null){
//			Site site = CmsUtils2.getSite(Site.defaultSiteId());
//			model.addAttribute("site", site);
//			return "error/404";
//		}
//		model.addAttribute("site", category.getSite());
//		if ("article".equals(category.getModule())){
//			// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
//			List<Category> categoryList = Lists.newArrayList();
//			if (category.getParent().getId().equals("1")){
//				categoryList.add(category);
//			}else{
//				categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
//			}
//			// 获取文章内容
//			Article article = articleService.get(contentId);
//			if (article==null || !Article.DEL_FLAG_NORMAL.equals(article.getDelFlag())){
//				return "error/404";
//			}
//			// 文章阅读次数+1
//			articleService.updateHitsAddOne(contentId);
//			// 获取推荐文章列表
//			List<Object[]> relationList = articleService.findByIds(articleDataService.get(article.getId()).getRelation());
//			// 将数据传递到视图
//			model.addAttribute("category", categoryService.get(article.getCategory().getId()));
//			model.addAttribute("categoryList", categoryList);
//			article.setArticleData(articleDataService.get(article.getId()));
//			model.addAttribute("article", article);
//			model.addAttribute("relationList", relationList); 
//			CmsUtils2.addViewConfigAttribute(model, article.getCategory());
//			CmsUtils2.addViewConfigAttribute(model, article.getViewConfig());
//			Site site = siteService.get(category.getSite().getId());
//			model.addAttribute("site", site);
////			return "front/cms/themes/"+category.getSite().getTheme()+"/"+getTpl(article);
//			return "front/cms/themes/"+site.getTheme()+"/"+getTpl(article);
//		}
//		return "error/404";
//	}

    /**
     * 内容评论
     */
    @RequestMapping(value = "comment", method = RequestMethod.GET)
    public String comment(String theme, Comment comment, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Comment> page = new Page<Comment>(request, response);
        Comment c = new Comment();
        c.setCategory(comment.getCategory());
        c.setContentId(comment.getContentId());
        c.setDelFlag(Comment.DEL_FLAG_NORMAL);
        page = commentService.findPage(page, c);
        model.addAttribute("page", page);
        model.addAttribute("comment", comment);
        return "front/cms/themes/" + theme + "/frontComment";
    }

    /**
     * 内容评论保存
     */
    @ResponseBody
    @RequestMapping(value = "comment", method = RequestMethod.POST)
    public String commentSave(Comment comment, String validateCode, @RequestParam(required = false) Long replyId, HttpServletRequest request) {
        if (StringUtils.isNotBlank(validateCode)) {
            if (ValidateCodeServlet.validate(request, validateCode)) {
                if (replyId != null) {
                    Comment replyComment = commentService.get(replyId);
                    if (replyComment != null) {
                        comment.setContent("<div class=\"reply\">" + replyComment.getName() + ":<br/>"
                                + replyComment.getContent() + "</div>" + comment.getContent());
                    }
                }
                comment.setIp(request.getRemoteAddr());
                comment.setCreateDate(new Date());
                comment.setDelFlag(Comment.DEL_FLAG_AUDIT);
                commentService.save(comment);
                return "{result:1, message:'提交成功。'}";
            } else {
                return "{result:2, message:'验证码不正确。'}";
            }
        } else {
            return "{result:2, message:'验证码不能为空。'}";
        }
    }

//	/**
//	 * 站点地图
//	 */
//	@RequestMapping(value = "map-{siteId}")
//	public String map(@PathVariable Long siteId, Model model) {
//		Site site = CmsUtils2.getSite(siteId!=null?siteId:Site.defaultSiteId());
//		model.addAttribute("site", site);
//		return "front/cms/themes/"+site.getTheme()+"/frontMap";
//	}

    private String getTpl(Article article) {
        if (StringUtils.isBlank(article.getCustomContentView())) {
            String view = null;
            Category c = article.getCategory();
            boolean goon = true;
            do {
                if (StringUtils.isNotBlank(c.getCustomContentView())) {
                    view = c.getCustomContentView();
                    goon = false;
                } else if (c.getParent() == null || c.getParent().isRoot()) {
                    goon = false;
                } else {
                    c = c.getParent();
                }
            } while (goon);
            return StringUtils.isBlank(view) ? Article.DEFAULT_TEMPLATE : view;
        } else {
            return article.getCustomContentView();
        }
    }

}

