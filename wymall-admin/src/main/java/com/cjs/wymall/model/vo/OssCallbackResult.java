package com.cjs.wymall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: oss上传文件后的回调结果
 * @author: cuijunsheng
 * @date: 2020/6/25
 */
@Data
public class OssCallbackResult {
    @ApiModelProperty("文件名称")
    private String filename;
    @ApiModelProperty("文件大小")
    private String size;
    @ApiModelProperty("文件的mimeType")
    private String mimeType;
    @ApiModelProperty("图片文件的宽")
    private String width;
    @ApiModelProperty("图片文件的高")
    private String height;
}