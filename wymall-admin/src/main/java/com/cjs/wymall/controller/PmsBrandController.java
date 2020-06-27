package com.cjs.wymall.controller;

import com.cjs.wymall.common.web.CommonPage;
import com.cjs.wymall.common.web.CommonResult;
import com.cjs.wymall.model.PmsBrand;
import com.cjs.wymall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 商品品牌控制器
 * @author: cuijunsheng
 * @date: 2020/5/24
 */
@RestController
@RequestMapping("/brand")
@Api(tags = "商品品牌管理")
public class PmsBrandController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PmsBrandService brandService;

    @GetMapping("/queryBrandList")
    @ApiOperation("获取商品品牌列表")
    public CommonResult<List<PmsBrand>> queryBrandList() {
        logger.info("获取商品品牌列表");
        List<PmsBrand> pmsBrandList = brandService.listBrands();
        return CommonResult.success(pmsBrandList);
    }

    @PostMapping("/createBrand")
    @ApiOperation( "添加品牌")
    public CommonResult<Integer> createBrand(PmsBrand pmsBrand) {
        int count = brandService.saveBrand(pmsBrand);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed("添加失败！");
        }
    }

    @GetMapping("/pageBrandList")
    @ApiOperation("分页查询商品品牌列表")
    public CommonResult<CommonPage<PmsBrand>> queryBrandList(@RequestParam(value = "keyword", required = false) String keyword,
                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        logger.info("品牌关键字：{}",keyword);
        List<PmsBrand> brandList = brandService.listPageBrands(keyword, pageNum, pageSize);

        return CommonResult.success(CommonPage.getPageInfo(brandList));
    }

    @PostMapping("/updateFactoryStatus")
    @ApiOperation("修改厂商标志")
    public CommonResult updateFactoryStatus(@RequestParam(value = "ids") List<Long> ids,
                                            @RequestParam(value = "factoryStatus") Integer factoryStatus){
        logger.info("ids:{}",ids);
        int count = brandService.updateFactoryStatus(ids, factoryStatus);
        if(count>0){
            return CommonResult.success("修改成功！",count);
        }else {
            return CommonResult.failed("修改失败！");
        }
    }

    @RequestMapping("/updateShowStatus")
    @ApiOperation("是否显示品牌")
    public CommonResult updateShowStatus(@RequestParam(value = "ids") List<Long> ids,
                                         @RequestParam(value = "showStatus") Integer showStatus){
        int count = brandService.updateShowStatus(ids, showStatus);
        if(count>0){
            return CommonResult.success("修改成功！",count);
        }else {
            return CommonResult.failed("修改失败！");
        }
    }

}