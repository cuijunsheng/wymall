package com.cjs.wymall.service;

import com.cjs.wymall.dto.PmsBrandDTO;
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
     * 分页查询商品品牌列表
     * @param keyword 关键字(品牌名称)
     * @param pageNum 页码
     * @param pageSize 每一页的数目
     * @return
     */
    List<PmsBrand> listPageBrands(String keyword,Integer pageNum,Integer pageSize);

    /**
     * 添加品牌
     * @param pmsBrandDTO
     * @return
     */
    int saveBrand(PmsBrandDTO pmsBrandDTO);

    /**
     * 修改是否为品牌厂商
     * @param ids
     * @param factoryStatus
     * @return
     */
    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);

    /**
     * 修改显示状态（0不显示，1显示）
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids,Integer showStatus);

    /**
     * 根据id查询品牌信息
     * @param id
     * @return
     */
    PmsBrand getBrandById(Long id);

    /**
     * 修改品牌信息
     * @param id
     * @param pmsBrandDTO
     * @return
     */
    int updateBrand(Long id,PmsBrandDTO pmsBrandDTO);

    /**
     * 删除品牌信息
     * @param id
     * @return
     */
    int deleteBrandById(Long id);
}
