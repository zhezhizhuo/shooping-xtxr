package com.qgj.common.utils.file;

import com.alibaba.fastjson.JSON;
import com.qgj.common.config.QiNiuYunConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

@Component
public class FileUpLoad implements InitializingBean {

    @Autowired
    QiNiuYunConfig qiNiuYunConfig;

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    // 定义七牛云上传的相关策略
    private StringMap putPolicy;

    /**
     * 以文件的形式上传
     *
     * @param file
     * @return
     * @throws QiniuException
     */
    public String uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file, null, getUploadToken());
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, null, getUploadToken());
            retry++;
        }
        //解析结果
        DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
        String return_path = qiNiuYunConfig.getPrefix() + "/" + putRet.key;
        return return_path;
    }

    /**
     * 以流的形式上传  到七牛云
     *
     * @param inputStream
     * @return
     * @throws QiniuException
     */
    public String uploadFile(InputStream inputStream,String key) throws QiniuException {
        Response response = this.uploadManager.put(inputStream, key, getUploadToken(), null, null);
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, key, getUploadToken(), null, null);
            retry++;
        }
        //解析结果
        DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
        String return_path = "http://"+qiNiuYunConfig.getPrefix() + "/" + putRet.key;
        return return_path;
    }

    /**
     * 删除七牛云上的相关文件
     *
     * @param key
     * @return
     * @throws QiniuException
     */
    public Response delete(String key) throws QiniuException {
        Response response = bucketManager.delete(qiNiuYunConfig.getBucket(), key);
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(qiNiuYunConfig.getBucket(), key);
        }
        return response;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
        // putPolicy.put("saveKey", UUID.randomUUID().timestamp());
    }

    /**
     * 获取上传凭证
     *
     * @return
     */
    private String getUploadToken() {
        return this.auth.uploadToken(qiNiuYunConfig.getBucket(), null, 3600, putPolicy);
    }
}
