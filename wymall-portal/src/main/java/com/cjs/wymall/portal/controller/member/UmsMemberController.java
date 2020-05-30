package com.cjs.wymall.portal.controller.member;

import com.cjs.wymall.common.web.CommonResult;
import com.cjs.wymall.portal.service.member.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 会员控制器
 * @author: cuijunsheng
 * @date: 2020/5/29
 */
@RestController("member")
@Api(tags = "UmsMemberController",description = "会员登录注册管理")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @GetMapping("/getAuthCode")
    @ApiOperation(value = "获取验证码")
    public CommonResult getAuthCode(String telephone){

        String authCode = umsMemberService.getAuthCode(telephone);
        return CommonResult.success(authCode);
    }

    @PostMapping("/verifyAuthCode")
    public CommonResult verifyAuthCode(String telephone,String authCode){
        Boolean flag = umsMemberService.verifyAuthCode(telephone, authCode);
        if(flag){
            return CommonResult.success("验证成功！");
        }else {
            return CommonResult.failed("验证码错误！");
        }
    }
}