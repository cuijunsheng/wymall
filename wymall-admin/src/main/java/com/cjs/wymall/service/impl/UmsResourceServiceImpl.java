package com.cjs.wymall.service.impl;

import com.cjs.wymall.mapper.UmsResourceMapper;
import com.cjs.wymall.model.UmsResource;
import com.cjs.wymall.model.UmsResourceExample;
import com.cjs.wymall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/6/13
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResource> listResources() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }
}