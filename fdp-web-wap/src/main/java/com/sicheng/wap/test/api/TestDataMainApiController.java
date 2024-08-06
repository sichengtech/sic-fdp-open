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
package com.sicheng.wap.test.api;

import com.sicheng.admin.test.entity.TestDataMain;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.persistence.wrapper.Wrapper;
import com.sicheng.common.utils.StringUtils;
import com.sicheng.common.utils4m.ApiUtils;
import com.sicheng.common.utils4m.AppDataUtils;
import com.sicheng.common.web.BaseController;
import com.sicheng.common.web.R;
import com.sicheng.wap.test.service.TestDataMainService;
import com.sicheng.wap.test.vo.TestDataMainVo;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务数据表管理 Api Controller
 * 所属模块：test
 * @author zhaolei
 * @version 2022-04-12
 */
@RestController
@RequestMapping(value = "${wapPath}/api")
@Api(tags = "TestDataMainApiController-业务数据表Api接口")
public class TestDataMainApiController extends BaseController {
    @Autowired
    private TestDataMainService testDataMainService;

    /**
     * 根据id查询业务数据表
     * @return TestDataMain 实体对象
     */
    @RequestMapping(value = "/testDataMain/one.htm", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "根据id查询业务数据表", notes = "根据id查询业务数据表，返回一个TestDataMain 实体对象结构的数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "服务器成功返回用户请求的数据"),
            @ApiResponse(code = 400, message = "用户发出的请求有错误，具体错误信息在msg中，例如：访问登录接口时失败，可能是用户不存在或密码不正确"),
            @ApiResponse(code = 401, message = "访问业务接口时未登录，返回401，告知客户端去登录"),
            @ApiResponse(code = 403, message = "无访问权限"),
            @ApiResponse(code = 404, message = "用户发出的请求针对的是不存在的记录"),
            @ApiResponse(code = 406, message = "用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）"),
            @ApiResponse(code = 500, message = "服务器发生错误"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "int", paramType="query" ),
    })
    public AppDataUtils.AppData<TestDataMain> one() {
        try {
            // 取出参数
            String id = R.get("id");//主键id

            // 检查参数
            List<String> errorList = new ArrayList<>();
            if (StringUtils.isBlank(id)) {
                errorList.add("id不可为空");
            }
            if (StringUtils.isNotBlank(id) && !StringUtils.isNumeric(id)) {
                errorList.add("id必须是数字");
            }
            String message = ApiUtils.errorMessage(errorList);
            if (!errorList.isEmpty()) {
                return AppDataUtils.getAppData(AppDataUtils.STATUS_INVALID, message, null, null);
            }

            // 处理业务
            TestDataMain entity = testDataMainService.selectById(Long.valueOf(id));
            message = "根据id查询业务数据表成功";
            return AppDataUtils.getAppData(AppDataUtils.STATUS_OK, message, entity, null);
        } catch (Exception e) {
            // 处理异常
            String errMsg = "根据id查询业务数据表遇到错误:";
            logger.error(errMsg, e);
            return AppDataUtils.getAppData(AppDataUtils.STATUS_SERVER_ERROR, errMsg + e.getMessage(), null, null);
        }
    }


    /**
     * 根据ids查询业务数据表
     *
     * @return 返回一个数组，数据内是TestDataMain实体对象结构的数据
     */
    @RequestMapping(value = "/testDataMain/listByIdIn.htm", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "根据多个主键id查询业务数据表", notes = "根据多个主键id查询业务数据表，返回一个数组，数组内是TestDataMain实体对象结构的数据")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "服务器成功返回用户请求的数据"),
            @ApiResponse(code = 400, message = "用户发出的请求有错误，具体错误信息在msg中，例如：访问登录接口时失败，可能是用户不存在或密码不正确"),
            @ApiResponse(code = 401, message = "访问业务接口时未登录，返回401，告知客户端去登录"),
            @ApiResponse(code = 403, message = "无访问权限"),
            @ApiResponse(code = 404, message = "用户发出的请求针对的是不存在的记录"),
            @ApiResponse(code = 406, message = "用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）"),
            @ApiResponse(code = 500, message = "服务器发生错误"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "ids", value = "多个主键id用逗号隔开", required = true, dataType = "string", paramType = "query"),
    })
    public AppDataUtils.AppData<TestDataMain> listByIdIn() {
        try {
            // 取出参数
            String ids = R.get("ids");//多个主键id用逗号隔开

            // 检查参数
            List<String> errorList = new ArrayList<>();
            if (StringUtils.isBlank(ids)) {
                errorList.add("ids不可为空");
            }
            String[] arr=ids.split(",| |，");
            if (arr.length==0) {
                errorList.add("id不可为空");
            }
            for(String id:arr){
                if (StringUtils.isNotBlank(id) && !StringUtils.isNumeric(id)) {
                    errorList.add("id必须是数字");
                }
            }
            String message = ApiUtils.errorMessage(errorList);
            if (!errorList.isEmpty()) {
                return AppDataUtils.getAppData(AppDataUtils.STATUS_INVALID, message, null, null);
            }

            // 处理业务
            List<Long> idsList=new ArrayList<>();
            for(String id:arr){
                idsList.add(Long.valueOf(id));
            }
            List<TestDataMain> entityList = testDataMainService.selectByIdIn(idsList);
            message = "根据ids查询业务数据表";
            return AppDataUtils.getAppData(AppDataUtils.STATUS_OK, message, entityList, null);
        } catch (Exception e) {
            // 处理异常
            String errMsg = "根据ids查询业务数据表遇到错误:";
            logger.error(errMsg, e);
            return AppDataUtils.getAppData(AppDataUtils.STATUS_SERVER_ERROR, errMsg + e.getMessage(), null, null);
        }
    }

    /**
     * 查询业务数据表列表(不分页)
     *
     * @return TestDataMain 实体对象
     */
    @RequestMapping(value = "/testDataMain/list.htm", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "查询业务数据表列表(不分页)", notes = "查询业务数据表列表(不分页)，当你不需要分页时请使用本接口。limit参数用于限制返回条数，limit默认值是10条。请使用sortStr参数控制排序")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "服务器成功返回用户请求的数据"),
            @ApiResponse(code = 400, message = "用户发出的请求有错误，具体错误信息在msg中，例如：访问登录接口时失败，可能是用户不存在或密码不正确"),
            @ApiResponse(code = 401, message = "访问业务接口时未登录，返回401，告知客户端去登录"),
            @ApiResponse(code = 403, message = "无访问权限"),
            @ApiResponse(code = 404, message = "用户发出的请求针对的是不存在的记录"),
            @ApiResponse(code = 406, message = "用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）"),
            @ApiResponse(code = 500, message = "服务器发生错误"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "limit", value = "取前n条数据", defaultValue = "10", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sortStr", value = "综合排序条件,sortStr单独使用,优先级高于sort和sortMode,示例:\"id desc,name asc,type desc\"", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "单个排序字段,sort和sortMode要配套使用(因只支持一个字段淘汰但可用)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sortMode", value = "单个排序模式,sort和sortMode要配套使用(因只支持一个字段淘汰但可用)", required = false, dataType = "string", paramType = "query", allowableValues = "ASC,DESC"),
    })
    public AppDataUtils.AppData<TestDataMain> list(TestDataMainVo testDataMainVo) {
        try {
            // 取出参数
            Integer limit = R.getInteger("limit", ApiUtils.LIMIT_DEFAULT);//取前n条数据
            String sortStr = R.get("sortStr"); //综合排序条件
            String sort = R.get("sort"); //排序字段
            String sortMode = R.get("sortMode"); //排序模式
            TestDataMain testDataMain=new TestDataMain();//PO实体对象
            BeanUtils.copyProperties(testDataMainVo,testDataMain);//copy属性值

            // 检查参数
            List<String> errorList = validate(testDataMainVo);
            if (limit == null) {
                errorList.add("limit不可为空");
            }
            String message = ApiUtils.errorMessage(errorList);
            if (!errorList.isEmpty()) {
                return AppDataUtils.getAppData(AppDataUtils.STATUS_INVALID, message, null, null);
            }

            // 处理：查询条件、排序
            Wrapper wrapper = new Wrapper(testDataMain);//Wrapper是查询条件拼装工具
            wrapper.orderBy(sortStr,sort,sortMode);//排序条件

            // 处理业务
            List<TestDataMain> list = testDataMainService.selectByWhere(wrapper);
            message = "查询业务数据表列表成功";
            return AppDataUtils.getAppData(AppDataUtils.STATUS_OK, message, list, null);
        } catch (Exception e) {
            // 处理异常
            String errMsg = "查询业务数据表列表错误:";
            logger.error(errMsg, e);
            return AppDataUtils.getAppData(AppDataUtils.STATUS_SERVER_ERROR, errMsg + e.getMessage(), null, null);
        }
    }

    /**
     * 查询业务数据表列表(带分页)
     *
     * @return
     */
    @RequestMapping(value = "/testDataMain/page.htm", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "查询业务数据表列表(带分页)", notes = "查询业务数据表列表(带分页)，需要分页时请使用本接口。使用pageSize、pageNo控制分页，请使用sortStr参数控制排序")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "服务器成功返回用户请求的数据"),
            @ApiResponse(code = 400, message = "用户发出的请求有错误，具体错误信息在msg中，例如：访问登录接口时失败，可能是用户不存在或密码不正确"),
            @ApiResponse(code = 401, message = "访问业务接口时未登录，返回401，告知客户端去登录"),
            @ApiResponse(code = 403, message = "无访问权限"),
            @ApiResponse(code = 404, message = "用户发出的请求针对的是不存在的记录"),
            @ApiResponse(code = 406, message = "用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）"),
            @ApiResponse(code = 500, message = "服务器发生错误"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageSize", value = "页大小", defaultValue = "10", required = false, dataType = "int",paramType="query"),
            @ApiImplicitParam(name = "pageNo", value = "页码", defaultValue = "1", required = false, dataType = "int",paramType="query"),
            @ApiImplicitParam(name = "sort", value = "排序字段", required = false, dataType = "string",paramType="query"),
            @ApiImplicitParam(name = "sortMode", value = "排序模式", required = false, dataType = "string",paramType="query"),
    })
    public AppDataUtils.AppData<TestDataMain> page(TestDataMainVo testDataMainVo) {
        try {
            // 取出参数
            Integer pageSize = R.getInteger("pageSize",20);//页大小
            Integer pageNo = R.getInteger("pageNo", 1);//页码
            String sortStr = R.get("sortStr"); //综合排序条件
            String sort = R.get("sort"); //排序字段
            String sortMode = R.get("sortMode"); //排序模式
            TestDataMain testDataMain=new TestDataMain();//PO实体对象
            BeanUtils.copyProperties(testDataMainVo,testDataMain);//copy属性值

            // 检查参数
            List<String> errorList = validate(testDataMainVo);
            if (pageSize==null) {
                errorList.add("pageSize不可为空");
            }
            if (pageNo==null) {
                errorList.add("pageNo不可为空");
            }
            String message = ApiUtils.errorMessage(errorList);
            if (!errorList.isEmpty()) {
                return AppDataUtils.getAppData(AppDataUtils.STATUS_INVALID, message, null, null);
            }

            // 处理：查询条件、排序、分页
            Wrapper wrapper = new Wrapper(testDataMain);//Wrapper是查询条件拼装工具
            wrapper.orderBy(sortStr,sort,sortMode);//排序条件
            Page<TestDataMain> page = new Page(pageNo, pageSize);//分页

            // 执行分页查询
            page = testDataMainService.selectByWhere(page, new Wrapper(testDataMain));
            message = "分页查询业务数据表列表成功";
            return AppDataUtils.getAppData(AppDataUtils.STATUS_OK, message, page.getList(), page);
        } catch (Exception e) {
            // 处理异常
            String errMsg = "分页查询业务数据表列表错误:";
            logger.error(errMsg, e);
            return AppDataUtils.getAppData(AppDataUtils.STATUS_SERVER_ERROR, errMsg + e.getMessage(), null, null);
        }
    }

    /**
     * 保存业务数据表
     *
     * @param testDataMainVo vo对象
     * @return 返回保存成功的TestDataMain实体对象，内有新生成的主键ID、创建时间、创建人等新增加的信息
     */
    @RequestMapping(value = "/testDataMain/save.htm", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "保存业务数据表", notes = "保存--业务数据表，返回保存成功的TestDataMain实体对象，内有新生成的主键ID、创建时间、创建人等新增加的信息")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "服务器成功返回用户请求的数据"),
            @ApiResponse(code = 400, message = "用户发出的请求有错误，具体错误信息在msg中，例如：访问登录接口时失败，可能是用户不存在或密码不正确"),
            @ApiResponse(code = 401, message = "访问业务接口时未登录，返回401，告知客户端去登录"),
            @ApiResponse(code = 403, message = "无访问权限"),
            @ApiResponse(code = 404, message = "用户发出的请求针对的是不存在的记录"),
            @ApiResponse(code = 406, message = "用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）"),
            @ApiResponse(code = 500, message = "服务器发生错误"),
    })
    public AppDataUtils.AppData<TestDataMain> save(TestDataMainVo testDataMainVo) {
        try {
            // 取出参数
            TestDataMain testDataMain = new TestDataMain();//PO实体对象
            BeanUtils.copyProperties(testDataMainVo, testDataMain);//copy属性值

            // 检查参数
            List<String> errorList = validate(testDataMainVo);
            String message = ApiUtils.errorMessage(errorList);
            if (!errorList.isEmpty()) {
                return AppDataUtils.getAppData(AppDataUtils.STATUS_INVALID, message, null, null);
            }

            //业务处理
            int count=testDataMainService.insertSelective(testDataMain);
            message =  "保存业务数据表成功,影响"+count+"行";
            return AppDataUtils.getAppData(AppDataUtils.STATUS_OK, message, testDataMain, null);
        } catch (Exception e) {
            // 处理异常
            String errMsg = "保存业务数据表错误:";
            logger.error(errMsg, e);
            return AppDataUtils.getAppData(AppDataUtils.STATUS_SERVER_ERROR, errMsg + e.getMessage(), null, null);
        }
    }

    /**
     * 按id修改业务数据表
     * @param testDataMainVo vo对象
     * @return
     */
    @RequestMapping(value = "/testDataMain/edit.htm", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "按id修改业务数据表", notes = "按id更新xx一条记录,testDataMainVo中的id是条件，testDataMainVo中的其它属性值被更新到表中。")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "服务器成功返回用户请求的数据"),
            @ApiResponse(code = 400, message = "用户发出的请求有错误，具体错误信息在msg中，例如：访问登录接口时失败，可能是用户不存在或密码不正确"),
            @ApiResponse(code = 401, message = "访问业务接口时未登录，返回401，告知客户端去登录"),
            @ApiResponse(code = 403, message = "无访问权限"),
            @ApiResponse(code = 404, message = "用户发出的请求针对的是不存在的记录"),
            @ApiResponse(code = 406, message = "用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）"),
            @ApiResponse(code = 500, message = "服务器发生错误"),
    })
    public AppDataUtils.AppData<TestDataMain> edit(TestDataMainVo testDataMainVo) {
        try {
            // 取出参数
            TestDataMain testDataMain = new TestDataMain();//PO实体对象
            BeanUtils.copyProperties(testDataMainVo, testDataMain);//copy属性值

            // 检查参数
            List<String> errorList = validate(testDataMainVo);
            if(testDataMain.getId()==null){
                errorList.add("id主键不可为空");
            }
            String message = ApiUtils.errorMessage(errorList);
            if (!errorList.isEmpty()) {
                return AppDataUtils.getAppData(AppDataUtils.STATUS_INVALID, message, null, null);
            }

            //业务处理
            int count=testDataMainService.updateByIdSelective(testDataMain);
            message =  "按id修改业务数据表成功,影响"+count+"行";
            return AppDataUtils.getAppData(AppDataUtils.STATUS_OK, message, null, null);
        } catch (Exception e) {
            // 处理异常
            String errMsg = "按id修改业务数据表错误:";
            logger.error(errMsg, e);
            return AppDataUtils.getAppData(AppDataUtils.STATUS_SERVER_ERROR, errMsg + e.getMessage(), null, null);
        }
    }

    /**
     * 按条件修改业务数据表
     * @param testDataMainVo vo对象用于存储数据，这些数据将被update到表中
     * @return
     */
    @RequestMapping(value = "/testDataMain/editByWhere.htm", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "按条件修改业务数据表(未实现)", notes = "按多个条件更新业务数据表一条记录,还未想好怎么实现，Where条件与运算符怎么传递还要再构思一下！")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "服务器成功返回用户请求的数据"),
            @ApiResponse(code = 400, message = "用户发出的请求有错误，具体错误信息在msg中，例如：访问登录接口时失败，可能是用户不存在或密码不正确"),
            @ApiResponse(code = 401, message = "访问业务接口时未登录，返回401，告知客户端去登录"),
            @ApiResponse(code = 403, message = "无访问权限"),
            @ApiResponse(code = 404, message = "用户发出的请求针对的是不存在的记录"),
            @ApiResponse(code = 406, message = "用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）"),
            @ApiResponse(code = 500, message = "服务器发生错误"),
    })
    public AppDataUtils.AppData<TestDataMain> editByWhere(TestDataMainVo testDataMainVo) {
        String errMsg = "还未想好怎么实现呢，无法执行";
        return AppDataUtils.getAppData(AppDataUtils.STATUS_SERVER_ERROR, errMsg , null, null);
    }

    /**
     * 按id删除业务数据表
     * @return
     */
    @RequestMapping(value = "/testDataMain/delete.htm", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "按id删除业务数据表", notes = "按id删除业务数据表一条记录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "服务器成功返回用户请求的数据"),
            @ApiResponse(code = 400, message = "用户发出的请求有错误，具体错误信息在msg中，例如：访问登录接口时失败，可能是用户不存在或密码不正确"),
            @ApiResponse(code = 401, message = "访问业务接口时未登录，返回401，告知客户端去登录"),
            @ApiResponse(code = 403, message = "无访问权限"),
            @ApiResponse(code = 404, message = "用户发出的请求针对的是不存在的记录"),
            @ApiResponse(code = 406, message = "用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）"),
            @ApiResponse(code = 500, message = "服务器发生错误"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "int", paramType = "query"),
    })
    public AppDataUtils.AppData<TestDataMain> delete() {
        try {
            // 取出参数
            String id = R.get("id");//主键id

            // 检查参数
            List<String> errorList = new ArrayList<>();
            if (StringUtils.isBlank(id)) {
                errorList.add("id不可为空");
            }
            if (StringUtils.isNotBlank(id) && !StringUtils.isNumeric(id)) {
                errorList.add("id必须是数字");
            }
            String message = ApiUtils.errorMessage(errorList);
            if (!errorList.isEmpty()) {
                return AppDataUtils.getAppData(AppDataUtils.STATUS_INVALID, message, null, null);
            }

            //业务处理
            int count=testDataMainService.deleteById(Long.valueOf(id));;
            message =  "按id删除业务数据表成功,影响"+count+"行";
            return AppDataUtils.getAppData(AppDataUtils.STATUS_OK, message, null, null);
        } catch (Exception e) {
            // 处理异常
            String errMsg = "按id删除业务数据表错误:";
            logger.error(errMsg, e);
            return AppDataUtils.getAppData(AppDataUtils.STATUS_SERVER_ERROR, errMsg + e.getMessage(), null, null);
        }
    }


    /**
     * 按条件删除业务数据表
     * @param testDataMainVo vo对象内存放的是条件
     * @return
     */
    @RequestMapping(value = "/testDataMain/deleteByWhere.htm", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "按条件删除业务数据表", notes = "按多个条件删除业务数据表,每个条件的默认运算符是=，如果你需要大于、小于、like、!=等运算符号请联系程序员修改接口。")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "服务器成功返回用户请求的数据"),
            @ApiResponse(code = 400, message = "用户发出的请求有错误，具体错误信息在msg中，例如：访问登录接口时失败，可能是用户不存在或密码不正确"),
            @ApiResponse(code = 401, message = "访问业务接口时未登录，返回401，告知客户端去登录"),
            @ApiResponse(code = 403, message = "无访问权限"),
            @ApiResponse(code = 404, message = "用户发出的请求针对的是不存在的记录"),
            @ApiResponse(code = 406, message = "用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）"),
            @ApiResponse(code = 500, message = "服务器发生错误"),
    })
    public AppDataUtils.AppData<TestDataMain> deleteByWhere(TestDataMainVo testDataMainVo) {
        try {
            // 取出参数
            TestDataMain testDataMain = new TestDataMain();//PO实体对象
            BeanUtils.copyProperties(testDataMainVo, testDataMain);//copy属性值

            // 检查参数
            List<String> errorList = new ArrayList<>();
            String message = ApiUtils.errorMessage(errorList);
            if (!errorList.isEmpty()) {
                return AppDataUtils.getAppData(AppDataUtils.STATUS_INVALID, message, null, null);
            }

            // 处理：删除条件
            Wrapper wrapper = new Wrapper(testDataMain);//Wrapper是查询条件拼装工具
            //wrapper.and(xxx);//其它自定义条件

            //业务处理
            int count=testDataMainService.deleteByWhere(wrapper);
            message =  "按条件业务数据表成功,影响"+count+"行";
            return AppDataUtils.getAppData(AppDataUtils.STATUS_OK, message, null, null);
        } catch (Exception e) {
            // 处理异常
            String errMsg = "按条件业务数据表错误:";
            logger.error(errMsg, e);
            return AppDataUtils.getAppData(AppDataUtils.STATUS_SERVER_ERROR, errMsg + e.getMessage(), null, null);
        }
    }

    /**
     * 数据验证
     * @param testDataMainVo Vo对象
     * @return List<String> 错误提示信息
     */
    private List<String> validate(TestDataMainVo testDataMainVo){
        List<String> errorList = new ArrayList<String>();
        if(StringUtils.isNotBlank(R.get("name")) && R.get("name").length() > 100){
            errorList.add("名称最大长度不能超过100字符");
        }
        if(StringUtils.isNotBlank(R.get("sex")) && R.get("sex").length() > 1){
            errorList.add("性别最大长度不能超过1字符");
        }
        return errorList;
    }

}