package com.springboot.cloud.resourcemanage.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.qiniu.common.QiniuException;
import com.springboot.cloud.common.core.constant.GlobalConstant;
import com.springboot.cloud.common.core.constant.MqTopicConstants;
import com.springboot.cloud.common.core.entity.dto.ElementImgUrlDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.AttachmentDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.BatchGetUrlRequest;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.GetUrlRequest;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UpdateAttachmentDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileByteInfoReqDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileReqDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileRespDto;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.resourcemanage.entity.po.Attachment;
import com.springboot.cloud.resourcemanage.mapper.AttachmentMapper;
import com.springboot.cloud.resourcemanage.service.IAttachmentService;
import com.springboot.cloud.resourcemanage.service.IQiniuService;
import com.springboot.cloud.util.JacksonUtil;
import com.springboot.cloud.util.PublicUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.springboot.cloud.common.core.util.SnowflakeIdWorker.generateId;

@Service
@Slf4j
public class AttachmentServiceImpl implements IAttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private IQiniuService qiniuService;

    @Override
    public void handlerProductPicMessage(String body, String tags) {

        if (StringUtils.equals(tags, MqTopicConstants.MqTagEnum.DELETE_ATTACHMENT.getTag())) {
            List<String> idList = attachmentMapper.queryAttachmentByRefNo(body);
            for (final String id : idList) {
//                this.deleteFile(id);
            }
        } else {
            UpdateAttachmentDto attachmentDto;
            try {
                attachmentDto = JacksonUtil.parseJson(body, UpdateAttachmentDto.class);
            } catch (Exception e) {
                log.error("发送短信MQ出现异常={}", e.getMessage(), e);
                throw new IllegalArgumentException("JSON转换异常", e);
            }
            this.updateAttachment(attachmentDto);
        }

    }

    @Override
    public void updateAttachment(UpdateAttachmentDto attachmentDto) {
        List<String> attachmentIdList = attachmentDto.getAttachmentIdList();
        String refNo = attachmentDto.getRefNo();
        List<String> idList = attachmentMapper.queryAttachmentByRefNo(refNo);
        if (PublicUtil.isNotEmpty(idList)) {
            idList.removeAll(attachmentIdList);
            for (final String id : idList) {
//                this.deleteFile(id);
            }
        }
        for (final String id : attachmentIdList) {
            Attachment attachment = new Attachment();
            attachment.setId(id);
            attachment.setRefNo(refNo);
            attachmentMapper.updateById(attachment);
        }
    }

    @Override
    public UploadFileRespDto uploadFile(UploadFileReqDto uploadFileReqDto) {
        String fileType = uploadFileReqDto.getFileType();
        String filePath = uploadFileReqDto.getFilePath();
        String bucketName = uploadFileReqDto.getBucketName();
        UploadFileByteInfoReqDto uploadFileByteInfoReqDto = uploadFileReqDto.getUploadFileByteInfoReqDto();
//        UserInfoDto userInfoDto = new UserInfoDto();
//        userInfoDto.setId(uploadFileReqDto.getUserId());
//        userInfoDto.setUsername(uploadFileReqDto.getUserName());

        if (PublicUtil.isEmpty(filePath)) {
            filePath = GlobalConstant.Qiniu.DEFAULT_FILE_PATH;
        }
        Preconditions.checkArgument(uploadFileByteInfoReqDto != null, "上传数据不能为空");
        byte[] fileByteArray = uploadFileByteInfoReqDto.getFileByteArray();
        Preconditions.checkArgument(fileByteArray.length / GlobalConstant.M_SIZE <= GlobalConstant.FILE_MAX_SIZE, "上传文件不能大于5M");
        try(InputStream is = new ByteArrayInputStream(fileByteArray)){
            String fileName = uploadFileByteInfoReqDto.getFileName();
            String type = FileTypeUtil.getType(is);
            Preconditions.checkArgument(type.equals(fileType), "文件类型不符");
            fileName = filePath + fileName;
            UploadFileRespDto optUploadFileRespDto = this.uploadFile(fileByteArray, fileName, fileType, filePath, bucketName);
            optUploadFileRespDto.setFileType(fileType);
            return optUploadFileRespDto;
        } catch (IOException e) {
            log.error("上传文件失败={}", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String getFileUrl(GetUrlRequest getUrlRequest) {
        String attachmentId = getUrlRequest.getAttachmentId();
        Long expires = getUrlRequest.getExpires();
        boolean encrypt = getUrlRequest.isEncrypt();
        if (null == attachmentId) {
            throw new IllegalArgumentException("参数异常, 请检查参数");
        }

        Attachment optAttachment = this.getById(attachmentId);
        String fileName = optAttachment.getPath() + optAttachment.getName();

        return qiniuService.getUrl(expires, encrypt, fileName);
    }

    @Override
    public List<ElementImgUrlDto> listFileUrl(BatchGetUrlRequest urlRequest) {
        List<ElementImgUrlDto> result = Lists.newArrayList();
        String refNo = urlRequest.getRefNo();
        Long expires = urlRequest.getExpires();
        boolean encrypt = urlRequest.isEncrypt();
        Preconditions.checkArgument(StringUtils.isNotEmpty(refNo), "业务单号不能为空");

        List<AttachmentDto> list = this.listByRefNo(refNo);
        for (final AttachmentDto attachmentDto : list) {
            String fileName = attachmentDto.getPath() + attachmentDto.getName();
            String url = qiniuService.getUrl(expires, encrypt, fileName);
            if (StringUtils.isNotEmpty(url)) {
                ElementImgUrlDto dto = new ElementImgUrlDto(url, attachmentDto.getName(), attachmentDto.getId());
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public List<Attachment> listExpireFile() {
        return null;
    }

    @Override
    public int deleteFile(String fileName, String bucketName, String attachmentId) throws QiniuException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(fileName), SystemErrorType.RESOURCE10040010.getMesg());
        Preconditions.checkArgument(StringUtils.isNotEmpty(bucketName), "存储空间不能为空");

        qiniuService.deleteFile(fileName, bucketName);
        return attachmentMapper.deleteById(attachmentId);
    }

    @Override
    public UploadFileRespDto uploadFile(byte[] uploadBytes, String fileName, String fileType, String filePath, String bucketName) {
        UploadFileRespDto fileInfo = qiniuService.uploadFile(uploadBytes, fileName, filePath, bucketName);
        insertAttachment(fileType, bucketName, fileInfo);
        return fileInfo;
    }

    @Override
    public Attachment getById(String attachmentId) {
        Preconditions.checkArgument(attachmentId != null, "文件流水号不能为空");
        Attachment optAttachment = attachmentMapper.selectById(attachmentId);
        if (PublicUtil.isEmpty(optAttachment)) {
            throw new ServiceException(SystemErrorType.RESOURCE10040008, attachmentId);
        }
        return optAttachment;
    }

    @Override
    public List<AttachmentDto> listByRefNo(String refNo) {
        Attachment optAttachment = new Attachment();
        optAttachment.setRefNo(refNo);
        return attachmentMapper.queryAttachment(optAttachment);
    }

    private void insertAttachment(String fileType, String bucketName, UploadFileRespDto fileInfo) {
        String attachmentName = fileInfo.getAttachmentName();
        long id = generateId();
        Attachment optAttachment = new Attachment();
        optAttachment.setBucketName(bucketName);
        optAttachment.setFormat(fileInfo.getFileType());
        optAttachment.setName(attachmentName);
        optAttachment.setType(fileType);
        optAttachment.setFormat(StringUtils.substringAfterLast(attachmentName, "."));
        optAttachment.setPath(fileInfo.getAttachmentPath());
        optAttachment.setId(String.valueOf(id));
        optAttachment.setCenterName(bucketName);
        fileInfo.setAttachmentId(id);
        attachmentMapper.insert(optAttachment);
    }

}
