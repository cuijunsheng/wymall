package com.cjs.wymall.controller.product;

import com.cjs.wymall.common.web.CommonResult;
import com.cjs.wymall.model.PmsBrand;
import com.cjs.wymall.service.product.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 商品品牌控制器
 * @author: cuijunsheng
 * @date: 2020/5/24
 */
@RestController
@RequestMapping("/brand")
@Api(tags = "PmsBrandController",description = "商品品牌")
public class PmsBrandController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PmsBrandService pmsBrandService;

    @GetMapping("/queryBrandList")
    @ApiOperation(value = "获取商品品牌列表")
    public CommonResult<List<PmsBrand>> queryBrandList() {
        logger.info("获取商品品牌列表");
        List<PmsBrand> pmsBrandList = pmsBrandService.listBrands();
        return CommonResult.success(pmsBrandList);
    }

    @PostMapping("/createBrand")
    @ApiOperation(value = "添加品牌")
    public CommonResult createBrand(PmsBrand pmsBrand){
        CommonResult commonResult;
        int count = pmsBrandService.saveBrand(pmsBrand);
        if(count>0){
            commonResult = CommonResult.success(count);
        }else {
            commonResult = CommonResult.failed("添加失败！");
        }

        return commonResult;
    }


}