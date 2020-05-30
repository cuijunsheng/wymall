package com.cjs.wymall.portal.service.member;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/5/30
 */
public interface UmsMemberService {

    /**
     * 获取验证码
     * @param telephone
     * @return
     */
    String getAuthCode(String telephone);

    /**
     * 检验验证码是否正确
     * @param telephone
     * @param authCode
     * @return
     */
    Boolean verifyAuthCode(String telephone,String authCode);
}
