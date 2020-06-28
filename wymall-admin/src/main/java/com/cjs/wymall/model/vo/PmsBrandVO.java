package com.cjs.wymall.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/6/28
 */
@Data
public class PmsBrandVO {
    @ApiModelProperty(value = "商品品牌名称")
    private String name;
    @ApiModelProperty(value = "首字母")
    private String firstLetter;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "是否为品牌制造商：0->不是；1->是")
    private Integer factoryStatus;
    @ApiModelProperty(value = "是否显示该品牌：0->不是；1->是")
    private Integer showStatus;
    @ApiModelProperty(value = "品牌logo")
    private String logo;
    @ApiModelProperty(value = "专区大图")
    private String bigPic;
    @ApiModelProperty(value = "品牌故事")
    private String brandStory;
}