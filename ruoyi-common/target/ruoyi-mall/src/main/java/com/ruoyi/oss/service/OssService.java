package com.ruoyi.oss.service;

import com.ruoyi.oss.domain.OssCallbackResult;
import com.ruoyi.oss.domain.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 * Created by macro on 2018/5/17.
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy(HttpServletRequest request);

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);

    /**
     * 删除
     */
    String delete(String fileKey);
}
