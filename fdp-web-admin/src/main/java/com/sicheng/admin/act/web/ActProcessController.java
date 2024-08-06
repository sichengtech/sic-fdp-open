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
package com.sicheng.admin.act.web;

import com.sicheng.admin.act.service.ActProcessService;
import com.sicheng.admin.sys.service.MenuService;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.utils.StringUtils;
import com.sicheng.common.web.BaseController;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 流程定义相关Controller
 * @author zhaolei
 */
@Controller
@RequestMapping(value = "${adminPath}/act/process")
public class ActProcessController extends BaseController {

	@Autowired
	private ActProcessService actProcessService;
	@Autowired
	private MenuService sysMenuService;
	

	/**
	 * 流程定义列表
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = {"list", ""})
	public String processList(String category, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*
		 * 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署）
		 */
		Page<Object[]> page = actProcessService.processList(new Page<Object[]>(request, response), category);
		model.addAttribute("page", page);
		model.addAttribute("category", category);
		return "admin/act/actProcessList";
	}
	
	/**
	 * 运行中的实例列表
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "running")
	public String runningList(String procInsId, String procDefKey, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProcessInstance> page = actProcessService.runningList(new Page<ProcessInstance>(request, response), procInsId, procDefKey);
		model.addAttribute("page", page);
		model.addAttribute("procInsId", procInsId);
		model.addAttribute("procDefKey", procDefKey);
		return "admin/act/actProcessRunningList";
	}

	/**
	 * 读取资源，通过部署ID
	 * @param processDefinitionId  流程定义ID
	 * @param processInstanceId 流程实例ID
	 * @param resourceType 资源类型(xml|image)
	 * @param response
	 * @throws Exception
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "resource/read")
	public void resourceRead(String procDefId, String proInsId, String resType, HttpServletResponse response) throws Exception {
		InputStream resourceAsStream = actProcessService.resourceRead(procDefId, proInsId, resType);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 部署流程
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "/deploy", method=RequestMethod.GET)
	public String deploy(Model model) {
		return "admin/act/actProcessDeploy";
	}
	
	/**
	 * 部署流程 - 保存
	 * @param file
	 * @return
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "/deploy", method=RequestMethod.POST)
	public String deploy(@Value("#{APP_PROP['activiti.export.diagram.path']}") String exportDir, 
			String category, MultipartFile file, RedirectAttributes redirectAttributes) {

		String fileName = file.getOriginalFilename();
		
		if (StringUtils.isBlank(fileName)){
			redirectAttributes.addFlashAttribute("message", "请选择要部署的流程文件");
		}else{
			String message = actProcessService.deploy(exportDir, category, file);
			redirectAttributes.addFlashAttribute("message", message);
		}

		return "redirect:" + adminPath + "/act/process.do";
	}
	
	/**
	 * 设置流程分类
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "updateCategory")
	public String updateCategory(String procDefId, String category, RedirectAttributes redirectAttributes) {
		actProcessService.updateCategory(procDefId, category);
		return "redirect:" + adminPath + "/act/process.do";
	}

	/**
	 * 挂起、激活流程实例
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "update/{state}")
	public String updateState(@PathVariable("state") String state, String procDefId, RedirectAttributes redirectAttributes) {
		String message = actProcessService.updateState(state, procDefId);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:" + adminPath + "/act/process.do";
	}
	
	/**
	 * 将部署的流程转换为模型
	 * @param procDefId
	 * @param redirectAttributes
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws XMLStreamException
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "convert/toModel")
	public String convertToModel(String procDefId, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, XMLStreamException {
		org.activiti.engine.repository.Model modelData = actProcessService.convertToModel(procDefId);
		redirectAttributes.addFlashAttribute("message", "转换模型成功，模型ID="+modelData.getId());
		return "redirect:" + adminPath + "/act/model.do";
	}
	
	/**
	 * 导出图片文件到硬盘
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "export/diagrams")
	@ResponseBody
	public List<String> exportDiagrams(@Value("#{APP_PROP['activiti.export.diagram.path']}") String exportDir) throws IOException {
		List<String> files = actProcessService.exportDiagrams(exportDir);;
		return files;
	}

	/**
	 * 删除部署的流程，级联删除流程实例
	 * @param deploymentId 流程部署ID
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "delete")
	public String delete(String deploymentId) {
		actProcessService.deleteDeployment(deploymentId);
		return "redirect:" + adminPath + "/act/process.do";
	}
	
	/**
	 * 删除流程实例
	 * @param procInsId 流程实例ID
	 * @param reason 删除原因
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "deleteProcIns")
	public String deleteProcIns(String procInsId, String reason, RedirectAttributes redirectAttributes) {
		if (StringUtils.isBlank(reason)){
			addMessage(redirectAttributes, "请填写删除原因");
		}else{
			actProcessService.deleteProcIns(procInsId, reason);
			addMessage(redirectAttributes, "删除流程实例成功，实例ID=" + procInsId);
		}
		return "redirect:" + adminPath + "/act/process/running.do";
	}
	
}
