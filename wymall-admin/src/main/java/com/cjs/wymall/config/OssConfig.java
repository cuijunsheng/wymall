package com.cjs.wymall.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 阿里云oss配置类
 * @author: cuijunsheng
 * @date: 2020/6/24
 */
@Configuration
public class OssConfig {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    public OSSClient ossClient(){
        return new OSSClient(endpoint,accessKeyId,accessKeySecret);
    }

}