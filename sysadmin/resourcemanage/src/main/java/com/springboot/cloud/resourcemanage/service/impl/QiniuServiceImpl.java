package com.springboot.cloud.resourcemanage.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileRespDto;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.resourcemanage.config.qiniu.QiniuProperties;
import com.springboot.cloud.resourcemanage.service.IQiniuService;
import com.springboot.cloud.util.PublicUtil;
import com.springboot.cloud.util.RedisKeyUtil;
import com.springboot.cloud.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static com.springboot.cloud.common.core.util.SnowflakeIdWorker.generateId;

@Slf4j
@Service
public class QiniuServiceImpl implements IQiniuService {

    @Autowired
    private QiniuProperties qiniuProperties;
    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private StringRedisTemplate srt;
    @Autowired
    private Auth auth;
    @Autowired
    private BucketManager bucketManager;

    private static final String OPEN_IMG_BUCKET = "open-mine";

    @Override
    public UploadFileRespDto uploadFile(byte[] uploadBytes, String fileName, String filePath, String bucketName){
        log.info("uploadFile - 上传文件. fileName={}, bucketName={}", fileName, bucketName);

        Preconditions.checkArgument(uploadBytes != null, "读取文件失败");
        Preconditions.checkArgument(StringUtils.isNotEmpty(fileName), SystemErrorType.RESOURCE10040010.getMesg());
        Preconditions.checkArgument(StringUtils.isNotEmpty(filePath), "文件路径不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(bucketName), "存储节点不能为空");

        InputStream is = new ByteArrayInputStream(uploadBytes);
        String inputStreamFileType = FileTypeUtil.getType(is);
        String newFileName = generateId() + "." + inputStreamFileType;

        // 检查数据大小
        this.checkFileSize(uploadBytes);

        try {
            Response response = uploadManager.put(uploadBytes, filePath + newFileName, getUpToken(bucketName));
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("uploadFile - 上传文件. [OK] putRet={}", putRet);
            if (PublicUtil.isEmpty(putRet) || StringUtils.isEmpty(putRet.key)) {
                throw new ServiceException(SystemErrorType.RESOURCE10040009);
            }
        } catch (QiniuException e) {
            log.error(e.getMessage());
        }
        String fileUrl;
        // 获取图片路径
        if (StringUtils.equals(OPEN_IMG_BUCKET, bucketName)) {
            fileUrl = qiniuProperties.getPublicDomain() + "/" + filePath + newFileName;
        } else {
            String domainUrl = qiniuProperties.getPrivateDomain();
            fileUrl = this.getFileUrl(domainUrl, fileName);
        }
        UploadFileRespDto result = new UploadFileRespDto();
        result.setAttachmentUrl(fileUrl);
        result.setAttachmentName(newFileName);
        result.setAttachmentPath(filePath);
        return result;
    }

    @Override
    public String getFileUrl(String domainUrl, String fileName) {
        return this.getFileUrl(domainUrl, fileName, null);
    }

    @Override
    public String getFileUrl(String domainUrl, String fileName, Long expires) {
        String downloadUrl;
        String encodedFileName = UrlUtil.getURLEncoderString(fileName);
        String url = String.format("%s/%s", domainUrl, encodedFileName);
        log.info("getFileUrl - 获取文件全路径. url={}", url);

        if (null == expires) {
            downloadUrl = auth.privateDownloadUrl(url);
        } else {
            downloadUrl = auth.privateDownloadUrl(url, expires);
        }
        log.info("getFileUrl - 获取文件全路径. [OK] downloadUrl={}", downloadUrl);
        return downloadUrl;
    }

    @Override
    public String getUrl(Long expires, boolean encrypt, String fileName) {
        final String domainOfBucket;
        if (encrypt) {
            domainOfBucket = qiniuProperties.getPrivateDomain();
            return this.getFileUrl(domainOfBucket, fileName, expires);
        } else {
            domainOfBucket = qiniuProperties.getPublicDomain();
            return domainOfBucket + "/" + fileName;
        }
    }

    @Override
    public void deleteFile(String fileName, String bucketName) throws QiniuException {
        log.info("deleteFile - 删除OSS文件. fileName={}, bucketName={}", fileName, bucketName);

        Preconditions.checkArgument(StringUtils.isNotEmpty(fileName), SystemErrorType.RESOURCE10040010.getMesg());
        Preconditions.checkArgument(StringUtils.isNotEmpty(bucketName), "存储空间不能为空");


        Response response = bucketManager.delete(bucketName, fileName);
        log.info("deleteFile - 删除OSS文件. [OK] response={}", response);
    }

    private void checkFileSize(byte[] uploadFileByte) {
        long redisFileSize;
        Long fileMaxSize =qiniuProperties.getFileMaxSize();
        Preconditions.checkArgument(fileMaxSize != null, "每天上传文件最大值没有配置");

        String fileSizeKey = RedisKeyUtil.getFileSizeKey();
        long fileSize = uploadFileByte.length;

        String redisFileSizeStr = srt.opsForValue().get(fileSizeKey);

        if(StringUtils.isEmpty(redisFileSizeStr)) {
            redisFileSizeStr = "0";
        }
        redisFileSize = Long.valueOf(redisFileSizeStr);
        if (fileSize + redisFileSize > fileMaxSize) {
            throw new ServiceException(SystemErrorType.RESOURCE10040011);
        }

        srt.opsForValue().set(fileSizeKey, String.valueOf(redisFileSize + fileSize), 1, TimeUnit.DAYS);
    }

    private String getUpToken(String bucketName) {
        return auth.uploadToken(bucketName);
    }
}
