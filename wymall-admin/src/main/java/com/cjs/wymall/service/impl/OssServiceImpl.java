package com.cjs.wymall.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.cjs.wymall.model.param.OssCallbackParam;
import com.cjs.wymall.model.vo.OssCallbackResult;
import com.cjs.wymall.model.vo.OssPolicyResult;
import com.cjs.wymall.service.OssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: oss上传管理的业务实现类
 * @author: cuijunsheng
 * @date: 2020/6/25
 */
@Service
public class OssServiceImpl implements OssService {
    private static final Logger logger = LoggerFactory.getLogger(OssServiceImpl.class);

    @Value("${aliyun.oss.policy.expire}")
    private int expire;
    @Value("${aliyun.oss.maxSize}")
    private int maxSize;
    @Value("${aliyun.oss.callbackUrl}")
    private String callbackUrl;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.dir.prefix}")
    private String dirPrefix;

    @Autowired
    private OSSClient ossClient;

    @Override
    public OssPolicyResult policy() {
        OssPolicyResult policyResult = new OssPolicyResult();
        //存储目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dir = dirPrefix + sdf.format(new Date());
        //签名有效期
        long expireEndTime = System.currentTimeMillis() + expire * 1000;
        Date expiration = new Date(expireEndTime);
        //文件大小
        long fileMaxSize = maxSize * 1024 * 1024;
        // 回调
        OssCallbackParam callbackParam = new OssCallbackParam();
        callbackParam.setCallbackUrl(callbackUrl);
        callbackParam.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        callbackParam.setCallbackBodyType("application/x-www-form-urlencoded");
        //提交节点
        String action = "http://" + bucketName + "." + endpoint;
        try {
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, fileMaxSize);
            policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient.calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callbackParam).toString().getBytes(StandardCharsets.UTF_8));
            //返回结果
            policyResult.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
            policyResult.setPolicy(policy);
            policyResult.setSignature(signature);
            policyResult.setCallback(callbackData);
            policyResult.setDir(dir);
            policyResult.setHost(action);
        } catch (Exception e) {
            logger.error("签名失败:{}",e.getMessage());
        }
        return policyResult;
    }

    @Override
    public OssCallbackResult callback(HttpServletRequest request) {
        OssCallbackResult result = new OssCallbackResult();
        String fileName = request.getParameter("filename");
        fileName = "http://" + bucketName + "." + endpoint + "/" + fileName;
        result.setFilename(fileName);
        result.setSize(request.getParameter("size"));
        result.setMimeType(request.getParameter("mimeType"));
        result.setWidth(request.getParameter("width"));
        result.setHeight(request.getParameter("height"));
        return result;
    }
}