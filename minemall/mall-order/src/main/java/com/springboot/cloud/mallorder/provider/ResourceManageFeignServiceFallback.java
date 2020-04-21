package com.springboot.cloud.mallorder.provider;

import com.springboot.cloud.common.core.entity.resourcemanage.dto.GetUrlRequest;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileReqDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileRespDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;
import org.springframework.stereotype.Component;

@Component
public class ResourceManageFeignServiceFallback implements ResourceManageFeignService {
    @Override
    public Result saveAndSendExceptionLog(GlobalExceptionLogDto exceptionLogDto) {
        return Result.fail("异常日志存储失败");
    }

    @Override
    public Result<UploadFileRespDto> uploadFile(UploadFileReqDto uploadFileReqDto) {
        return Result.fail("文件上传失败");
    }

    @Override
    public Result<String> getFileUrl(GetUrlRequest getUrlRequest) {
        return Result.fail("获取文件路径失败");
    }
}
