package com.cjs.wymall.service;

import com.cjs.wymall.model.PmsBrand;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-05-26 15:56
 **/
public interface PmsBrandService {

    /**
     * 获取商品品牌列表
     * @return
     */
    List<PmsBrand> listBrands();

    /**
     * 添加品牌
     * @param pmsBrand
     * @return
     */
    int saveBrand(PmsBrand pmsBrand);
}
