package com.springboot.cloud.malluser.controller.mall;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.malluser.provider.OrganizationFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class Mall user controller.
 *
 * @author guiji
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - MallUserController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MallUserController extends BaseController {

    @Autowired
    private OrganizationFeignService organizationService;

    /**
     * 更新用户信息.
     *
     * @param userInfoDto the user info dto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/updateInformation")
    @ApiOperation(httpMethod = "POST", value = "更新用户信息")
    public Result<UserInfoDto> updateInformation(@RequestBody UserInfoDto userInfoDto) {
        logger.info("updateInformation - 更新用户基本信息 userInfoDto={}", userInfoDto);
        organizationService.updateUser(userInfoDto);
        return Result.success();
    }

    /**
     * 获取用户信息.
     *
     * @return the information
     */
    @PostMapping(value = "/getInformation")
    @ApiOperation(httpMethod = "POST", value = "获取用户信息")
    public Result<UserInfoDto> getInformation() {
        UserInfoDto userInfo = getLoginUserInfo();
        String userId = userInfo.getId();
        logger.info("queryUserInfo - 查询用户基本信息 userId={}", userId);
        Result result = organizationService.queryByUserId(userId);
        if (result.getData() == null) {
            return Result.fail("找不到当前用户");
        }

        return result;
    }

}
