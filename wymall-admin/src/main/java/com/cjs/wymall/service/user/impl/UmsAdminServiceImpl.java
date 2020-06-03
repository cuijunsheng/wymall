package com.cjs.wymall.service.user.impl;

import com.cjs.wymall.dao.user.UmsAdminRoleRelationDao;
import com.cjs.wymall.dto.UmsAdminDTO;
import com.cjs.wymall.mapper.UmsAdminMapper;
import com.cjs.wymall.model.UmsAdmin;
import com.cjs.wymall.model.UmsAdminExample;
import com.cjs.wymall.model.UmsPermission;
import com.cjs.wymall.security.util.JwtTokenUtils;
import com.cjs.wymall.service.user.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/5/31
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private final Logger logger = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public String login(String username, String password) {
        String token = null;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        try {
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码错误！");
            }
            logger.info("用户权限：{}",userDetails.getAuthorities());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtils.generateToken(userDetails);
        } catch (Exception e) {
            logger.error("登录异常：{}", e.getMessage());
        }
        return token;
    }


    @Override
    public List<UmsPermission> listPermissions(Long adminId) {
        return adminRoleRelationDao.listPermissions(adminId);
    }


    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin save(UmsAdminDTO umsAdminDTO) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminDTO, umsAdmin);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }


}