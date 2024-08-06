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
package com.sicheng.admin.act.utils;

import com.sicheng.admin.act.entity.Act;
import com.sicheng.admin.sys.entity.Role;
import com.sicheng.admin.sys.entity.User;
import com.sicheng.common.config.Global;
import com.sicheng.common.utils.Encodes;
import com.sicheng.common.utils.StringUtils;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程工具
 * @author zhaolei
 * @version 2013-11-03
 */
public class ActUtils {

//	private static Logger logger = LoggerFactory.getLogger(ActUtils.class);
	
	/**
	 * 定义流程定义KEY，必须以“PD_”开头
	 * 组成结构：string[]{"流程标识","业务主表表名"}
	 */
	public static final String[] PD_LEAVE = new String[]{"leave", "oa_leave"};
	public static final String[] PD_TEST_AUDIT = new String[]{"test_audit", "oa_test_audit"};
	
//	/**
//	 * 流程定义Map（自动初始化）
//	 */
//	private static Map<String, String> procDefMap = new HashMap<String, String>() {
//		private static final long serialVersionUID = 1L;
//		{
//			for (Field field : ActUtils.class.getFields()){
//				if(StringUtils.startsWith(field.getName(), "PD_")){
//					try{
//						String[] ss = (String[])field.get(null);
//						put(ss[0], ss[1]);
//					}catch (Exception e) {
//						logger.debug("load pd error: {}", field.getName());
//					}
//				}
//			}
//		}
//	};
//	
//	/**
//	 * 获取流程执行（办理）URL
//	 * @param procId
//	 * @return
//	 */
//	public static String getProcExeUrl(String procId) {
//		String url = procDefMap.get(StringUtils.split(procId, ":")[0]);
//		if (StringUtils.isBlank(url)){
//			return "404";
//		}
//		return url;
//	}
	
//	@SuppressWarnings({ "unused" })
//	public static Map<String, Object> getMobileEntity(Object entity,String spiltType){
//		if(spiltType==null){
//			spiltType="@";
//		}
//		Map<String, Object> map = Maps.newHashMap();
//
//		List<String> field = Lists.newArrayList();
//		List<String> value = Lists.newArrayList();
//		List<String> chinesName =Lists.newArrayList();
//
//		try{
//			for (Method m : entity.getClass().getMethods()){
//				if (m.getAnnotation(JsonIgnore.class) == null && m.getAnnotation(JsonBackReference.class) == null && m.getName().startsWith("get")){
//					if (m.isAnnotationPresent(FieldName.class)) {
//						Annotation p = m.getAnnotation(FieldName.class);
//						FieldName fieldName=(FieldName) p;
//						chinesName.add(fieldName.value());
//					}else{
//						chinesName.add("");
//					}
//					if (m.getName().equals("getAct")){
//						Object act = m.invoke(entity, new Object[]{});
//						Method actMet = act.getClass().getMethod("getTaskId");
//						map.put("taskId", ObjectUtils.toString(m.invoke(act, new Object[]{}), ""));
//					}else{
//						field.add(StringUtils.uncapitalize(m.getName().substring(3)));
//						value.add(ObjectUtils.toString(m.invoke(entity, new Object[]{}), ""));
//					}
//				}
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		map.put("beanTitles", StringUtils.join(field, spiltType));
//		map.put("beanInfos", StringUtils.join(value, spiltType));
//		map.put("chineseNames", StringUtils.join(chinesName, spiltType));
//
//		return map;
//	}
	
	/**
	 * 获取流程表单URL
	 * @param formKey
	 * @param act 表单传递参数
	 * @return
	 */
	public static String getFormUrl(String formKey, Act act){
		
		StringBuilder formUrl = new StringBuilder();
		
		String formServerUrl = Global.getConfig("activiti.form.server.url");
		if (StringUtils.isBlank(formServerUrl)){
			formUrl.append(Global.getAdminPath());
		}else{
			formUrl.append(formServerUrl);
		}
		
		formUrl.append(formKey).append(formUrl.indexOf("?") == -1 ? "?" : "&");
		formUrl.append("act.taskId=").append(act.getTaskId() != null ? act.getTaskId() : "");
		formUrl.append("&act.taskName=").append(act.getTaskName() != null ? Encodes.urlEncode(act.getTaskName()) : "");
		formUrl.append("&act.taskDefKey=").append(act.getTaskDefKey() != null ? act.getTaskDefKey() : "");
		formUrl.append("&act.procInsId=").append(act.getProcInsId() != null ? act.getProcInsId() : "");
		formUrl.append("&act.procDefId=").append(act.getProcDefId() != null ? act.getProcDefId() : "");
		formUrl.append("&act.status=").append(act.getStatus() != null ? act.getStatus() : "");
		formUrl.append("&id=").append(act.getBusinessId() != null ? act.getBusinessId() : "");
		
		return formUrl.toString();
	}
	
	/**
	 * 转换流程节点类型为中文说明
	 * @param type 英文名称
	 * @return 翻译后的中文名称
	 */
	public static String parseToZhType(String type) {
		Map<String, String> types = new HashMap<String, String>();
		types.put("userTask", "用户任务");
		types.put("serviceTask", "系统任务");
		types.put("startEvent", "开始节点");
		types.put("endEvent", "结束节点");
		types.put("exclusiveGateway", "条件判断节点(系统自动根据条件处理)");
		types.put("inclusiveGateway", "并行处理任务");
		types.put("callActivity", "子流程");
		return types.get(type) == null ? type : types.get(type);
	}

	public static UserEntity toActivitiUser(User user){
		if (user == null){
			return null;
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getLoginName());
		userEntity.setFirstName(user.getName());
		userEntity.setLastName(StringUtils.EMPTY);
		userEntity.setPassword(user.getPassword());
		userEntity.setEmail(user.getEmail());
		userEntity.setRevision(1);
		return userEntity;
	}
	
	public static GroupEntity toActivitiGroup(Role role){
		if (role == null){
			return null;
		}
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setId(role.getEnname());
		groupEntity.setName(role.getName());
		groupEntity.setType(role.getRoleType());
		groupEntity.setRevision(1);
		return groupEntity;
	}
	
//	public static void main(String[] args) {
//		 User user = new User();
//		 System.out.println(getMobileEntity(user, "@"));
//	}
}
