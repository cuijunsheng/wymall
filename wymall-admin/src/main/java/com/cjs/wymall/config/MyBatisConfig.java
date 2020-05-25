package com.cjs.wymall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: mybatis配置类
 * @author: cuijunsheng
 * @date: 2020/5/24
 */
@Configuration
@MapperScan({"com.cjs.wymall.mapper","com.cjs.wymall.dao"})
public class MyBatisConfig {
}