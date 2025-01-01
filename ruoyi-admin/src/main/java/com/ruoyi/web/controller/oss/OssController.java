package com.ruoyi.web.controller.oss;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.oss.domain.OssCallbackResult;
import com.ruoyi.oss.domain.OssPolicyResult;
import com.ruoyi.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss相关操作接口
 * Created by macro on 2018/4/26.
 */

@RestController
@Api(tags = "OssController", description = "Oss管理")
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    @CrossOrigin
    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    public AjaxResult policy(HttpServletRequest request) {
        OssPolicyResult result = ossService.policy(request);
        return AjaxResult.success(result);
    }

    @CrossOrigin
    @ApiOperation(value = "oss上传成功回调")
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    public AjaxResult callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return AjaxResult.success(ossCallbackResult);
    }

    @CrossOrigin
    @ApiOperation(value = "oss上传删除")
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public AjaxResult delete(HttpServletRequest request) {
        String fileKey = request.getParameter("fileKey");
        return AjaxResult.success(ossService.delete(fileKey));
    }

}
