package com.cjs.wymall.model.bo;

import com.cjs.wymall.model.UmsAdmin;
import com.cjs.wymall.model.UmsResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: AdminUserDetails :SpringSecurity需要的用户详情
 * UserDetails: SpringSecurity定义用于封装用户信息的类（主要是用户信息和权限），需要自行实现
 * @author: cuijunsheng
 * @date: 2020/5/31
 */
public class AdminUserDetails implements UserDetails {
    private UmsAdmin umsAdmin;
    private List<UmsResource> resourceList;

    public AdminUserDetails() {
    }

    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsResource> resourceList) {
        this.umsAdmin = umsAdmin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色(当前用户可以访问的资源)
        return resourceList.stream()
                .map(resource -> new SimpleGrantedAuthority(resource.getId() + ":" + resource.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}