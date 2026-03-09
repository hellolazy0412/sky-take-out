package com.sky.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("员工分页查询提供的数据模型")
public class EmployeePageQueryDTO implements Serializable {

    @ApiParam("员工姓名")
    private String name;

    @ApiParam("页码")
    private int page;

    @ApiParam("每页显示记录数")
    private int pageSize;

}
