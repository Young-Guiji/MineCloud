package com.springboot.cloud.resourcemanage.service;

import com.qiniu.common.QiniuException;
import com.springboot.cloud.common.core.entity.dto.ElementImgUrlDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.AttachmentDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.BatchGetUrlRequest;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.GetUrlRequest;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UpdateAttachmentDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileReqDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileRespDto;
import com.springboot.cloud.resourcemanage.entity.po.Attachment;

import java.util.List;

public interface IAttachmentService {
    void handlerProductPicMessage(String body, String tags);

    void updateAttachment(UpdateAttachmentDto attachmentDto);

    UploadFileRespDto uploadFile(UploadFileReqDto uploadFileReqDto);

    String getFileUrl(GetUrlRequest getUrlRequest);

    List<ElementImgUrlDto> listFileUrl(BatchGetUrlRequest urlRequest);

    List<Attachment> listExpireFile();

    int deleteFile(String fileName, String bucketName, String attachmentId) throws QiniuException;

    UploadFileRespDto uploadFile(byte[] uploadBytes, String fileName, String fileType, String filePath, String bucketName);

    Attachment getById(String attachmentId);

    List<AttachmentDto> listByRefNo(String refNo);
}
