package com.springboot.cloud.sysadmin.organization.rest;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.web.support.BaseController;
import com.springboot.cloud.sysadmin.organization.entity.form.RoleForm;
import com.springboot.cloud.sysadmin.organization.entity.form.RoleQueryForm;
import com.springboot.cloud.sysadmin.organization.entity.form.RoleResourceForm;
import com.springboot.cloud.sysadmin.organization.entity.param.RoleQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Role;
import com.springboot.cloud.sysadmin.organization.service.IRoleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
@Api("role")
public class RoleController  extends BaseController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "新增角色", notes = "新增一个角色")
    @ApiImplicitParam(name = "roleForm", value = "新增角色form表单", required = true, dataType = "RoleForm")
    @PostMapping
    public Result add(@Valid @RequestBody RoleForm roleForm) {
        logger.debug("name:{}", roleForm);
        Role role = roleForm.toPo(Role.class);
        return Result.success(roleService.add(role));
    }

    @ApiOperation(value = "删除角色", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色ID", required = true, dataType = "long")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        roleService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "修改角色", notes = "修改指定角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "roleForm", value = "角色实体", required = true, dataType = "RoleForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable String id, @Valid @RequestBody RoleForm roleForm) {
        Role role = roleForm.toPo(Role.class);
        role.setId(id);
        roleService.update(role);
        return Result.success();
    }

    @ApiOperation(value = "获取角色", notes = "获取指定角色信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色ID", required = true, dataType = "long")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable String id) {
        logger.debug("get with id:{}", id);
        return Result.success(roleService.get(id));
    }

    @ApiOperation(value = "获取所有角色", notes = "获取所有角色")
    @GetMapping(value = "/all")
    public Result get() {
        return Result.success(roleService.get());
    }

    @ApiOperation(value = "查询角色", notes = "根据用户id查询用户所拥有的角色信息")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "long")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @GetMapping(value = "/user/{userId}")
    public Result query(@PathVariable String userId) {
        logger.debug("query with userId:{}", userId);
        return Result.success(roleService.query(userId));
    }

    @ApiOperation(value = "搜索角色", notes = "根据条件搜索角色信息")
    @ApiImplicitParam(name = "roleQueryForm", value = "角色查询参数", required = true, dataType = "RoleQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result query(@Valid @RequestBody RoleQueryForm roleQueryForm) {
        logger.debug("query with name:{}", roleQueryForm);
        return Result.success(roleService.query(roleQueryForm.getPage(), roleQueryForm.toParam(RoleQueryParam.class)));
    }

    @ApiOperation(value = "添加角色拥有的资源", notes = "添加角色拥有的资源")
    @ApiImplicitParam(name = "roleResourceForm", value = "角色资源参数", required = true, dataType = "RoleResourceForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/addResourceForRole")
    public Result addResourceForRole(@Valid @RequestBody RoleResourceForm roleResourceForm) {
        logger.debug("query with name:{}", roleResourceForm);
        return Result.success(roleService.addResourceForRole(roleResourceForm.getResourceId(), roleResourceForm.getRoleId()));
    }

}