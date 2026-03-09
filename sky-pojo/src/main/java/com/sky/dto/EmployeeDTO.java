package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "新增员工时传入的数据模型")
public class EmployeeDTO implements Serializable {

    @ApiParam("用户名")
    private String username;

    @ApiParam("姓名")
    private String name;

    @ApiParam("手机号")
    private String phone;

    @ApiParam("性别")
    private String sex;

    @ApiParam("身份证号")
    private String idNumber;

}
