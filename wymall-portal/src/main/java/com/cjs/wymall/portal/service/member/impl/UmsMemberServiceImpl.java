package com.cjs.wymall.portal.service.member.impl;

import com.cjs.wymall.common.util.RedisUtils;
import com.cjs.wymall.portal.service.member.UmsMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/5/30
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Value("${redis.key.authCode.prefix}")
    private String REDIS_KEY_AUTH_CODE_PREFIX;
    @Value("${redis.key.authCode.expire}")
    private Long REDIS_KEY_AUTH_CODE_EXPIRE_SECONDS;
    @Autowired
    private RedisUtils redisUtils;


    @Override
    public String getAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        System.out.println(REDIS_KEY_AUTH_CODE_PREFIX);
        redisUtils.set(REDIS_KEY_AUTH_CODE_PREFIX+telephone,sb.toString(),REDIS_KEY_AUTH_CODE_EXPIRE_SECONDS);
        return sb.toString();
    }

    @Override
    public Boolean verifyAuthCode(String telephone, String authCode) {
        if(StringUtils.isBlank(authCode)){
            return false;
        }
        String localAuthCode = (String) redisUtils.get(REDIS_KEY_AUTH_CODE_PREFIX+telephone);
        return authCode.equals(localAuthCode);
    }
}