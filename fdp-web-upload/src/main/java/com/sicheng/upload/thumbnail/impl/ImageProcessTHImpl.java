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

package com.sicheng.upload.thumbnail.impl;

import com.sicheng.upload.thumbnail.ImageInfo;
import com.sicheng.upload.thumbnail.ImageProcess;
import com.sicheng.upload.thumbnail.thumbnailator.ImageUtils4TH;

import java.io.InputStream;

/**
 * <p>标题: ImageProcessJavaImpl</p>
 * <p>描述:使用了Thumbnails图片处理库，Thumbnailator 是一个优秀的图片处理的Google开源Java类库，可运行于OpenJdk8\11\17</p>
 * <p>公司: 思程科技 www.sicheng.net</p>
 *
 * @author zhaolei
 * @version 2017年9月13日 下午3:27:57
 */
public class ImageProcessTHImpl implements ImageProcess {

    /**
     * 生成缩略图，保持图片原有长宽比例
     *
     * @param inputStream
     * @param width       宽，像素
     * @param height      高，像素
     * @param quality     清晰度，1-100
     * @param format      文件后缀
     * @return
     */
    @Override
    public InputStream resize(InputStream inputStream, int width, int height, int quality, String format) {
        return ImageUtils4TH.resize(inputStream, width, height, quality, format);
    }

    /**
     * 按“最小边”生成指定宽与高的缩略图，由于图片长宽比不同造成的“超出”，将切掉
     *
     * @param inputStream
     * @param width       宽，像素
     * @param height      高，像素
     * @param quality     清晰度，1-100
     * @param format      文件后缀
     * @return
     */
    @Override
    public InputStream resizeAndCut(InputStream inputStream, int width, int height, int quality, String format) {
        return ImageUtils4TH.resizeAndCut(inputStream, width, height, quality, format);
    }

    /**
     * 取得图片信息 （宽、高、图片类型）
     *
     * @param inputStream 文件内容
     * @return ImageInfo
     */
    @Override
    public ImageInfo getImageInfo(InputStream inputStream) {
        return ImageUtils4TH.getImageInfo(inputStream);
    }

    /**
     * 取得图片类型
     * 支持判断JPEG, GIF, PNG, BMP and TIFF
     * 注意：
     * 1、图片类型使用大写字母描述。
     * 2、jpg格式使用JPEG来表示。
     *
     * @param inputStream
     * @return 大写的文件类型
     */
    public String getFormat(InputStream inputStream) {
        return ImageUtils4TH.getFormat(inputStream);
    }

    /**
     * 取得图片信息 （宽、高）
     *
     * @param inputStream
     * @return
     */
    public ImageInfo getImageWH(InputStream inputStream) {
        return ImageUtils4TH.getImageWH(inputStream);
    }

}
