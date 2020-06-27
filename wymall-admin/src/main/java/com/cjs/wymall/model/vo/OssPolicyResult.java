package com.cjs.wymall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 前端获取OSS上传文件授权返回结果
 * @author: cuijunsheng
 * @date: 2020/6/25
 */
@Data
public class OssPolicyResult {
    @ApiModelProperty("访问身份验证中用到的用户标识")
    private String accessKeyId;
    @ApiModelProperty("用户表单上传时的策略，经过base64编码过的字符串")
    private String policy;
    @ApiModelProperty("对policy签名后的字符串")
    private String signature;
    @ApiModelProperty("上传文件夹路径的前缀")
    private String dir;
    @ApiModelProperty("oss对外提供服务的访问域名")
    private String host;
    @ApiModelProperty("上传成功后的回调设置")
    private String callback;
}