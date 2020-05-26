package com.cjs.wymall.service.product.impl;

import com.cjs.wymall.mapper.PmsBrandMapper;
import com.cjs.wymall.model.PmsBrand;
import com.cjs.wymall.model.PmsBrandExample;
import com.cjs.wymall.service.product.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-05-26 15:58
 **/
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Override
    public List<PmsBrand> listBrands() {

        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }
}
