package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "员工密码修改时传递的数据模型")
public class PasswordEditDTO implements Serializable {

    @ApiParam(value = "员工id", required = true)
    private Long empId;

    @ApiParam(value = "旧密码", required = true)
    private String oldPassword;

    @ApiParam(value = "新密码", required = true)
    private String newPassword;

}
