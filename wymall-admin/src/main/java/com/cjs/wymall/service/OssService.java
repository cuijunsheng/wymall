package com.cjs.wymall.service;

import com.cjs.wymall.model.vo.OssCallbackResult;
import com.cjs.wymall.model.vo.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: oss上传管理Service
 * @author: cuijunsheng
 * @date: 2020/6/25
 */
public interface OssService {

    /**
     * 获取oss上传策略
     * @return
     */
    OssPolicyResult policy();

    /**
     * oss上传成功后回调
     * @param request
     * @return
     */
    OssCallbackResult callback(HttpServletRequest request);
}