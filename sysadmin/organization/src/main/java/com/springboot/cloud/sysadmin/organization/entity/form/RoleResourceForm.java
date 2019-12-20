package com.springboot.cloud.sysadmin.organization.entity.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class RoleResourceForm implements Serializable {

    private String resourceId;

    private String roleId;

}
