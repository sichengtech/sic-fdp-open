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

import com.sicheng.common.web.BaseController;
import com.sicheng.front.cms.utils.WiexinSignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试Controller
 *
 * @author zhaolei
 * @version 2014-02-28
 */
@Controller
@RequestMapping(value = "${frontPath}/weixin")
public class WeixinController extends BaseController {

    /**
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机数
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String get(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {

        System.out.println("=============================================== get start");
        for (Object o : request.getParameterMap().keySet()) {
            System.out.println(o + " = " + request.getParameter((String) o));
        }
        System.out.println("=============================================== get end");

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (WiexinSignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }

        return "";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public String post(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
        System.out.println("=============================================== post start");
        for (Object o : request.getParameterMap().keySet()) {
            System.out.println(o + " = " + request.getParameter((String) o));
        }
        System.out.println("=============================================== post end");
        StringBuilder result = new StringBuilder();
        result.append("<xml>" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>" +
                "<CreateTime>12345678</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[你好]]></Content>" +
                "</xml>");
        return result.toString();
    }


}
