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

package com.sicheng.wap.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        //添加授权token列
        //对于接口验证来说通常需要个token并且放在header里面，这里我们直接在swagger上增加一个显示的token，只需要在build之前增加一个header参数
        List<Parameter> pars = new ArrayList<>();
        //添加授权token
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("token").description("授权token 注：登录不需要填，只有post方式的接口必填").
                modelRef(new ModelRef("string")).
                parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//调用下面apiInfo()方法
                .select()
                //Controller所在路径
//                .apis(RequestHandlerSelectors.basePackage("com.sicheng.front"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))  //只显示添加@Api注解的类

                //过滤默认错误api
                //由于springmvc封装有错误的controller，因此swagger也会把这个展示出来，因为是扫描的所有controller来展示swagger文档的，
                // 故此我们需要屏蔽这些对于对接方没用的接口；
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);

    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("中欧班列APP接口API")
                .description("API接口")   //描述
                .termsOfServiceUrl("http://localhost:8079/xzs/")
                .version("2.0.1")
                .license("中欧班列接口文档")   //链接名称
                //.licenseUrl("http://localhost:8084/app/swagger-ui.html")   //链接地址
                .build();

    }

}