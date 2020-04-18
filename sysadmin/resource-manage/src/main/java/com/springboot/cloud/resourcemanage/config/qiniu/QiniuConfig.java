package com.springboot.cloud.resourcemanage.config.qiniu;

import com.google.gson.Gson;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QiniuConfig {

    @Autowired
    private QiniuProperties qiniuProperties;

    /**
     * 配置空间的存储区域
     */
    @Bean
    public com.qiniu.storage.Configuration qiNiuConfig() {
        switch (qiniuProperties.getRegion()) {
            case "huadong":
                return new com.qiniu.storage.Configuration(Region.huadong());
            case "huabei":
                return new com.qiniu.storage.Configuration(Region.huabei());
            case "huanan":
                return new com.qiniu.storage.Configuration(Region.huanan());
            case "beimei":
                return new com.qiniu.storage.Configuration(Region.beimei());
            default:
                throw new ServiceException(SystemErrorType.CONFIGERROR001);
        }
    }

    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiNiuConfig());
    }

    /**
     * 认证信息实例
     */
    @Bean
    public Auth auth() {
        return Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiNiuConfig());
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
