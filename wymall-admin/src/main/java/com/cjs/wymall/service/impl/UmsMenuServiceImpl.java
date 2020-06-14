package com.cjs.wymall.service.impl;

import com.cjs.wymall.dao.UmsMenuDao;
import com.cjs.wymall.model.UmsMenu;
import com.cjs.wymall.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/6/14
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Autowired
    private UmsMenuDao menuDao;

    @Override
    public List<UmsMenu> listMenusByAdminId(Long adminId) {
        return menuDao.listMenusByAdminId(adminId);
    }
}