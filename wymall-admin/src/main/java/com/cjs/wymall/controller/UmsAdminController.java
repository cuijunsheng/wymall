package com.cjs.wymall.controller;

import com.cjs.wymall.common.web.CommonResult;
import com.cjs.wymall.dto.UmsAdminDTO;
import com.cjs.wymall.model.UmsAdmin;
import com.cjs.wymall.service.UmsAdminService;
import com.cjs.wymall.service.UmsMenuService;
import com.cjs.wymall.service.UmsRoleService;
import com.cjs.wymall.vo.UmsAdminLoginVO;
import com.cjs.wymall.vo.UmsAdminVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;
    @Autowired
    private UmsMenuService menuService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping("/createUser")
    @ApiOperation(value = "添加后台用户")
    public CommonResult<UmsAdmin> createUser(UmsAdminVO umsAdminVO) {
        UmsAdminDTO umsAdminDTO = new UmsAdminDTO();
        BeanUtils.copyProperties(umsAdminVO, umsAdminDTO);
        UmsAdmin umsAdmin = adminService.save(umsAdminDTO);
        if (umsAdmin != null) {
            return CommonResult.success("添加成功！", umsAdmin);
        } else {
            return CommonResult.failed("用户已经存在！");
        }

    }

    @PostMapping("/login")
    @ApiOperation(value = "后台用户登录")
    public CommonResult login(@RequestBody UmsAdminLoginVO umsAdminLoginVO) {
        logger.info("umsAdminLoginVO：{}",umsAdminLoginVO);
        UmsAdminDTO umsAdminDTO = new UmsAdminDTO();
        BeanUtils.copyProperties(umsAdminLoginVO,umsAdminDTO);
        String token = adminService.login (umsAdminDTO);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误！");
        }
        Map<String, String> tokenMap = new HashMap<>(4);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        return CommonResult.success("登录成功", tokenMap);
    }

    @GetMapping("/getCaptcha")
    @ApiOperation(value = "获取验证码")
    public CommonResult getCaptcha() {
        Map<String, Object> captchaResult = adminService.getCaptcha();
        return CommonResult.success("获取验证码成功！",captchaResult);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录")
    public CommonResult logout(){

        return CommonResult.success("退出成功！",null);
    }

    @GetMapping("/info")
    @ApiOperation(value = "登录后获取管理员信息")
    public CommonResult getAdminInfo(Principal principal){
        logger.info("principal信息：{}",principal);
        if(principal==null){
            return CommonResult.unauthorized();
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        Map<String,Object> data = new HashMap<>();
        data.put("username",username);
        data.put("roles", new String[]{"TEST"});
        data.put("menus", menuService.listMenusByAdminId(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        return CommonResult.success(data);

    }
}