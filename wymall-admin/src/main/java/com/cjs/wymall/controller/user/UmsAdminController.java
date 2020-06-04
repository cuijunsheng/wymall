package com.cjs.wymall.controller.user;

import com.cjs.wymall.common.web.CommonResult;
import com.cjs.wymall.dto.UmsAdminDTO;
import com.cjs.wymall.model.UmsAdmin;
import com.cjs.wymall.service.user.UmsAdminService;
import com.cjs.wymall.vo.UmsAdminVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 后台用户管理控制器
 * @author: cuijunsheng
 * @date: 2020/5/24
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "后台用户管理")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping("/createUser")
    @ApiOperation(value = "添加后台用户")
    public CommonResult<UmsAdmin> createUser(UmsAdminVO umsAdminVO){
        UmsAdminDTO umsAdminDTO = new UmsAdminDTO();
        BeanUtils.copyProperties(umsAdminVO,umsAdminDTO);
        UmsAdmin umsAdmin = adminService.save(umsAdminDTO);
        if(umsAdmin!=null){
            return CommonResult.success("添加成功！",umsAdmin);
        }else {
            return CommonResult.failed("用户已经存在！");
        }

    }

    @PostMapping("/login")
    @ApiOperation(value = "后台用户登录")
    public CommonResult login(String username,String password){
        String token = adminService.login(username,password);
        if(token==null){
            return CommonResult.validateFailed("用户名或密码错误！");
        }
        Map<String,String> tokenMap = new HashMap<>(16);
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return CommonResult.success("登录成功！",tokenMap);
    }
}