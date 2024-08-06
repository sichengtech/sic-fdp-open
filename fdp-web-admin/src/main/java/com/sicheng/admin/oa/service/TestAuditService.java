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
package com.sicheng.admin.oa.service;

import com.google.common.collect.Maps;
import com.sicheng.admin.act.service.ActTaskService;
import com.sicheng.admin.act.utils.ActUtils;
import com.sicheng.admin.oa.dao.TestAuditDao;
import com.sicheng.admin.oa.entity.TestAudit;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.persistence.wrapper.Wrapper;
import com.sicheng.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 审批Service
 * @author zhaolei
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class TestAuditService extends CrudService<TestAuditDao, TestAudit> {

	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private TestAuditDao testAuditDao;
	
	public TestAudit getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}
	
	public Page<TestAudit> findPage(Page<TestAudit> page, TestAudit testAudit) {
		Wrapper w=new Wrapper(testAudit);
		w.orderBy("a.id DESC");
		page.setList(testAuditDao.selectByWhere(page,w));
		return page;
	}
	
	/**
	 * 审核新增或编辑
	 * @param testAudit
	 */
	@Transactional(readOnly = false)
	public void save(TestAudit testAudit) {
		// 申请发起
		if (testAudit.getId()==null){
			testAudit.preInsert();
			dao.insert(testAudit);
			// 启动流程
			actTaskService.startProcess(ActUtils.PD_TEST_AUDIT[0], ActUtils.PD_TEST_AUDIT[1], testAudit.getId(), testAudit.getContent());
		}
		
		// 重新编辑申请		
		else{
			testAudit.preUpdate();
			dao.updateByWhere(testAudit,new Wrapper(testAudit));
			testAudit.getAct().setComment(("yes".equals(testAudit.getAct().getFlag())?"[重申] ":"[销毁] ")+testAudit.getAct().getComment());
			// 完成流程任务
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(testAudit.getAct().getFlag())? "1" : "0");
			actTaskService.complete(testAudit.getAct().getTaskId(), testAudit.getAct().getProcInsId(), testAudit.getAct().getComment(), testAudit.getContent(), vars);
		}
	}

	/**
	 * 审核审批保存
	 * @param testAudit
	 */
	@Transactional(readOnly = false)
	public void auditSave(TestAudit testAudit) {
		
		// 设置意见
		testAudit.getAct().setComment(("yes".equals(testAudit.getAct().getFlag())?"[同意] ":"[驳回] ")+testAudit.getAct().getComment());
		testAudit.preUpdate();
		
		// 对不同环节的业务逻辑进行操作
		String taskDefKey = testAudit.getAct().getTaskDefKey();

		// 审核环节
		if ("audit".equals(taskDefKey)){
			
		}
		else if ("audit2".equals(taskDefKey)){
			testAudit.setHrText(testAudit.getAct().getComment());
			dao.updateHrText(testAudit);
		}
		else if ("audit3".equals(taskDefKey)){
			testAudit.setLeadText(testAudit.getAct().getComment());
			dao.updateLeadText(testAudit);
		}
		else if ("audit4".equals(taskDefKey)){
			testAudit.setMainLeadText(testAudit.getAct().getComment());
			dao.updateMainLeadText(testAudit);
		}
		else if ("apply_end".equals(taskDefKey)){
			
		}
		// 未知环节，直接返回
		else{
			return;
		}
		
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(testAudit.getAct().getFlag())? "1" : "0");
		actTaskService.complete(testAudit.getAct().getTaskId(), testAudit.getAct().getProcInsId(), testAudit.getAct().getComment(), vars);
		
	}
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public TestAudit get(Long id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param testAudit 实体
	 * @return
	 */
	public TestAudit get(TestAudit testAudit) {
		return dao.get(testAudit);
	}
	
}
