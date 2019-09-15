package com.dlion.life.punch.controller;

import com.alibaba.fastjson.JSONObject;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.utils.CommonUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件服务
 *
 * @author 李正元
 * @date 2019/9/12
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;

    @RequestMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {

        if (!multipartFile.isEmpty()) {
            try {
                InputStream inputStream = multipartFile.getInputStream();
                String path = uploadFile(inputStream, CommonUtil.getFileName());

                Map<String, Object> result = new HashMap<>();
                result.put("fileUrl", path);
                logger.info("七牛云返回的图片链接:{}", path);
                return new ResponseModel(result);
            } catch (IOException e) {
                logger.error("上传图片错误", e);
            }
        }
        return new ResponseModel(ResultConstant.ERROR, "上传失败");
    }

    private String uploadFile(InputStream inputStream, String fileName) throws QiniuException {

        Response response = this.uploadManager.put(inputStream, fileName, getUploadToken(), null, null);

        logger.info("图片上传结果：{}", JSONObject.parse(response.bodyString()));

        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, fileName, getUploadToken(), null, null);
            retry++;
        }
        if (response.statusCode == 200) {
            return new StringBuffer().append("http://").append(cdnPrefix).append("/").append(fileName).toString();
        }
        return null;
    }

    /**
     * 删除七牛云上的相关文件
     *
     * @param key
     * @return
     * @throws QiniuException
     */
    public String delete(String key) throws QiniuException {
        Response response = bucketManager.delete(this.bucket, key);
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(bucket, key);
        }
        return response.statusCode == 200 ? "删除成功!" : "删除失败!";
    }

    /**
     * 获取上传凭证
     *
     * @return
     */
    private String getUploadToken() {

        String token = this.auth.uploadToken(bucket);
        logger.info("获取的token:{}", token);

        return token;
    }

}
