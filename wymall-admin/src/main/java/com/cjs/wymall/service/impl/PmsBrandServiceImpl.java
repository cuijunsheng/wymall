package com.cjs.wymall.service.impl;

import com.cjs.wymall.dto.PmsBrandDTO;
import com.cjs.wymall.mapper.PmsBrandMapper;
import com.cjs.wymall.model.PmsBrand;
import com.cjs.wymall.model.PmsBrandExample;
import com.cjs.wymall.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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

    @Override
    public List<PmsBrand> listPageBrands(String keyword, Integer pageNum, Integer pageSize) {
        PmsBrandExample example = new PmsBrandExample();
        example.setOrderByClause("sort desc");
        PmsBrandExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        PageHelper.startPage(pageNum, pageSize);
        return pmsBrandMapper.selectByExample(example);
    }

    @Override
    public int saveBrand(PmsBrandDTO pmsBrandDTO) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandDTO, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isBlank(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        return pmsBrandMapper.insertSelective(pmsBrand);
    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrand brand = new PmsBrand();
        brand.setFactoryStatus(factoryStatus);
        PmsBrandExample example = new PmsBrandExample();
        PmsBrandExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return pmsBrandMapper.updateByExampleSelective(brand, example);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrand brand = new PmsBrand();
        brand.setShowStatus(showStatus);
        PmsBrandExample example = new PmsBrandExample();
        PmsBrandExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);

        return pmsBrandMapper.updateByExampleSelective(brand, example);
    }

    @Override
    public PmsBrand getBrandById(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateBrand(Long id, PmsBrandDTO pmsBrandDTO) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandDTO, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StringUtils.isBlank(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        //TODO 更新品牌时要更新商品中的品牌名称
        return pmsBrandMapper.updateByPrimaryKeySelective(pmsBrand);
    }

    @Override
    public int deleteBrandById(Long id) {

        return pmsBrandMapper.deleteByPrimaryKey(id);
    }
}
