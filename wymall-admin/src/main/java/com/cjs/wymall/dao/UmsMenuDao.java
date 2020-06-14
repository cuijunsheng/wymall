package com.cjs.wymall.dao;

import com.cjs.wymall.model.UmsMenu;

import java.util.List;

/**
 * @description: 自定义菜单操作Dao
 * @author: cuijunsheng
 * @date: 2020/6/14
 */
public interface UmsMenuDao {

    /**
     * 根据后台用户id，查询菜单列表
     * @param adminId
     * @return
     */
    List<UmsMenu> listMenusByAdminId(Long adminId);
}