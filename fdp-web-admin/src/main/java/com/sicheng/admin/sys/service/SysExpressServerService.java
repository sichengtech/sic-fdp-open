/**
 * 本作品使用 木兰公共许可证,第2版（Mulan PubL v2） 开源协议，请遵守相关条款，或者联系sicheng.net获取商用授权。
 * Copyright (c) 2016 SiCheng.Net
 * This software is licensed under Mulan PubL v2.
 * You can use this software according to the terms and conditions of the Mulan PubL v2.
 * You may obtain a copy of Mulan PubL v2 at:
 * http://license.coscl.org.cn/MulanPubL-2.0
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PubL v2 for more details.
 */
package com.sicheng.admin.sys.service;

import com.sicheng.admin.sys.dao.SysExpressServerDao;
import com.sicheng.admin.sys.entity.SysExpressServer;
import com.sicheng.common.persistence.Page;
import com.sicheng.common.persistence.wrapper.Wrapper;
import com.sicheng.common.service.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理快递接口的配置 Service
 * @author zjl
 * @version 2017-04-20
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class SysExpressServerService extends CrudService<SysExpressServerDao, SysExpressServer> {

    //请在这里编写业务逻辑

    //父类中20个单表操作的常用方法，已全部继承下来，可直接使用。

    //注意：把多条业务sql包在一个事务中


    /**
     * 查出express设置信息
     *  SysExpressServer表可以保存多行，但只使用一行记录
     * 从库中查出ID最小的一个
     */
    public SysExpressServer selectExpressSet() {
        SysExpressServer entity = null;
        if (entity == null) {
            //从库中查出ID最小的一个
            Page<SysExpressServer> page = new Page<SysExpressServer>();
            page.setOrderBy("ses_id asc");//按ID排序
            SysExpressServer expressServer = new SysExpressServer();
            page = this.selectByWhere(page, new Wrapper(expressServer));
            if (page.getList().size() > 0) {
                entity = page.getList().get(0);//取ID最小的一个
            }
        }
        return entity;
    }

}