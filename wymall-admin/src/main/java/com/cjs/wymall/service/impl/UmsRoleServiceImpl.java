package com.cjs.wymall.service.impl;

import com.cjs.wymall.dao.UmsRoleDao;
import com.cjs.wymall.model.UmsRole;
import com.cjs.wymall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/6/13
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    private UmsRoleDao adminRoleDao;
    @Override
    public List<UmsRole> listRolesByAdminId(Long adminId) {

        return adminRoleDao.listRolesByAdminId(adminId);
    }
}