package com.springboot.cloud.sysadmin.organization.rest;


import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.organization.dto.UserLogDto;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.sysadmin.organization.service.IUserLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/userlog")
@Api("用户日志")
public class UserLogController extends BaseController {

    @Autowired
    private IUserLogService userLogService;

    @ApiOperation(value = "添加日志", notes = "添加日志用户操作日志")
    @ApiImplicitParam(name = "userLogDto", value = "userLogDto", required = true, dataType = "UserLogDto")
    @PostMapping("/add")
    public Result add(@Valid @RequestBody UserLogDto userLogDto) {
        logger.debug("name:{}", userLogDto);
        int row = userLogService.addUserLog(userLogDto);
        return Result.success(row);
    }

}
