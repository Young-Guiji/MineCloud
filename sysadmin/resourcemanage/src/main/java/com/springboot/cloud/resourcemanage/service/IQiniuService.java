package com.springboot.cloud.resourcemanage.service;

import com.qiniu.common.QiniuException;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileRespDto;

public interface IQiniuService {
    UploadFileRespDto uploadFile(byte[] uploadBytes, String fileName, String filePath, String bucketName);

    String getFileUrl(String domainUrl, String fileName);

    String getFileUrl(String domainUrl, String fileName, Long expires);

    String getUrl(Long expires, boolean encrypt, String fileName);

    void deleteFile(String fileName, String bucketName) throws QiniuException;
}
