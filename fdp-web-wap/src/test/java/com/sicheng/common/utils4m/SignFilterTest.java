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

package com.sicheng.common.utils4m;

import com.sicheng.common.mapper.JsonMapper;

public class SignFilterTest {
    public static void main(String[] args) {
        String jsonStr = "{data:\"e2E6MSxiOjJ9\",timestamp:\"111\",sign:\"666\"}";
        Vo json = JsonMapper.getInstance().fromJson(jsonStr, SignFilterTest.Vo.class);
        System.out.println(json);
    }

    static class Vo {
        String data;
        String timestamp;
        String sign;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        @Override
        public String toString() {
            return "Vo{" +
                    "data='" + data + '\'' +
                    ", timestamp='" + timestamp + '\'' +
                    ", sign='" + sign + '\'' +
                    '}';
        }
    }
}
