package com.cjs.wymall.service;

import com.cjs.wymall.model.UmsMenu;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/6/14
 */
public interface UmsMenuService {

    /**
     * 根据后台用户id，查询菜单列表
     * @param adminId
     * @return
     */
    List<UmsMenu> listMenusByAdminId(Long adminId);
}