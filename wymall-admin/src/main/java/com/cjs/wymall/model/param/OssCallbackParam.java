package com.cjs.wymall.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: oss上传成功后的回调参数
 * @author: cuijunsheng
 * @date: 2020/6/25
 */
@Data
public class OssCallbackParam {
    @ApiModelProperty("请求的回调地址")
    private String callbackUrl;
    @ApiModelProperty("回调时传入request的参数")
    private String callbackBody;
    @ApiModelProperty("回调时传入参数的格式，比如表单提交形式")
    private String callbackBodyType;
}