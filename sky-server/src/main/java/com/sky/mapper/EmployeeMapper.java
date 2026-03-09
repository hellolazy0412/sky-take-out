package com.sky.mapper;

import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Update("update employee set password = #{newPassword} where id = #{id}")
    void updatePasswordById(Long id, String newPassword);

    @Select("select password from employee where id = #{id}")
    String getPasswordById(Long id);
}
