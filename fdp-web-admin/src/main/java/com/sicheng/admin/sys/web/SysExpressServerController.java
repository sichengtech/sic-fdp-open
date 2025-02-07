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

import com.alibaba.fastjson.JSON;
import com.sicheng.admin.sys.entity.SysExpressServer;
import com.sicheng.admin.sys.service.SysExpressServerService;
import com.sicheng.common.express.KdniaoTrackQuery;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理快递接口的配置 Controller
 * 所属模块：sys 
 * @author zjl
 * @version 2017-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysExpressServer")
public class SysExpressServerController extends BaseController {

	@Autowired
	private SysExpressServerService sysExpressServerService;

	/**
	 * 进入列表页
	 * @param sysExpressServer 实体对象
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:sysExpressServer:view")
	@RequestMapping(value = "list")
	public String list(SysExpressServer sysExpressServer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysExpressServer> page = sysExpressServerService.selectByWhere(new Page<SysExpressServer>(request, response), new Wrapper(sysExpressServer)); 
		model.addAttribute("page", page);
		return "admin/sys/sysExpressServerList";
	}

	/**
	 * 进入保存页面
	 * @param sysExpressServer 实体对象
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:sysExpressServer:edit")
	@RequestMapping(value = "save1")
	public String save1(SysExpressServer sysExpressServer, Model model) {
		if (sysExpressServer == null){
			sysExpressServer = new SysExpressServer();
		}
		model.addAttribute("sysExpressServer", sysExpressServer);
		return "admin/sys/sysExpressServerForm";
	}

	/**
	 * 执行保存
	 * @param sysExpressServer 实体对象
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:sysExpressServer:edit")
	@RequestMapping(value = "save2")
	public String save2(SysExpressServer sysExpressServer, Model model, RedirectAttributes redirectAttributes) {
		//表单验证
		List<String> errorList=validate(sysExpressServer, model);
		if(errorList.size()>0){
			errorList.add(0, "数据验证失败：");
			addMessage(model, errorList.toArray(new String[]{}));
			return save1(sysExpressServer, model);//回显错误提示
		}
		
		//业务处理
		sysExpressServerService.insertSelective(sysExpressServer);
		addMessage(redirectAttributes, "保存快递通道成功");
		return "redirect:"+adminPath+"/sys/sysExpressServer/edit1.do?repage";
	}

	/**
	 * 进入编辑页面
	 * @param sysExpressServer 实体对象
	 * @param model
	 * @return
	 */	
	@RequiresPermissions("sys:sysExpressServer:edit")
	@RequestMapping(value = "edit1")
	public String edit1(SysExpressServer sysExpressServer, Model model) {
		SysExpressServer entity = null;
		if(sysExpressServer!=null){
			if (sysExpressServer.getId()!=null){
				entity = sysExpressServerService.selectById(sysExpressServer.getId());
			}
		}
		if(entity==null){
			//从库中查出ID最小的一个
			entity= sysExpressServerService.selectExpressSet();
		}
		//初始化一条数据
		if(entity==null){
			//向表中插入一条记录
			entity=new SysExpressServer();
			entity.setStatus("1");//状态，0停用，1启用
			entity.setEbusinessId("1");//电商id
			entity.setAppkey("1");//电商加密私钥
			entity.setUrl("1");//请求url
			sysExpressServerService.insertSelective(entity);
		}
		model.addAttribute("sysExpressServer", entity);
		return "admin/sys/sysExpressServerForm";
	}

	/**
	 * 执行编辑
	 * @param sysExpressServer 实体对象
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:sysExpressServer:edit")
	@RequestMapping(value = "edit2")
	public String edit2(SysExpressServer sysExpressServer, Model model, RedirectAttributes redirectAttributes) {
		//表单验证
		List<String> errorList=validate(sysExpressServer, model);
		if(errorList.size()>0){
			errorList.add(0, "数据验证失败：");
			addMessage(model, errorList.toArray(new String[]{}));
			return edit1(sysExpressServer, model);//回显错误提示
		}		
		
		//业务处理
		sysExpressServerService.updateByIdSelective(sysExpressServer);
		addMessage(redirectAttributes, "编辑快递通道成功");
		return "redirect:"+adminPath+"/sys/sysExpressServer/edit1.do?repage";
	}	

	/**
	 * 删除
	 * @param sysExpressServer 实体对象
	 * @param redirectAttributes
	 * @return
	 */	
	@RequiresPermissions("sys:sysExpressServer:edit")
	@RequestMapping(value = "delete")
	public String delete(SysExpressServer sysExpressServer, RedirectAttributes redirectAttributes) {
		sysExpressServerService.deleteById(sysExpressServer.getId());
		addMessage(redirectAttributes, "删除快递通道成功");
		return "redirect:"+adminPath+"/sys/sysExpressServer/list.do?repage";
	}

	/**
	 * 表单验证
	 * @param sysExpressServer 实体对象
	 * @param model 
	 * @return List<String> 错误提示信息
	 */
	private List<String> validate(SysExpressServer sysExpressServer, Model model){
		List<String> errorList = new ArrayList<String>();
		if(StringUtils.isBlank(R.get("status"))){
			errorList.add("请选择开关");
		}
		if(StringUtils.isNotBlank(R.get("status")) && R.get("status").length() > 1){
			errorList.add("状态最大长度不能超过1位");
		}
		if(StringUtils.isBlank(R.get("ebusinessId"))){
			errorList.add("电商id不能为空");
		}
		if(StringUtils.isNotBlank(R.get("ebusinessId")) && R.get("ebusinessId").length() > 64){
			errorList.add("电商id最大长度不能超过64位");
		}
		if(StringUtils.isBlank(R.get("appkey"))){
			errorList.add("电商加密私钥不能为空");
		}
		if(StringUtils.isNotBlank(R.get("appkey")) && R.get("appkey").length() > 64){
			errorList.add("电商加密私钥最大长度不能超过64位");
		}
		if(StringUtils.isBlank(R.get("url"))){
			errorList.add("请求url不能为空");
		}
		if(StringUtils.isNotBlank(R.get("url")) && R.get("url").length() > 128){
			errorList.add("请求url最大长度不能超过128位");
		}
		return errorList;
	}

	
	/**
	 * 快递测试 
	 */
	@ResponseBody
	@RequestMapping(value = "expressTest")
	private Map<String,String> expressTest(String companyNumber,String expressNumber) {
		String result="";
		KdniaoTrackQuery api = new KdniaoTrackQuery();
		Map<String,String> map=new HashMap<>();
		//返回来的json格式物流信息
		try {
			result=api.getOrderTracesByJson(companyNumber, expressNumber);
			System.out.println(result);
			//把json转为map
			Map<String,Object> resultMap = JSON.parseObject(result);
			if((boolean) resultMap.get("Success")){
				map.put("status", "1");
			}else{
				map.put("status", "0");
			}
		} catch (Exception e) {
			map.put("status", "0");
			logger.error("物流查询出现错误：",e);
		}
		return map;
	}
}