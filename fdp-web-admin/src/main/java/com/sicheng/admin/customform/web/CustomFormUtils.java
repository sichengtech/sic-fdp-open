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
package com.sicheng.admin.customform.web;

import com.sicheng.admin.customform.entity.Slot;
import com.sicheng.common.mapper.JaxbMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <p>标题:自定义表单工具类 </p>
 * <p>描述: </p>
 * <p>公司: 思程科技 www.sicheng.net</p>
 * @author zhaolei
 * @date 2016年5月26日 下午3:38:30
 */
public class CustomFormUtils {

	private static Logger logger = LoggerFactory.getLogger(CustomFormUtils.class);

	
	/**
	 * XML文件转换为对象
	 * @param fileName
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fileToObject(String fileName, Class<?> clazz){
		
		try {
//			String pathName = "/templates/modules/gen/" + fileName;
			String pathName =  fileName;
//			logger.debug("File to object: {}", pathName);
			Resource resource = new ClassPathResource(pathName); 
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder sb = new StringBuilder();  
			while (true) {
				String line = br.readLine();
				if (line == null){
					break;
				}
				sb.append(line).append("\r\n");
			}
			if (is != null) {
				is.close();
			}
			if (br != null) {
				br.close();
			}
//			logger.debug("Read file content: {}", sb.toString());
			return (T) JaxbMapper.fromXml(sb.toString(), clazz);
		} catch (IOException e) {
			logger.warn("Error file convert: {}", e.getMessage());
		}
		return null;
	}
	
	/**
	 * 获取代码生成配置对象
	 * @return
	 */
	public static Slot getFormMetadata(){
		return fileToObject("/form-slot.xml", Slot.class);
	}

//	/**
//	 * 生成到文件
//	 * @param tpl
//	 * @param model
//	 * @param replaceFile
//	 * @return
//	 */
//	public static String generateToFile(GenTemplate tpl, Map<String, Object> model, boolean isReplaceFile){
//		// 获取生成文件
//		String fileName = Global.getProjectPath() + File.separator
//				+ StringUtils.replaceEach(FreeMarkers.renderString(tpl.getFilePath() + "/", model),
//						new String[]{"//", "/", "."}, new String[]{File.separator, File.separator, File.separator})
//				+ FreeMarkers.renderString(tpl.getFileName(), model);
//		logger.debug(" fileName === " + fileName);
//
//		// 获取生成文件内容
//		String content = FreeMarkers.renderString(StringUtils.trimToEmpty(tpl.getContent()), model);
//		logger.debug(" content === \r\n" + content);
//
//		// 如果选择替换文件，则删除原文件
//		if (isReplaceFile){
//			FileUtils.deleteFile(fileName);
//		}
//
//		// 创建并写入文件
//		if (FileUtils.createFile(fileName)){
//			FileUtils.writeToFile(fileName, content, true);
//			logger.debug(" file create === " + fileName);
//			return "生成成功："+fileName+"<br/>";
//		}else{
//			logger.debug(" file extents === " + fileName);
//			return "文件已存在："+fileName+"<br/>";
//		}
//	}
	
	public static void main(String[] args) {
		try {
			Slot slot = CustomFormUtils.getFormMetadata();
			System.out.println(slot);
			System.out.println(JaxbMapper.toXml(slot));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
