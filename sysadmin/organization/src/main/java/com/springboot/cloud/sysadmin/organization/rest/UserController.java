package com.springboot.cloud.sysadmin.organization.rest;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.entity.malluser.dto.UserInfoDto;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.sysadmin.organization.entity.form.UserForm;
import com.springboot.cloud.sysadmin.organization.entity.form.UserQueryForm;
import com.springboot.cloud.sysadmin.organization.entity.param.UserQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.User;
import com.springboot.cloud.sysadmin.organization.service.IUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api("user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @ApiImplicitParam(name = "userForm", value = "新增用户form表单", required = true, dataType = "UserForm")
    @PostMapping
    public Result add(@Valid @RequestBody UserForm userForm) {
        logger.debug("name:{}", userForm);
        User user = userForm.toPo(User.class);
        return Result.success(userService.add(user));
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象，逻辑删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "long")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        userService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "修改用户", notes = "修改指定用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "userForm", value = "用户实体", required = true, dataType = "UserForm")})
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody UserForm userForm) {
        User user = userForm.toPo(User.class);
        user.setId(id);
        userService.update(user);
        return Result.success();
    }

    @ApiOperation(value = "修改用户", notes = "修改指定用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userInfoDto", value = "用户实体", required = true, dataType = "UserInfoDto")})
    @PutMapping(value = "/updateUser")
    public int updateUser(@RequestBody UserInfoDto userInfoDto) {
        int row = userService.updateUser(userInfoDto);
        return row;
    }


    @ApiOperation(value = "获取用户", notes = "获取指定用户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "long")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        logger.debug("get with id:{}", id);
        return Result.success(userService.get(id));
    }

    @ApiOperation(value = "获取用户", notes = "根据用户唯一标识（username or mobile）获取用户信息")
    @ApiImplicitParam(paramType = "query", name = "uniqueId", value = "用户唯一标识", required = true, dataType = "string")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @GetMapping
    public Result query(@RequestParam String uniqueId) {
        logger.debug("query with username or mobile:{}", uniqueId);
        return Result.success(userService.getByUniqueId(uniqueId));
    }

    @ApiOperation(value = "搜索用户", notes = "根据条件查询用户信息")
    @ApiImplicitParam(name = "userQueryForm", value = "用户查询参数", required = true, dataType = "UserQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody UserQueryForm userQueryForm) {
        logger.debug("search with userQueryForm:{}", userQueryForm);
        return Result.success(userService.query(userQueryForm.getPage(), userQueryForm.toParam(UserQueryParam.class)));
    }
}