package com.springboot.cloud.resourcemanage.controller;

import com.qiniu.common.QiniuException;
import com.springboot.cloud.common.core.entity.dto.ElementImgUrlDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.BatchGetUrlRequest;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.GetUrlRequest;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileReqDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileRespDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.ServiceException;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.resourcemanage.entity.po.Attachment;
import com.springboot.cloud.resourcemanage.service.IAttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@Api(value = "API - AttachmentController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(value = "/resource", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AttachmentController extends BaseController {

    @Autowired
    private IAttachmentService attachmentService;

    @ApiOperation(httpMethod = "POST", value = "上传文件")
    @PostMapping(value = "/uploadFile")
    public Result<UploadFileRespDto> uploadFile(@RequestBody UploadFileReqDto uploadFileReqDto) {
        UploadFileRespDto result;
        try {
            logger.info("rpcUploadFile - RPC上传附件信息. optUploadFileReqDto={}", uploadFileReqDto);
            result = attachmentService.uploadFile(uploadFileReqDto);
        } catch (ServiceException ex) {
            logger.error("RPC上传附件信息, 出现异常={}", ex.getMessage(), ex);
            return Result.fail(ex);
        } catch (Exception e) {
            logger.error("RPC上传附件信息, 出现异常={}", e.getMessage(), e);
            return Result.fail();
        }
        return Result.success(result);
    }

    @ApiOperation(httpMethod = "POST", value = "获取附件完整路径")
    @PostMapping(value = "/getFileUrl")
    public Result<String> getFileUrl(@RequestBody GetUrlRequest getUrlRequest) {
        String result;
        try {
            logger.info("getFileUrl - 获取附件完整路径. optGetUrlRequest={}", getUrlRequest);
            result = attachmentService.getFileUrl(getUrlRequest);
        } catch (ServiceException ex) {
            logger.error("RPC获取附件完整路径, 出现异常={}", ex.getMessage(), ex);
            return Result.fail(ex.getMessage());
        } catch (Exception e) {
            logger.error("RPC获取附件完整路径, 出现异常={}", e.getMessage(), e);
            return Result.fail();
        }
        return Result.success(result);
    }

    @ApiOperation(httpMethod = "POST", value = "批量获取url链接")
    @PostMapping(value = "/listFileUrl")
    public Result<List<ElementImgUrlDto>> listFileUrl(@RequestBody BatchGetUrlRequest urlRequest) {
        logger.info("getFileUrl - 批量获取url链接. urlRequest={}", urlRequest);
        List<ElementImgUrlDto> result = attachmentService.listFileUrl(urlRequest);
        return Result.success(result);
    }


    @PostMapping(value = "/deleteExpireFile")
    public void deleteExpireFile() {
        // 1.查询过期的文件
        List<Attachment> list = attachmentService.listExpireFile();
        // 2.删除这些文件
        for (final Attachment attachment : list) {
            try {
                attachmentService.deleteFile(attachment.getPath() + attachment.getName(), attachment.getBucketName(), attachment.getId());
            } catch (QiniuException e) {
                logger.info("删除文件失败, attachmentId={}", attachment.getId(), e);
            }
        }
    }

}
