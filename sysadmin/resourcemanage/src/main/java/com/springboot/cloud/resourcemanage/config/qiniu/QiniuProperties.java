package com.springboot.cloud.resourcemanage.config.qiniu;

import com.springboot.cloud.common.core.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = GlobalConstant.Qiniu.PREFIX)
public class QiniuProperties {

    private String accessKey;
    private String secretKey;
    private String region;
    private String privateDomain;
    private String publicDomain;
    private Long fileMaxSize;

}
