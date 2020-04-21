package com.springboot.cloud.mallorder.provider;

import com.springboot.cloud.common.core.entity.resourcemanage.dto.GetUrlRequest;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileReqDto;
import com.springboot.cloud.common.core.entity.resourcemanage.dto.UploadFileRespDto;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.mallgoods.dto.GlobalExceptionLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "resource-manage",fallback = ResourceManageFeignServiceFallback.class)
public interface ResourceManageFeignService {

    @PostMapping(value = "/exception/saveException")
    Result saveAndSendExceptionLog(@RequestBody GlobalExceptionLogDto exceptionLogDto);

    @PostMapping(value = "/resource/uploadFile")
    Result<UploadFileRespDto> uploadFile(@RequestBody UploadFileReqDto uploadFileReqDto);

    @PostMapping(value = "/resource/getFileUrl")
    Result<String> getFileUrl(@RequestBody GetUrlRequest getUrlRequest);
}
