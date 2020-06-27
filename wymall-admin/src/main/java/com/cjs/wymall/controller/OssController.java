package com.cjs.wymall.controller;

import com.cjs.wymall.common.web.CommonResult;
import com.cjs.wymall.model.vo.OssCallbackResult;
import com.cjs.wymall.model.vo.OssPolicyResult;
import com.cjs.wymall.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: oss相关操作接口
 * @author: cuijunsheng
 * @date: 2020/6/25
 */
@RestController
@RequestMapping("aliyun/oss")
@Api(tags = "oss管理")
public class OssController {
    @Autowired
    private OssService ossService;

    @GetMapping("/policy")
    @ApiOperation(value = "获取oss上传策略（签名生成）")
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult policy = ossService.policy();
        return CommonResult.success(policy);
    }

    @PostMapping("/callback")
    @ApiOperation(value = "oss上传成功回调")
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult callback = ossService.callback(request);
        return CommonResult.success(callback);
    }
}